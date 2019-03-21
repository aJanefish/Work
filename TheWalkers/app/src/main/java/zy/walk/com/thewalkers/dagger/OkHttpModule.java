package zy.walk.com.thewalkers.dagger;


import android.provider.SyncStateContract;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import okhttp3.OkHttpClient;
import zy.walk.com.thewalkers.utils.Constant;


@Module
public class OkHttpModule {


    public OkHttpModule() {
    }

    @Provides
    //@Singleton
    OkHttpClient provideOkHttpClient() {
        //Log.d(Constant.LOG,""+Log.getStackTraceString(new Throwable()));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.MILLISECONDS)
                .connectTimeout(5, TimeUnit.MILLISECONDS)
                .build();
        return okHttpClient;
    }


}