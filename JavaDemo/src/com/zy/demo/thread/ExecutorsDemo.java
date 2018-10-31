package com.zy.demo.thread;


import javafx.concurrent.Task;
import org.junit.Test;

import java.util.concurrent.*;

public class ExecutorsDemo {

    public static  void main(String[] args){
        System.out.println("Test Start");

        test7();


    }


    public static void test7(){
        //newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
        ScheduledExecutorService ss = Executors.newScheduledThreadPool(5);
        long start = System.currentTimeMillis();
        System.out.println(start);
        for (int i = 0; i < 10; i++) {
           ss.schedule(new MyRunnable(i),10, TimeUnit.SECONDS);
        }


        System.out.println(System.currentTimeMillis()-start);
    }



    public static void test6(){
        //newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
        ExecutorService ss = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            ss.execute(new MyRunnable(i));
        }


        System.out.println(10);
    }




    public static void test5(){
        //newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，
        //保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
        //
        ExecutorService ss = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            ss.execute(new MyRunnable(i));
        }





        System.out.println(10);
    }


    @Test
    public void test4(){
        //newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
        //创建一个可缓存线程池，应用中存在的线程数可以无限大
        Runnable runnable = new Runnable() {
            int t = 1 ;
            @Override
            public void run() {
                System.out.println(this+","+System.currentTimeMillis());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //
        ExecutorService ss = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            ss.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(this+","+System.currentTimeMillis());
                }
            });
        }



        ss.execute(runnable);
        ss.execute(runnable);


        System.out.println(10);
    }



    @Test
    public void test3(){
        Runnable runnable = new Runnable() {
            int t = 1 ;
            @Override
            public void run() {
                System.out.println(this+","+System.currentTimeMillis());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //
        ExecutorService ss = Executors.newCachedThreadPool();
        ss.execute(runnable);
        ss.execute(runnable);
        ss.execute(runnable);


        System.out.println(10);
    }


    @Test
    public void test2(){

        //
        Callable<Object> ss = Executors.callable(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i);
                }
            }
        },"Sss");

        try {

            System.out.println(ss.call());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(10);
    }

    @Test
    public void test1(){
        Callable<Object> ss = Executors.callable(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i);
                }
            }
        });

        try {
            ss.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(10);
    }

}
