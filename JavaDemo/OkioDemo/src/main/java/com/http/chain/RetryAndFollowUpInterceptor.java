package com.http.chain;

import com.http.Request;
import com.http.Response;

import java.io.IOException;

public class RetryAndFollowUpInterceptor implements Interceptor {
    private boolean cancel = false;


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        return response;
    }

    public void cancel() {
        cancel = true;
    }

    public boolean isCanceled() {
        return cancel;
    }
}
