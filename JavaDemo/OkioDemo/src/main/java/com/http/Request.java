package com.http;

import com.sun.istack.internal.Nullable;

public class Request {

    private final String method;
    private final HttpUrl url;
    private final Headers headers;


    public Request(Builder builder) {
        this.method = builder.method;
        this.url = builder.url;
        this.headers = builder.headers.build();
    }

    public String getUrl() {
        return url.getUrl();
    }

    @Override
    public String toString() {
        return "Request{" +
                "method='" + method + '\'' +
                ", url=" + url +
                ", headers=" + headers +
                '}';
    }

    public String host() {
        return url.getHost();
    }

    public @Nullable String header(String name) {
        return headers.get(name);
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public static class Builder {
        private String method;
        private HttpUrl url;
        private Headers.Builder headers;

        public Builder() {
            this.method = "GET";
            this.headers = new Headers.Builder();
        }

        public Builder(Request request) {
            this.method = request.method;
            this.url = request.url;
            this.headers = request.headers.newBuilder();
        }

        public Builder url(HttpUrl url) {
            this.url = url;
            return this;
        }

        public Builder headers(Headers headers) {
            this.headers = headers.newBuilder();
            return this;
        }

        public Builder addHeader(String name, String value) {
            headers.add(name, value);
            return this;
        }


        public Request build() {
            return new Request(this);
        }


    }
}
