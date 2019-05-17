package com.test;

import com.utils.P;

//synchronized同步代码块
//除了使用关键字修饰实例方法和静态方法外，还可以使用同步代码块，
// 在某些情况下，我们编写的方法体可能比较大，同时存在一些比较耗时的操作，而需要同步的代码又只有一小部分，
// 如果直接对整个方法进行同步操作，可能会得不偿失，这样就无需对整个方法进行同步操作了
public class Test3 implements Runnable {

    static String name = "zhangyu";


    static int i = 0;

    @Override
    public void run() {
        //省略其他耗时操作....

        //使用同步代码块对变量i进行同步操作,锁对象为instance || name
        P.P(this + " -- start");
        synchronized (name) {
            for (int j = 0; j < 1000000; j++) {
                i++;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        P.P(this + " -- end");
    }
}
