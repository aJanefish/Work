package zy.walk.com.thewalkers;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.hjq.toast.ToastUtils;

public class App extends Application {

    public static final String TAG = "TheWalkersTAG";
    public static int appId = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.init(this);
        Log.d(TAG,"App onCreate "+this );

    }

    //finalize

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.d(TAG,"App finalize "+this );
    }
}
