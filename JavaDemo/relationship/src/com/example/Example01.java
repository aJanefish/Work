package com.example;

import com.utils.P;

import java.lang.reflect.Constructor;
import java.util.Arrays;


interface DataService{
    int show();
}

public class Example01 {

    int age;

    public Example01(int age) {
        this.age = age;
    }

    public Example01() {

    }

    public static void main(String[] args) {
        Example01 example01 = new Example01();
        Class<Example01> testClass = Example01.class;
        try {
            Example01 test = testClass.newInstance();
            Constructor<?>[] constructors = testClass.getConstructors();
            P.pln(Arrays.toString(constructors));
            P.pln(test);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Example01{" +
                "age=" + age +
                '}';
    }
}