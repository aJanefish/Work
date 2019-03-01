package com.algorithm.algorithm;

import com.algorithm.utils.P;

import java.util.Arrays;
import java.util.Random;

/**
 * 插入排序
 * 1.直接插入排序
 * 2.二分插入排序
 */


public class InsertionSortingDemo {

    /**
     * 直接插入排序 1
     * 空间复杂度：O(1)
     * 时间复杂度：
     */
    public static void ZJInsertionSort1(int dates[]) {
        int length = dates.length;

        for (int x = 1; x < length; x++) {

            int tmp = dates[x];
            int j = x - 1;
            while (j >= 0 && tmp < dates[j]) {
                dates[j + 1] = dates[j];
                j--;
            }

            dates[++j] = tmp;

        }
    }


    /**
     * 直接插入排序 2
     */
    public static void ZJInsertionSort2(int dates[]) {
        int length = dates.length;

        for (int x = 1; x < length; x++) {
            for (int j = x - 1; j > -1; j--) {
                if (dates[j + 1] < dates[j]) {
                    int tmp = dates[j + 1];
                    dates[j + 1] = dates[j];
                    dates[j] = tmp;
                }
            }
        }
    }

    /**
     * 直接插入排序 1
     */
    public static void ZJInsertionSort2(int dates[], int start, int end, int step) {
        //[start,end)

        for (int x = start + step; x < end; x = x + step) {

            int tmp = dates[x];
            int j = x - step;
            while (j >= start && tmp < dates[j]) {
                dates[j + step] = dates[j];
                j = j - step;
            }
            dates[j + step] = tmp;
        }
    }

//    /**
//     * 折半插入排序（二分插入排序）
//     *
//     * */
//    public static void ZBInsertionSort1(int dates[], int start, int end) {
//        //[start,end]
//        int step = 1;
//        for (int i = start + step; i <= end; i = i +step) {
//
//            // [start,i) [i,end)
//
//            int tmp = dates[i];
//            int j = i - step;
//            while (j >= start && tmp < dates[j]){
//
//                dates[j+step] = dates[j];
//                j = j - step;
//            }
//
//            dates[j+step]  = tmp;
//
//
//        }
//
//    }


    public static void main(String args[]) {
        int tmp = 100000;
        int[] ints = new int[tmp];
        Random random = new Random();
        for (int i = 0; i < tmp; i++) {
            ints[i] = random.nextInt(tmp);
        }
        //ints = new int[]{18, 11, 14, 2, 2, 14, 7, 0, 0, 12};
        //P.pln(Arrays.toString(ints));
        int[] ints1 = Arrays.copyOf(ints, ints.length);
        int[] ints2 = Arrays.copyOf(ints, ints.length);
        int[] ints3 = Arrays.copyOf(ints, ints.length);
        //P.pln(Arrays.toString(ints1));
        long start = System.currentTimeMillis();
        ZJInsertionSort1(ints);
        long end = System.currentTimeMillis();
        P.pln("第一种插入排序时间:" + (end - start));
        //P.pln(Arrays.toString(ints));
        start = System.currentTimeMillis();
        ZJInsertionSort2(ints1);
        end = System.currentTimeMillis();
        P.pln("第二种插入排序时间:" + (end - start));
        //P.pln(Arrays.toString(ints1));


        //ZJInsertionSort2(ints2,0,ints2.length,1);
        //P.pln(Arrays.toString(ints2));


//        P.pln(Arrays.toString(ints3));
//        ZBInsertionSort1(ints3,0,ints3.length-1);
//        P.pln(Arrays.toString(ints3));

    }

}
