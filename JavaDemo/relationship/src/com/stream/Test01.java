package com.stream;

import com.utils.P;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Test01 {
    public static void main(String args[]) throws IOException {
        //FileOutputStream fileOutputStream = new FileOutputStream(new File(""));
        showChar1(null);


    }

    private static void showChar1(OutputStream outputStream) throws IOException {
        //outputStream.flush();
        int tmp = 105;
        for (int i = 33; i < tmp; i++) {
            P.p((char)i);
        }
        P.pln();
        P.pln((int)'\r');
        P.pln((int)'\n');

        Integer integer = 100110101;
        byte byteValue = integer.byteValue();
        P.pln(byteValue);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        int ss = Thread.MIN_PRIORITY;
        Object o = new Object();

        thread.start();
    }
}
