package demo.okhttp.zy.com.okhttpdemo.log;

import android.util.Log;

import demo.okhttp.zy.com.okhttpdemo.Constant;

public class MyLog {

    public static void d(String tag,String values){
        if(Constant.DEBUG){
            Log.d(tag,values);
        }
    }

    public static void e(String tag,String values){
        if(Constant.DEBUG){
            Log.e(tag,values);
        }
    }

    public static void i(String tag,String values){
        if(Constant.DEBUG){
            Log.i(tag,values);
        }
    }


    public static void v(String tag,String values){
        if(Constant.DEBUG){
            Log.v(tag,values);
        }
    }

    public static void w(String tag,String values){
        if(Constant.DEBUG){
            Log.w(tag,values);
        }
    }
}
