package com.http;

import com.util.P;

import java.io.IOException;
import java.util.Random;

public class RealCall implements Call {
    Request request;
    OkHttpClient client;
    private boolean executed;
    final boolean forWebSocket;

    private RealCall(Request request, OkHttpClient okHttpClient, boolean forWebSocket) {
        this.request = request;
        this.client = okHttpClient;
        this.forWebSocket = forWebSocket;
    }

    static RealCall newRealCall(Request request, OkHttpClient okHttpClient, boolean forWebSocket) {
        return new RealCall(request, okHttpClient, forWebSocket);
    }

    @Override
    public Request request() {
        return request;
    }

    public class AsyncCall extends NamedRunnable {
        private Callback callback;

        AsyncCall(Callback responseCallback) {
            super("ZY-Http%s", "" + request.getUrl());
            this.callback = responseCallback;
        }

        String host() {
            return request.host();
        }

        RealCall get() {
            return RealCall.this;
        }

        @Override
        protected void execute() {
            //异步 网络请求业务逻辑实现
            P.pln(get()+"  --- starting");
            Response response = new Response(request);
            try {
                Random random = new Random();

                Thread.sleep(random.nextInt(10)*1000);
                callback.onSuccess(get(), response);
            } catch (IOException e) {
                e.printStackTrace();
                callback.onFailure(get(), e);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                client.dispatcher().finished(this);
            }
        }
    }

    @Override
    public void enqueue(Callback responseCallback) {
        synchronized (this) {
            if (executed) throw new IllegalStateException("Already Executed");
            executed = true;
        }
        client.dispatcher().enqueue(new AsyncCall(responseCallback));
    }

    @Override
    public Response execute() throws IOException {
        synchronized (this) {
            if (executed) throw new IllegalStateException("Already Executed");
            executed = true;
        }
        //return new Response(request());
        try {
            Response result = new Response(request());
            client.dispatcher().executed(this);
            if (result == null) throw new IOException("Canceled");
            return result;
        } catch (IOException e) {
            throw e;
        } finally {
            client.dispatcher().finished(this);
        }
    }

    @Override
    public synchronized boolean isExecuted() {
        return executed;
    }

    @Override
    public void cancel() {
        //retryAndFollowUpInterceptor.cancel();
    }
}
