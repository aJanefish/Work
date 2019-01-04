package com.zy;

import com.zy.utils.Print;

import java.util.concurrent.Semaphore;

/**
 * Semaphore主要方法：
 *
 * Semaphore(int permits):构造方法，创建具有给定许可数的计数信号量并设置为非公平信号量。
 *
 * Semaphore(int permits,boolean fair):构造方法，当fair等于true时，创建具有给定许可数的计数信号量并设置为公平信号量。
 *
 * void acquire():从此信号量获取一个许可前线程将一直阻塞。相当于一辆车占了一个车位。
 *
 * void acquire(int n):从此信号量获取给定数目许可，在提供这些许可前一直将线程阻塞。比如n=2，就相当于一辆车占了两个车位。
 *
 * void release():释放一个许可，将其返回给信号量。就如同车开走返回一个车位。
 *
 * void release(int n):释放n个许可。
 *
 * int availablePermits()：当前可用的许可数。
 * */

//SemaPhore 基本使用
public class SemaPhoreDemo001 {

    private final Semaphore semaphore;

    public SemaPhoreDemo001(int permits) {
        //总许可数为3
        semaphore = new Semaphore(permits);
        Print.P(Thread.currentThread());
    }

    public void acquire(){
        try {
            Print.P("当前可用许可数 1："+semaphore.availablePermits());
            semaphore.acquire();
            Print.P("当前可用许可数 2："+semaphore.availablePermits());
            if(semaphore.availablePermits() < 3){
                Print.P("需要申请的许可数为3  大于当前 可用许可数：释放掉一定数量的许可数");
                semaphore.release();
                semaphore.acquire(3);
            }else {
                semaphore.acquire(3);
            }

            Print.P("当前可用许可数 3："+semaphore.availablePermits());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {

            semaphore.release();
            semaphore.release();
            semaphore.release();
            Print.P("acquire - finally --- 当前可用许可数 4:"+semaphore.availablePermits());
        }
    }
}
