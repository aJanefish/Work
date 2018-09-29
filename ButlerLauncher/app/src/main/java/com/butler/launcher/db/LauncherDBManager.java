package com.butler.launcher.db;

import android.content.Context;
import android.util.Log;

import com.butler.launcher.bean.AppInfo;
import com.butler.launcher.db.dao.DaoMaster;
import com.butler.launcher.db.dao.DaoSession;
import com.butler.launcher.db.event.AppLayoutEvent;

public class LauncherDBManager {

    private static LauncherDBManager launcherDBManager;
    private DaoSession daoSession;

    private LauncherDBManager() {

    }

    public static LauncherDBManager getInstance() {
        if (launcherDBManager == null) {
            launcherDBManager = new LauncherDBManager();
        }
        return launcherDBManager;
    }

    public void init(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "LauncherButler.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public AppLayoutEvent getLayoutEvent(AppInfo info){
        AppLayoutEvent appLayoutEvent = new AppLayoutEvent();
        appLayoutEvent.setId(null);
        appLayoutEvent.setAppName(info.getAppName());
        appLayoutEvent.setClassName(info.getClassName());
        appLayoutEvent.setIdd(info.getId());
        appLayoutEvent.setIsShow(info.isShow());
        appLayoutEvent.setLaunchActivityName(info.getLaunchActivityName());
        appLayoutEvent.setVersionName(info.getVersionName());
        appLayoutEvent.setPackageName(info.getPackageName());
        appLayoutEvent.setLayoutX(info.getLayoutX());
        return appLayoutEvent;
    }
}
