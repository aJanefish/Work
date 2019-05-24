package com.test;

import com.diy.DiyReentrantLock;

public class DiyReentrantLockTest {

    public static void main(String args[]) {

        //testLock();

        testNote();

    }

    private static void testNote() {
        DiyReentrantLock diyReentrantLock = new DiyReentrantLock(true);
        diyReentrantLock.addShare();
        diyReentrantLock.addExcluSive();

        diyReentrantLock.show();
    }

    private static void testLock() {
        DiyReentrantLock diyReentrantLock = new DiyReentrantLock(true);
        diyReentrantLock.lock();
        diyReentrantLock.lock();
        new Thread(() -> {
            diyReentrantLock.lock();
            diyReentrantLock.lock();
        }).start();
    }
}
