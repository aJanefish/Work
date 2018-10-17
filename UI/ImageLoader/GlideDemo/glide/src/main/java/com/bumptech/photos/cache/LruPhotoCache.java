package com.bumptech.photos.cache;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;


public class LruPhotoCache {

    private static final float SIZE_RATIO = 1f / 8f;
    private final PhotoCache photoCache;

    /*
    Canonly call after context is created (ie in onCreate or later...)
    */
    public static int getMaxCacheSize(Context context) {
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        return Math.round(SIZE_RATIO * activityManager.getMemoryClass() * 1024 * 1024);
    }


    public LruPhotoCache(int size) {
        photoCache = new PhotoCache(size);
    }

    public void put(String key, Bitmap bitmap) {
        photoCache.put(key, bitmap);
    }

    public Bitmap get(String key) {
        return photoCache.get(key);
    }

    public void remove(String key) {
        photoCache.remove(key);
    }

    public void evictAll() {
        photoCache.evictAll();
    }
}
