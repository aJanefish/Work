package com.algorithm.algorithm;


import com.algorithm.utils.P;

import java.util.Random;

/**
 * 冒泡排序
 * 冒泡排序的英文Bubble Sort，是一种最基础的交换排序。
 */
public class BubbleSortDemo {


    public static void bubbleSort(int[] ints) {
        P.pln("基本冒泡排序");
        int length = ints.length;
        if (length == 0) {
            return;
        }

        for (int i = 0; i < length; i++) {
            for (int i1 = i + 1; i1 < length; i1++) {
                if (ints[i1] < ints[i]) {
                    int tmp = ints[i];
                    ints[i] = ints[i1];
                    ints[i1] = tmp;
                }
            }
        }

    }

    //优化的冒泡算法
    //最大的数在后面
    private static void sort(int array[]) {
        P.pln("优化的冒泡算法");
        int tmp = 0;
        //记录最后一次交换的位置
        int lastExchangeIndex = 0;
        //无序数列的边界，每次比较只需要比到这里为止
        int sortBorder = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            //有序标记，每一轮的初始是true
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                    //把无序数列的边界更新为最后一次交换元素的位置
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            if (isSorted) {
                break;
            }
        }
    }

    //鸡尾酒排序 = 双冒泡排序
    // 小 ==>> 大
    public static void doubleSort(int array[]) {
        //P.pln("鸡尾酒排序 = 双冒泡排序");
        int length = array.length;
        //int x
        int maxIndex = length;
        int minIndex = 0;
        //P.pln(maxIndex+" : "+Arrays.toString(array));
        for (int i = 0; i < length/2 ; i++) {
            //>> 找到最大的数

            int tmpMaxIndex = maxIndex;
            //有序标记，每一轮的初始是true
            boolean isSorted = true;
            for (int x = i +1 ;x < tmpMaxIndex; x ++){
                if(array[x-1] > array[x]){
                    int tmp = array[x-1];
                    array[x-1] = array[x];
                    array[x] = tmp;

                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                    maxIndex = x;
                }
            }

            //P.pln(isSorted+" : maxIndex:"+maxIndex+" : "+Arrays.toString(array));
            if (isSorted) {
                break;
            }


            isSorted = true;
            int tmpMinIndex = minIndex;
            for (int x = maxIndex ;x > tmpMinIndex; x --){
                if(array[x] < array[x - 1]){
                    int tmp = array[x-1];
                    array[x-1] = array[x];
                    array[x] = tmp;

                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                    minIndex = x;
                }
            }

            //P.pln(isSorted+" : minIndex: "+minIndex+" : "+Arrays.toString(array)+"\n");
            if(isSorted){
                break;
            }

        }
    }


    public static void main(String[] args) {
        P.pln("冒泡排序");
        int tmp = 100000000;
        int[] ints = new int[tmp];
        Random random = new Random();
        for (int i = 0; i < tmp; i++) {
            ints[i] = random.nextInt(10000000);
        }
//        P.pln(Arrays.toString(ints));
//
//        int[] ints1 = Arrays.copyOf(ints, ints.length);
//        bubbleSort(ints1);
//
//        P.pln(Arrays.toString(ints1));
//
//        int[] ints2 = Arrays.copyOf(ints, ints.length);

        //P.pln(Arrays.toString(ints2));
        long start = System.currentTimeMillis();
        doubleSort(ints);
        long end = System.currentTimeMillis();
        P.pln(end - start);



        //P.pln(Arrays.toString(ints));

    }

}
