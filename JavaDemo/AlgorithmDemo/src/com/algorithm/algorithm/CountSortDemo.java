package com.algorithm.algorithm;

import com.algorithm.utils.Print;

import java.util.Arrays;
import java.util.Random;

/**
 * 计数排序
 * <p>
 * 知道一定范围内的数据
 */

public class CountSortDemo {


    public static void main(String[] args) {

        int tmp = 20;
        int[] ints = new int[tmp];
        Random random = new Random();
        for (int i = 0; i < tmp; i++) {
            ints[i] = random.nextInt(20);
        }
        Print.println(Arrays.toString(ints));
        countSort(ints, 0, 20);

        Print.println(Arrays.toString(ints));
    }

    /**
     * @param array 数组
     * @param min   数据最小值
     * @param max   数据最大值
     */

    private static void countSort(int[] array, int min, int max) {
        int[] newArray = new int[max - min];
        int length = array.length;
        for (int i = 0; i < length; i++) {
            newArray[array[i]]++;
        }
        Print.println("newArray:" + Arrays.toString(newArray));
        int tmp = 0;
        for (int i = 0; i < newArray.length; i++) {
            if (newArray[i] == 0) {
                continue;
            } else {
                while (newArray[i] > 0) {
                    newArray[i]--;
                    array[tmp] = i;
                    tmp++;
                }
            }
        }

        Print.println("newArray:" + Arrays.toString(newArray));

    }
}
