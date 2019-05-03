package com.zy.glidecache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class ZYGlide {

    private static final ZYLruCache cache = ZYLruCache.getInstance();

    public static Bitmap getBitmap(String path)  {
        Bitmap bitmap = getBitmapByCache(path);
        if (bitmap == null) {
            bitmap = getBitmapByNet(path);
        }
        return bitmap;
    }

    private static Bitmap getBitmapByNet(String path)  {
        try {
            URL url = new URL(path);
            HttpURLConnection conn = null;
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                saveToCache(path,bitmap);
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void saveToCache(String path, Bitmap bitmap) {
        cache.put(path,bitmap);
    }

    private static Bitmap getBitmapByCache(String path){
       return cache.get(path);
    }

    public static void/**/ save(final String path, final int quality, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = getBitmap(path);
                save(bitmap, Bitmap.CompressFormat.PNG, quality, context);
            }
        }).start();


    }

    private static String save(Bitmap bitmap, Bitmap.CompressFormat format, int quality, Context context) {
        if (!Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        File dir = new File(Environment.getExternalStorageDirectory() +
                "/" + context.getPackageName());
        if (!dir.exists()) {
            dir.mkdir();
        }

        File desFile = new File(dir, UUID.randomUUID().toString() + ".png");
//        if (!desFile.exists()){
//            desFile
//        }
        System.out.println("ZYGlide desFile:" + desFile + " - " + desFile.exists());
        return save(bitmap, format, quality, desFile);
    }


    private static String save(Bitmap bitmap, Bitmap.CompressFormat format, int quality, File desFile) {
        try {
            FileOutputStream out = new FileOutputStream(desFile);
            if (bitmap.compress(format, quality, out)) {
                out.flush();
                out.close();
            }
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
            return desFile.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
