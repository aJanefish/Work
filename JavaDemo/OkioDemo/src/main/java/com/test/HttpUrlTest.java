package com.test;

import com.http.HttpUrl;
import com.util.P;

public class HttpUrlTest {
    public static void main(String[] args) {
        String url = "http://localhost:4567/blog";
        if (url == null) throw new NullPointerException("url == null");
        if (url.regionMatches(true, 0, "ws:", 0, 3)) {
            url = "http:" + url.substring(3);
        } else if (url.regionMatches(true, 0, "wss:", 0, 4)) {
            url = "https:" + url.substring(4);
        }

        P.pln(url);

        HttpUrl httpUrl = new HttpUrl("http","loaclhost",4567,"blog");
        P.pln(httpUrl);
        P.pln(httpUrl.getUrl());

    }
}
