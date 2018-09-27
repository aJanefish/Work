package com.butler.launcher.acitivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.util.Log;

import com.butler.launcher.event.WifiEvent;

import org.greenrobot.eventbus.EventBus;

public class ButlerWifiBroadcastReceiver  extends BroadcastReceiver {
    private static final String TAG = "ButlerWifi";


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,intent.getAction());
        // 自定义个Broadcast，并在Broadcast中判断这个WIFI功能的打开与关闭（与WIFI是否连接无关）
        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
            Log.d(TAG, "--- wifiState ---:"+wifiState);
            switch (wifiState) {
                case WifiManager.WIFI_STATE_DISABLED:
                    Log.d(TAG, "--- WIFI状态：已关闭WIFI功能 ---");
                    break;
                case WifiManager.WIFI_STATE_DISABLING:
                    Log.d(TAG, "--- WIFI状态：正在关闭WIFI功能 ---");
                    break;
                case WifiManager.WIFI_STATE_ENABLED:// WIFI_STATE_ENABLED = 3; // Wifi功能已经打开
                    Log.d(TAG, "--- WIFI状态：Wifi功能已经打开 ---");
                    break;
                case WifiManager.WIFI_STATE_ENABLING://WIFI_STATE_ENABLING = 2; // Wifi功能正在打开中
                    Log.d(TAG, "--- WIFI状态：Wifi功能正在打开中 ---");
                    break;
            }
        } else if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
            Parcelable parcelableExtra = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (null != parcelableExtra) {
                NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
                NetworkInfo.State state = networkInfo.getState();
                boolean isConnected = state == NetworkInfo.State.CONNECTED;
                // WIFI已连接
                if (isConnected) {
                    Log.d(TAG, "--- WIFI状态：WIFI已连接 ---");
                    EventBus.getDefault().post(new WifiEvent(true));
                }
                // WIFI未连接
                else {
                    Log.d(TAG, "--- WIFI状态：WIFI未连接 ---");
                    EventBus.getDefault().post(new WifiEvent(false));
                }
            }
        }

    }
}
