package com.example.singou.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.singou.myapplication.activity.ArrayBlockingQueueActivity;
import com.example.singou.myapplication.activity.DebugActvity;
import com.example.singou.myapplication.activity.FlexboxActivity;
import com.example.singou.myapplication.activity.GlideActivity;
import com.example.singou.myapplication.activity.SPPXActivity;
import com.example.singou.myapplication.activity.ScaleTypeActivity;
import com.example.singou.myapplication.activity.ZXingActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Logger研究
    public void onDebug(View view) {
        startActivity(new Intent(MainActivity.this,DebugActvity.class));
    }
    
    //Glide 图片加载的问题
    public void onGlide(View view) {
        startActivity(new Intent(MainActivity.this,GlideActivity.class));
    }
    
    
    
    //SP_PX 的细节
    public void onText(View view) {
        startActivity(new Intent(MainActivity.this,SPPXActivity.class));
    }
    
    //阻塞队列demo
    public void onArrayBlockingQueue(View view) {
        startActivity(new Intent(MainActivity.this,ArrayBlockingQueueActivity.class));
    }
    
    //ScaleType Demo
    public void onScaleType(View view) {
        startActivity(new Intent(MainActivity.this,ScaleTypeActivity.class));
    }
    
    //二维码demo
    public void onZXing(View view) {
        startActivity(new Intent(MainActivity.this,ZXingActivity.class));
    }
    
    public void onFlexbox(View view) {
        startActivity(new Intent(MainActivity.this,FlexboxActivity.class));
    }
}
