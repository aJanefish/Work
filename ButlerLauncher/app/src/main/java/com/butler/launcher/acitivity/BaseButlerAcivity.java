package com.butler.launcher.acitivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import org.greenrobot.eventbus.EventBus;

public class BaseButlerAcivity extends AppCompatActivity {
	
	private Window window;
	private BulterBatteryBroadcastReceiver bulterBatteryBroadcastReceiver;
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//remove title bar  即隐藏标题栏
		getSupportActionBar().hide();// 隐藏ActionBar
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//remove notification bar  即全屏
		
		//保持常亮
		window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		initEventBus();

		initReceiver();

	}

	private void initEventBus() {
		if(!EventBus.getDefault().isRegistered(this)){
			EventBus.getDefault().register(this);
		}
	}

	private void initReceiver(){
		bulterBatteryBroadcastReceiver = new BulterBatteryBroadcastReceiver();
		IntentFilter filter2 = new IntentFilter();
		filter2.addAction(Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(bulterBatteryBroadcastReceiver,filter2);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//清除常亮
		window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		unregisterReceiver(bulterBatteryBroadcastReceiver);
		unregisterEventBus();
	}

	private void unregisterEventBus() {
		if(EventBus.getDefault().isRegistered(this)){
			EventBus.getDefault().unregister(this);
		}
	}
}
