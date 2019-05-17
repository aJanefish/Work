package com.test;

import com.utils.P;

import java.util.concurrent.TimeUnit;

public class Test06 {
    //有时我们在编码时可能需要兼顾以上两种中断情况
    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    //判断当前线程是否已中断,注意interrupted方法是静态的,执行后会对中断状态进行复位
                    while (!Thread.interrupted()) {
                        P.P("run:"+isInterrupted());
                        TimeUnit.SECONDS.sleep(2);//堵塞状态
                        long start = System.currentTimeMillis();
                        P.P("start for");
                        for(int i = 0;i < 1000000000;i++){
                            int x = i;
                        }
                        P.P(System.currentTimeMillis() -start);
                    }
                    P.P("run end:"+isInterrupted());
                } catch (InterruptedException e) {
                    P.P("InterruptedException:"+e);
                }finally {
                    P.P("finally:"+isInterrupted());
                }
            }
        };
        t1.start();
        try {
            TimeUnit.MILLISECONDS.sleep(2005);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //中断处于阻塞状态的线程
        t1.interrupt();

        //t1.stop();
        //P.P("run:" + t1.isInterrupted());


    }

}
