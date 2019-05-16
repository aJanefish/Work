package com.example.zy.rxjavademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.zy.rxjavademo.activity.RxJavaDemoActivity;
import com.example.zy.rxjavademo.presenter.MainPresenter;
import com.example.zy.rxjavademo.view.IMainView;
import com.example.zy.rxjavademo.viewinjection.ViewField;
import com.example.zy.rxjavademo.viewinjection.ViewMethod;
import com.example.zy.rxjavademo.viewinjection.ViewUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * MVP 测试界面
 * */
public class MainActivity extends AppCompatActivity implements IMainView {

	@ViewField(R.id.activity_main_des)
	TextView des;


	@ViewField(R.id.activity_main_state)
	TextView state;
	private MainPresenter mainPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.register(this);

		mainPresenter = new MainPresenter(this);

	}


	@ViewMethod(R.id.main_button_rx_java)
	public void go_rx_java(View view){
		Intent intent = new Intent(this, RxJavaDemoActivity.class);
		startActivity(intent);
	}


	@ViewMethod(R.id.main_button_test_normal)
	public void show(View view){
		mainPresenter.getData("normal");
	}

	@ViewMethod(R.id.main_button_test_failure)
	public void hide(View view){
		mainPresenter.getData("failure");
	}

	@ViewMethod(R.id.main_button_test_error)
	public void error(View view){
		mainPresenter.getData("error");
	}

	@Override
	public void showLoading() {
		setState("showLoading");
	}

	private void setDes(String values){
		des.setText(values);
	}

	private void setState(String values){
		state.setText(values);
	}

	@Override
	public void hideLoading() {
		setState("hideLoading");
	}

	@Override
	public void showData(String data) {
		setDes("showData:"+data);
	}

	@Override
	public void showFailureMessage(String msg) {
		setDes("showFailureMessage:"+msg);
	}

	@Override
	public void showErrorMessage() {
		setDes("showErrorMessage");
	}
}
