package com.example.zy.rxjavademo;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络操作类
 *
 */

public class Network {
	private static GankApi gankApi;
	private static OkHttpClient okHttpClient = new OkHttpClient();
	
	public static GankApi getGankApi() {
		if (gankApi == null) {
			Retrofit retrofit = new Retrofit.Builder()
					.client(okHttpClient)
					.baseUrl("http://gank.io/api/")
					.addConverterFactory(GsonConverterFactory.create())
					.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
					.build();
			gankApi = retrofit.create(GankApi.class);
		}
		return gankApi;
	}
}

