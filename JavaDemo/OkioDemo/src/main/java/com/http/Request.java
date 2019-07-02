package com.http;

public class Request {
    private final String url;

    public Request(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Request{" +
                "url='" + url + '\'' +
                '}';
    }

    public String host() {
        return url;
    }
}
