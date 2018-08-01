package com.example.singou.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.example.singou.myapplication.R;

public class SPPXActivity extends AppCompatActivity {
	
	private TextView sppx_px36;
	private TextView sppx_sp36;
	private TextView sppx_show;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sppx);
		sppx_px36 = findViewById(R.id.sppx_px36);
		sppx_sp36 = findViewById(R.id.sppx_sp36);
		
		sppx_show = findViewById(R.id.sppx_show);
		
		initDate();
	}
	
	private void initDate() {
		DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
		
		
		String show = displayMetrics.toString()+" sppx_px36:"+sppx_px36.getTextSize()+" sppx_sp36:"+sppx_sp36.getTextSize()+" "+
				(sppx_sp36.getTextSize()/sppx_px36.getTextSize());
		sppx_show.setText(show);
	}
}
