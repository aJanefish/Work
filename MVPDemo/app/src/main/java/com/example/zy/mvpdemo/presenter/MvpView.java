package com.example.zy.mvpdemo.presenter;

public interface MvpView extends IBaseView {
	/**
	 * 当数据请求成功后，调用此接口显示数据
	 * @param data 数据源
	 */
	void showData(String data);
}
