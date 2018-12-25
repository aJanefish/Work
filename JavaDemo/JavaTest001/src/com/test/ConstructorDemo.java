package com.test;

import com.test.bean.User;
import com.test.utils.PrintUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class ConstructorDemo {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

    public static void test4(){

        Class<?> clazz = null;

        //获取Class对象的引用
//      clazz = Class.forName("reflect.User");
        clazz = User.class;


        ClassLoader classLoader = clazz.getClassLoader();
        println(classLoader);
        new PrintUtils<AnnotatedType>().show(clazz.getAnnotatedInterfaces());

        new PrintUtils<Field>().show(clazz.getDeclaredFields());
        new PrintUtils<Annotation>().show(clazz.getDeclaredAnnotations());
        new PrintUtils<Method>().show(clazz.getDeclaredMethods());
        new PrintUtils<Method>().show(clazz.getMethods());
        System.out.println("--------------------------------------------");
    }


    public static void test3(){

        Class<?> clazz = null;

        //获取Class对象的引用
//      clazz = Class.forName("reflect.User");
        clazz = User.class;

        //获取所有构造包含private
        Constructor<?> cons[] = clazz.getDeclaredConstructors();
        int length = cons.length;
        for (int i = 0; i < length; i++) {

            Constructor<?> con = cons[i];
            println("构造函数["+i+"]:"+cons[i].toString()+" , "+ con.getName());
            //获取构造函数参数类型
            System.out.print("参数类型["+i+"]:(");
            Class<?> clazzs[] = con.getParameterTypes();
            new PrintUtils<Class>().show(clazzs);
            System.out.println(")");
        }


        System.out.println("--------------------------------------------");
    }

    public static void test2(){

        Class<?> clazz = null;

        //获取Class对象的引用
//        clazz = Class.forName("reflect.User");
        clazz = User.class;

        //取得指定带int和String参数构造函数,该方法是私有构造private
        try {
            Constructor cs2=clazz.getDeclaredConstructor(int.class,String.class);
            //由于是private必须设置可访问
            cs2.setAccessible(true);
            //创建user对象
            User user2= (User) cs2.newInstance(25,"lidakang");
            System.out.println("user2:"+user2);

            Constructor cs3=clazz.getDeclaredConstructor(String.class);
            User user3 = (User) cs3.newInstance("zhangyu");
            System.out.println("user3:"+user3);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("--------------------------------------------");
    }



    public static void test1(){

        Class<?> clazz = null;

        //获取Class对象的引用
//        clazz = Class.forName("reflect.User");
        clazz = User.class;

        //第一种方法，实例化默认构造方法，User必须无参构造函数,否则将抛异常
        try {
            User user = (User) clazz.newInstance();
            System.out.println(user);
            user.setAge(20);
            user.setName("Rollen");
            System.out.println(user);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("--------------------------------------------");
    }


    public static void println(Object object){
        System.out.println(object);
    }


}
