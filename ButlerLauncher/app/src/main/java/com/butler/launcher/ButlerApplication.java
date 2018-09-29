package com.butler.launcher;

import android.app.Application;

import com.butler.launcher.db.LauncherDBManager;
import com.butler.launcher.model.LauncherModel;
import com.butler.launcher.utils.SPUtils;

public class ButlerApplication extends Application {

	private LauncherModel launcherModel;

	@Override
	public void onCreate() {
		super.onCreate();
		SPUtils.getInstance().init(this);
		LauncherDBManager.getInstance().init(this);
	}

	public LauncherModel getLauncherModel() {
		return launcherModel;
	}

	public void setLauncherModel(LauncherModel launcherModel) {
		this.launcherModel = launcherModel;
	}


}
