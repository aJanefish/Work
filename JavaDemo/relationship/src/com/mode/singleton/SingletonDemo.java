package com.mode.singleton;

import com.utils.P;

public class SingletonDemo {
    static {
        P.pln("SingletonDemo static ...");
    }

    {
        P.pln("SingletonDemo {} ...");

    }

    private SingletonDemo() {
        P.pln("SingletonDemo create...");
    }

    private static SingletonDemo singletonDemo = new SingletonDemo();

    public static SingletonDemo getInstance(){
        return singletonDemo;
    }
}
