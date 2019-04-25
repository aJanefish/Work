package com;

import com.lock.ReentrantLock;
import com.utils.P;
import com.utils.Print;
import sun.dc.pr.PRError;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;


public class LockMain implements Runnable {

    private int id = 0;

    public LockMain(int id) {
        this.id = id;
    }

    //公平锁
    public static ReentrantLock lock=new ReentrantLock(true);
    public static int x = 0;
    //非公平锁
    //public static ReentrantLock lock=new ReentrantLock(false);

    public static void main(String[] ages)  {
        Print.P("Lock main");
        test1();
        test2();
        test3();
        int s = test4();
        P.pln(s);


    }

    private static int test4() {
        try {
            P.pln("test4 return");
            return 1;
        }finally {
            P.pln("test4 finally");
            return 2;
        }
    }

    private static void test3() {
        Print.P("test3 -----------------start");
        LockMain lockMain = new LockMain(1);
        lockMain.park();
        Print.P("test3 -----------------end");
    }

    public void park(){
        Thread thread = Thread.currentThread();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LockSupport.unpark(thread);
            }
        }).start();

        LockSupport.park(this);

    }

    //测试重入锁的数量
    private static void test2() {
        Print.P("test2-------------------------");
        Print.P("1,"+lock.getStatus());
        lock.lock();
        lock.lock();
        try {
            Print.P("2,"+lock.getStatus());
        }finally {
            lock.unlock();

            Print.P("3,"+lock.getStatus());
        }

        lock.lock();
        try {
            Print.P("4,"+lock.getStatus());
        }finally {
            lock.unlock();
            lock.unlock();
            Print.P("5,"+lock.getStatus());
        }

    }

    //测试lock
    private static void test1() {
        Print.P("test1-------------------------");
        Thread thread1 = new Thread(new LockMain(100));
        Thread thread2 = new Thread(new LockMain(101));
        thread1.start();
        thread2.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        Thread thread3 = new Thread(new LockMain(102));
//        thread3.start();
//
//        Thread thread4 = new Thread(new LockMain(103));
//        thread4.start();

        Print.P(x);
    }

    @Override
    public void run() {
        int tmp  = 10000;
        for (int i = 0; i < tmp; i++) {

            //Print.P(id+":等待获取锁");
            lock.lock();

            //Print.P(id+":获取锁");
            try {

                x ++;
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
            } finally {
                //Print.P(id+":释放锁");
                lock.unlock();
            }

        }
    }
}
