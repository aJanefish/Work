package com.http;

public class Response {

    Request request;

    public Response(Request request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "Response{" +
                "request=" + request +
                '}';
    }
}
