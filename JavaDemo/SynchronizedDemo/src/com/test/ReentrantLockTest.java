package com.test;

import com.utils.P;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    public static void main(String args[]) {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                P.pln("" + Thread.currentThread() + "get lock");
                reentrantLock.lock();
                P.pln("" + Thread.currentThread() + "geted lock");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                P.pln("" + Thread.currentThread() + "get lock");
                reentrantLock.lock();
                P.pln("" + Thread.currentThread() + "geted lock");
            }
        }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        reentrantLock.unlock();
    }
}
