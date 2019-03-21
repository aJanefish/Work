package demo.okhttp.zy.com.okhttpdemo;

import java.util.ArrayList;
import java.util.List;

import demo.okhttp.zy.com.okhttpdemo.event.MainEvent;

public class Constant {

    public static final boolean DEBUG = true;

    public static final List<MainEvent> getMainEvent(){

        List<MainEvent> list = new ArrayList<>();
        list.add(createMainEvent("DIY","用来做一些小测试之类页面","demo.okhttp.zy.com.okhttpdemo","demo.okhttp.zy.com.okhttpdemo.activity.DiyHttpActivity"));
        list.add(createMainEvent("Face++ ","HttpDemo","demo.okhttp.zy.com.okhttpdemo","demo.okhttp.zy.com.okhttpdemo.activity.FaceDemoActivity"));
        list.add(createMainEvent("OkHttpDemo","OkHttpDemo","demo.okhttp.zy.com.okhttpdemo","demo.okhttp.zy.com.okhttpdemo.activity.OkHttpDemoActivity"));
        list.add(createMainEvent("Socket","demo","demo.okhttp.zy.com.okhttpdemo","demo.okhttp.zy.com.okhttpdemo.activity.SocketDemoActivity"));
        list.add(createMainEvent("Retrofit","Retrofit","demo.okhttp.zy.com.okhttpdemo","demo.okhttp.zy.com.okhttpdemo.activity.RetrofitActivity"));



        list.add(createMainEvent("废柴","行走","zy.walk.com.thewalkers","zy.walk.com.thewalkers.MainActivity1"));
        list.add(createMainEvent("废柴","行走","zy.walk.com.thewalkers","zy.walk.com.thewalkers.MainActivity1"));

        return list;
    }


    private static MainEvent createMainEvent(String title, String content, String packName, String className){
        return new MainEvent.Builder()
                .title(title)
                .content(content)
                .className(className)
                .packageName(packName)
                .bulde();
    }
}
