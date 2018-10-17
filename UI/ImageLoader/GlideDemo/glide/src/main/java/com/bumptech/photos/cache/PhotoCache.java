package com.bumptech.photos.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

public class PhotoCache extends LruCache<String, Bitmap> {

    public PhotoCache(int maxSize) {
        super(maxSize);
    }


    @Override
    protected int sizeOf(String key, Bitmap value) {
        //get the size, getByteCount() is API 12+...
        return value.getHeight() * value.getRowBytes();
    }
}