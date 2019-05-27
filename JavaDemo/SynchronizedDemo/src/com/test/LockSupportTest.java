package com.test;


import com.utils.P;

import java.util.concurrent.locks.LockSupport;

/**
 * 程序挂起测试
 */
public class LockSupportTest {

    public static void main(String args[]) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                P.pln("park");
                boolean is = getIs(Thread.currentThread());
                P.pln("un park = " + is);
            }
        });
        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LockSupport.unpark(thread);
    }

    public static boolean getIs(Thread thread) {
        LockSupport.park(thread);
        return Thread.interrupted();
    }
}
