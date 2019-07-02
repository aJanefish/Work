package com.test;

import com.util.P;

import java.util.ArrayDeque;
import java.util.Deque;

public class QueneTest {

    private final Deque<String> readyAsyncCalls = new ArrayDeque<>();

    private void show() {
        P.pln(readyAsyncCalls);
    }

    private void add(String values) {
        readyAsyncCalls.add(values);
    }

    private void remove() {
    }

    private void removeLast() {
        readyAsyncCalls.removeLast();
    }

    private void addFirst(String values) {
        readyAsyncCalls.addFirst(values);
    }

    private void addLast(String values) {
        readyAsyncCalls.addLast(values);
    }


    private void remove(String values) {
        boolean remove = readyAsyncCalls.remove(values);
        P.pln("remove:" + remove);
    }

    public static void main(String[] args) {
        QueneTest queneTest = new QueneTest();

        int w = 15;
        for (int i = 0; i < w; i++) {
            queneTest.add(""+(char)('A'+i));
        }
        queneTest.show();

        queneTest.remove("Z");

    }
}
