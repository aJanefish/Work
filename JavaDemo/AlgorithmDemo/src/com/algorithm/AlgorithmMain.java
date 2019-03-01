package com.algorithm;

import com.algorithm.utils.P;

/**
 * 学习算法
 * */
public class AlgorithmMain {
    public static void main(String[] args){
        P.pln("Algorithm Main");
        P.pln(1&1);
        P.pln(1|0);
        P.pln(1<<2);
        int  max = Integer.numberOfLeadingZeros(0x00);
        P.pln(max);
        int tmp = max - Integer.numberOfLeadingZeros(0x01);
        P.pln(tmp);
        tmp = max - Integer.numberOfLeadingZeros(1<<1);
        P.pln(tmp);



    }

}
