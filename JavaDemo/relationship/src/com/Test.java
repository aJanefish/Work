package com;

import com.utils.P;

import java.util.concurrent.TimeUnit;

class Outer {

    Object obj;

    public void outerMethod() {

        //局部变量
        int x = 5;
        //定义在方法中的内部类称为局部内部类
        class Inner {

            @Override
            public String toString() {
                P.pln(x);
                return "Inner{"+x+"}";
            }

            @Override
            protected void finalize() throws Throwable {
                super.finalize();
                P.pln("finalize");
            }
        }
        //创建内部类实例
        Inner in = new Inner();
        in.toString();

        //将内部类实例的引用赋值给obj
        //obj = in;
    }
}

public class Test {

    public static void main(String[] args) throws InterruptedException {
        Outer out = new Outer();
        out.outerMethod();
        System.gc();

        TimeUnit.SECONDS.sleep(2);

        P.pln(out.obj);

        StringBuffer stringBuffer = new StringBuffer();


    }
}

interface ssfas{
    default void show(){

    }
    int lenght();
}

