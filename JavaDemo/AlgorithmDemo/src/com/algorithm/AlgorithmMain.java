package com.algorithm;

import com.algorithm.utils.Print;

/**
 * 学习算法
 * */
public class AlgorithmMain {
    public static void main(String[] args){
        Print.println("AlgorithmMain");
        Print.println(1&1);
        Print.println(1|0);
        Print.println(1<<2);
        int  max = Integer.numberOfLeadingZeros(0x00);
        Print.println(max);
        int tmp = max - Integer.numberOfLeadingZeros(0x01);
        Print.println(tmp);
        tmp = max - Integer.numberOfLeadingZeros(1<<1);
        Print.println(tmp);


        while (true){
            test1();
            Print.println("end test1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void test1() {
        long start = System.currentTimeMillis();
        while (true){
            Print.println("ss");
            long end = System.currentTimeMillis();
            if(end - start >= 1000){
                return;
            }
        }
    }


}
