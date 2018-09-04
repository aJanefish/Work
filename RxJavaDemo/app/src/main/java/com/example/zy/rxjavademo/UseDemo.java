package com.example.zy.rxjavademo;


import android.util.Log;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.rx2androidnetworking.Rx2AndroidNetworking;


import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class UseDemo {
	
	private static String TAG = "UseDemo";
	
	
	public static void use3(){
		/**
		 * zip 操作符的使用场景
		 * <p>
		 * 结合多个接口的数据再更新 UI
		 * <p>
		 */
		Observable<WeatherBean> observable1 = Rx2AndroidNetworking.get("https://www.sojson.com/open/api/weather/json.shtml?city=深圳")
				.build()
				.getObjectObservable(WeatherBean.class);
		
		Observable<CategoryResult> observable2 = Network.getGankApi()
				.getCategoryData("Android",1,1);
		
		Observable.zip(observable1, observable2, new BiFunction<WeatherBean, CategoryResult, String>() {
			@Override
			public String apply(@NonNull WeatherBean weatherBean, @NonNull CategoryResult categoryResult) throws Exception {
				Logger.init(TAG);
				Logger.e(new Throwable(),"zhangyu");
				Logger.d(categoryResult.results);
				
				
				return "合并后的数据为："+weatherBean.getCity() +" 人名："+categoryResult.results.get(0).who;
			}
		}).subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<String>() {
					@Override
					public void accept(@NonNull String s) throws Exception {
						Log.e(TAG, "accept: 成功：" + s+"\n");
					}
				}, new Consumer<Throwable>() {
					@Override
					public void accept(@NonNull Throwable throwable) throws Exception {
						Log.e(TAG, "accept: 失败：" + throwable+"\n");
					}
				});
		
		
		
		
	}
	
	
	public static void use2(){
		Rx2AndroidNetworking.get("https://www.sojson.com/open/api/weather/json.shtml?city=深圳")
				.build()
				.getObjectObservable(WeatherBean.class)
				.observeOn(AndroidSchedulers.mainThread()) // 为doOnNext() 指定在主线程，否则报错
				.doOnNext(new Consumer<WeatherBean>() {
					@Override
					public void accept(@NonNull WeatherBean data) throws Exception {
						Log.e(TAG, "doOnNext:"+Thread.currentThread().getName()+"\n" );
						Log.e(TAG,"doOnNext:"+data.toString()+"\n");
					}
				})
				.map(new Function<WeatherBean, String>() {
					@Override
					public String apply(@NonNull WeatherBean weatherBean) throws Exception {
						Log.e(TAG, "\n" );
						Log.e(TAG, "map:"+Thread.currentThread().getName()+"\n" );
						Logger.init(TAG);
						Logger.json(new Gson().toJson(weatherBean.getData().getYesterday()));
						return weatherBean.getData().getYesterday().toString();
					}
				})
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<String>() {
					@Override
					public void accept(@NonNull String data) throws Exception {
						Log.e(TAG, "subscribe 成功:"+Thread.currentThread().getName()+"\n" );
						Log.e(TAG, "成功:" + data.toString() + "\n");
					}
				}, new Consumer<Throwable>() {
					@Override
					public void accept(@NonNull Throwable throwable) throws Exception {
						Log.e(TAG, "subscribe 失败:"+Thread.currentThread().getName()+"\n" );
						Log.e(TAG, "失败："+ throwable.getMessage()+"\n" );
					}
				});
		
		
	}
	
	public static void use1(){
		
		/**
		 *
		 * 采用 OkHttp3 配合 map , doOnNext , 线程切换做简单的网络请求
		 *
		 * 1、通过 Observable.create() 方法，调用 OkHttp 网络请求;
		 * 2、通过 map 操作符结合 Gson , 将 Response 转换为 bean 类;
		 * 3、通过 doOnNext() 方法，解析 bean 中的数据，并进行数据库存储等操作;
		 * 4、调度线程，在子线程进行耗时操作任务，在主线程更新 UI;
		 * 5、通过 subscribe(),根据请求成功或者失败来更新 UI。
		 */
		
		Observable<Response> observable  =Observable.create(new ObservableOnSubscribe<Response>() {
			@Override
			public void subscribe(@NonNull ObservableEmitter<Response> e) throws Exception {
				Builder builder = new Builder()
						.url("https://www.sojson.com/open/api/weather/json.shtml?city=北京")
						.get();
				Request request = builder.build();
				Call call = new OkHttpClient().newCall(request);
				Response response = call.execute();
				e.onNext(response);
			}
		});
		observable.map(new Function<Response, WeatherBean>() {
			@Override
			public WeatherBean apply(@NonNull Response response) throws Exception {
				
				Log.e(TAG, "map 线程:" + Thread.currentThread().getName() + "\n");
				if (response.isSuccessful()) {
					ResponseBody body = response.body();
					if (body != null) {
						Log.e(TAG, "map:转换前:" + response.body());
						//return new Gson().fromJson(body.string(), MobileAddress.class);
						return new Gson().fromJson(body.string(), WeatherBean.class);
					}
				}
				return null;
			}
		}).observeOn(AndroidSchedulers.mainThread())
				.doOnNext(new Consumer<WeatherBean>() {
					@Override
					public void accept(@NonNull WeatherBean s) throws Exception {
						Log.e(TAG, "doOnNext 线程:" + Thread.currentThread().getName() + "\n");
						
						Log.e(TAG, "doOnNext: 保存成功：" + s.toString() + "\n");
						
						
					}
				}).subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<WeatherBean>() {
					@Override
					public void accept(@NonNull WeatherBean data) throws Exception {
						Log.e(TAG, "subscribe 线程:" + Thread.currentThread().getName() + "\n");
						Logger.init(TAG);
						Logger.json(new Gson().toJson(data));
						
						Log.e(TAG, "成功:" + data + "\n");
						
					}
				}, new Consumer<Throwable>() {
					@Override
					public void accept(@NonNull Throwable throwable) throws Exception {
						Log.e(TAG, "subscribe 线程:" + Thread.currentThread().getName() + "\n");
						
						
						Log.e(TAG, "失败：" + throwable.getMessage() + "\n");
						
					}
				}, new Action() {
					@Override
					public void run() throws Exception {
						Log.e(TAG, "Action：run\n");
					}
				});
		
		
	}
}
