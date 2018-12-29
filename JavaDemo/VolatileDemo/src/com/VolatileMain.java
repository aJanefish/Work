package com;

import com.utils.Print;

public class VolatileMain {

    private static int numTimes = 0;
    public static void main(String[] ages) {


        //test2();
        test3();
    }

    private static void test3() {
        new Thread() {
            @Override
            public void run() {

                //Print.P(i+"--start--"+mixedOrder);
                Thread thread1 = new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        DoubleCheckLock doubleCheckLock = DoubleCheckLock.getInstance(1);
                        Print.P("1:"+doubleCheckLock);
                    }
                };
                Thread thread2 = new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        DoubleCheckLock doubleCheckLock = DoubleCheckLock.getInstance(2);
                        Print.P("2:"+doubleCheckLock);
                    }
                };

                //同时运行
                thread1.start();
                thread2.start();

                try {
                    thread1.join();
                    thread2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private static void test2() {
        int tmp = 1000;
        for (int i = 0; i < tmp; i++) {
            test1(i);
        }
    }


    //
    private static void test1(int num) {

        new Thread() {
            @Override
            public void run() {
                MixedOrder mixedOrder = new MixedOrder();
                //Print.P(i+"--start--"+mixedOrder);
                Thread thread1 = new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        mixedOrder.writer();
                    }
                };
                Thread thread2 = new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        mixedOrder.read();
                    }
                };
                //同时运行
                thread1.start();
                thread2.start();

                try {
                    thread1.join();
                    thread2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (mixedOrder.i == 1) {
                    Print.P(num + "--end--" + mixedOrder + " numTimes:"+numTimes ++);

                }
            }
        }.start();

    }
}
