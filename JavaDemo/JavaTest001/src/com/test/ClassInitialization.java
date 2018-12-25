package com.test;

import java.util.Random;
/**
 * 获取Class对象引用的方式3种，
 * 通过继承自Object类的getClass方法，
 * Class类的静态方法forName以及
 * 字面常量的方式”.class”
 *
 * 其中实例类的getClass方法和Class类的静态方法forName都将会触发类的初始化阶段，
 * 而字面常量获取Class对象的方式则不会触发初始化。
 *
 * 初始化是类加载的最后一个阶段，也就是说完成这个阶段后类也就加载到内存中(Class对象在加载阶段已被创建)，
 * 此时可以对类进行各种必要的操作了（如new对象，调用静态成员等），
 * 注意在这个阶段，才真正开始执行类中定义的Java程序代码或者字节码
 * */

class Initable {
    //编译期静态常量
    static final int staticFinal = 47;
    //非编期静态常量
    static final int staticFinal2 =
            ClassInitialization.rand.nextInt(1000);
    static {
        System.out.println("Initializing Initable");
    }
}

class Initable2 {
    //静态成员变量
    static int staticNonFinal = 147;
    static {
        System.out.println("Initializing Initable2");
    }
}

class Initable3 {
    //静态成员变量
    static int staticNonFinal = 74;
    static {
        System.out.println("Initializing Initable3");
    }
}

public class ClassInitialization {
    static {
        System.out.println("Initializing ClassInitialization:");
    }
    public static Random rand = new Random(47);

    public static void main(String[] args) throws Exception {
        //字面常量获取方式获取Class对象
        Class initable = Initable.class;
        System.out.println("After creating Initable ref:");
        //不触发类初始化
        System.out.println(Initable.staticFinal);
        //会触发类初始化
        System.out.println(Initable.staticFinal2);
        //会触发类初始化
        System.out.println(Initable2.staticNonFinal);
        //forName方法获取Class对象
        Class initable3 = Class.forName("com.test.Initable3");
        System.out.println("After creating Initable3 ref");
        System.out.println(Initable3.staticNonFinal);
        

//        Initable3 initable3 =  new Initable3();
//        System.out.println(initable3.staticNonFinal);


    }
}

