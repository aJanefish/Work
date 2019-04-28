package com.zy.eventbus;

public class P {
    public static void p(Object o){
        System.out.print(o);
    }

    public static void p(){
        p("");
    }

    public static void pln(Object o){
        System.out.println(o);
    }

    public static void pln(){
        pln("'");
    }
}
