package com.example.singou.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.singou.myapplication.R;

public class GlideActivity extends AppCompatActivity {
	
	private ImageView glide_imageview1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_glide);
		glide_imageview1 = (ImageView) findViewById(R.id.glide_imageview1);
		Glide.with(this).load(R.drawable.loading)
				//.diskCacheStrategy(DiskCacheStrategy.ALL)
				.into(glide_imageview1);
	}
}
