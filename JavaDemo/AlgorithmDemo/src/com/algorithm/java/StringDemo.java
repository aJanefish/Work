package com.algorithm.java;

import com.algorithm.utils.P;

public class StringDemo {

    public static void main(String[] args) {
        P.pln("String Demo");
        String str = "hello";
        String str1 = str.substring(0, str.length() / 2);
        P.pln(str);
        P.pln(str1);

    }
}
