package com.butler.launcher.model;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.butler.launcher.bean.AppInfo;
import com.butler.launcher.utils.AppInfoUtil;

public class LauncherModel extends Thread {
	
	private static final String TAG = "LauncherModel";
	
	
	private Context mContext;
	private CallBack mCallBack;
	private AppInfoUtil appInfoUtil;
	private HashMap<String,AppInfo> mAppInfoHashMap;
	private List<AppInfo> appInfos;
	private String[] filterAppPackageName = {
			"com.butler.launcher",
			"com.baidu.searchbox",
			"com.android.wallpaperpicker"
	};
	
	
	public LauncherModel(Context context) {
		this.mContext = context;
		this.appInfoUtil = AppInfoUtil.getInstance(context);
		this.appInfos = new ArrayList<>();
		this.mAppInfoHashMap = appInfoUtil.getInstalledApps(2);
	}
	
	public void setCallBack(CallBack mCallBack) {
		this.mCallBack = mCallBack;
	}
	
	@Override
	public void run() {
		super.run();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//过滤apk
		filter();
		//加载桌面
		if (mCallBack != null) {
			mCallBack.addView(appInfos);
		}
		
	}
	
	private void filter() {
		Log.d(TAG,"filter start");
		if (mAppInfoHashMap !=null){
			Log.d(TAG,"filter start:"+mAppInfoHashMap.size()+"");
			for(String appPackageName : filterAppPackageName){
				mAppInfoHashMap.remove(appPackageName);
			}
			Log.d(TAG,"filter end:"+mAppInfoHashMap.size()+"");
			for(AppInfo info : mAppInfoHashMap.values()){
				appInfos.add(info);
			}
		}
		Log.d(TAG,"filter end");
	}
	
	
	public interface CallBack {
		
		void addView(List<AppInfo> appInfoList);
		
		void deleteView();
		
	}
	
}
