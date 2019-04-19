package zy.walk.com.thewalkers.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, getLog() + "-onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
        initDate();
        Log.d(TAG, getLog() + "-onStart");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, getLog() + "-onRestart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, getLog() + "-onResume");
        System.gc();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, getLog() + "-onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, getLog() + "-onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, getLog() + "-onDestroy");

    }

    public abstract void initView();

    public abstract void initDate();

    protected abstract String getLog();

}
