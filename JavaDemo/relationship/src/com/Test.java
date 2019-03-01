package com;

import com.sun.istack.internal.NotNull;
import com.utils.P;

import java.util.concurrent.ConcurrentHashMap;

public class Test {


    public static void main(String[] args) {

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("","");
//        HashMap<Integer, Integer> hashMap = new HashMap();
//        int i = 12;
//        for (int i1 = 0; i1 < i; i1++) {
//            hashMap.put(i1,i1);
//        }
//        P.pln(hashMap);
//        Hashtable<Integer,Integer> hashtable = new Hashtable<>();
//        hashtable.put(1,1);
//        hashtable.get(1);

        test1();

        test2(null);


    }


    private static void test2(@NotNull String string) {
        NoteA noteA = new NoteA(null);
        NoteB noteB = new NoteB(null);
        noteA.noteB = noteB;

        P.pln(noteA);
    }

    private static void test1() {
        P.pln(""+'A'+'B');

        P.pln('A'+'B');

        int x = 9;
        P.pln(x + " : "+Integer.toHexString(x)+" : "+Integer.toBinaryString(x));
        x = ~x;
        P.pln(x + " : "+Integer.toHexString(x)+" : "+Integer.toBinaryString(x));

    }


    static class NoteA{
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


    static class NoteB{
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

