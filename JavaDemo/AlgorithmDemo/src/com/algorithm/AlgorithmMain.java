package com.algorithm;

import com.algorithm.utils.Print;

/**
 * 学习算法
 * */
public class AlgorithmMain {
    public static void main(String[] args){
        Print.println("Algorithm Main");
        Print.println(1&1);
        Print.println(1|0);
        Print.println(1<<2);
        int  max = Integer.numberOfLeadingZeros(0x00);
        Print.println(max);
        int tmp = max - Integer.numberOfLeadingZeros(0x01);
        Print.println(tmp);
        tmp = max - Integer.numberOfLeadingZeros(1<<1);
        Print.println(tmp);



    }

}
