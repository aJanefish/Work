package com.http.chain;

import com.http.Request;
import com.http.Response;
import com.http.Version;
import com.util.P;

import java.io.IOException;

//桥接拦截
public class BridgeInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request userRequest = chain.request();
        Request.Builder requestBuilder = userRequest.newBuilder();

        //目前是不带请求体的简单版本
        //Content-Type
        //Content-Length

        if (userRequest.header("Host") == null) {
            requestBuilder.addHeader("Host",userRequest.host());
        }
        if (userRequest.header("Connection") == null) {
            requestBuilder.addHeader("Connection", "Keep-Alive");
        }

        if (userRequest.header("User-Agent") == null) {
            requestBuilder.addHeader("User-Agent", Version.userAgent());
        }

        P.pln(requestBuilder.build());
        Response response = chain.proceed(requestBuilder.build());

        // gzip 解压操作

        return response;
    }
}
