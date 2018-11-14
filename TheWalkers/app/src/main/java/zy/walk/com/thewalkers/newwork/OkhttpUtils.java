package zy.walk.com.thewalkers.newwork;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpUtils {
    private static final OkHttpClient okHttpClient;
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        okHttpClient = builder.build();
    }

    public static void getAuxiliaryAll(){

        String postBody = ""
                + "Releases\n"
                + "--------\n"
                + "\n"
                + " * _1.0_ May 6, 2013\n"
                + " * _1.1_ June 15, 2013\n"
                + " * _1.2_ August 11, 2013\n";

        Request request = new Request.Builder()
                .url("http://encounter-msc.singou.mo/api/tool/getAuxiliaryAll")
                //.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("onFailure",""+e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("OkhttpUtils",""+response);
                Log.d("OkhttpUtils",""+response.body());
                Log.d("OkhttpUtils","ss:"+response.body().string());
                //Log.d("OkhttpUtils",""+new String(response.body().bytes()));
            }
        });

    }

    public static void getTestSingou(){


        Request request = new Request.Builder()
                .url("http://encounter-msc.singou.mo/api/tool/RecordClicks?question_name=科學館幾點鐘開門")
                //.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("OkhttpUtils",""+e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("OkhttpUtils",""+response);
                Log.d("OkhttpUtils",""+response.body());
                Log.d("OkhttpUtils","ss:"+response.body().string());
                //Log.d("OkhttpUtils",""+new String(response.body().bytes()));
            }
        });

    }




}
