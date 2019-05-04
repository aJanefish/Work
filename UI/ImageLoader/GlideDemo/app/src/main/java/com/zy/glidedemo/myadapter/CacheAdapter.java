package com.zy.glidedemo.myadapter;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zy.glidecache.ZYGlide;
import com.zy.glidedemo.R;

import java.util.List;


public class CacheAdapter extends RecyclerView.Adapter<CacheHolder> {

    private static final String TAG = "CacheAdapter";
    List<MessageEvent> list;
    Handler handler = new Handler(Looper.getMainLooper());

    public CacheAdapter(List<MessageEvent> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        int type = super.getItemViewType(position);
        Log.d(TAG, "getItemViewType:position-" + position + ", type-" + type);
        return position;
    }

    @NonNull
    @Override
    public CacheHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder:,viewType-" + viewType);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cache_item_list, parent, false);
        CacheHolder cacheHolder = new CacheHolder(view);

        return cacheHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CacheHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder:holder-" + holder + " position-" + position);
        if (position >10) return;
        final MessageEvent messageEvent = list.get(position);
        //holder.image_view.setImageResource(R);
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = ZYGlide.getBitmap(messageEvent.getUrl());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        holder.image_view.setImageBitmap(bitmap);
                        holder.title.setText(messageEvent.title);

                    }
                });
            }
        }).start();
    }


    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }
}
