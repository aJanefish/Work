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
		
		int level = intent.getIntExtra("level", 0);    ///���ʣ�����
		int scale = intent.getIntExtra("scale", 0);  ///��ȡ�����������ֵ
		
		int status = intent.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN); ///��ȡ���״̬
		int plugged = intent.getIntExtra("plugged", 0);  ///��ȡ��Դ��Ϣ
		int health = intent.getIntExtra("health",BatteryManager.BATTERY_HEALTH_UNKNOWN);  ///��ȡ��ؽ�����
		int voltage = intent.getIntExtra("voltage", 0);  ///��ȡ��ص�ѹ
		int temperature = intent.getIntExtra("temperature", 0);  ///��ȡ����¶�
		
		Log.d(TAG,level+","+scale+","+status+","+plugged+","+health+","+voltage+","+temperature);
		
	}
	
	
}
