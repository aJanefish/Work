package com.test;

import com.test.utils.Print;

public class ClassDemo {

    public  static void main(String[] args){
        test1();
    }

    //静态内部类的加载时机
    private static void test1() {
        Class singletonInnerClass = SingletonInner.class;
//        try {
//            Class.forName("com.test.SingletonInner");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        //Print.println(SingletonInner.Type);
        SingletonInner.getSingleton();
    }


}
    /**
     *    修饰符、父类、实现的接口、注解相关
     */

//    //获取修饰符，返回值可通过Modifier类进行解读
//    public native int getModifiers();
//    //获取父类，如果为Object，父类为null
//    public native Class<? super T> getSuperclass();
//    //对于类，为自己声明实现的所有接口，对于接口，为直接扩展的接口，不包括通过父类间接继承来的
//    public native Class<?>[] getInterfaces();
//    //自己声明的注解
//    public Annotation[] getDeclaredAnnotations();
//    //所有的注解，包括继承得到的
//    public Annotation[] getAnnotations();
//    //获取或检查指定类型的注解，包括继承得到的
//    public <A extends Annotation> A getAnnotation(Class<A> annotationClass);
//    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass);

    /**
     *   内部类相关
     */
//    //获取所有的public的内部类和接口，包括从父类继承得到的
//    public Class<?>[] getClasses();
//    //获取自己声明的所有的内部类和接口
//    public Class<?>[] getDeclaredClasses();
//    //如果当前Class为内部类，获取声明该类的最外部的Class对象
//    public Class<?> getDeclaringClass();
//    //如果当前Class为内部类，获取直接包含该类的类
//    public Class<?> getEnclosingClass();
//    //如果当前Class为本地类或匿名内部类，返回包含它的方法
//    public Method getEnclosingMethod();

    /**
     *    Class对象类型判断相关
     */
//    //是否是数组
//    public native boolean isArray();
//    //是否是基本类型
//    public native boolean isPrimitive();
//    //是否是接口
//    public native boolean isInterface();
//    //是否是枚举
//    public boolean isEnum();
//    //是否是注解
//    public boolean isAnnotation();
//    //是否是匿名内部类
//    public boolean isAnonymousClass();
//    //是否是成员类
//    public boolean isMemberClass();
//    //是否是本地类
//    public boolean isLocalClass();
