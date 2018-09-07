package com.zy.canvasdemo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zy.canvasdemo.bean.AppInfo;
import com.zy.canvasdemo.utils.AppUtils;
import com.zy.canvasdemo.utils.CanvasUtils;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
	
	private AppUtils appUtils;
	private HashMap<String, AppInfo> appInfoHashMap;
	private List<AppInfo> appInfos  = new ArrayList<>();
	private ImageView my_imageView;
	private TextView appname_launcher_item;
	private RelativeLayout rel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		appUtils= AppUtils.getInstance(this);
		appInfoHashMap = appUtils.getInstalledApps(0);
		for(AppInfo info : appInfoHashMap.values()){
			appInfos.add(info);
		}
	}
	
	private void initView() {
		my_imageView = findViewById(R.id.my_imageView);
		appname_launcher_item = findViewById(R.id.appname_launcher_item);
		rel = findViewById(R.id.rel);
	}
	
	public void drawableToBitmap(View view) {
		
		Log.d("zhangyu",rel.getWidth()+" , "+rel.getHeight());
		
		
		int tmp = new Random().nextInt(appInfos.size());
		
		Bitmap bitmap = CanvasUtils.drawableToBitmap(appInfos.get(tmp).getIcon());
		my_imageView.setImageBitmap(bitmap);
		appname_launcher_item.setText(appInfos.get(tmp).getAppName());
	}
	
	//使用Matrix将Bitmap压缩到指定大小
	public void resize(View view) {
		
		int tmp = new Random().nextInt(appInfos.size());
		
		Bitmap bitmap = CanvasUtils.drawableToBitmap(appInfos.get(tmp).getIcon());
		bitmap = CanvasUtils.resizeBitmap(bitmap,200,200) ;
		my_imageView.setImageBitmap(bitmap);
		appname_launcher_item.setText(appInfos.get(tmp).getAppName());
	}
	
	public void Round(View view) {
		int tmp = new Random().nextInt(appInfos.size());
		
		Bitmap bitmap = CanvasUtils.drawableToBitmap(appInfos.get(tmp).getIcon());
		bitmap = CanvasUtils.resizeBitmap(bitmap,200,200) ;
		bitmap = CanvasUtils.getRoundedCornerBitmap(bitmap,20) ;
		my_imageView.setImageBitmap(bitmap);
		appname_launcher_item.setText(appInfos.get(tmp).getAppName());
	}
	
	public void Oval(View view) {
		int tmp = new Random().nextInt(appInfos.size());
		
		Bitmap bitmap = CanvasUtils.drawableToBitmap(appInfos.get(tmp).getIcon());
		bitmap = CanvasUtils.resizeBitmap(bitmap,200,200) ;
		bitmap = CanvasUtils.getOvalBitmap(bitmap) ;
		my_imageView.setImageBitmap(bitmap);
		appname_launcher_item.setText(appInfos.get(tmp).getAppName());
	}
}
