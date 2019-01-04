package com.utils;

public class Print {

    public static void P(){
        P("");
    }
    public static void P(Object object){
        if(object == null){
            System.out.println("object is null");
        }else {
            System.out.println(object);
        }

    }
}
