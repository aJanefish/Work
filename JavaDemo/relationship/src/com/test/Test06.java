package com.test;

import com.Main;
import com.utils.P;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Test06 {

    public static void main(String args[]) {
        Object integers = Array.newInstance(Integer.TYPE, 10);
        int length = Array.getLength(integers);

        P.pln(integers + " : " + length);

        int[] a = {1,2,3,4,5,6,7,8,8,10};
        P.pln(a.length+":"+Arrays.toString(a));
        a = (int[]) goodArrayGrow(a);
        P.pln(a.length+":"+Arrays.toString(a));

    }

    private static Object goodArrayGrow(Object a) {
        Class<?> cl = a.getClass();
        if (!cl.isArray()) {
            return null;
        }

        Class<?> componentType = cl.getComponentType();
        int length = Array.getLength(a);
        int newLength = length * 11 / 10 + 10;
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(a, 0, newArray, 0, length);
        return newArray;
    }


}
