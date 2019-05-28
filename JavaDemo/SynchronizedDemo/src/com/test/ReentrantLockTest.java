package com.test;

import com.utils.P;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 测试
 */
public class ReentrantLockTest {

    enum ReentrantLockTestType {
        LOCK, lockInterruptibly, tryLock, tryLock1, newCondition
    }

    public static void main(String args[]) {
        ReentrantLockTestType reentrantLockTestType = ReentrantLockTestType.newCondition;
        switch (reentrantLockTestType) {
            case LOCK:
                lockTest();
                break;
            case lockInterruptibly:
                lockInterruptiblyTest();
                break;
            case tryLock:
                tryLockTest();
                break;
            case tryLock1:
                tryLock1Test();
                break;
            case newCondition:
                newConditionTest();
                break;
        }


    }

    /**
     * 返回绑定到此Lock实例的新Condition实例
     */
    private static void newConditionTest() {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        Condition condition1 = reentrantLock.newCondition();

        P.pln(condition);
        P.pln(condition1);

    }

    /**
     * tryLock(long timeout, TimeUnit unit)
     * 如果在给定的等待时间内空闲并且当前线程未被中断，则获取锁
     */
    private static void tryLock1Test() {
        ReentrantLock reentrantLock = new ReentrantLock();
        boolean ooo = false;
        try {
            P.pln("tryLockTest - start...");
            ooo = reentrantLock.tryLock(1, TimeUnit.SECONDS);
            P.pln("tryLockTest - end =" + ooo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    P.pln("tryLockTest -1 start...");

                    boolean ooo1 = reentrantLock.tryLock(2, TimeUnit.SECONDS);
                    P.pln("tryLockTest -2 end =" + ooo1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }

    /**
     * 测试tryLock - 只有在调用时它是空闲的才能获取锁
     */
    private static void tryLockTest() {
        ReentrantLock reentrantLock = new ReentrantLock();
        boolean ooo = reentrantLock.tryLock();
        boolean ooo1 = reentrantLock.tryLock();
        P.pln("tryLockTest - end =" + ooo + "-" + ooo1);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean ooo2 = reentrantLock.tryLock();
                P.pln("tryLockTest - end =" + ooo2);
            }
        });
        thread.start();

    }

    /**
     * 测试lockInterruptibly - 除非当前线程被中断，否则获取锁定
     * lockInterruptibly可被中断 - 失败后不会被添加到队列中
     */
    private static void lockInterruptiblyTest() {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    P.pln("lockInterruptibly...");
                    reentrantLock.lockInterruptibly();
                    P.pln("lockInterruptibly ed");

                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                } finally {
                    P.pln("finally");
//                    P.pln("unlock");
//                    reentrantLock.unlock();
                    return;
                }
            }
        });
        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();

    }

    /**
     * Lock 使用测试
     * Lock 获取锁
     * unlock 释放锁
     */
    private static void lockTest() {
        ReentrantLock reentrantLock = new ReentrantLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    P.pln("" + Thread.currentThread() + "get lock...");
                    reentrantLock.lock();
                    P.pln("" + Thread.currentThread() + "geted lock");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                    P.pln("" + Thread.currentThread() + "unlock");
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    P.pln("" + Thread.currentThread() + "get lock...");
                    reentrantLock.lock();
                    P.pln("" + Thread.currentThread() + "geted lock");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                    P.pln("" + Thread.currentThread() + "unlock");
                }

            }
        }).start();
    }
}
