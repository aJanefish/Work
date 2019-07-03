package com.http;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.*;

import com.http.RealCall.AsyncCall;
import com.util.P;

/**
 * 线程调度类 实现线程调度逻辑
 */
public class Dispatcher {

    // 同步队列
    private final Deque<RealCall> runningSyncCalls = new ArrayDeque<>();
    /**
     * 异步 运行队列
     */
    private final Deque<AsyncCall> runningAsyncCalls = new ArrayDeque<>();

    /**
     * 异步准备队列
     */
    private final Deque<AsyncCall> readyAsyncCalls = new ArrayDeque<>();
    private int maxRequests = 64;//
    private int maxRequestsPerHost = 3;
    private ThreadPoolExecutor executorService;

    Dispatcher() {
    }

    private synchronized ExecutorService executorService() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 10, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>(), threadFactory("OkHttp Dispatcher", false));
        }
        return executorService;
    }

    private static ThreadFactory threadFactory(final String name, final boolean daemon) {
        return new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread result = new Thread(runnable, name);
                result.setDaemon(daemon);
                return result;
            }
        };
    }

    synchronized void enqueue(AsyncCall call) {
        //按照特定需求放入到线程池中
        showTips("enqueue start");
        if (runningAsyncCalls.size() < maxRequests && runningCallsForHost(call) < maxRequestsPerHost) {
            runningAsyncCalls.add(call);
            executorService().execute(call);
        } else {
            readyAsyncCalls.add(call);
        }
        showTips("enqueue end");
    }

    synchronized void executed(RealCall realCall) {
        runningSyncCalls.add(realCall);
        showTips("executed");
    }

    /**
     * Used by {@code Call#execute} to signal completion.
     */
    void finished(RealCall call) {
        finished(runningSyncCalls, call, false);
        showTips("finished");
    }

    /**
     * Used by {@code AsyncCall#run} to signal completion.
     */
    void finished(AsyncCall call) {
        finished(runningAsyncCalls, call, true);
    }

    private <T> void finished(Deque<T> calls, T call, boolean promoteCalls) {
        //int runningCallsCount;
        synchronized (this) {
            if (!calls.remove(call)) throw new AssertionError("Call wasn't in-flight!");
            if (promoteCalls) promoteCalls();
            //runningCallsCount = runningCallsCount();
        }
        showTips("Finished");
    }

    public synchronized int runningCallsCount() {
        //运行ing的Call
        return runningAsyncCalls.size() + runningSyncCalls.size();
    }

    private void promoteCalls() {
        if (runningAsyncCalls.size() >= maxRequests) return; // Already running max capacity.
        if (readyAsyncCalls.isEmpty()) return; // No ready calls to promote.
        for (Iterator<AsyncCall> i = readyAsyncCalls.iterator(); i.hasNext(); ) {
            AsyncCall call = i.next();
            if (runningCallsForHost(call) < maxRequestsPerHost) {//5
                i.remove();
                runningAsyncCalls.add(call);
                executorService().execute(call);
            }
            if (runningAsyncCalls.size() >= maxRequests) return; // Reached max capacity.
        }
    }

    /**
     * Returns the number of running calls that share a host with {@code call}.
     */
    private int runningCallsForHost(AsyncCall call) {
        int result = 0;
        for (AsyncCall c : runningAsyncCalls) {
            if (c.get().forWebSocket) continue;
            if (c.host().equals(call.host())) result++;
        }
        return result;
    }

    public synchronized void cancelAll() {
        for (AsyncCall call : readyAsyncCalls) {
            call.get().cancel();
        }

        for (AsyncCall call : runningAsyncCalls) {
            call.get().cancel();
        }

        for (RealCall call : runningSyncCalls) {
            call.cancel();
        }
    }

    private void showTips(String tips) {
        P.pln(tips + " - [" + readyAsyncCalls.size() + "," + runningAsyncCalls.size() + "," + runningSyncCalls.size() + "]");
    }
}
