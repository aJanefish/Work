package com.test;

/**
 * Class  加载时机
 */
class Candy {
    static {
        System.out.println("Loading Candy");
    }
}

class Gum {
    static {
        System.out.println("Loading Gum");
    }
}

class Cookie {
    public Cookie() {
        //System.out.println("create Cookie");
    }

    static {
        System.out.println("Loading Cookie");
    }
}

public class ClassTest001 {

    public static void main(String[] args) {
        //test1();
        test2();
    }


    //字面量的方法获取Class对象的引用不会自动初始化该类
    public static void test2() {
        print("inside main");
        Class class1 = Candy.class;
        Class class2 = Gum.class;
        Class class3 = Cookie.class;

//        boolean.class  = Boolean.TYPE;
//        char.class = Character.TYPE;
//        byte.class = Byte.TYPE;
//        short.class = Short.TYPE;
//        int.class = Integer.TYPE;
//        long.class = Long.TYPE;
//        float.class = Float.TYPE;
//        double.class = Double.TYPE;
//        void.class = Void.TYPE;

//        print(Void.TYPE.getName());
//        print(Double.TYPE);


    }

    //Class 加载到JVM的时机
    public static void test1() {
        print("inside main");
        new Candy();
        print("After creating Candy");
        //字面常量的方式获取Class对象
        try {
            Class gum = Class.forName("com.test.Gum");
            print(gum);
        } catch (ClassNotFoundException e) {
            print("Couldn't find Gum");
        }
        print("After Class.forName(\"com.zejian.Gum\")");
        new Cookie();
        print("After creating Cookie");

    }

    public static void print(Object obj) {
        System.out.println(obj);
    }
}

