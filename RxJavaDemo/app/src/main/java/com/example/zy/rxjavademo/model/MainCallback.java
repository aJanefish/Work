package com.example.zy.rxjavademo.model;

public interface MainCallback {
    void onSuccess(String s);

    void onFailure(String s);

    void onError();

    void onComplete();
}
