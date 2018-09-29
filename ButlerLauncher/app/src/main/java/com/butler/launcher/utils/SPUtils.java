package com.butler.launcher.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Map;

public class SPUtils {


    private SharedPreferences sharedPreferences;
    private static SPUtils spUtils;
    private String TAG = "SPUtils";

    private SPUtils() {

    }

    public static SPUtils getInstance() {
        if (spUtils == null) {
            spUtils = new SPUtils();
        }
        return spUtils;
    }

    public void init(Context context) {
        sharedPreferences = context.getSharedPreferences("ButlerLauncher", Context.MODE_PRIVATE);
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean def) {
        return sharedPreferences.getBoolean(key, def);
    }

    public void remove(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    public void setBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public Map<String, ?> getAll() {
        Map<String, ?> map = sharedPreferences.getAll();
        return map;
    }

    public void show() {
        Map<String, ?> map = sharedPreferences.getAll();
        Log.d(TAG, "show:" + map);
    }

}
