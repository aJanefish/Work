package com.algorithm.algorithm;

import com.algorithm.utils.P;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断一个数是不是2的乘方
 */
public class Demo001 {
    public static void main(String[] args) {
        P.pln("判断一个数是不是2的乘方");
        P.pln(int[].class);
        P.pln(int.class);
        P.pln(Integer.class);
        test1(1325454155);
        test2(1325454155);
        test3(1325454155);
    }

    // 2的阶乘 100000   2的阶乘-1  11111  相与为0
    private static void test3(int testNum) {
        P.pln("test2------------------");
//        int tmp = 31;
//        for (int i = 0; i < tmp; i++) {
//            int n = 1 << i;
//            int nd = n - 1;
//            P.pln(n+" -- "+nd+" : "+(n&nd));
//        }
        long start = System.currentTimeMillis();
        boolean flag = (testNum & (testNum - 1)) == 0;
        long end = System.currentTimeMillis();
        P.pln("耗时:"+(end - start));
        if (flag) {
            P.pln(testNum + "是2的阶乘");
        } else {
            P.pln(testNum + "不是2的阶乘");
        }
    }

    //位运算判断 判断只有一个 1
    private static void test2(int testNum) {
        P.pln("test2------------------");
        P.pln(0xFFFFFFFF + " , " + 0x1);

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
        P.pln("耗时:"+(end - start));
        P.pln(testNum + "的二进制中1的个数是:" + temp);

        if (temp == 1) {
            P.pln(testNum + "是2的阶乘");
        } else {
            P.pln(testNum + "不是2的阶乘");
        }

    }

    //枚举 出所有的2的阶乘
    private static void test1(int cases) {
        P.pln("test1------------------");
        List list = new ArrayList();

        int tmp = 31;
        for (int i = 0; i < tmp; i++) {
            //P.pln(i+":"+(1<<i)+","+Integer.numberOfLeadingZeros(1<<i));
            list.add(1 << i);
        }
        P.pln(list.size() + ":" + list);

        long start = System.currentTimeMillis();
        boolean flag = list.contains(cases);
        long end = System.currentTimeMillis();
        P.pln("耗时:"+(end - start));

        if (flag) {
            P.pln(cases + "是2的阶乘");
        } else {
            P.pln(cases + "不是2的阶乘");
        }
    }
}
