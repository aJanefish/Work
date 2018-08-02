package com.example.zy.mvpdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zy.mvpdemo.presenter.MvpPresenter;
import com.example.zy.mvpdemo.presenter.MvpView;

/**
 * MVP模式Demo
 * MVP 理论知识
 * 在MVP 架构中跟MVC类似的是同样也分为三层。
 * Activity 和Fragment 视为View层，负责处理 UI。
 * Presenter 为业务处理层，既能调用UI逻辑，又能请求数据，该层为纯Java类，不涉及任何Android API。
 * Model 层中包含着具体的数据请求，数据源。
 * 三层之间调用顺序为view->presenter->model，为了调用安全着想不可反向调用！不可跨级调用！
 * */
public class MainActivity extends BaseActivity implements MvpView {
	//进度条
	ProgressDialog progressDialog;
	TextView text;
	MvpPresenter presenter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text = (TextView)findViewById(R.id.text);
		// 初始化进度条
		progressDialog = new ProgressDialog(this);
		progressDialog.setCancelable(false);
		progressDialog.setMessage("正在加载数据");
		//初始化Presenter
		presenter = new MvpPresenter();
		presenter.attachView(this);
	}
	// button 点击事件调用方法
	public void getData(View view){
		presenter.getData("normal");
	}
	// button 点击事件调用方法
	public void getDataForFailure(View view){
		presenter.getData("failure");
	}
	// button 点击事件调用方法
	public void getDataForError(View view){
		presenter.getData("error");
	}
	
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		presenter.detachView();
	}
	
	@Override
	public void showData(String data) {
		text.setText(data);
		Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
	}
}
