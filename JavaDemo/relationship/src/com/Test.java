package com;

import com.sun.istack.internal.NotNull;
import com.utils.P;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Test {


    public static void main(String[] args) {

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("", "");
//        HashMap<Integer, Integer> hashMap = new HashMap();
//        int i = 12;
//        for (int i1 = 0; i1 < i; i1++) {
//            hashMap.put(i1,i1);
//        }
//        P.pln(hashMap);
//        Hashtable<Integer,Integer> hashtable = new Hashtable<>();
//        hashtable.put(1,1);
//        hashtable.get(1);

        //test1();

        //test2(null);

        test3();
        List list = null;
        list.iterator();

    }

    private static void test3() {

        //在100-999这900个自然数中,若将组成这个数的三个数字认为是三条线段的长度,那么是三条线段组成一个等腰三角形(包括等边)的共有()个.
        int tmp = 1000;
        int sum = 0;
        for (int i = 100; i < tmp; i++) {

            int i1 = i / 100;
            int i2 = i % 100 / 10;
            int i3 = i % 10;
            if (isTriangle(i1, i2, i3)) {
                sum++;
                P.pln(i1 + ":" + i2 + ":" + i3);
            }
        }
        P.pln("sum:" + sum);
    }

    private static boolean isTriangle(int i1, int i2, int i3) {
        if (i1 == 0 || i2 == 0 || i3 == 0) {
            return false;
        }

        if ((i1 < i2 + i3) && (i2 < i1 + i3) && (i3 < i1 + i2)) {
            if(i1==i2 || i1 == i3 || i2 ==i3){
                return true;
            }
        }
        return false;
    }


    private static void test2(@NotNull String string) {
        NoteA noteA = new NoteA(null);
        NoteB noteB = new NoteB(null);
        noteA.noteB = noteB;

        P.pln(noteA);
    }

    private static void test1() {
        P.pln("" + 'A' + 'B');

        P.pln('A' + 'B');

        int x = 9;
        P.pln(x + " : " + Integer.toHexString(x) + " : " + Integer.toBinaryString(x));
        x = ~x;
        P.pln(x + " : " + Integer.toHexString(x) + " : " + Integer.toBinaryString(x));

    }


    static class NoteA {
        private NoteB noteB;

        public NoteA(NoteB noteB) {
            this.noteB = noteB;
        }

        @Override
        public String toString() {
            return "NoteA{" +
                    noteB +
                    '}';
        }
    }


    static class NoteB {
        private NoteA noteA;

        public NoteB(NoteA noteA) {
            this.noteA = noteA;
        }

        @Override
        public String toString() {
            return "NoteB{" +
                    noteA +
                    '}';
        }
    }


}

