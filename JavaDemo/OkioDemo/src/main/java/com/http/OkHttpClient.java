package com.http;

/**
 * 研究 OkHttp 框架
 * 1. 框架逻辑
 */

public class OkHttpClient implements Call.Factory {

    private Dispatcher dispatcher = new Dispatcher();

    @Override
    public Call newCall(Request request) {
        return RealCall.newRealCall(request, this,false);
    }

    public Dispatcher dispatcher() {
        return dispatcher;
    }
}
