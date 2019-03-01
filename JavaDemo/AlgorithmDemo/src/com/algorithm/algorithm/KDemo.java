package com.algorithm.algorithm;


import com.algorithm.utils.P;

import java.util.Arrays;
import java.util.Random;

/**
 * 寻找无序数组的第k大元素
 * 方法一:二叉堆 -- 最小堆(K 大小) -- 结束时堆顶就是所求
 */
public class KDemo {
    //二叉堆方法
    private static int test(int[] array, int k) {
        //长度为k 的二叉堆(最小堆 - 根节点大于儿子节点)
        int[] ints = new int[k];
        P.pln(Arrays.toString(ints));

        for (int i = 0; i < array.length; i++) {

            if (array[i] > ints[0]) {
                //替换掉 二叉堆的root
                ints[0] = array[i];

                int parent = 0;
                int childIndex = parent * 2 + 1;
                while (childIndex < k){
                    //P.pln(childIndex+ " : " +parent);
                    if((childIndex+1) < k && ints[childIndex +1] < ints[childIndex]){
                        childIndex ++;
                    }

                    if(ints[childIndex] < ints[parent]){
                        int tmp = ints[childIndex];
                        ints[childIndex] = ints[parent];
                        ints[parent] = tmp;

                        parent = childIndex;
                        childIndex = 2*parent +1;
                    }else {
                        break;
                    }
                }
            }
        }
        P.pln(Arrays.toString(ints));
        return ints[0];
    }


    //分治法 类似快速排序 分堆


    public static void main(String[] args) {
        P.pln("寻找无序数组的第k大元素");
        int tmp = 100;
        int[] array = new int[tmp];
        Random random = new Random();
        for (int i = 0; i < tmp; i++) {
            array[i] = random.nextInt(100);
        }
        P.pln(Arrays.toString(array));
        int values = test(array, 3);
        P.p(values);
    }


}
