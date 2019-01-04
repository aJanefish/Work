package com.zy;

import com.zy.utils.Print;

public class SemaphoreMain {

    public static void main(String[] args){
        Print.P("Semaphore Main");
        test1();
    }

    private static void test1() {
        SemaPhoreDemo001 semaPhoreDemo001 = new SemaPhoreDemo001(3);
        semaPhoreDemo001.acquire();
    }
}
