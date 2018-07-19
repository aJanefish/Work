package com.example.singou.stethodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.stetho.Stetho;

/**
 * stetho是Facebook开发的一框基于Chrome开发者工具的Debug库。
 *
 * Elements：查看View的层次结构
 * Network：网络抓包
 * Resources：查看本地SharePrefenrence和本地Sqlite DB
 * Console：app执行js
 *
 * //chrome://inspect/#devices
 *
 * */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
    }
}
