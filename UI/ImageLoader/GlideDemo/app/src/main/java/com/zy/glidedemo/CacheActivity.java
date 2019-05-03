package com.zy.glidedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;

import com.zy.glidecache.ZYGlide;
import com.zy.glidedemo.myadapter.BaseAdapter;
import com.zy.glidedemo.myadapter.BaseHolder;
import com.zy.glidedemo.myadapter.ItemViewDelegate;
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
        BaseAdapter<MessageEvent> baseAdapter = new BaseAdapter<>(this, mTmpDates);
        baseAdapter.addDelegate(new ItemViewDelegate<MessageEvent>() {
            @Override
            public int getLayoutId() {
                return R.layout.activity_cache_item_list;
            }

            @Override
            public boolean isShowing(MessageEvent messageEvent, int position) {
                return true;
            }

            @Override
            public void convert(final BaseHolder holder, final MessageEvent messageEvent, int position) {
                holder.setText(R.id.activity_cache_item_title, messageEvent.getTitle());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Bitmap bitmap = ZYGlide.getBitmap(messageEvent.getUrl());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                holder.setImage(R.id.activity_cache_item_image_view, bitmap);

                            }
                        });
                    }
                }).start();

            }
        });
        recyclerView.setAdapter(baseAdapter);
    }
}
