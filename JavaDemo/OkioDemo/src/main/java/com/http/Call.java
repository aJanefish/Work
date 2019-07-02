package com.http;

import java.io.IOException;

public interface Call {
    Request request();

    void enqueue(Callback responseCallback);

    Response execute() throws IOException;

    boolean isExecuted();

    /** Cancels the request, if possible. Requests that are already complete cannot be canceled. */
    void cancel();

    interface  Factory{
        Call newCall(Request request);
    }
}