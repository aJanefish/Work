package com.http.chain;

import com.http.Request;
import com.http.Response;
import com.http.connection.RealConnection;
import com.util.P;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class ConnectInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        RealConnection realConnection = new RealConnection(request);

        Response response = chain.proceed(request);
        return response;
    }
}
