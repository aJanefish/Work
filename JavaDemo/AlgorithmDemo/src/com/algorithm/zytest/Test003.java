package com.algorithm.zytest;

import java.util.Scanner;

/**
 * 给定区间[-2的31次方, 2的31次方]内的3个整数A、B和C，请判断A+B是否大于C。
 */
public class Test003 {
    /**
     * 4
     * <p>
     * 1 2 3
     * 2 3 4
     * 5 6 7
     * 8 9 2
     */
    private static int MIN = Integer.MIN_VALUE;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int valuesNum = sc.nextInt();
        long[][] values = new long[3][valuesNum];
        for (int i = 0; i < valuesNum; i++) {
            values[0][i] = sc.nextLong();
            values[1][i] = sc.nextLong();
            values[2][i] = sc.nextLong();

        }

        for (int i = 0; i < valuesNum; i++) {


            boolean flag = values[0][i] + values[1][i] > values[2][i];
            //P.pln(values[0][i]+":"+values[1][i]+":"+values[2][i]);
            System.out.println("Case #" + (i + 1) + ": " + flag);

        }
    }
}
