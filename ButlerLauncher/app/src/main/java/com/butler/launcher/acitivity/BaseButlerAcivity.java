package com.butler.launcher.acitivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class BaseButlerAcivity extends AppCompatActivity {
	
	private Window window;
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//remove title bar  即隐藏标题栏
		getSupportActionBar().hide();// 隐藏ActionBar
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//remove notification bar  即全屏
		
		//保持常亮
		window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//清除常亮
		window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
}
