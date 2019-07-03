package com.test;

import com.http.Headers;
import com.util.P;

public class HeadersTest {
    public static void main(String[] args) {
        Headers.Builder builder = new Headers.Builder();
        builder.add("Connection","keep-alive");
        Headers headers = builder.build();
        P.pln(headers);
    }
}
