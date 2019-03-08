package com.algorithm.zytest;


import java.util.Scanner;

/**
 * 题目描述
 * 给定任一个各位数字不完全相同的4位正整数，如果我们先把4个数字按非递增排序，再按非递减排序，然后用第1个数字减第2个数字，将得到
 * 一个新的数字。一直重复这样做，我们很快会停在有“数字黑洞”之称的6174，这个神奇的数字也叫Kaprekar常数。
 * <p>
 * 例如，我们从6767开始，将得到
 * <p>
 * 7766 - 6677 = 1089
 * 9810 - 0189 = 9621
 * 9621 - 1269 = 8352
 * 8532 - 2358 = 6174
 * 7641 - 1467 = 6174
 * ... ...
 * <p>
 * 现给定任意4位正整数，请编写程序演示到达黑洞的过程。
 * <p>
 * 输入描述:
 * 输入给出一个(0, 10000)区间内的正整数N。
 * <p>
 * <p>
 * 输出描述:
 * 如果N的4位数字全相等，则在一行内输出“N - N = 0000”；否则将计算的每一步在一行内输出，直到6174作为差出现，输出格式见样例,每行中间没有空行。注意每个数字按4位数格
 * 式输出。
 * <p>
 * 输入例子:
 * 6767
 * <p>
 * 输出例子:
 * 7766 - 6677 = 1089
 * 9810 - 0189 = 9621
 * 9621 - 1269 = 8352
 * 8532 - 2358 = 6174
 */
public class Test011 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tmp = sc.nextInt();
        int max = 0;
        int min = 0;
        int[] x = new int[4];
        do {

            max = countMax(tmp,x);
            min = countMin(tmp,x);
            tmp = max - min;

            show(max);
            System.out.print(" - ");
            show(min);
            System.out.print(" = ");
            show(tmp);
            System.out.println();
        } while (tmp != 6174 && tmp != 0);


    }

    private static void show(int show) {
        
        if (show > 999) {
            System.out.print(show);
        } else if (show > 99) {
            System.out.print("0" + show);
        } else if (show > 9) {
            System.out.print("00" + show);
        } else {
            System.out.print("000" + show);
        }

    }

    // 1234
    private static int countMax(int tmp,int x[]) {
        tmp = tmp % 10000;

        x[0] = tmp / 1000;
        x[1] = tmp % 1000 / 100;
        x[2] = tmp % 100 / 10;
        x[3] = tmp % 10;
        //P.pln(Arrays.toString(x));
        insertSortJ(x);
        //P.pln(Arrays.toString(x));

        return x[0] * 1000 + x[1] * 100 + x[2] * 10 + x[3];
    }


    private static int countMin(int tmp,int x[]) {
        //tmp = tmp % 10000;
        x[0] = tmp / 1000;
        x[1] = tmp % 1000 / 100;
        x[2] = tmp % 100 / 10;
        x[3] = tmp % 10;

        //P.pln(Arrays.toString(x));
        insertSortS(x);
        // P.pln(Arrays.toString(x));

        return x[0] * 1000 + x[1] * 100 + x[2] * 10 + x[3];
    }


    // 大 - 小
    private static void insertSortJ(int dates[]) {

        for (int i = 1; i < dates.length; i++) {
            int tmp = dates[i];
            int i1;
            for (i1 = i - 1; i1 >= 0; i1--) {

                if (tmp > dates[i1]) {
                    dates[i1 + 1] = dates[i1];
                } else {
                    break;
                }
            }
            if(i == i1+1){
               continue; 
            }
            dates[i1 + 1] = tmp;
        }
    }


    // 小 - 大
    private static void insertSortS(int dates[]) {

        for (int i = 1; i < dates.length; i++) {
            int tmp = dates[i];
            int i1;
            for (i1 = i - 1; i1 >= 0; i1--) {

                if (tmp < dates[i1]) {
                    dates[i1 + 1] = dates[i1];
                } else {
                    break;
                }
            }

            if(i == i1+1){
                continue;
            }
            dates[i1 + 1] = tmp;
        }
    }
}
