package com.algorithm.algorithm;

import com.algorithm.utils.Print;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断一个数是不是2的乘方
 */
public class Demo001 {
    public static void main(String[] args) {
        Print.println("判断一个数是不是2的乘方");
        Print.println(int[].class);
        Print.println(int.class);
        Print.println(Integer.class);
        test1(1325454155);
        test2(1325454155);
        test3(1325454155);
    }

    // 2的阶乘 100000   2的阶乘-1  11111  相与为0
    private static void test3(int testNum) {
        Print.println("test2------------------");
//        int tmp = 31;
//        for (int i = 0; i < tmp; i++) {
//            int n = 1 << i;
//            int nd = n - 1;
//            Print.println(n+" -- "+nd+" : "+(n&nd));
//        }
        long start = System.currentTimeMillis();
        boolean flag = (testNum & (testNum - 1)) == 0;
        long end = System.currentTimeMillis();
        Print.println("耗时:"+(end - start));
        if (flag) {
            Print.println(testNum + "是2的阶乘");
        } else {
            Print.println(testNum + "不是2的阶乘");
        }
    }

    //位运算判断 判断只有一个 1
    private static void test2(int testNum) {
        Print.println("test2------------------");
        Print.println(0xFFFFFFFF + " , " + 0x1);

        int temp = 0; //1 出现的次数
        long start = System.currentTimeMillis();

        for (int x = 0; x < 32 - Integer.numberOfLeadingZeros(testNum); x++) {
            int temp1 = testNum >> x;
            int temp2 = temp1 & 0x1;

            if (temp2 == 1) {
                temp++;
            }
        }
        long end = System.currentTimeMillis();
        Print.println("耗时:"+(end - start));
        Print.println(testNum + "的二进制中1的个数是:" + temp);

        if (temp == 1) {
            Print.println(testNum + "是2的阶乘");
        } else {
            Print.println(testNum + "不是2的阶乘");
        }

    }

    //枚举 出所有的2的阶乘
    private static void test1(int cases) {
        Print.println("test1------------------");
        List list = new ArrayList();

        int tmp = 31;
        for (int i = 0; i < tmp; i++) {
            //Print.println(i+":"+(1<<i)+","+Integer.numberOfLeadingZeros(1<<i));
            list.add(1 << i);
        }
        Print.println(list.size() + ":" + list);

        long start = System.currentTimeMillis();
        boolean flag = list.contains(cases);
        long end = System.currentTimeMillis();
        Print.println("耗时:"+(end - start));

        if (flag) {
            Print.println(cases + "是2的阶乘");
        } else {
            Print.println(cases + "不是2的阶乘");
        }
    }
}
