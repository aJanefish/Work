package com.thread;


import com.utils.P;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 研究
 */
public class ThreadDemo01 {
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String args[]) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                int tmp = 10;
                for (int i = 0; i < tmp; i++) {
                    try {
                        P.pln("1:" + i);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    } finally {

                    }

                }
            }
        });
        executorService.shutdownNow();


        executorService.execute(new Runnable() {
            @Override
            public void run() {
                int tmp = 10;
                for (int i = 0; i < tmp; i++) {
                    try {
                        P.pln("2：" + i);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    } finally {

                    }

                }
            }
        });
    }
}
