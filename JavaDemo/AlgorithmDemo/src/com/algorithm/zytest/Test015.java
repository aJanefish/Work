package com.algorithm.zytest;

import java.util.Scanner;

public class Test015 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numTotal = 10;
        StringBuilder stringBuilder = new StringBuilder();

        int numberOfData = 0;
        int numberOfZero = 0;
        boolean firstNumber_NonZero = true;
        for (int i = 0; i < numTotal; i++) {
            numberOfData = sc.nextInt();
            if(i == 0){
                numberOfZero = numberOfData;
            }else {
                if(numberOfData>0){
                   if(firstNumber_NonZero){
                       firstNumber_NonZero = false;


                       stringBuilder.append(i);

                       for (int i1 = 0; i1 < numberOfZero; i1++) {
                           stringBuilder.append(0);
                       }

                       for (int i1 = 1; i1 < numberOfData; i1++) {
                           stringBuilder.append(i);
                       }


                   }else {
                       for (int i1 = 0; i1 < numberOfData; i1++) {
                           stringBuilder.append(i);
                       }
                   }
                }
            }
        }


        System.out.println(stringBuilder.toString());
    }
}
