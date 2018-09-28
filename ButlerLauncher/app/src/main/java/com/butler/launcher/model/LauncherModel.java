package com.butler.launcher.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.butler.launcher.R;
import com.butler.launcher.bean.AppInfo;
import com.butler.launcher.utils.AppInfoUtil;
import com.butler.launcher.utils.CanvasUtils;
import com.butler.launcher.utils.LauncherUtils;
import com.butler.launcher.utils.SPUtils;

public class LauncherModel extends Thread {

    private static final String TAG = "LauncherModel";


    private Context mContext;
    private CallBack mCallBack;
    private AppInfoUtil appInfoUtil;
    public static HashMap<String, AppInfo> mAppInfoHashMap;
    public static List<AppInfo> appAllInfos;
    public static List<AppInfo> appShowInfos;



    public LauncherModel(Context context) {
        this.mContext = context;
        this.appInfoUtil = AppInfoUtil.getInstance(context);
        appAllInfos = new ArrayList<>();
        appShowInfos = new ArrayList<>();
        mAppInfoHashMap = appInfoUtil.getInstalledApps(0);
    }

    public void setCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
    }

    public void onDestroy(){
        this.mCallBack = null;
        this.mContext = null;
        this.appInfoUtil = null;
        appAllInfos.clear();
        appAllInfos = null;
        appShowInfos.clear();
        appShowInfos = null;
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
        //SPUtil --> appShowInfos
        spShow();
        //加载桌面
        if (mCallBack != null) {
            mCallBack.addView(appShowInfos);
        }
    }

    public void update() {
        spShow();
        //加载桌面
        if (mCallBack != null) {
            mCallBack.addView(appShowInfos);
        }
    }

    //从sp中获取到需要显示的app
    private void spShow() {
        //appShowInfos.clear();
        Map<String, ?> map = SPUtils.getInstance().getAll();
        for (Map.Entry<String, ?> entry : map.entrySet()) {

            if (entry.getValue().toString().contains("true")) {
                appShowInfos.add(mAppInfoHashMap.get(entry.getKey()));
            }else {
                
            }
        }
        //add DIY AppInfo
        addDiyAppinfo();
    }

    private void addDiyAppinfo() {
        AppInfo appInfo = new AppInfo();
        appInfo.setId(AppInfo.ADD);
        Bitmap bitmap = CanvasUtils.drawableToBitmap(mContext.getDrawable(R.drawable.ic_add_black_200dp));
        appInfo.setBitmap(bitmap);
        //appInfo.setAppName("添加app");
        appShowInfos.add(appInfo);
    }


    private void filter() {

        if (mAppInfoHashMap != null) {
            for (AppInfo info : mAppInfoHashMap.values()) {
                if (!LauncherUtils.getAppFifter().contains(info.getPackageName())) {
                    appAllInfos.add(info);
                }
            }
        }
    }


    public interface CallBack {

        void addView(List<AppInfo> appInfoList);

        void deleteView();

    }

}
