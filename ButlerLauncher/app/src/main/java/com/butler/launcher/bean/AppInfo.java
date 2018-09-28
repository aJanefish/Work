package com.butler.launcher.bean;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * App信息类
 */
public class AppInfo {
	public static int COMMON = 0x0000;
	public static int ADD = 0x0001;

	// 包名
	private String packageName;
	// APP名
	private String appName;
	private String className;
	// 图标
	private Drawable icon;
	private Bitmap bitmap;
	// 版本号
	private String versionName;
	// 权限
	private String[] permissions;
	// 主Activity的类名
	private String launchActivityName;

	//是否显示
	private boolean isShow = false;

	//特殊appInfo
	private int id = COMMON;

	public String getLaunchActivityName() {
		return launchActivityName;
	}
	
	public void setLaunchActivityName(String launchActivityName) {
		this.launchActivityName = launchActivityName;
	}
	
	public AppInfo() {}
	
	public String getPackageName() {
		return packageName;
	}
	
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public String getAppName() {
		return appName;
	}
	
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	public Drawable getIcon() {
		return icon;
	}
	
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
	
	public String getVersionName() {
		return versionName;
	}
	
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	
	public String[] getPermissions() {
		return permissions;
	}
	
	public void setPermissions(String[] permissions) {
		this.permissions = permissions;
	};
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	public String getClassName() {
		return className;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AppInfo{" +
				"packageName='" + packageName + '\'' +
				", appName='" + appName + '\'' +
				", className='" + className + '\'' +
				", isShow=" + isShow +
				", id=" + id +
				'}';
	}
}