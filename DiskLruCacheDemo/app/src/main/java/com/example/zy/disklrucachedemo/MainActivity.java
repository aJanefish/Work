package com.example.zy.disklrucachedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.GridView;
import android.widget.TextView;

import com.example.zy.disklrucachedemo.adapter.PhotoWallAdapter;
import com.example.zy.disklrucachedemo.utils.Images;

public class MainActivity extends AppCompatActivity {
	
	/**
	 * 用于展示照片墙的GridView
	 */
	private GridView mPhotoWall;
	
	/**
	 * GridView的适配器
	 */
	private PhotoWallAdapter mAdapter;
	
	private int mImageThumbSize;
	private int mImageThumbSpacing;
	private TextView textview_show;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textview_show = findViewById(R.id.textview_show);
		mImageThumbSize = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
		mImageThumbSpacing = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_spacing);
		
		textview_show.setText("mImageThumbSize:"+mImageThumbSize+" mImageThumbSpacing:"+mImageThumbSpacing);
		
		mPhotoWall = findViewById(R.id.photo_wall);
		mAdapter = new PhotoWallAdapter(this, 0, Images.imageThumbUrls,
				mPhotoWall);
		mPhotoWall.setAdapter(mAdapter);
		mPhotoWall.getViewTreeObserver().addOnGlobalLayoutListener(
				new ViewTreeObserver.OnGlobalLayoutListener() {
					
					@Override
					public void onGlobalLayout() {
						//Log.d("zhangyu0730","onGlobalLayout:"+Log.getStackTraceString(new Throwable()));
						final int numColumns = (int) Math.floor(mPhotoWall
								.getWidth()
								/ (mImageThumbSize + mImageThumbSpacing));
						if (numColumns > 0) {
							int columnWidth = (mPhotoWall.getWidth() / numColumns)
									- mImageThumbSpacing;
							mAdapter.setItemHeight(columnWidth);
							Log.d("zhangyu0730","onGlobalLayout:"+columnWidth);
							mPhotoWall.getViewTreeObserver().removeGlobalOnLayoutListener(this);
						}
					}
				});
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		mAdapter.fluchCache();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 退出程序时结束所有的下载任务
		mAdapter.cancelAllTasks();
	}
	
}

