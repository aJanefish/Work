package com.stream;

import com.utils.P;

import java.util.concurrent.TimeUnit;

// 子线程
public class Child extends Thread {
    @Override
    public void run() {
        // ...
        P.pln("child start");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        P.pln("child end");
    }
}
