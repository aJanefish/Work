package com.algorithm.java;

import com.algorithm.utils.Print;

public class StringDemo {

    public static void main(String[] args) {
        Print.println("String Demo");
        String str = "hello";
        String str1 = str.substring(0, str.length() / 2);
        Print.println(str);
        Print.println(str1);

    }
}
