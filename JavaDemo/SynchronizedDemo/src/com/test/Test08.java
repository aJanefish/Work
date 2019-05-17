package com.test;

import com.utils.P;

import java.util.concurrent.atomic.AtomicInteger;

public class Test08 {
    private volatile  int id = 0;
    public  static void main(String[] args) {
        test1();
    }

    private static void test1() {
        AtomicInteger atomicInteger = new AtomicInteger(1);

        final int[] num = {0};
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                //super.run();
                int tmp = 10000;
                for (int i = 0; i < tmp; i++) {
                    atomicInteger.incrementAndGet();
                    num[0]++;
                }
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                //super.run();
                int tmp = 10000;
                for (int i = 0; i < tmp; i++) {
                    atomicInteger.incrementAndGet();
                    num[0]++;
                }
            }
        };
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        P.P(atomicInteger + " , " + num[0]);

    }
}
