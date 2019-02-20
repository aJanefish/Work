package com.zy.contentproviderdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyContentProvider extends ContentProvider {


    public static final String AUTOHORITY = "com.zy.content.provider.demo";
    // UriMatcher类使用:在ContentProvider 中注册URI
    private static UriMatcher mMatcher;
    // 设置ContentProvider的唯一标识
    public static final int User_Code = 1;
    public static final int Job_Code = 2;

    static {

    }

    private String TAG = "MyContentProvider";

    @Override
    public boolean onCreate() {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        //地址需要注册才会别识别到
        // 初始化
        mMatcher.addURI(AUTOHORITY,"user", User_Code);
        mMatcher.addURI(AUTOHORITY, "job", Job_Code);
        mMatcher.addURI(AUTOHORITY, "zy/test", Job_Code+1);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d(TAG,"query:"+mMatcher.match(uri) +" projection: "+Arrays.toString(projection)+" selection:"+ selection+" selectionArgs:"+Arrays.toString(selectionArgs)+" sortOrder:"+sortOrder);

        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        //return null;
        Log.d(TAG,"insert:"+mMatcher.match(uri) +" values: "+values);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG,"delete:"+mMatcher.match(uri) +" selection: "+selection+" selectionArgs:"+ Arrays.toString(selectionArgs));

        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG,"update:"+mMatcher.match(uri) +" values: "+values+" selection: "+selection+" selectionArgs:"+ Arrays.toString(selectionArgs));

        return 0;
    }
}
