package com.algorithm.algorithm;

import com.algorithm.utils.P;

import java.util.Arrays;

/**
 * 一个无序数组里有99个不重复正整数，范围从1到100，唯独缺少一个整数。如何找出这个缺失的整数？
 * */
public class Demo002 {

    public static void main(String[] args) {
        P.pln("一个无序数组里有99个不重复正整数，范围从1到100，唯独缺少一个整数。如何找出这个缺失的整数？");
        test1();
        test2();
    }

    //一组数据只有两个数 出现的次数是奇数次 找出来
    private static void test2() {
        P.pln("test2--------------------------");

        int[] ints = new int[]{0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,8,9,9,9};
        int temp = 0;
        for (int i = 0; i < ints.length; i++) {
            temp ^= ints[i];
        }
        P.pln(temp);

        if(temp == 0){
            P.pln("数据不对,不合题目");
            return;
        }

        int[] ints1 = new int[ints.length];
        int[] ints2 = new int[ints.length];
        for (int i = 0; i < ints.length; i++) {
            int tmp = ints[i] & 0x01;
            if(tmp == 1){
                ints1[i] = ints[i];
            }else {
                ints2[i] = ints[i];
            }
        }

        P.pln(Arrays.toString(ints1));
        P.pln(Arrays.toString(ints2));

        temp = 0;
        for (int i = 0; i < ints1.length; i++) {
            temp ^= ints1[i];
        }
        P.pln("两个奇数次是:"+temp);


        temp = 0;
        for (int i = 0; i < ints2.length; i++) {
            temp ^= ints2[i];
        }
        P.pln("两个奇数次是:"+temp);

    }


    //根据异或找出出现奇数次的数
    private static void test1() {
        P.pln("test1--------------------------");
        int[] ints = new int[]{0,0,1,1,2,2,3,3,4,4,5};
        int tmp = 0;
        P.pln(tmp);
        for (int i = 0; i < ints.length; i++) {
            tmp ^=ints[i];
        }
        P.pln(tmp);
        P.pln(ints.length+" : "+Arrays.toString(ints));
    }
}
