package zy.walk.com.thewalkers.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getLog(), "onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
        initDate();
        Log.d(getLog(), "onStart");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(getLog(), "onRestart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(getLog(), "onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(getLog(), "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(getLog(), "onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(getLog(), "onDestroy");

    }

    public abstract void initView();

    public abstract void initDate();

    protected abstract String getLog();

}
