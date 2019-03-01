package com.algorithm.algorithm;

import com.algorithm.utils.P;

import java.util.Arrays;
import java.util.Random;

/**
 * 选择排序
 * */

public class SelectSortDemo {

    public static void main(String args[]) {
        int tmp = 100000;
        int[] ints = new int[tmp];
        Random random = new Random();
        for (int i = 0; i < tmp; i++) {
            ints[i] = random.nextInt(tmp);
        }

        int[] ints1 = Arrays.copyOf(ints, ints.length);

//        P.pln(Arrays.toString(ints));
//        SelectSortMin(ints);
//        P.pln(Arrays.toString(ints));

        long start = System.currentTimeMillis();
        SelectSortMin(ints);
        long end = System.currentTimeMillis();
        P.pln("第一种选择排序时间:" + (end - start));

        start = System.currentTimeMillis();
        SelectSortMax(ints1);
        end = System.currentTimeMillis();
        P.pln("第二种选择排序时间:" + (end - start));

    }


    //选择最小的
    private static void SelectSortMin(int[] ints) {

        int tmp;
        int minIndex;
        for (int i = 0; i < ints.length; i++) {
            minIndex = i;
            for (int j = i + 1; j < ints.length; j++) {
                if (ints[j] < ints[minIndex]) {
                    minIndex = j;
                }
            }

            if(i != minIndex){
                tmp = ints[i];
                ints[i] = ints[minIndex];
                ints[minIndex] = tmp;
            }
        }
    }


    //选择最大的
    private static void SelectSortMax(int[] ints) {

        int tmp;
        int maxIndex;
        for (int i = 0; i < ints.length; i++) {
            //[0,i) 有序的
            //[i,end) 无序的
            maxIndex = i;
            for (int j = i + 1; j < ints.length; j++) {
                if (ints[j] > ints[maxIndex]) {
                    maxIndex = j;
                }
            }

            if(i != maxIndex){
                tmp = ints[i];
                ints[i] = ints[maxIndex];
                ints[maxIndex] = tmp;
            }
        }
    }
}
