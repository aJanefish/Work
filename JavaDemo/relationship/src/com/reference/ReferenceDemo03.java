package com.reference;

import com.bean.Apple;
import com.utils.P;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 测试ReferenceQueue在对象被回收时的情况
 * 当Reference被回收时,Reference加入到队列ReferenceQueue中
 */
public class ReferenceDemo03 {
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String args[]) {
        ExecutorService ss = Executors.newCachedThreadPool();
        int key = 1;
        switch (key) {
            case 0: {//测试Reference基本用法
                test1();
            }
            break;
            case 1: {//测试ReferenceQueue基本用法
                //test1();
                test2();
            }
            break;
            default:
                break;
        }


    }

    private static void test2() {
        final ReferenceQueue<Apple> referenceQueue = new ReferenceQueue<>();
        //GC对象的产生
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                int tmp = 10;
                Apple apple = null;
                for (int i = 0; i < tmp; i++) {
                    apple = new Apple("apple0" + i);
                    AppleWeakReference weakReference1 = new AppleWeakReference(apple, referenceQueue);
                    apple = null;
                    System.gc();
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                apple = null;
//                P.pln("end...");
//                System.gc();
//                try {
//                    TimeUnit.SECONDS.sleep(2);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });

        //referenceQueue检测
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        P.pln("referenceQueue.remove() ... ");
                        Reference<? extends Apple> reference = referenceQueue.remove();
                        P.pln(Thread.currentThread() + " - " + reference);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }

    private static void test1() {
        WeakReference weakReference = new WeakReference(new Apple("apple1"));


        ReferenceQueue<Apple> referenceQueue = new ReferenceQueue<>();
        AppleWeakReference weakReference1 = new AppleWeakReference(new Apple("apple01"), referenceQueue);
        AppleWeakReference weakReference2 = new AppleWeakReference(new Apple("apple02"), referenceQueue);
        P.pln("weakReference1 - " + weakReference1);
        P.pln("weakReference2 - " + weakReference2);

        System.gc();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        try {
//            P.pln("remove1 ");
//            Reference<? extends Apple> ss = referenceQueue.remove();
//            P.pln(ss);
//            P.pln("remove2 ");
//            Reference<? extends Apple> ss1 = referenceQueue.remove();
//            P.pln(ss1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        Reference<? extends Apple> ss = referenceQueue.poll();
        P.pln(ss);
        Reference<? extends Apple> ss1 = referenceQueue.poll();
        P.pln(ss1);
        Reference<? extends Apple> ss2 = referenceQueue.poll();
        P.pln(ss2);
    }
}
