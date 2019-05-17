package com.test;

import com.utils.P;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 在JMM中,如果一个操作执行的结果需要对另一个操作可见，
 * 那么这两个操作之间必须存在happens-before关系。
 * 1.程序顺序规则：一个线程中的每个操作，happens-before与该线程中的任意后续操作
 * 2.监视器锁规则：对于一个监视器的解锁，happens-before于随后对这个监视器的加锁
 * 3.volatile变量规则：对一个volatile域的写，happens-before于任意后续对于这个volatile域的读
 * 4.传递性：如果A happens-before B，且B happens-before C,那么 A happens-before C
 */
public class VolatileDemo {
    private int id = 0;
    private volatile int idV = 0;
    private int idS = 0;
    private AtomicInteger atomicInteger = new AtomicInteger(0);


    public synchronized void addAtomicInteger() {
        this.atomicInteger.incrementAndGet();
    }

    public synchronized void addIds() {
        this.idS++;
    }


    public void addIdV() {
        this.idV++;
    }


    public void addId() {
        this.id++;
    }

    @Override
    public String toString() {
        return "VolatileDemo{" +
                "id=" + id +
                ", idV=" + idV +
                ", idS=" + idS +
                ", atomicInteger=" + atomicInteger +
                '}' + " - " + hashCode();
    }

    public static void main(String args[]) {


        VolatileDemo volatileDemo = new VolatileDemo();
        volatileDemo.testId("id");
        volatileDemo.testIdV("idv");
        volatileDemo.testIdS("ids");
        volatileDemo.testAtomic("Atomic");

    }


    //測試AtomicInteger
    private void testAtomic(String id) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 500000; i++) {
                    addAtomicInteger();
                }
                P.pln(id + " - " + VolatileDemo.this);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 500000; i++) {
                    addAtomicInteger();
                }
                P.pln(id + " - " + VolatileDemo.this);
            }
        });

        thread1.start();
        thread2.start();


    }


    //測試被synchronized修飾的數據
    private void testIdS(String id) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 500000; i++) {
                    addIds();
                }
                P.pln(id + " - " + VolatileDemo.this);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 500000; i++) {
                    addIds();
                }
                P.pln(id + " - " + VolatileDemo.this);
            }
        });

        thread1.start();
        thread2.start();


    }


    //測試被volatile修飾的數據
    private void testIdV(String id) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 500000; i++) {
                    addIdV();
                }
                P.pln(id + " - " + VolatileDemo.this);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 500000; i++) {
                    addIdV();
                }
                P.pln(id + " - " + VolatileDemo.this);
            }
        });

        thread1.start();
        thread2.start();


    }


    //測試普通的數據
    private void testId(String id) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 500000; i++) {
                    addId();
                }
                P.pln(id + " - " + VolatileDemo.this);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 500000; i++) {
                    addId();
                }
                P.pln(id + " - " + VolatileDemo.this);
            }
        });

        thread1.start();
        thread2.start();


    }

    private void clear() {
        id = 0;
        idV = 0;
        idS = 0;
    }
}
