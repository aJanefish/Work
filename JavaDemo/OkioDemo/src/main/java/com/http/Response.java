package com.http;

public class Response {

    Request request;
    private String log;

    @Override
    public String toString() {
        return "Response{" +
                "request=" + request +
                ", log='" + log + '\'' +
                '}';
    }

    public Response(Request request, String log) {
        this.request = request;
        this.log = log;
    }

}
