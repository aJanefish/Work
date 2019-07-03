package com.http;

public final class HttpUrl {
    //http://192.168.1.101:4567/blog
    /**
     * Either "http" or "https".
     */
    private final String scheme;
    /**
     * Canonical hostname.
     */
    private final String host;

    /**
     * Either 80, 443 or a user-specified port. In range [1..65535].
     */
    private final int port;

    private final String path;

    public HttpUrl(String scheme, String host, int port, String path) {
        this.scheme = scheme;
        this.host = host;
        this.port = port;
        this.path = path;
    }


    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUrl() {
        StringBuilder stringBuilder = new StringBuilder(scheme);
        stringBuilder.append("://")
                .append(host)
                .append(":")
                .append(port)
                .append(path);
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "HttpUrl{" +
                getUrl() +
                '}';
    }
}
