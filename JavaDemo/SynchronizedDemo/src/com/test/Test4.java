package com.test;

import com.utils.Print;

import java.util.concurrent.TimeUnit;

public class Test4 {
    public static void main(String[] args)   {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                //while在try中，通过异常中断就可以退出run循环
                try {
                    while (true) {
                        //当前线程处于阻塞状态，异常必须捕捉处理，无法往外抛出
                        Print.P("run:"+this.isInterrupted());
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Interrupted When Sleep:"+e);
                    boolean interrupt = this.isInterrupted();
                    //中断状态被复位
                    System.out.println("interrupt:"+interrupt);
                }catch (Exception e){
                    System.out.println("Exception:"+e);
                }finally {
                    boolean interrupt = this.isInterrupted();
                    //中断状态被复位
                    System.out.println("interrupt:"+interrupt);
                }
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
        Print.P("run:"+t1.isInterrupted());

        /**
         * 输出结果:
         Interruted When Sleep
         interrupt:false
         */
    }

}
