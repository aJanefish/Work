package com.example.zy.universaldemo.model;

public interface CallBack<T> {
	void success(T t);
	void error(String err);
	
}
