package com.http.chain;

import com.http.Call;
import com.http.Request;
import com.http.Response;

import java.io.IOException;

public interface Interceptor {

    Response intercept(Chain chain) throws IOException;

    interface Chain{
        Request request();

        Response proceed(Request request) throws IOException;

        /**
         * Returns the connection the request will be executed on. This is only available in the chains
         * of network interceptors; for application interceptors this is always null.
         */
        //@Nullable Connection connection();

        Call call();

    }
}
