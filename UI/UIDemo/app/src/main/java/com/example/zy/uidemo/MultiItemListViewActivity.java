package com.example.zy.uidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.zy.uidemo.lv.ChatAdapter;
import com.zhy.adapter.abslistview.CommonAdapter;

public class MultiItemListViewActivity extends AppCompatActivity {
	
	
	private ListView mListView;
	private CommonAdapter mAdapter ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi_item_list_view);
		
		mListView = findViewById(R.id.id_listview_list);
		mListView.setDivider(null);
		mListView.setAdapter(new ChatAdapter(this, ChatMessage.MOCK_DATAS));
	}
}
