package com.http.chain;

import com.http.Call;
import com.http.Request;
import com.http.Response;

import java.io.IOException;
import java.util.List;

public class RealInterceptorChain implements Interceptor.Chain {

    private final List<Interceptor> interceptors;
    private final int index;
    private final Request request;
    private final Call call;

    public RealInterceptorChain(List<Interceptor> interceptors, int index, Request request, Call call) {
        this.interceptors = interceptors;
        this.index = index;
        this.request = request;
        this.call = call;
    }

    @Override
    public Request request() {
        return request;
    }

    @Override
    public Response proceed(Request request) throws IOException {
        if (index >= interceptors.size()) throw new AssertionError();
        RealInterceptorChain next = new RealInterceptorChain(interceptors, index + 1, request, call);
        Interceptor interceptor = interceptors.get(index);
        Response response = interceptor.intercept(next);
        return response;
    }

    @Override
    public Call call() {
        return call;
    }
}
