package com;

import com.test.TestMain;
import com.utils.Print;
/**
 * synchronized的三种应用方式
 * synchronized关键字最主要有以下3种应用方式，下面分别介绍
 * 修饰实例方法，作用于当前实例加锁，进入同步代码前要获得当前实例的锁
 * 修饰静态方法，作用于当前类对象加锁，进入同步代码前要获得当前类对象的锁
 * 修饰代码块，指定加锁对象，对给定对象加锁，进入同步代码库前要获得给定对象的锁。
 * */

public class Main {

    public static void main(String[] ages){
        Print.P("synchronized ---------------- main");
        Print.P(ClassLoader.getSystemClassLoader());
        //TestMain.test1();
        //TestMain.test2();
        //TestMain.test3();
        TestMain.test4();
    }
}

////this,当前实例对象锁
//synchronized(this){
//    for(int j=0;j<1000000;j++){
//        i++;
//    }
//}
//
////class对象锁
//synchronized(AccountingSync.class){
//    for(int j=0;j<1000000;j++){
//        i++;
//    }
//}

