package com.stream;

import com.utils.P;

// 父线程
public class Parent extends Thread {
    @Override
    public void run() {
        P.pln("Parent start");
        Child child = new Child();
        child.start();
        try {
            P.pln("parent join start");
            child.join();
            P.pln("parent join end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            P.pln("Parent end");
        }
        // ...
    }
}