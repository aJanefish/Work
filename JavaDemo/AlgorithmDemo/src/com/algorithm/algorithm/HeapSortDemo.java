package com.algorithm.algorithm;


import com.algorithm.utils.Print;

import java.util.Arrays;
import java.util.Random;

/**
 * 二叉堆--> 最大堆||最小堆 --> 堆排序
 */

public class HeapSortDemo {

    /**
     * 最大堆 parentIndex下沉调整
     *
     * @param array       待调整的堆
     * @param parentIndex 要下沉的父节点
     * @param length      堆的有效大小
     */
    private static void downAdjust(int[] array, int parentIndex, int length) {
        //保存父节点
        int temp = array[parentIndex];
        int childIndex = parentIndex * 2 + 1;
        while (childIndex < length) {
            //如果右孩子存在 且大于左孩子
            if (childIndex + 1 < length && array[childIndex + 1] > array[childIndex]) {
                childIndex++;
            }

            //父类最大
            if (temp > array[childIndex]) {
                break;
            }

            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = parentIndex * 2 + 1;
        }

        array[parentIndex] = temp;
    }

    private static void heapSort(int[] array) {
        // 1.把无序数组构建成二叉堆。

        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            downAdjust(array, i, array.length);
        }

        Print.println("最大堆:" + Arrays.toString(array));
        //循环去除最大元素放到最后的位置
        for (int i = array.length - 1; i >= 0; i--) {

            int tmp = array[0];
            array[0] = array[i];
            array[i] = tmp;

            //目前最大堆的有效长度是 i
            int length = i;
            downAdjust(array,0,length);
        }


    }


    public static void main(String[] args) {
        Print.println("二叉堆--> 最大堆||最小堆 --> 堆排序");
        int tmp = 10;
        int[] array = new int[tmp];
        Random random = new Random();
        for (int i = 0; i < tmp; i++) {
            array[i] = random.nextInt(100);
        }
        Print.println("原始无序:"+Arrays.toString(array));
        heapSort(array);
        Print.println("堆排序后的数据:"+Arrays.toString(array));

    }


}
