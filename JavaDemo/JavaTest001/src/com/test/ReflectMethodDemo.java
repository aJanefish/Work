package com.test;

import com.test.bean.Circle;
import com.test.bean.Student;
import com.test.utils.Print;
import com.test.utils.PrintUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Method	getDeclaredMethod(String name, Class<?>... parameterTypes)
 * 返回一个指定参数的Method对象，该对象反映此 Class 对象所表示的类或接口的指定已声明方法。
 * Method[]	getDeclaredMethod()
 * 返回 Method 对象的一个数组，这些对象反映此 Class 对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。
 * Method	getMethod(String name, Class<?>... parameterTypes)
 * 返回一个 Method 对象，它反映此 Class 对象所表示的类或接口的指定公共成员方法。
 * Method[]	getMethods()
 * 返回一个包含某些 Method 对象的数组，这些对象反映此 Class 对象所表示的类或接口（包括那些由该类或接口声明的以及从超类和超接口继承的那些的类或接口）的公共 member 方法。

 * */
public class ReflectMethodDemo {

    public static void main(String[] args) {
        test1();
        test2();
//        test3();
//        test4();
    }


    //通过Method 调用类的方法

    public static void test2(){
        Class circleClass = Circle.class;
        //根据参数获取public的Method,包含继承自父类的方法
        Method method = null;
        try {
            Circle circle = (Circle) circleClass.newInstance();

            method = circleClass.getMethod("draw",int.class,String.class);
            Print.println("method:"+method);
            //通过Method对象的invoke(Object obj,Object... args)方法调用
            method.invoke(circle,15,"圈圈");
            Print.println(method.getReturnType());
            Method methods = circleClass.getMethod("draw");
            Print.println("methods:"+methods);
            methods.invoke(circle);



            //获取当前类的方法包含private,该方法无法获取继承自父类的method
            Method method1 = circleClass.getDeclaredMethod("drawCircle");
            System.out.println("method1::"+method1);
            method1.setAccessible(true);
            method1.invoke(circle);
            Method method2 = circleClass.getDeclaredMethod("getAllCount");
            System.out.println("method2::"+method2);
            Print.println(method2.invoke(circle));
            Print.println(method2.getReturnType());
            Print.println(method2.toGenericString());
            Print.println(method2.isVarArgs());
            //new PrintUtils<Class>().show(method2.getParameterTypes());


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        Print.println("..................................");
    }





    public static void test1(){
        Class circleClass = Circle.class;
        //获取类的所有方法 不包括父类
        Method[] methods = circleClass.getDeclaredMethods();
        new PrintUtils<Method>().show(methods);

        //获取类的所有方法 包括父类
        Method[] methods1 = circleClass.getMethods();
        new PrintUtils<Method>().show(methods1);
        Print.println("..................................");
    }


}
