package com.example.singou.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.singou.myapplication.R;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueActivity extends AppCompatActivity {
	private static final String TAG = "ArrayBlockingQueue";
	private ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue(5);
	private int tmp  = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_array_blocking_queue);
	}
	
	
	public void onAdd(View view) {
		boolean flag = arrayBlockingQueue.add("onAdd");
		Log.d(TAG,"onAdd:"+arrayBlockingQueue.toString()+" flag:"+flag);
	}
	public void onOffer(View view) {
		boolean flag = arrayBlockingQueue.offer("onAdd");
		Log.d(TAG,"onOffer:"+arrayBlockingQueue.toString()+" flag:"+flag);
	}
	
	public void onPut(View view) {
		
		try {
			Log.d(TAG,"onPut:"+arrayBlockingQueue.size());
			//满了，就会堵塞
			arrayBlockingQueue.put("onAdd");
			Log.d(TAG,"onPut:已经存放了数据:"+arrayBlockingQueue.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void onRemove(View view) {
		boolean flag = arrayBlockingQueue.remove("onAdd");
		Log.d(TAG,"onRemove:"+arrayBlockingQueue.toString()+" flag:"+flag);
	}
	
	
	public void onPoll(View view) {
		String s = arrayBlockingQueue.poll();
		Log.d(TAG,"onPoll:"+arrayBlockingQueue.toString()+" flag:"+s);
	}
	
	public void onTake(View view) {
		try {
			String s =  arrayBlockingQueue.take();
			Log.d(TAG,"onTake:"+arrayBlockingQueue.toString()+" flag:"+s);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
