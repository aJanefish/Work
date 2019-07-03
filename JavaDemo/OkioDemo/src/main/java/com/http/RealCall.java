package com.http;

import com.http.chain.*;
import com.util.P;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RealCall implements Call {
    Request request;
    OkHttpClient client;
    private boolean executed;
    final boolean forWebSocket;
    private final RetryAndFollowUpInterceptor retryAndFollowUpInterceptor;

    private RealCall(Request request, OkHttpClient okHttpClient, boolean forWebSocket) {
        this.request = request;
        this.client = okHttpClient;
        this.forWebSocket = forWebSocket;
        this.retryAndFollowUpInterceptor = new RetryAndFollowUpInterceptor();
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
            boolean signalledCallback = false;

            try {
                Response response = getResponseWithInterceptorChain();

                if (retryAndFollowUpInterceptor.isCanceled()) {
                    signalledCallback = true;
                    callback.onFailure(RealCall.this, new IOException("Canceled"));
                } else {
                    signalledCallback = true;
                    callback.onResponse(RealCall.this, response);
                }
            } catch (IOException e) {
                if (signalledCallback) {
                    // Do not signal the callback twice!
                    //Platform.get().log(INFO, "Callback failure for " + toLoggableString(), e);
                } else {
                    //eventListener.callFailed(RealCall.this, e);
                    callback.onFailure(RealCall.this, e);
                }
            } finally {
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
            client.dispatcher().executed(this);
            Response result = getResponseWithInterceptorChain();
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
        retryAndFollowUpInterceptor.cancel();
    }

    Response getResponseWithInterceptorChain() throws IOException {
        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(retryAndFollowUpInterceptor);
        interceptors.add(new BridgeInterceptor());
        interceptors.add(new CacheInterceptor());
        interceptors.add(new ConnectInterceptor());
        interceptors.add(new CallServerInterceptor());


        Interceptor.Chain chain = new RealInterceptorChain(interceptors, 0, request, this);
        return chain.proceed(request);
    }
}
