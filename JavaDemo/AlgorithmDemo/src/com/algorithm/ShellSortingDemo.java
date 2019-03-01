package com.algorithm;


import com.algorithm.algorithm.InsertionSortingDemo;
import com.algorithm.utils.P;

import java.util.Arrays;
import java.util.Random;

/**
 * Shell排序 希尔排序(Shell's Sort)是
 * 插入排序的一种又称“缩小增量排序”（Diminishing Increment Sort），是直接插入排序算法的一种更高效的改进版本。希尔排序是非稳定排序算法。该方法因D.L.Shell于1959年提出而得名。
 */
public class ShellSortingDemo {

    /**
     * Shell 1
     */
    public static void ShellSort1(int dates[]) {
        int d = dates.length;
        while (true) {
            d = d / 2;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < dates.length; i = i + d) {
                    int temp = dates[i];
                    int j;
                    for (j = i - d; j >= 0 && dates[j] > temp; j = j - d) {
                        dates[j + d] = dates[j];
                    }
                    dates[j + d] = temp;
                }
            }
            if (d == 1) {
                break;
            }
        }

    }


    /**
     * Shell 2
     */
    public static void ShellSort2(int dates[]) {
        int d = dates.length;
        while (true) {
            d = d / 2;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < dates.length; i = i + d) {
                    //对x x+d x+d+d ...  进行插入排序
                    int tmp = dates[i];
                    int j = i - d;
                    while (j > -1 && tmp < dates[j]){
                        dates[j+d] = dates[j];
                        j = j - d;
                    }
                    j = j + d;
                    dates[j] = tmp;
                }
            }
            if (d == 1) {
                break;
            }
        }
    }


    /**
     * Shell 3
     */
    public static void ShellSort3(int dates[]) {
        int d = dates.length;
        while (true) {
            d = d / 2;
            for (int x = 0; x < d; x++) {
                //对[x,x+d,x+d+d,  length)  进行插入排序
                InsertionSortingDemo.ZJInsertionSort2(dates,x,dates.length,d);
            }
            if (d == 1) {
                break;
            }
        }
    }






    public static void main(String args[]) {
        int tmp = 20000000;
        int[] ints = new int[tmp];
        Random random = new Random();
        for (int i = 0; i < tmp; i++) {
            ints[i] = random.nextInt(tmp);
        }
//        P.pln(Arrays.toString(ints));
//
//        P.pln(Arrays.toString(ints));
        int[] ints1 = Arrays.copyOf(ints,ints.length);
        int[] ints2 = Arrays.copyOf(ints,ints.length);

        P.pln("Start Shell Sort");
        long start = System.currentTimeMillis();
        ShellSort1(ints);
        long end = System.currentTimeMillis();
        P.pln("Shell1排序时间:" + (end - start));
        //P.pln(Arrays.toString(ints));

        start = System.currentTimeMillis();
        ShellSort2(ints1);
        end = System.currentTimeMillis();
        P.pln("Shell2排序时间:"+(end - start));
        //P.pln(Arrays.toString(ints1));


        //P.pln(Arrays.toString(ints2));
        start = System.currentTimeMillis();
        ShellSort3(ints2);
        end = System.currentTimeMillis();
        P.pln("Shell3排序时间:"+(end - start));
        //P.pln(Arrays.toString(ints2));
    }
}
