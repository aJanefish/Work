package com;

import com.utils.P;

/**
 * JAVA 复习
 *
 * */
public class Main {

    public static void main(String[] args) {
        P.pln("relationship");
        //int和Integer的区别
        // 4 1 8
        Integer i1 =  1;
        Integer i2 =  1;

        Integer i3 =  128;
        Integer i4 =  128;
        P.pln(i1 == i2);
        P.pln(i3 == i4);

        Integer i5 =  new Integer(128);
        Integer i6 =  new Integer(128);
        P.pln(i5 == i6);
    }
}
