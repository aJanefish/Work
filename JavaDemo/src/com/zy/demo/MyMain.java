package com.zy.demo;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyMain {

    private static Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    };

    private static ThreadPoolExecutor executor1 = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    private static ThreadPoolExecutor executor2 = new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
    private static ThreadPoolExecutor executor3 = new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    //核心线程数是3，最大线程数是4，队列是LinkedBlockingDeque
    private static ThreadPoolExecutor executor4 = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(2));
    // 将队列大小设置为1
    private static ThreadPoolExecutor executor5 = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(1));
    //核心线程数是3 ，最大线程数是4，队列是SynchronousQueue
    private static ThreadPoolExecutor executor6 = new ThreadPoolExecutor(3, Integer.MAX_VALUE, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

    public static  void main(String[] args){
        System.out.println();

        //executor(executor1);
        //executor(executor2);
        //executor(executor3);
        //executor(executor4);
        //executor(executor5);
        executor(executor6);


    }

    private static void executor(ThreadPoolExecutor executor ){
        executor.execute(myRunnable);
        executor.execute(myRunnable);
        executor.execute(myRunnable);
        System.out.println("---先开三个---");
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池数" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());
        executor.execute(myRunnable);
        executor.execute(myRunnable);
        executor.execute(myRunnable);
        System.out.println("---再开三个---");
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池数" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());
        //executor.execute(myRunnable);
//        executor.execute(myRunnable);
//        executor.execute(myRunnable);
//        executor.execute(myRunnable);
//        executor.execute(myRunnable);
//
//        System.out.println("---再开四个---");
//        System.out.println("核心线程数" + executor.getCorePoolSize());
//        System.out.println("线程池数" + executor.getPoolSize());
//        System.out.println("队列任务数" + executor.getQueue().size());
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----8秒之后----");
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池数" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());


    }


}
