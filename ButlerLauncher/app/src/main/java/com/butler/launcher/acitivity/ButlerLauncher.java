package com.butler.launcher.acitivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;


import com.butler.launcher.R;
import com.butler.launcher.adapter.LauncherAdapter;
import com.butler.launcher.bean.AppInfo;
import com.butler.launcher.model.LauncherModel;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class ButlerLauncher extends BaseButlerAcivity implements LauncherModel.CallBack {
	
	private LauncherModel mLauncherModel;
	private String TAG = "ButlerLauncher";
	private RecyclerView mRecyclerView;
	private LauncherAdapter launcherAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		
		initView();
		
		mLauncherModel = new LauncherModel(this);
		mLauncherModel.setCallBack(this);
		mLauncherModel.start();
		
		Log.d(TAG,"onCreate");
		
	}
	
	private void initView() {
		mRecyclerView =   findViewById(R.id.recyclerView);
		//设置横向布局：
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
	}
	
	@Override
	public void addView(List<AppInfo> appInfoList) {
		Log.d(TAG,""+appInfoList.size());
		Log.d(TAG,appInfoList+"");
		launcherAdapter = new LauncherAdapter(this,appInfoList);
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
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mLauncherModel.setCallBack(null);
	}
}
