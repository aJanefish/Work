package com;

import com.utils.Print;

import java.util.concurrent.atomic.AtomicInteger;

//AtomicInteger主要是针对int类型的数据执行原子操作，它提供了原子自增方法、原子自减方法以及原子赋值方法等
public class AtomicIntegerDemo {

    //创建AtomicInteger,用于自增操作
    static AtomicInteger i=new AtomicInteger();

    public static class AddThread implements Runnable{
        public void run(){
            for(int k=0;k<100000;k++)
                i.incrementAndGet();
        }
    }

    public static void main(String[] args){
        
        test1();
    }

    private static void test1() {
        long start = System.currentTimeMillis();
        Thread[] ts=new Thread[10];
        //开启10条线程同时执行i的自增操作
        for(int k=0;k<10;k++){
            ts[k]=new Thread(new AddThread());
        }
        //启动线程
        for(int k=0;k<10;k++){ts[k].start();}

        for(int k=0;k<10;k++){
            try {
                ts[k].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(i +" , "+(System.currentTimeMillis() - start));//输出结果:100000

    }
}
