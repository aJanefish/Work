package com.algorithm.zytest;

import com.algorithm.utils.P;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 令Pi表示第i个素数。现任给两个正整数M <= N <= 10000，请输出PM到PN的所有素数。
 */
public class Test005 {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int min = sc.nextInt();

        int max = sc.nextInt();
        int[] tmp = new int[max + 1];
        tmp[0] = 2;
        int tmpNum = 1;
        for (int x = 3; x < 1000000; x += 2) {
            if (isSuShu(x)) {
                tmp[tmpNum] = x;
                tmpNum++;
                if (tmpNum > max) {
                    break;
                }
            }
        }
        int num = 0;

        for (int x = min - 1; x < max; x++) {


            if(num == 0) {
                System.out.print(tmp[x]);
            }else {
                System.out.print(" "+tmp[x]);
            }

            num++;
            if (num == 10) {
                num = 0;
                System.out.println();
            }
        }

    }

    public static boolean isSuShu(int x) {
        for (int j = 2; j <= (int) (Math.sqrt(x)); j++) {
            //如果i被整除,立刻跳到外层for循环
            if (x % j == 0) {
                return false;
            }
        }
        return true;
    }
}
