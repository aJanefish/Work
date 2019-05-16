package com.example.zy.rxjavademo.presenter;

import com.example.zy.rxjavademo.model.RxJavaModel;
import com.example.zy.rxjavademo.view.IRxJavaView;

import java.lang.ref.SoftReference;

public class RxJavaPresenter {

    private SoftReference<IRxJavaView> softReference;

    public RxJavaPresenter(IRxJavaView rxJavaView) {
        this.softReference = new SoftReference<>(rxJavaView);

    }

    public void simple() {
        RxJavaModel.simple();
    }
}
