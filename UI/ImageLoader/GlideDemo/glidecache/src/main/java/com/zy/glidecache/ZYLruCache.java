package com.zy.glidecache;

import android.graphics.Bitmap;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ZYLruCache {
    private final Map<String, Bitmap> cache = new LinkedHashMap<>(100, 0.75f, true);

    private static ZYLruCache zyLruCache;
    private int MAX_NUMBER = 4;
    private int currentSize = 0;

    private ZYLruCache() {
    }

    public static ZYLruCache getInstance() {
        if (zyLruCache == null) {
            synchronized (ZYLruCache.class) {
                if (zyLruCache == null) {
                    zyLruCache = new ZYLruCache();
                }
            }
        }
        return zyLruCache;
    }

    public void put(String key, Bitmap bitmap) {
        Bitmap old = cache.put(key, bitmap);
        if (old == null)
            currentSize++;

        balance();
    }

    private void balance() {
         control(MAX_NUMBER);
    }

    public void clear() {
        control(0);
    }

    private synchronized void control(int size) {
        Iterator<Map.Entry<String, Bitmap>> iterator;
        Map.Entry<String, Bitmap> entry;
        while (currentSize > size) {
            iterator = cache.entrySet().iterator();
            entry = iterator.next();
            System.out.println("control:" + entry.getKey() + " - " + entry.getValue());
            currentSize--;
            iterator.remove();
        }
    }

    private Bitmap remove(String key) {
        Bitmap bitmap = cache.remove(key);
        if (bitmap != null) {
            currentSize--;
        }

        return bitmap;
    }

    public Bitmap get(String key) {
        System.out.println("currentSize:" + currentSize);
        return cache.get(key);
    }
}
