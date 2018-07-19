package com.example.singou.marqueeviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;
/**
 *git地址：git@github.com:sfsheng0322/MarqueeView.git
 *实现文字广告展示
 * */

public class MainActivity extends AppCompatActivity {

    private MarqueeView marqueeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        marqueeView=findViewById(R.id.marqueeView);
        initdate();
        init();
    }

    private void initdate() {

        List<String> info = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            info.add("这里是热门头条" +
                    "\n这里是热门头条" + i);
        }
        //marqueeView.setNotices(info);
        marqueeView.startWithText("天气台风预警");
        marqueeView.startWithList(info);
        //点击事件
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(getApplicationContext(), String.valueOf(marqueeView.getPosition()) + ". " + textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init(){
        //marqueeView.start();

        //marqueeView.start();
        //marqueeView.stopFlipping();
    }
}
