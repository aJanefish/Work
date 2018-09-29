package com.butler.launcher.db.event;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class AppLayoutEvent {
    @Id
    private Long id;

    // 包名
    private String packageName;
    // APP名
    private String appName;
    private String className;
    // 版本号
    private String versionName;
    // 主Activity的类名
    private String launchActivityName;

    //显示的列数
    private int layoutX;

    //是否显示
    private boolean isShow = false;

    //特殊appInfo
    private int idd ;

    @Generated(hash = 171300603)
    public AppLayoutEvent(Long id, String packageName, String appName,
            String className, String versionName, String launchActivityName,
            int layoutX, boolean isShow, int idd) {
        this.id = id;
        this.packageName = packageName;
        this.appName = appName;
        this.className = className;
        this.versionName = versionName;
        this.launchActivityName = launchActivityName;
        this.layoutX = layoutX;
        this.isShow = isShow;
        this.idd = idd;
    }

    @Generated(hash = 2091647546)
    public AppLayoutEvent() {
    }



    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getLaunchActivityName() {
        return this.launchActivityName;
    }

    public void setLaunchActivityName(String launchActivityName) {
        this.launchActivityName = launchActivityName;
    }

    public boolean getIsShow() {
        return this.isShow;
    }

    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }

    public int getIdd() {
        return this.idd;
    }

    public void setIdd(int idd) {
        this.idd = idd;
    }

    public int getLayoutX() {
        return this.layoutX;
    }

    public void setLayoutX(int layoutX) {
        this.layoutX = layoutX;
    }

    @Override
    public String toString() {
        return "AppLayoutEvent{" +
                "id=" + id +
                ", packageName='" + packageName + '\'' +
                ", appName='" + appName + '\'' +
                ", className='" + className + '\'' +
                ", versionName='" + versionName + '\'' +
                ", launchActivityName='" + launchActivityName + '\'' +
                ", layoutX=" + layoutX +
                ", isShow=" + isShow +
                ", idd=" + idd +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }
}
