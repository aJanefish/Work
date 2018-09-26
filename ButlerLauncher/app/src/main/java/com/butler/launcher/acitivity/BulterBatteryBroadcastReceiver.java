package com.butler.launcher.acitivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;

public class BulterBatteryBroadcastReceiver extends BroadcastReceiver {
	private static final String TAG = "BulterBattery";
	@Override
	public void onReceive(Context context, Intent intent) {
		
		
		//filter2.addAction(Intent.ACTION_BATTERY_CHANGED);
		
		int level = intent.getIntExtra("level", 0);    ///电池剩余电量
		int scale = intent.getIntExtra("scale", 0);  ///获取电池满电量数值
		
		int status = intent.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN); ///获取电池状态
		int plugged = intent.getIntExtra("plugged", 0);  ///获取电源信息
		int health = intent.getIntExtra("health",BatteryManager.BATTERY_HEALTH_UNKNOWN);  ///获取电池健康度
		int voltage = intent.getIntExtra("voltage", 0);  ///获取电池电压
		int temperature = intent.getIntExtra("temperature", 0);  ///获取电池温度
		
		Log.d(TAG,level+","+scale+","+status+","+plugged+","+health+","+voltage+","+temperature);
		
	}
	
	
}
