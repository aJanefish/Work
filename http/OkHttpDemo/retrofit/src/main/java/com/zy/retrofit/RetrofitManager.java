package com.zy.retrofit;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitManager {
    public static void request() {
        System.out.println("request");
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                //指定baseurl，这里有坑，最后后缀出带着“/”
                .baseUrl("https://www.baidu.com/")
                //设置内容格式,这种对应的数据返回值是String类型
                //.addConverterFactory(BuiltInConverter)
                //.addConverterFactory()
                //定义client类型
                .client(new OkHttpClient())
                //创建
                .build();

        //通过retrofit和定义的有网络访问方法的接口关联
        DataService dataService = retrofit.create(DataService.class);

        //在这里又重新设定了一下baidu的地址，是因为Retrofit要求传入具体，如果是决定路径的话，路径会将baseUrl覆盖掉
        Call<ResponseBody> baiDu = dataService.baiDu("http://wwww.baidu.com");

        baiDu.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(Constant.TAG, "onResponse");
                System.out.println("onResponse");
                try {
                    String string = response.body().string();
                    System.out.println(string);
                    Log.d(Constant.TAG, "onResponse:"+string);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(Constant.TAG, "onFailure");
                System.out.println("onFailure");
            }
        });


    }
}
