package com.algorithm.algorithm;


import com.algorithm.utils.Print;

import java.util.Arrays;
import java.util.Random;

/**
 * 冒泡排序
 * 冒泡排序的英文Bubble Sort，是一种最基础的交换排序。
 */
public class BubbleSortDemo {


    public static void bubbleSort(int[] ints) {
        Print.println("基本冒泡排序");
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
        Print.println("优化的冒泡算法");
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
    private static void doubleSort(int array[]) {
        Print.println("鸡尾酒排序 = 双冒泡排序");
        int length = array.length;
        //int x
        int maxIndex = length;
        int minIndex = 0;
        Print.println(maxIndex+" : "+Arrays.toString(array));
        for (int i = 0; i < length/2 ; i++) {
            //>> 找到最大的数

            int tmxMaxIndex = maxIndex;
            //有序标记，每一轮的初始是true
            boolean isSorted = true;
            for (int x = i +1 ;x < tmxMaxIndex; x ++){
                if(array[x-1] > array[x]){
                    int tmp = array[x-1];
                    array[x-1] = array[x];
                    array[x] = tmp;

                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                    maxIndex = x;
                }
            }

            Print.println(maxIndex+" : "+Arrays.toString(array));
            if (isSorted) {
                break;
            }

        }
    }


    public static void main(String[] args) {
        Print.println("冒泡排序");
        int tmp = 10;
        int[] ints = new int[tmp];
        Random random = new Random();
        for (int i = 0; i < tmp; i++) {
            ints[i] = random.nextInt(100);
        }
        Print.println(Arrays.toString(ints));

        int[] ints1 = Arrays.copyOf(ints, ints.length);
        bubbleSort(ints1);

        Print.println(Arrays.toString(ints1));

        int[] ints2 = Arrays.copyOf(ints, ints.length);
        sort(ints2);
        Print.println(Arrays.toString(ints2));


        //int[] ints3 = Arrays.copyOf(ints, ints.length);
        int[] ints3 = new int[]{10,1,2,3,4,5,6,7,8,9};
        doubleSort(ints3);
        Print.println(Arrays.toString(ints3));

    }

}
