package zy.walk.com.thewalkers;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.IntDef;

import com.hjq.toast.ToastUtils;
import com.squareup.leakcanary.LeakCanary;

public class MyApp extends Application {

    public static final String TAG = "TheWalkersTAG";
    public static int appId = 0;


    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.init(this);
        Log.d(TAG,"MyApp onCreate "+this );
        if (LeakCanary.isInAnalyzerProcess(this)) {//1
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

    }

    //finalize

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.d(TAG,"MyApp finalize "+this );
    }
}
