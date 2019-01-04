package com.zy;

import com.zy.utils.Print;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.LinkedBlockingQueue;

public class SemaphoreDemo {
    private static final Semaphore semaphore = new Semaphore(3);
    private static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    static {
        //设置allowCoreThreadTimeout=true（默认false）时，核心线程会超时关闭
        threadPool.allowCoreThreadTimeOut(true);
    }

    private static class InformationThread extends Thread {
        private final String name;
        private final int age;

        public InformationThread(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public void run() {
            try {
                Print.P(name+" 申请");
                semaphore.acquire();//申请许可
                System.out.println(Thread.currentThread().getName() + ":大家好，我是\"" + name + "\"我今年" + age + "岁,当前时间为：" + System.currentTimeMillis()+" , 申请到许可证");
                Thread.sleep(1000);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println(name + "要准备释放许可证了，当前时间为：" + System.currentTimeMillis() + ", 当前可使用的许可数为：" + semaphore.availablePermits());
                semaphore.release();//释放许可
                //System.out.println(name + "已经释放许可证了，当前时间为：" + System.currentTimeMillis() + ", 当前可使用的许可数为：" + semaphore.availablePermits());
            }
        }
    }

    public static void main(String[] args) {
        try {
            semaphore.drainPermits();
            //semaphore.acquire(3);
        }finally {
            semaphore.release(3);

        }
        String[] name = {"李明", "王五", "张杰", "王强", "赵二", "李四", "张三"};
        int[] age = {26, 27, 28, 29, 30, 31, 32};
        for (int i = 0; i < 7; i++) {
            Thread t1 = new InformationThread(name[i], age[i]);
            threadPool.execute(t1);
        }


//        threadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                Print.P("Hello :"+Thread.currentThread());
//            }
//        });
        //Print.P("Main end");
    }

}
