package demo.okhttp.zy.com.okhttpdemo.application;

import android.app.Application;

import demo.okhttp.zy.com.okhttpdemo.http.MyAsyncHttpServer;
import demo.okhttp.zy.com.okhttpdemo.http.MyServerManager;
import demo.okhttp.zy.com.okhttpdemo.http.MyWsManager;

public class App extends Application {


    private MyServerManager myServerManager;
    private MyAsyncHttpServer myAsyncHttpServer;
    private MyWsManager myWsManager;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化操作
        myServerManager = new MyServerManager(this);
        myServerManager.start();
        myAsyncHttpServer  = new MyAsyncHttpServer();
        myWsManager = new MyWsManager(this);
        myWsManager.start();
    }

    public MyServerManager getMyServerManager() {
        return myServerManager;
    }

    public MyAsyncHttpServer getMyAsyncHttpServer() {
        return myAsyncHttpServer;
    }

    public MyWsManager getMyWsManager() {
        return myWsManager;
    }
}
