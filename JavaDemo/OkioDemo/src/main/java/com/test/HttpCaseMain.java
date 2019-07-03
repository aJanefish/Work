package com.test;

import com.http.*;
import com.util.P;

import java.io.IOException;

public class HttpCaseMain {
    //https://free-api.heweather.com/s6/air/now?location=beijing&key=f464c53cb02240a194640685ee425116
    static OkHttpClient okHttpClient = new OkHttpClient();

    public static void main(String[] args) {
        //http://localhost:4567/blog
        P.pln("OkHttpClient Case");
        //runableTest();
        enqueueTest();
    }

    private static void runableTest() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        enqueueTest();
                    }
                }
            }).start();
        }
    }

    private static void enqueueTest() {
        P.pln("enqueueTest");

        Headers headers = new Headers.Builder()
                .add("name", "test")
                .build();
        //http://www.googletagservices.com/activeview/js/current/osd.js?cb=%2Fr20100101
        HttpUrl httpUrl = new HttpUrl("http", "www.googletagservices.com", 80, "/activeview/js/current/osd.js?cb=%2Fr20100101");

        Request request = new Request.Builder()
                .url(httpUrl)
                .headers(headers)
                .build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                P.pln(call);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                P.pln(call + " - " + response + " - " + Thread.currentThread());
            }
        });
    }

    private static void executeTest() {
        P.pln("executeTest -- start");
        HttpUrl httpUrl = new HttpUrl("http", "www.baidu.com", 80, "");

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        try {
            P.pln(request);
            Response response = call.execute();
            P.pln(response);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            P.pln("executeTest -- end");
        }
    }
}

