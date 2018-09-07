package com.example.zy.uidemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
	}
	
	public void demo1(View view) {
		startActivity(new Intent(Main2Activity.this,MainActivity.class));
	}
	
	public void demo2(View view) {
		startActivity(new Intent(Main2Activity.this,MultiItemListViewActivity.class));
	}
	public void demo3(View view) {
		startActivity(new Intent(Main2Activity.this,RecyclerViewActivity.class));
	}
	
	public void demo4(View view) {
		startActivity(new Intent(Main2Activity.this,MultiItemRvActivity.class));
	}
	
	
}
