package com.mode.singleton;

import com.utils.P;

public class SingleTonMain {
    public static void main(String args[]){

        test1();
        test2();

    }

    private static void test2() {
        P.pln(TicketMaker.getInstance().getNextTicketNumber());
        P.pln(TicketMaker.getInstance().getNextTicketNumber());

        P.pln(TicketMaker.getInstance().getNextTicketNumber());

        P.pln(TicketMaker.getInstance().getNextTicketNumber());

    }


    private static void test1() {
        SingletonDemo singletonDemo1 = SingletonDemo.getInstance();
        SingletonDemo singletonDemo2 = SingletonDemo.getInstance();
        P.pln(singletonDemo1);
        P.pln(singletonDemo2);

        if (singletonDemo1 == singletonDemo2){
            P.pln("==");
        }else {
            P.pln("!=");
        }
        P.pln("end.");

    }
}
