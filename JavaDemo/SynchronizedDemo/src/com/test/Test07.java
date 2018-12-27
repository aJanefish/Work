package com.test;

import com.utils.Print;

import java.util.concurrent.TimeUnit;

//中断与synchronized
//事实上线程的中断操作对于正在等待获取的锁对象的synchronized方法或者代码块并不起作用，
// 也就是对于synchronized来说，如果一个线程在等待锁，那么结果只有两种，
// 要么它获得这把锁继续执行，
// 要么它就保存等待，
// 即使调用中断线程的方法，也不会生效
public class Test07 implements Runnable {

    public synchronized void f(int x) {
        System.out.println("Trying to call f(" +
                x+")");
        while(true) // Never releases lock
            Thread.yield();

    }

    /**
     * 在构造器中创建新线程并启动获取对象锁
     */
    public Test07() {
        //该线程已持有当前实例锁
        new Thread() {
            public void run() {
                Print.P("f 1 starting:"+this);
                f(1); // Lock acquired by this thread
            }
        }.start();
    }

    @Override
    public void run() {
        //中断判断
        while (true) {
            if (Thread.interrupted()) {
                System.out.println("中断线程!!");
                break;
            } else {
                Print.P("f 2 starting:"+this);//锁 被占用 不能使用
                f(2);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Test07 sync = new Test07();
        Thread t = new Thread(sync);
        //启动后调用f()方法,无法获取当前实例锁处于等待状态
        t.start();
        TimeUnit.SECONDS.sleep(1);
        //中断线程,无法生效
        //t.interrupt();
        t.stop();
    }

}
