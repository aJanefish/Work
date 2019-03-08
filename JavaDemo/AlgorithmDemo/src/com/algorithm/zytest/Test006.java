package com.algorithm.zytest;


import com.algorithm.utils.P;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 大侦探福尔摩斯接到一张奇怪的字条：“我们约会吧！ 3485djDkxh4hhGE 2984akDfkkkkggEdsb s&hgsfdk d&Hyscvnm”。大侦探很
 * <p>
 * 快就明白了，字条上奇怪的乱码实际上就是约会的时间“星期四 14:04”，
 * 因为前面两字符串中第1对相同的大写英文字母（大小写有区分）是第4个字母'D'，代表星期四；
 * 第2对相同的字符是'E'，那是第5个英文字母，代表一天里的第14个钟头（于是一天的0点到23点由数字0到9、以及大写字母A到N表示）；
 * 后面两字符串第1对相同的英文字母's'出现在第4个位置（从0开始计数）上，代表第4分钟。现给定两对字符串，
 * <p>
 * 请帮助福尔摩斯解码得到约会的时间。
 */

public class Test006 {

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        int size = 4;
        String[] values = new String[4];
        for (int i = 0; i < size; i++) {
            values[i] = sc.nextLine();
        }
        //3485djEkxh4hhGE
        //2984akEfkkkkggEdsb
        //s&hgsfdk
        //d&Hyscvnm
//        values[0] = "3485djEkxh4hhGE";
//        values[1] = "2984akEfkkkkggEdsb";
//        values[2] = "s&hgsfdk";
//        values[3] = "d&Hyscvnm";

        //values[0] values[1]

        //找出相同的字母 1 代表星期  2 代表小时  3 代表分钟
        int state = 0;
        int tmpLenght = 0;
        while (tmpLenght < values[0].length() && tmpLenght < values[1].length()){

            char ch1 = values[0].charAt(tmpLenght);
            char ch2 = values[1].charAt(tmpLenght);
            if (ch1 == ch2) {
                if (state == 0) {
                    if (ch1 >= 'A' && ch1 <= 'Z') {
                        int week = ch1 - 'A';
                        //P.pln(state + ":" + week + ":" + ch1 + ":" + ch2);
                        Week week1 = Week.values()[week];
                        System.out.print(week1 + " ");
                        state++;
                    }
                } else {
                    //小时数
                    if ((ch1 >= 'A' && ch1 <= 'Z') || (ch1 >= '0' && ch1 <= '9')) {
                        if (ch1 <= '9') {
                            System.out.print("0"+ch1+":");
                        } else {
                            int count = ch1 - 'A' + 1;
                            System.out.print((9 + count) + ":");
                        }
                        break;
                    }
                }
            }

            tmpLenght ++;
        }


        tmpLenght = 0;
        while (tmpLenght < values[2].length() && tmpLenght < values[3].length()){
            char ch1 = values[2].charAt(tmpLenght);
            char ch2 = values[3].charAt(tmpLenght);

            if (ch1 == ch2) {
                if ((ch1 >= 'a' && ch1 <= 'z') || (ch1 >= 'A' && ch1 <= 'Z')) {
                    if (tmpLenght < 9) {
                        System.out.print("0" + tmpLenght);
                    } else {
                        System.out.print(tmpLenght);
                    }
                    break;
                }
            }
            tmpLenght++;
        }
    }


    //MON表示星期一，TUE表示星期二，WED表示星期三，THU表示星期
    //四，FRI表示星期五，SAT表示星期六，SUN表示星期日。题目输入保证每个测试存在唯一解。
    enum Week {
        MON(0),
        TUE(1),
        WED(2),
        THU(3),
        FRI(4),
        SAT(5),
        SUN(6);
        public int id;

        Week(int id) {
            this.id = id;
        }
    }
}

