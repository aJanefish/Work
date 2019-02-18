package com;

import com.utils.Print;

/**
 * JAVA 复习
 *
 * */
public class Main {

    public static void main(String[] args) {
        Print.println("relationship");
        //int和Integer的区别
        // 4 1 8
        Integer i1 =  1;
        Integer i2 =  1;

        Integer i3 =  128;
        Integer i4 =  128;
        Print.println(i1 == i2);
        Print.println(i3 == i4);

        Integer i5 =  new Integer(128);
        Integer i6 =  new Integer(128);
        Print.println(i5 == i6);
    }
}
