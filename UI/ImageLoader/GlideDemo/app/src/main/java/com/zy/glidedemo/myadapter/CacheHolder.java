package com.zy.glidedemo.myadapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zy.glidedemo.R;

public class CacheHolder extends RecyclerView.ViewHolder{

    public final TextView title;
    public final ImageView image_view;

    public CacheHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.activity_cache_item_title);
        image_view =itemView.findViewById(R.id.activity_cache_item_image_view);

    }
}
