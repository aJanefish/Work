package com.zy.example;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Url;

import java.io.IOException;

/**
 * 访问百度
 * */
public class BaiDuExample {

    private static final String URL = "https://www.baidu.com/";

    public interface BaiDuService{

        @GET(URL)
        Call<ResponseBody> getBaiDu();

        @GET("")
        Call<ResponseBody> get(@Url String url);
    }

    public static void main(String args[]){

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).build();

        BaiDuService baiDuService = retrofit.create(BaiDuService.class);
        Call<ResponseBody> serviceBaiDu = baiDuService.getBaiDu();
        serviceBaiDu.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        Call<ResponseBody> responseBodyCall = baiDuService.get(URL);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
