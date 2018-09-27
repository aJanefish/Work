package com.butler.launcher;

import android.app.Application;

import com.butler.launcher.model.LauncherModel;

public class ButlerApplication extends Application {

	private LauncherModel launcherModel;

	@Override
	public void onCreate() {
		super.onCreate();
	}

	public LauncherModel getLauncherModel() {
		return launcherModel;
	}

	public void setLauncherModel(LauncherModel launcherModel) {
		this.launcherModel = launcherModel;
	}


}
