package com.example;

import okhttp3.*;

import java.io.IOException;
import java.rmi.server.RemoteRef;
import java.util.concurrent.TimeUnit;

import static com.example.Constant.BAIDU;


public class ExampleBaidu {



    public static void main(String args[]) {
        Request request = new Request.Builder()
                .url(BAIDU)
                .build();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        OkHttpClient okHttpClient = builder.build();

        Call cell = okHttpClient.newCall(request);

        cell.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body());
            }
        });

    }
}
