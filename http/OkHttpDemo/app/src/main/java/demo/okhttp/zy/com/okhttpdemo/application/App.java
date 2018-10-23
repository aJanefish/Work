package demo.okhttp.zy.com.okhttpdemo.application;

import android.app.Application;

import demo.okhttp.zy.com.okhttpdemo.http.MyServerManager;

public class App extends Application {


    private MyServerManager myServerManager;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化操作
        myServerManager = new MyServerManager(this);
        myServerManager.start();
    }

    public MyServerManager getMyServerManager() {
        return myServerManager;
    }


}
