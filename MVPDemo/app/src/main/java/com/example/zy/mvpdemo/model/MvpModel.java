package com.example.zy.mvpdemo.model;

import android.os.Handler;

public class MvpModel {
	
	//Model层相比其他单元来说比较特殊，因为它们更像一个整体，只是单纯的帮上层拿数据而已。
	// 再就是MVP的理念是让业务逻辑互相独立，这就导致每个的网络请求也被独立成了单个Model，
	// 不光没必要这么做而且找起来贼麻烦，所以时尚版MVP架构中Model层被整体封装成了庞大且独立单一模块。
	
	
	/**
	 * 获取网络接口数据
	 * @param param 请求参数
	 * @param callback 数据回调接口
	 */
	public static void getNetData(final String param, final Callback callback){
		// 利用postDelayed方法模拟网络请求数据的耗时操作
		Handler handler = new Handler();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				switch (param){
					case "normal":
						callback.onSuccess("根据参数"+param+"的请求网络数据成功");
						break;
					case "failure":
						callback.onFailure("请求失败：参数有误");
						break;
					case "error":
						callback.onError();
						break;
				}
				callback.onComplete();
			}
		};
		handler.postDelayed(runnable,2000);
	}
}