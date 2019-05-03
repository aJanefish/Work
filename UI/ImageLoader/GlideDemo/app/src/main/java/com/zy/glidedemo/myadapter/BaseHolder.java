package com.zy.glidedemo.myadapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BaseHolder  extends RecyclerView.ViewHolder {

    private BaseHolder(@NonNull View itemView) {
        super(itemView);
    }

    public static BaseHolder createViewHolder(ViewGroup parent,final int layoutId){
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        BaseHolder viewHolder = new BaseHolder(view);
        return viewHolder;
    }

    public void setText(final int id,String values){
         TextView textView = itemView.findViewById(id);
         textView.setText(values);
    }

    public void setImage(final int id, Bitmap bitmap){
        ImageView imageView = itemView.findViewById(id);
        imageView.setImageBitmap(bitmap);
    }

}
