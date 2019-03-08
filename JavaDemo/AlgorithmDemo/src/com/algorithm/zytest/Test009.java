package com.algorithm.zytest;

import com.algorithm.algorithm.BinaryHeapDemo;

import java.math.BigInteger;
import java.util.Scanner;

public class Test009 {

    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);
        BigInteger bigInteger1 = sc.nextBigInteger();
        BigInteger bigInteger2 = sc.nextBigInteger();
        sc.close();
        BigInteger bigInteger3 = bigInteger1.divide(bigInteger2);

        BigInteger bigInteger4 = bigInteger1.mod(bigInteger2);
        System.out.print(bigInteger3 + " "+ bigInteger4);


    }
}
