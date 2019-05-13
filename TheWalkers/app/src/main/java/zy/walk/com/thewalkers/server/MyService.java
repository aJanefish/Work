package zy.walk.com.thewalkers.server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    private static final String TAG = "MyService";

    public MyService() {

    }


    public class MyBinder extends Binder {
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void show() {
            showLog("MyBinder:" + name);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return new MyBinder();
    }

    private void showLog(String values) {
        Log.d(TAG, Thread.currentThread() + " - " + values);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        showLog("onCreate");
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        showLog("onLowMemory");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        showLog("onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        showLog("onUnbind");
        return super.onUnbind(intent);

    }


    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        showLog("onRebind");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showLog("onStartCommand");
        new Thread(new Runnable() {
            public void run() {
                //处理具体的逻辑
                for (int index = 0; index < 10; index++) {
                    showLog("Job:"+index);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                stopSelf();  //服务执行完毕后自动停止
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onTaskRemoved(Intent rootIntent) {
        showLog("onTaskRemoved");
        super.onTaskRemoved(rootIntent);
    }


}
