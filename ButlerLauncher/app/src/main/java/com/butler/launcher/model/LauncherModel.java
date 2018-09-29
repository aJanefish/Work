package com.butler.launcher.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.butler.launcher.R;
import com.butler.launcher.bean.AppInfo;
import com.butler.launcher.db.LauncherDBManager;
import com.butler.launcher.db.dao.AppLayoutEventDao;
import com.butler.launcher.db.event.AppLayoutEvent;
import com.butler.launcher.utils.AppInfoUtil;
import com.butler.launcher.utils.CanvasUtils;
import com.butler.launcher.utils.LauncherUtils;
import com.butler.launcher.utils.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class LauncherModel extends Thread {

    private static final String TAG = "LauncherModel";


    private Context mContext;
    private CallBack mCallBack;
    private AppInfoUtil appInfoUtil;
    private static HashMap<String, AppInfo> mAppInfoHashMap;
    public static List<AppInfo> appAllInfos;
    private static List<AppInfo> appShowInfos;

    private AppInfo addAppInfo = new AppInfo();



    public LauncherModel(Context context) {
        this.mContext = context;
        this.appInfoUtil = AppInfoUtil.getInstance(context);
        appAllInfos = new ArrayList<>();
        appShowInfos = new LinkedList<>();
        mAppInfoHashMap = appInfoUtil.getInstalledApps(0);


        addAppInfo = new AppInfo();
        addAppInfo.setId(AppInfo.ADD);
        Bitmap bitmap = CanvasUtils.drawableToBitmap(mContext.getDrawable(R.drawable.ic_add_black_200dp));
        addAppInfo.setBitmap(bitmap);
        addAppInfo.setShow(true);
        //appInfo.setAppName("添加app");
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
        //过滤apk
        filter();

        update(true);
    }

    public void update(boolean flag) {

        //取出之前的layout
        if(flag){
            loadLayout();
        }else {
            spShow();
        }

        //add DIY AppInfo
        addDiyAppinfo();

        //加载桌面
        if (mCallBack != null) {
            mCallBack.addView(appShowInfos);
        }

        //保存apk显示的顺序
        saveLayout();
    }

    private void saveLayout() {
        AppLayoutEventDao dao = LauncherDBManager.getInstance().getDaoSession().getAppLayoutEventDao();
        dao.deleteAll();
        int tmp  = 0;
        for(AppInfo appInfo:appShowInfos){

            appInfo.setLayoutX(tmp);
            if(appInfo.getId() == AppInfo.ADD){
                continue;
            }
            AppLayoutEvent appLayoutEvent = LauncherDBManager.getInstance().getLayoutEvent(appInfo);
            dao.insert(appLayoutEvent);

            tmp ++;
        }
    }

    private void loadLayout() {
        AppLayoutEventDao dao = LauncherDBManager.getInstance().getDaoSession().getAppLayoutEventDao();
        List<AppLayoutEvent> list = dao.loadAll();
        Log.d(TAG,"reLayout:"+list);

        appShowInfos.clear();
        for(AppLayoutEvent appLayoutEvent : list){

            if(mAppInfoHashMap.containsKey(appLayoutEvent.getPackageName())){
                appShowInfos.add(mAppInfoHashMap.get(appLayoutEvent.getPackageName()));
            }
        }

    }

    //从sp中获取到需要显示的app
    private void spShow() {
        //appShowInfos.clear();
        Map<String, ?> map = SPUtils.getInstance().getAll();
        for (Map.Entry<String, ?> entry : map.entrySet()) {

            AppInfo appInfo = mAppInfoHashMap.get(entry.getKey());
            if(appInfo == null){
                continue;
            }
            if (appInfo.isShow()) {
                if(!appShowInfos.contains(appInfo)){
                    appShowInfos.add(appInfo);
                }
            }else {
                if(appShowInfos.contains(appInfo)){
                    appShowInfos.remove(appInfo);
                    SPUtils.getInstance().remove(entry.getKey());
                }
            }
        }
    }

    private void addDiyAppinfo() {
        appShowInfos.remove(addAppInfo);
        appShowInfos.add(addAppInfo);
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
