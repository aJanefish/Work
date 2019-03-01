package com.algorithm.algorithm;

import com.algorithm.utils.P;

import java.util.Random;

/**
 * 辗转相除法， 又名欧几里得算法（Euclidean algorithm），目的是求出两个正整数的最大公约数。
 * 它是已知最古老的算法， 其可追溯至公元前300年前。
 * <p>
 * 这条算法基于一个定理：两个正整数a和b（a>b），它们的最大公约数等于a除以b的余数c和b之间的最大公约数。
 * 比如10和25，25除以10商2余5,那么10和25的最大公约数，等同于10和5的最大公约数。
 */
public class Demo003 {
    private static int MAXNUMBER = Integer.MAX_VALUE;
    private static int MINNUMBER = Integer.MIN_VALUE;

    //12 8
    public static void main(String[] args) {

        P.pln(MAXNUMBER + " -- " + MINNUMBER);
        Random random = new Random();
        int a = random.nextInt(MAXNUMBER);
        int b = random.nextInt(MAXNUMBER);
        P.pln(a + ":" + b);
        int max = a > b ? a : b;
        int min = a + b - max;
        P.pln(max + ":" + min);

        test1(max, min);
        test2(max, min);
        test3(max, min);
        tmpNumTest4 = 0;
        test4(max, min);
    }

    //更相减损术 和 辗转相除法 相结合的方法
    /**
     * 众所周知，移位运算的性能非常快。对于给定的正整数a和b，不难得到如下的结论。其中gcb(a,b)的意思是a,b的最大公约数函数：
     *
     * 当a和b均为偶数，gcb(a,b) = 2*gcb(a/2, b/2) = 2*gcb(a>>1, b>>1)
     *
     * 当a为偶数，b为奇数，gcb(a,b) = gcb(a/2, b) = gcb(a>>1, b)
     *
     * 当a为奇数，b为偶数，gcb(a,b) = gcb(a, b/2) = gcb(a, b>>1)
     *
     * 当a和b均为奇数，利用更相减损术运算一次，gcb(a,b) = gcb(b, a-b)， 此时a-b必然是偶数，又可以继续进行移位运算。
     * */
    private  static int tmpNumTest4 = 0;
    private static void test4(int max, int min) {
        tmpNumTest4 ++;
        //P.pln("更相减损术 和 辗转相除法 相结合的方法-------------------:"+max+" - "+min);
        if(max == min){
            P.pln(max + "和" + min + "的最大公约数是:" + min + " , tmpNumTest4:"+tmpNumTest4);
            return;
        }
        if(max < min){
            test4(min,max);
        }else {
            if((max&0x01) == 0 && (min&0x01) == 0){
                test4(max>>1,min>>1);
            }else if((max&0x01) == 0 && (min&0x01) != 0){
                test4(max>>1,min);
            }
            else if((max&0x01) != 0 && (min&0x01) == 0){
                test4(max,min>>1);
            }else {
                test4(min,max - min);
            }
        }
    }

    /**
     * 更相减损术， 出自于中国古代的《九章算术》，也是一种求最大公约数的算法。
     * <p>
     * 他的原理更加简单：两个正整数a和b（a>b），它们的最大公约数等于a-b的差值c和较小数b的最大公约数。
     * 比如10和25，25减去10的差是15,那么10和25的最大公约数，等同于10和15的最大公约数。
     */

    private static void test3(int max, int min) {
        P.pln("更相减损术-------------------");
        int a = max;
        int b = min;
        int tmpNum = 0;
        long start = System.currentTimeMillis();
        while (a != b ) {
            tmpNum++;
            int tmp = a - b;//当ab较大时,ab的取模运算效率较低

            int tmpMax = tmp > b ? tmp : b;
            int tmpMin = tmp + b - tmpMax;

            a = tmpMax;
            b = tmpMin;
            //P.pln(a + " -- " + b);
        }
        long end = System.currentTimeMillis();
        P.pln("耗时:" + (end - start) + " tmpNum:" + tmpNum);
        P.pln(max + "和" + min + "的最大公约数是:" + b);
    }

    private static void test2(int max, int min) {
        P.pln("辗转相除法-------------------");
        int a = max;
        int b = min;
        int tmpNum = 0;
        long start = System.currentTimeMillis();
        while (a % b != 0) {
            tmpNum++;
            int tmp = a % b;//当ab较大时,ab的取模运算效率较低
            a = b;
            b = tmp;
        }
        long end = System.currentTimeMillis();
        P.pln("耗时:" + (end - start) + " tmpNum:" + tmpNum);
        P.pln(max + "和" + min + "的最大公约数是:" + b);
    }

    //基本方法 暴力枚举求解
    private static void test1(int max, int min) {
        P.pln("暴力枚举求解 -------------------------");

        long start = System.currentTimeMillis();
        int tmpNum = 0;
        if (max % min == 0) {
            P.pln(max + "和" + min + "的最大公约数是:" + min);
        } else {
            int tmp = min / 2;
            P.pln(tmp);

            for (int i = tmp; i > 0; i--) {
                tmpNum++;
                if (min % i == 0 && max % i == 0) {
                    P.pln(max + "和" + min + "的最大公约数是:" + i);
                    P.pln(max + "/" + i + "=" + (max / i) + ":" + (max % i));
                    P.pln(min + "/" + i + "=" + (min / i) + ":" + (min % i));
                    break;
                }
            }
        }

        long end = System.currentTimeMillis();
        P.pln("耗时:" + (end - start) + " tmpNum:" + tmpNum);
    }
}
