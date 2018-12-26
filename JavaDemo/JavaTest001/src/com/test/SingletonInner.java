package com.test;

import com.test.utils.Print;

/**
 * 静态内部类
 */
public class SingletonInner {
    static {
        Print.println("static SingletonInner");
    }
    private static class Holder {
        private static SingletonInner singleton = new SingletonInner();
    }

    public static final int Type = 0;
    public static int TypeE = 1;

    private SingletonInner(){}

    public static SingletonInner getSingleton(){
        return Holder.singleton;
    }
}