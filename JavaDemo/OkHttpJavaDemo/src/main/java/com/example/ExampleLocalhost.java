package com.example;

import okhttp3.*;
import okio.BufferedSource;
import okio.Okio;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;


import static com.example.Constant.LOCALHOST_URL_4567;
import static com.example.Constant.LOCALHOST_URL_9999;

public class ExampleLocalhost {
    public static void main(String args[]) {
        test1();
    }


    private static void test1() {
        Request request = new Request.Builder()
                .url(LOCALHOST_URL_4567+"blog/1")
                .build();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(6, TimeUnit.SECONDS);
        builder.readTimeout(6, TimeUnit.SECONDS);
        builder.writeTimeout(6, TimeUnit.SECONDS);
        OkHttpClient okHttpClient = builder.build();

        Call cell = okHttpClient.newCall(request);

        cell.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("onFailure:" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                BufferedSource source = response.body().source();
                String string = source.readString(Charset.forName("utf-8"));
                System.out.println(string);
                System.out.println(response.body());
            }
        });

    }
}
