package com.test;


import com.utils.P;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示ReentrantLock上阻塞的任务是可以被中断的
 *
 * @author Administrator
 */
public class BlockedMutex {
    private Lock lock = new ReentrantLock();

    public BlockedMutex() {
        //获取当前对象锁
        try {
            lock.lockInterruptibly();
            P.pln("geted lock " + Thread.currentThread());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("获取到对象锁");
        }
    }

    public void f() {
        try {
            //获取挡墙对象锁
            //这种获取锁的方式在等待获取对象锁 被阻塞的时候是可以被中断的
            System.out.println("获取对象锁 在f()......" + Thread.currentThread());
            lock.lockInterruptibly();
            System.out.println("获取对象锁 在f()方法中");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("获取对象锁 在f() InterruptedException");
        } finally {
            System.out.println("获取对象锁 在f() finally");
        }
    }

}


class Blocked2 implements Runnable {
    BlockedMutex blocked = new BlockedMutex();

    public Blocked2() {
        P.pln("Blocked2:" + Thread.currentThread());
    }

    @Override
    public void run() {
        System.out.println("等待调用blocked的f方法- " + Thread.currentThread());
        blocked.f();
        System.out.println("呼叫中断");
    }

}
