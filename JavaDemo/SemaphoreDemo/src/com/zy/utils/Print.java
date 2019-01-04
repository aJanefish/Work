package com.zy.utils;

public class Print {

    public static void P(){
        P("");
    }
    public static void P(Object o){
        if(o == null){
            System.out.println("o is null");
        }else {
            System.out.println(o);
        }

    }
}
