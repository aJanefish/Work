package com.algorithm.zytest;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * 给定一系列正整数，请按要求对数字进行分类，并输出以下5个数字：
 * <p>
 * A1 = 能被5整除的数字中所有偶数的和；
 * A2 = 将被5除后余1的数字按给出顺序进行交错求和，即计算n1-n2+n3-n4...；
 * A3 = 被5除后余2的数字的个数；
 * A4 = 被5除后余3的数字的平均数，精确到小数点后1位；
 * A5 = 被5除后余4的数字中最大数字
 */

public class Test004 {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int valuesNum = sc.nextInt();
        int[] values = new int[valuesNum];
        int A1 = 0;
        int A2 = 0;
        int A3 = 0;
        int A4 = 0;
        int A5 = 0;
        boolean flagAdd = true;
        int A2count = 0;
        int A4count = 0;
        //10 348 344 986 351 131 514 50 162 126 768
        for (int i = 0; i < valuesNum; i++) {
            int tmp = sc.nextInt();

            int tmpX = tmp % 5;
            switch (tmpX) {
                case 1:
                    A2count++;
                    if (flagAdd) {
                        A2 += tmp;
                        flagAdd = false;
                    } else {
                        A2 -= tmp;
                        flagAdd = true;
                    }
                    break;
                case 2:
                    A3++;
                    break;
                case 3:
                    A4 += tmp;
                    A4count++;
                    break;
                case 4:
                    if (A5 < tmp) {
                        A5 = tmp;
                    }
                    break;
                default:
                    if ((tmp & 1) == 0) {
                        A1 += tmp;
                    }
                    break;
            }
        }

        //0 11 2 29 9
        if (A1 == 0) {
            System.out.print("N ");
        } else {
            System.out.print(A1 + " ");
        }
        if (A2count == 0) {
            System.out.print("N ");
        } else
            System.out.print(A2 + " ");

        if (A3 == 0) {
            System.out.print("N ");
        } else {
            System.out.print(A3 + " ");
        }

        //DecimalFormat df = new DecimalFormat ("#.0");
        //        double a = 1.11111;
        //        System.out.println (df.format (a));
        if (A4count == 0) {
            System.out.print("N ");
        } else {
            double l = (double) A4;
            l = l / (double)A4count;
            DecimalFormat df = new DecimalFormat("#.0");
            System.out.print(df.format(l) + " ");
        }

        if (A5 == 0) {
            System.out.print("N");
        } else {
            System.out.print(A5);
        }

    }

}
