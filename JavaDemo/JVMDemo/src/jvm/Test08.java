package jvm;

import utils.P;

public class Test08 {

    public static  int race = 0;
    public static  void inCreate(){
        race++;
    }
    
    private static final int THREADS_COUNT = 20;
    
    public static void main(String args[]) throws InterruptedException {
        
        Thread[] threads = new Thread[THREADS_COUNT];
        P.pln("0");

        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    int tmp = 10000 ;
                    for (int i1 = 0; i1 < tmp; i1++) {
                        inCreate();
                    }
                }
            });
            threads[i].start();
        }
        P.pln("1");

//        while (Thread.activeCount() > 1){
//            P.pln("2");
//
//            Thread.yield();
//            P.pln("3");
//
//        }
        P.pln("4");
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i].join();

        }

        P.pln("5");

        P.pln(race);

    }
}
