package com.example.zy.universaldemo.presenter;

public interface ViewOne<T> {
	void success(T t);
	void error(String err);
}
