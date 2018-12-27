package com.test;

public class TestMain {



    public static void test4(){
        Test2 test2 = new Test2();

        Thread t1=new Thread(new Test3());
        Thread t2=new Thread(new Test3());
        t1.start();
        t2.start();
        join(t1);
        join(t2);

        System.out.println(Test3.i);
    }

    public static void test3(){
        Test2 test2 = new Test2();

        Thread t1=new Thread(new Test2());
        Thread t2=new Thread(new Test2());
        t1.start();
        t2.start();
        join(t1);
        join(t2);
        System.out.println(Test2.i);
    }


    public static void test2(){
        Thread t1=new Thread(new Test1());
        Thread t2=new Thread(new Test1());
        t1.start();
        t2.start();
        join(t1);
        join(t2);
        System.out.println(Test1.i);
    }


    public static void test1(){
        Test1 instance=new Test1();
        Thread t1=new Thread(instance);
        Thread t2=new Thread(instance);
        t1.start();
        t2.start();
        join(t1);
        join(t2);

        System.out.println(Test1.i);

    }

    private static void join(Thread thread){
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
