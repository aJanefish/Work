package com.example.singou.glide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
/*
 * 用于个人测试的Glide
 * git地址：git@github.com:bumptech/glide.git
 *
 * 关于 Glide
 * Glide是一个快速高效的Android图片加载库，注重于平滑的滚动。Glide提供了易用的API，高性能、可扩展的图片解码管道（decode pipeline），以及自动的资源池技术。
 * Glide 支持拉取，解码和展示视频快照，图片，和GIF动画。Glide的Api是如此的灵活，开发者甚至可以插入和替换成自己喜爱的任何网络栈。
 * 默认情况下，Glide使用的是一个定制化的基于HttpUrlConnection的栈，但同时也提供了与Google Volley和Square OkHttp快速集成的工具库。
 * 虽然Glide 的主要目标是让任何形式的图片列表的滚动尽可能地变得更快、更平滑，但实际上，Glide几乎能满足你对远程图片的拉取/缩放/显示的一切需求。
 *
 * */

public class MainActivity extends AppCompatActivity {

    private ImageView glide_imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        glide_imageview= findViewById(R.id.glide_imageview);

        Glide.with(this)
                .load("http://content.macaotourism.gov.mo/uploads/mgto_sightseeing/cd22ef89ce82baced0a5a4d8f662d65f0a501181.jpg")
                .into(glide_imageview);
    }
}
