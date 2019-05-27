package com;

import com.utils.P;

import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized的三种应用方式
 * synchronized关键字最主要有以下3种应用方式，下面分别介绍
 * 修饰实例方法，作用于当前实例加锁，进入同步代码前要获得当前实例的锁
 * 修饰静态方法，作用于当前类对象加锁，进入同步代码前要获得当前类对象的锁
 * 修饰代码块，指定加锁对象，对给定对象加锁，进入同步代码库前要获得给定对象的锁。
 */

public class Main {

    public static void main(String[] ages) {
        P.pln("synchronized ---------------- main");
        P.pln(ClassLoader.getSystemClassLoader());

        test();
    }

    private static void test() {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();

        P.pln("1 "+Thread.currentThread()+" , geted Lock");
        reentrantLock.lock();
        P.pln("2 "+Thread.currentThread()+" , geted Lock");

    }


}


