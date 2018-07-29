package com.example.singou.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.singou.myapplication.R;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueActivity extends AppCompatActivity {
	private static final String TAG = "ArrayBlockingQueue";
	private ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue(10);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_array_blocking_queue);
	}
	
	
}
