package com.example.zy.universaldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
/**
 * 1、多线程异步加载和显示图片（网络图片、sd卡、资源文件（asset，mipmap等，不能加载9patch），新增加载视频缩略图）
 * 2、支持加载过程的监听，可以暂停加载图片，在经常使用的ListView、GridView中，可以设置滑动时暂停加载，停止滑动时加载图片（便于节约流量，在一些优化中可以使用）
 * 3、高度可定制化（可以根据自己的需求进行各种配置，如：线程池，图片下载器，内存缓存策略等）
 * 4、支持图片的内存缓存，SD卡（文件）缓存
 * */

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
}
