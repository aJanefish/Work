package com.http;

import com.util.P;

import java.io.IOException;

/**
 * OkHttp Case
 */
public class HttpCaseMain {
    //https://free-api.heweather.com/s6/air/now?location=beijing&key=f464c53cb02240a194640685ee425116
   static OkHttpClient okHttpClient = new OkHttpClient();

    public static void main(String[] args) {
        P.pln("OkHttpClient Case");
        runableTest();
    }

    private static void runableTest(){
        for (int i = 0; i <10 ; i++) {
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
        Request request = new Request("www.baidu.com");

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                P.pln(call);
            }

            @Override
            public void onSuccess(Call call, Response response) throws IOException {
                P.pln(call + " - " + request + " - " + Thread.currentThread());
            }
        });
    }

    private static void executeTest() {
        P.pln("executeTest -- start");
        Request request = new Request("www.baidu.com");
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        try {
            P.pln(request);
            Response response = call.execute();
            P.pln(response);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            P.pln("executeTest -- end");
        }
    }
}
