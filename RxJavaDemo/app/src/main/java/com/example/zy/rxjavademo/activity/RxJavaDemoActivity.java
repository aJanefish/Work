package com.example.zy.rxjavademo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.zy.rxjavademo.R;
import com.example.zy.rxjavademo.presenter.RxJavaPresenter;
import com.example.zy.rxjavademo.view.IRxJavaView;
import com.example.zy.rxjavademo.viewinjection.ViewMethod;
import com.example.zy.rxjavademo.viewinjection.ViewUtils;

public class RxJavaDemoActivity extends AppCompatActivity implements IRxJavaView {

    private RxJavaPresenter rxJavaPrssenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_demo);
        ViewUtils.register(this);
        rxJavaPrssenter = new RxJavaPresenter(this);
    }


    @ViewMethod(R.id.activity_rx_java_demo_simple)
    public void simple(View view){
        rxJavaPrssenter.simple();
    }
}
