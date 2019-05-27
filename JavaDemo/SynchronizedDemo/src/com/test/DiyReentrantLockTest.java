package com.test;

import com.diy.DiyReentrantLock;
import com.utils.P;

public class DiyReentrantLockTest {

    public static void main(String args[]) {

        testLock();

        //testNote();

    }


    private static void testLock() {
        DiyReentrantLock diyReentrantLock = new DiyReentrantLock(true);
        diyReentrantLock.lock();
        diyReentrantLock.lock();
        Thread thread = new Thread(() -> {
            diyReentrantLock.lock();
            P.pln(""+Thread.currentThread() +" getted Lock");
        }
        );
        thread.setName("Test-1");
        //thread.setDaemon(true);
        thread.start();

//        Thread thread1 = new Thread(() ->
//                diyReentrantLock.lock()
//        );
//        thread1.setName("Test-2");
//        //thread1.setDaemon(true);
//        thread1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        diyReentrantLock.show();
        diyReentrantLock.unlock();
        diyReentrantLock.unlock();
    }

    private static void testNote() {
        DiyReentrantLock diyReentrantLock = new DiyReentrantLock(true);
        diyReentrantLock.addShare();

        new Thread(new MyRunable(diyReentrantLock)).start();
        new Thread(new MyRunable(diyReentrantLock)).start();
        new Thread(new MyRunable(diyReentrantLock)).start();
        new Thread(new MyRunable(diyReentrantLock)).start();
        new Thread(new MyRunable(diyReentrantLock)).start();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        diyReentrantLock.show();
    }

    static class MyRunable implements Runnable {
        private DiyReentrantLock diyReentrantLock;

        public MyRunable(DiyReentrantLock diyReentrantLock) {
            this.diyReentrantLock = diyReentrantLock;
        }

        @Override
        public void run() {
            diyReentrantLock.addExcluSive();
        }
    }

}
