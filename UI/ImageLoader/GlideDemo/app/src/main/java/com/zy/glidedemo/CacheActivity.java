package com.zy.glidedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.os.Handler;

import com.zy.glidedemo.myadapter.CacheAdapter;
import com.zy.glidedemo.myadapter.MessageEvent;

import java.util.ArrayList;
import java.util.List;

public class CacheActivity extends AppCompatActivity {
    private final String uri = "https://i.loli.net/2019/02/21/5c6e1e24c4689.png";
    private final String uri1 = "https://www.baidu.com/aladdin/img/tools/tools-3.png";
    private final String uri2 = "https://www.baidu.com/aladdin/img/tools/tools-2.png";
    private final String uri3 = "https://www.baidu.com/aladdin/img/tools/tools-4.png";
    private final String uri4 = "https://www.baidu.com/aladdin/img/tools/tools-5.png";
    private final String uri5 = "https://www.baidu.com//img//sug_bd.png";
    private final String uri6 = "https://i.loli.net/2019/02/21/5c6e1e24c4689.png";

    private RecyclerView recyclerView;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);
        recyclerView= findViewById(R.id.activity_cache_list_view);
        initView();
        handler = new Handler();
    }

    private void initView() {
        //recyclerView.setAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<MessageEvent> mTmpDates = new ArrayList<>();
        mTmpDates.add(new MessageEvent("uri1",uri));
        mTmpDates.add(new MessageEvent("uri2",uri1));
        mTmpDates.add(new MessageEvent("uri3",uri2));
        mTmpDates.add(new MessageEvent("uri4",uri3));
        mTmpDates.add(new MessageEvent("uri5",uri4));
        mTmpDates.add(new MessageEvent("uri6",uri5));
        mTmpDates.add(new MessageEvent("uri7",uri6));
        mTmpDates.add(new MessageEvent("uri8",uri1));
        mTmpDates.add(new MessageEvent("uri9",uri2));
        mTmpDates.add(new MessageEvent("uri10",uri3));
        mTmpDates.add(new MessageEvent("uri11",uri4));
        mTmpDates.add(new MessageEvent("uri12",uri5));
        mTmpDates.add(new MessageEvent("uri22",uri1));
        mTmpDates.add(new MessageEvent("uri23",uri2));
        mTmpDates.add(new MessageEvent("uri24",uri3));
        mTmpDates.add(new MessageEvent("uri25",uri4));
        mTmpDates.add(new MessageEvent("uri26",uri5));
        mTmpDates.add(new MessageEvent("uri27",uri6));
        mTmpDates.add(new MessageEvent("uri28",uri1));
        mTmpDates.add(new MessageEvent("uri29",uri2));
        mTmpDates.add(new MessageEvent("uri210",uri3));
        mTmpDates.add(new MessageEvent("uri211",uri4));
        mTmpDates.add(new MessageEvent("uri212",uri5));
        mTmpDates.add(new MessageEvent("uri31",uri));
        mTmpDates.add(new MessageEvent("uri32",uri1));
        mTmpDates.add(new MessageEvent("uri33",uri2));
        mTmpDates.add(new MessageEvent("uri34",uri3));
        mTmpDates.add(new MessageEvent("uri35",uri4));
        mTmpDates.add(new MessageEvent("uri36",uri5));
        mTmpDates.add(new MessageEvent("uri37",uri6));
        mTmpDates.add(new MessageEvent("uri38",uri1));
        mTmpDates.add(new MessageEvent("uri39",uri2));
        mTmpDates.add(new MessageEvent("uri310",uri3));
        mTmpDates.add(new MessageEvent("uri311",uri4));
        mTmpDates.add(new MessageEvent("uri312",uri5));
        mTmpDates.add(new MessageEvent("uri322",uri1));
        mTmpDates.add(new MessageEvent("uri323",uri2));
        mTmpDates.add(new MessageEvent("uri324",uri3));
        mTmpDates.add(new MessageEvent("uri325",uri4));
        mTmpDates.add(new MessageEvent("uri326",uri5));
        mTmpDates.add(new MessageEvent("uri327",uri6));
        mTmpDates.add(new MessageEvent("uri328",uri1));
        mTmpDates.add(new MessageEvent("uri329",uri2));
        mTmpDates.add(new MessageEvent("uri3210",uri3));
        mTmpDates.add(new MessageEvent("uri3211",uri4));
        mTmpDates.add(new MessageEvent("uri3212",uri5));
        CacheAdapter cacheAdapter = new CacheAdapter(mTmpDates);
        recyclerView.setAdapter(cacheAdapter);
    }
}
