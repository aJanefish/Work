package com.test;

import com.utils.P;

import java.util.concurrent.TimeUnit;
/**
 * 中断两种情况
 * 一种是当线程处于阻塞状态或者试图执行一个阻塞操作时，我们可以使用实例方法interrupt()进行线程中断，
 * 执行中断操作后将会抛出interruptException异常(该异常必须捕捉无法向外抛出)并将中断状态复位
 *
 * 另外一种是当线程处于运行状态时，我们也可调用实例方法interrupt()进行线程中断，
 * 但同时必须手动判断中断状态，并编写中断线程的代码(其实就是结束run方法体的代码)
 *
 * */
public class Test5 {
    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while(true){
                    //判断当前线程是否被中断
                    if (this.isInterrupted()){
                        //isInterrupted判断线程是否已被中断，如果被中断将跳出循环以此结束线程,
                        //注意非阻塞状态调用interrupt()并不会导致中断状态重置
                        P.pln("线程中断");
                        break;
                    }
                }

                P.pln("已跳出循环,线程中断!");
            }
        };
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //中断处于阻塞状态的线程
        t1.interrupt();

        //t1.stop();
        //P.P("run:" + t1.isInterrupted());


    }

}
