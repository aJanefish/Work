package com.okhttp.review.zy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.okhttp.review.zy.activity.OkHttpDemoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void okhttp(View view) {
        startActivity(new Intent(MainActivity.this,OkHttpDemoActivity.class));
    }
}
