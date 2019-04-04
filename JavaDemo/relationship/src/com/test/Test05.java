package com.test;

import com.utils.P;

import java.util.ArrayList;

public class Test05 {
    public static void main(String args[]){
        ArrayList<Integer> integers = new ArrayList<>();
        int tmp = 5;
        for (int i = 0; i < tmp; i++) {
            integers.add(i);
        }

        ObjectAnalyzer objectAnalyzer = new ObjectAnalyzer();
        P.pln(objectAnalyzer.toString(new Integer(1)));
        P.pln(objectAnalyzer.toString(objectAnalyzer));

        P.pln(objectAnalyzer.toString(integers));
        P.pln(objectAnalyzer.toString(new int[]{1,2,3}));
    }
}
