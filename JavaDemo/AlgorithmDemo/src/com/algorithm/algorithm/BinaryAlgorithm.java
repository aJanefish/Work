package com.algorithm.algorithm;

import com.algorithm.utils.P;

/**
 * 二分算法
 * 应用：求一个数的平方根
 */
public class BinaryAlgorithm {
    private static double error_scope = 0.00000000001;

    public static void main(String[] args) {
        double tmp = 25;

        sqrt(tmp, tmp, 0);

        P.pln(Math.sqrt(tmp));
    }


    private static void sqrt(double target, double max, double min) {


        double tmp = (max + min) / 2;
        double square = tmp * tmp;
        P.pln(target + ":" + tmp + ":" + square);
        while (Math.abs(square - target) > error_scope) {

            if (square > target) {
                max = tmp;
            } else {
                min = tmp;
            }

            tmp = (max + min) / 2;
            square = tmp * tmp;
            P.pln(target + ":" + tmp + ":" + square);
        }

        P.pln(target + ":" + tmp + ":" + square);

    }
}
