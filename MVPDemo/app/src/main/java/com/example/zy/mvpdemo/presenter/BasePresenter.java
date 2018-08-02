package com.example.zy.mvpdemo.presenter;

public class BasePresenter<V extends IBaseView> {
	/**
	 * 绑定的view
	 */
	public V v;
	
	/**
	 * 绑定view，一般在初始化中调用该方法
	 */
	public void attachView(V v) {
		this.v = v;
	}
	
	/**
	 * 断开view，一般在onDestroy中调用
	 */
	public void detachView() {
		this.v = null;
	}
	
	/**
	 * 是否与View建立连接
	 * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
	 */
	public boolean isViewAttached() {
		return v != null;
	}
	
	/**
	 * 获取连接的view
	 */
	public V getView() {
		return v;
	}
}
