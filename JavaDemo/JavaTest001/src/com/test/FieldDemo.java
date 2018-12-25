package com.test;

import com.test.bean.Student;
import com.test.bean.User;
import com.test.utils.PrintUtils;

import java.lang.reflect.Field;

/**
 * Field	getDeclaredField(String name)	获取指定name名称的(包含private修饰的)字段，不包括继承的字段
 * Field[]	getDeclaredFields()	获取Class对象所表示的类或接口的所有(包含private修饰的)字段,不包括继承的字段
 * Field	getField(String name)	获取指定name名称、具有public修饰的字段，包含继承字段
 * Field[]	getFields()	获取修饰符为public的字段，包含继承字段
 *
 * */

public class FieldDemo {

    public static void main(String[] args) {
        test1();
        test2();
//        test3();
//        test4();
    }


    private static void test2(){
        Class studentClass = Student.class;

        try {
            Student st= (Student) studentClass.newInstance();
            println(st);

            //获取父类public字段并赋值
            Field nameField = studentClass.getField("name");
            nameField.setAccessible(true);
            nameField.set(st,"zhangyu");
            println(st);
            println(nameField.get(st));
            Field ageField = studentClass.getField("age");
            ageField.setAccessible(true);
            ageField.set(st,26);
            println(st);
            println(ageField.get(st));

            //获取子类public字段并赋值
            Field scoreField = studentClass.getDeclaredField("score");
            //设置可访问，score是private的
            scoreField.setAccessible(true);
            scoreField.set(st,88);
            println(st);
            println(scoreField.get(st));

            Field classDeclaredField = studentClass.getDeclaredField("desc");
            classDeclaredField.set(st,"math");
            println(st);
            println(classDeclaredField.get(st));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


    }


    private static void test1(){
        Class studentClass = Student.class;

        Field[] field = studentClass.getDeclaredFields();
        new PrintUtils<Field>().show(field);

        Field[] field1 = studentClass.getFields();
        new PrintUtils<Field>().show(field1);

        try {
            Field fields = studentClass.getDeclaredField("score");
            println(fields);

            Field fields1 = studentClass.getField("age");
            println(fields1);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void println(Object object){
        System.out.println(object);
    }
}
