package com.test;

import com.utils.P;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test07 {
    public static void main(String args[]) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method sqrt = Math.class.getDeclaredMethod("sqrt", double.class);
        int tmp = 10;
        for (int i = 0; i < tmp; i++) {
            Object ob = sqrt.invoke(null, i);
            P.pln(ob);
        }

    }
}
