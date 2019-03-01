package com.algorithm;

import java.util.ArrayList;
import java.util.List;
interface test{
    static void sdagf(){};
}

public class NameList {
    private List names = new ArrayList();

    public synchronized void add(String name) {
        names.add(name);
    }

    public synchronized void printAll() {
        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i) + ":"+names.size());
        }
    }

    public static void main(String[] args) {
        final NameList sl = new NameList();
        for (int i = 0; i < 2; i++) {
            new Thread() {
                public void run() {
                    int tmp = 100;
                    for (int i1 = 0; i1 < tmp; i1++) {
                        sl.add(""+i1);
                    }

                    sl.printAll();
                }
            }.start();
        }
    }
}
