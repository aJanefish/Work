package com.zy.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zy.eventbus.MyEventBus;
import com.zy.eventbus.ZySubscribe;
import com.zy.zyopenlibtest.ZYUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Logger;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.URL;
import java.util.Date;
import java.util.Enumeration;

import dalvik.system.PathClassLoader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = " ";
    private int id = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }


    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {/* Do something */
        Log.d(TAG, "onMessageEvent:" + Thread.currentThread() + " - " + event);
        Log.d(TAG, Log.getStackTraceString(new Throwable()));
    }


    public void eventTest(View view) {
        //Logger sss = EventBus.getDefault().getLogger();
        Date date = new Date();
        EventBus.getDefault().post(new MessageEvent(id++, date.toString()));
        PathClassLoader sss = (PathClassLoader) this.getClass().getClassLoader();

        Log.d(TAG, "" + sss);
    }

    public void MyEventInit(View view) {
        MyEventBus.getDefault().register(this);
    }

    @ZySubscribe
    public void onZyMessageEvent(MessageEvent event) {/* Do something */
        Log.d(TAG, "onZyMessageEvent:" + Thread.currentThread() + " - " + event);
        //Log.d(TAG,Log.getStackTraceString(new Throwable()));
    }

    @ZySubscribe
    public void onZyString(String event) {/* Do something */
        Log.d(TAG, "onZyString:" + Thread.currentThread() + " - " + event + " - " + ZYUtils.getID());
        //Log.d(TAG,Log.getStackTraceString(new Throwable()));
    }


    public void MyEventPost1(View view) {
        Date date = new Date();
        MyEventBus.getDefault().post(new MessageEvent(id++, date.toString()));
    }

    public void MyEventPost2(View view) {
        MyEventBus.getDefault().post(new String("" + id++));
    }
}
