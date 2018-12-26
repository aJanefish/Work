package com.test.utils;

import java.util.Arrays;

public class PrintUtils<T> {

    public void show(T[] ts){
        if(ts == null || ts.length == 0){
            Print.println(ts.length);
            return;
        }
        int length = ts.length;
        Print.println(length);

        Print.println(Arrays.toString(ts));
//        for (int i = 0; i < length; i++) {
//            Print.println(ts[i]);
//        }
    }

}
