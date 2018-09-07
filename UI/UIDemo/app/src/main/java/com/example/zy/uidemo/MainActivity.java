package com.example.zy.uidemo;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	
	private RecyclerView mRecyclerView;
	private MyRecyclerViewAdapter mAdapter;
	private List<String> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	
	private void init() {
		list = new ArrayList<>();
		for(int i = 0; i < 50; i ++){
			list.add("zhangyu "+i);
		}
		//通过findViewById拿到RecyclerView实例
		mRecyclerView =   findViewById(R.id.recyclerView);
		//设置RecyclerView管理器 纵向
		//mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		//mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
		
		//设置横向布局：
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
		mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
		//设置网格布局：
		//mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
		//设置瀑布流：
		//mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));
		
		
		
		
		//初始化适配器
		mAdapter = new MyRecyclerViewAdapter(list);
		
		
		mAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position, String data) {
				Toast.makeText(MainActivity.this, "您点击了：  " + data, Toast.LENGTH_SHORT).show();
			}
		});
		mAdapter.setOnItemLongClickListener(new MyRecyclerViewAdapter.OnItemLongClickListener() {
			@Override
			public void onItemLongClick(View view, int position, String data) {
				Toast.makeText(MainActivity.this, "您长按点击了：  " + data, Toast.LENGTH_SHORT).show();
			}
		});
		
		//设置添加或删除item时的动画，这里使用默认动画
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		
		
		

		//设置适配器
		mRecyclerView.setAdapter(mAdapter);
		
	}
}
