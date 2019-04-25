package com.utils;

public class P {

    public static void p() {
        p("");
    }

    private static void p(String s) {
        System.out.print(s);
    }

    public static void pln(Object o) {
        if (o == null) {
            System.out.println("o is null");
        } else {
            System.out.println(o);
        }
    }

    public static void pln() {
        pln("");
    }
}
