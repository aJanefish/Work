package com.test;

import com.utils.P;

import java.util.concurrent.TimeUnit;

/**
 * 互斥阻塞可以  中断
 * lockInterruptibly()
 * */
public class Interrupting2 {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Blocked2());
        t.start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Issuing t.interrupt()");
        t.interrupt();

        //test1();
    }


    public static void test1(){
        if(true) {
            throw new NullPointerException("NULL is  Empty");
        }

        P.pln("test1");
    }
}
