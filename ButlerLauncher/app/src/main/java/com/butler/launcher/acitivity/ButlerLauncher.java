package com.butler.launcher.acitivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.List;


import com.butler.launcher.ButlerApplication;
import com.butler.launcher.R;
import com.butler.launcher.adapter.LauncherAdapter;
import com.butler.launcher.bean.AppInfo;
import com.butler.launcher.event.BatteryEvent;
import com.butler.launcher.event.WifiEvent;
import com.butler.launcher.model.LauncherModel;
import com.butler.launcher.utils.SPUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class ButlerLauncher extends BaseButlerAcivity implements LauncherModel.CallBack {

    private String TAG = "ButlerLauncher";
    private ButlerApplication butlerApplication;
    private RecyclerView mRecyclerView;
    private LauncherAdapter launcherAdapter;
    private ImageView imageview_bulter_electric_bar;
    private ImageView imageview_bulter_wifi_bar;
    private ImageView imageview_bulter_setting_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initView();

        LauncherModel mLauncherModel = new LauncherModel(this);
        mLauncherModel.setCallBack(this);
        mLauncherModel.start();

        butlerApplication = (ButlerApplication) getApplication();
        butlerApplication.setLauncherModel(mLauncherModel);

        Log.d(TAG, "onCreate");
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SPUtils.getInstance().show();
        butlerApplication.getLauncherModel().update();
    }

    private void initData() {

    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        //设置横向布局：
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        imageview_bulter_electric_bar = findViewById(R.id.imageview_bulter_electric_bar);
        imageview_bulter_wifi_bar = findViewById(R.id.imageview_bulter_wifi_bar);
        imageview_bulter_setting_bar = findViewById(R.id.imageview_bulter_setting_bar);
    }

    @Override
    public void addView(List<AppInfo> appInfoList) {
        Log.d(TAG, "addView:" + appInfoList.size());
        launcherAdapter = new LauncherAdapter(this, appInfoList);
        //设置适配器

        Observable.create(new ObservableOnSubscribe<LauncherAdapter>() {
            @Override
            public void subscribe(ObservableEmitter<LauncherAdapter> emitter) throws Exception {
                emitter.onNext(launcherAdapter);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LauncherAdapter>() {
                    @Override
                    public void accept(LauncherAdapter launcherAdapter) throws Exception {
                        //回调后在UI界面上展示出来
                        mRecyclerView.setAdapter(launcherAdapter);
                    }
                });
    }

    @Override
    public void deleteView() {

    }

    //更新電池狀態
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBatteryState(BatteryEvent event) {

        int level = event.getLevel();
        if (level >= 80) {
            imageview_bulter_electric_bar.getBackground().setLevel(23);
        } else if (level >= 30) {
            imageview_bulter_electric_bar.getBackground().setLevel(13);
        } else {
            imageview_bulter_electric_bar.getBackground().setLevel(3);
        }
    }

    //更新wifi狀態
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWifiState(WifiEvent event) {
        boolean connect = event.isConnect();
        if (connect) {

            WifiManager wifi_service = (WifiManager)getApplicationContext().getSystemService(WIFI_SERVICE);
            WifiInfo wifiInfo    = wifi_service.getConnectionInfo();
            int rssi = wifiInfo.getRssi();
            if(rssi <=100 ){
                imageview_bulter_wifi_bar.getBackground().setLevel(13);
            }else {
                imageview_bulter_wifi_bar.getBackground().setLevel(23);
            }
            Log.d(TAG,"rssi :"+rssi);

        } else {
            imageview_bulter_wifi_bar.getBackground().setLevel(3);
        }
    }


    public void settingClick(View view) {
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.android.settings");
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        butlerApplication.getLauncherModel().onDestroy();
    }

    @Override
    protected boolean isEventBus() {
        return true;
    }
}
