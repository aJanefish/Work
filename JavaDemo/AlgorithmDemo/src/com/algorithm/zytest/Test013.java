package com.algorithm.zytest;

import java.util.Scanner;

public class Test013 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String tmp = sc.nextLine();


        int[] counts = new int[10];

        for (int i = 0; i < tmp.length(); i++) {
            char ch = tmp.charAt(i);
            counts[ch - '0'] ++;
        }

        //0:2
        for (int i = 0; i < counts.length; i++) {
            if(counts[i] != 0 ){
                System.out.println(i+":"+counts[i]);
            }
        }
    }

}
