package com.test;

import com.utils.P;

/**
 * 自动拆箱
 */
public class Test04 {
    public static void main(String args[]) {
        unBoxInteger();
    }

    private static void unBoxInteger() {
        P.pln("unBoxInteger......................");
        Integer integer1 = new Integer(3);
        Integer integer2 = new Integer(3);

        int i1 = integer1;
        int i2 = integer2;

        showObject(integer1, integer2);
        showObject(i1, i2);

        showObject(1, 1);

    }

    private static void showObject(int o1, int o2) {
        P.pln((o1 == o2));
    }

    private static void showObject(Object o1, Object o2) {
        P.pln(o1.getClass() + " -          " + (o1 == o2) + " - " + o1.equals(o2));
    }
}
