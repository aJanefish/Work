package com.example.singou.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.singou.myapplication.R;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DebugActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug_actvity);

        Log.d("Tag", "I'm a log which you don't see easily, hehe");
        Log.d("json content", "{ \"key\": 3, \n \"value\": something}");
        Log.d("error", "There is a crash somewhere or any warning");

        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.d("message");
        Logger.clearLogAdapters();
        
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(3)        // (Optional) Skips some method invokes in stack trace. Default 5
//        .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("My custom tag")   // (Optional) Custom tag for each log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override public boolean isLoggable(int priority, String tag) {
                return true;
            }
        });

        Logger.addLogAdapter(new DiskLogAdapter());


        Logger.w("no thread info and only 1 method");

        Logger.clearLogAdapters();
        formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        Logger.i("no thread info and method info");

        Logger.t("tag").e("Custom tag for only one use");

        Logger.json("{ \"key\": 3, \"value\": something}");

        Logger.d(Arrays.asList("foo", "bar"));

        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        map.put("key1", "value2");

        Logger.d(map);

        Logger.clearLogAdapters();
        
        formatStrategy = PrettyFormatStrategy.newBuilder()
                .tag("MyTag")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

        Logger.w("my log message with my tag 1");
        Logger.w("my log message with my tag 2");
        Logger.w("my log message with my tag 3");
        Logger.w("my log message with my tag 4");
        
        Logger.t("tag").e("Custom tag for only one use");
        Logger.e("Custom tag for only one use");
    
        Logger.json("{ \"key\": 3, \"value\": something}");
    
        Logger.d(Arrays.asList("foo", "bar"));
    
        Logger.d(Arrays.asList("foo", "bar"));
    
        Map<String, String> map1 = new HashMap<>();
        map1.put("key", "value");
        map1.put("key1", "value2");
    
        Logger.d(map1);
    
        Logger.clearLogAdapters();
        
        
    
    
    
    
    
    
    
        PrettyFormatStrategy formatStrategy1 = PrettyFormatStrategy.newBuilder()
                .tag("TAG")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy1));
        //Logger.json(body);
        Logger.clearLogAdapters();
    }
    
    public void onSnackbar(View view) {
    }
}
