package com.butler.launcher.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.butler.launcher.R;
import com.butler.launcher.bean.AppInfo;
import com.butler.launcher.utils.AppInfoUtil;
import com.butler.launcher.utils.CanvasUtils;

public class LauncherModel extends Thread {

    private static final String TAG = "LauncherModel";


    private Context mContext;
    private CallBack mCallBack;
    private AppInfoUtil appInfoUtil;
    public static HashMap<String, AppInfo> mAppInfoHashMap;
    public static List<AppInfo> appInfos;
    private String[] filterAppPackageName = {
            "com.butler.launcher",
            "com.baidu.searchbox",
            "com.android.wallpaperpicker"
    };


    public LauncherModel(Context context) {
        this.mContext = context;
        this.appInfoUtil = AppInfoUtil.getInstance(context);
        appInfos = new ArrayList<>();
        mAppInfoHashMap = appInfoUtil.getInstalledApps(0);
        Log.d(TAG, "LauncherModel:" + mAppInfoHashMap.size() + "");
    }

    public void setCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
    }

    public void onDestroy(){
        this.mCallBack = null;
        this.mContext = null;
        this.appInfoUtil = null;
        appInfos.clear();
        appInfos = null;
        mAppInfoHashMap.clear();
        mAppInfoHashMap = null;
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
        //add DIY AppInfo
        addDiyAppinfo();
        //加载桌面
        if (mCallBack != null) {
            mCallBack.addView(appInfos);
        }

    }

    private void addDiyAppinfo() {
        AppInfo appInfo = new AppInfo();
        appInfo.setId(AppInfo.ADD);
        Bitmap bitmap = CanvasUtils.drawableToBitmap(mContext.getDrawable(R.drawable.ic_add_black_200dp));
        appInfo.setBitmap(bitmap);
        //appInfo.setAppName("添加app");
        appInfos.add(appInfo);
    }

    private void filter() {
        Log.d(TAG, "filter start");
        if (mAppInfoHashMap != null) {
            Log.d(TAG, "filter start:" + mAppInfoHashMap.size() + "");
            for (String appPackageName : filterAppPackageName) {
                mAppInfoHashMap.remove(appPackageName);
            }
            Log.d(TAG, "filter end:" + mAppInfoHashMap.size() + "");
            for (AppInfo info : mAppInfoHashMap.values()) {
                appInfos.add(info);
            }
        }
        Log.d(TAG, "filter end");
    }


    public interface CallBack {

        void addView(List<AppInfo> appInfoList);

        void deleteView();

    }

}
