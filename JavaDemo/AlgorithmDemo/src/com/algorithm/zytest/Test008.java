package com.algorithm.zytest;

import java.util.Scanner;

/**
 * 正整数A的“DA（为1位整数）部分”定义为由A中所有DA组成的新整数PA。例如：给定A = 3862767，DA = 6，则A的“6部分”PA是66，因为A中有2个6。
 * <p>
 * 现给定A、DA、B、DB，请编写程序计算PA + PB。
 */
public class Test008 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int v1 = sc.nextInt();
        int v2 = sc.nextInt();
        int v3 = sc.nextInt();
        int v4 = sc.nextInt();
        //System.out.println(v1+":"+v2+":"+v3+":"+v4);


        String Str1 = Integer.toString(v1);
        String Str2 = Integer.toString(v2);
        char cha2 = Str2.charAt(0);

        String Str3 = Integer.toString(v3);
        String Str4 = Integer.toString(v4);
        char cha4 = Str4.charAt(0);
        //System.out.println(Str1+":"+cha2+":"+Str3+":"+cha4);
        int tmp1 = 0;
        for (int i = 0; i < Str1.length(); i++) {
            if (cha2 == Str1.charAt(i)) {
                tmp1++;
            }
        }


        int tmp3 = 0;
        for (int i = 0; i < Str3.length(); i++) {
            if (cha4 == Str3.charAt(i)) {
                tmp3++;
            }
        }

        //System.out.println(tmp1+":"+tmp3);
        int add1 = 0;
        if (tmp1 == 0) {

        } else {
            StringBuilder stringBuilder1 = new StringBuilder();
            for (int i = 0; i < tmp1; i++) {
                stringBuilder1.append(cha2);
            }

            add1 = Integer.parseInt(stringBuilder1.toString());
        }


        int add2 = 0;
        if (tmp3 == 0) {

        } else {

            StringBuilder stringBuilder2 = new StringBuilder();
            for (int i = 0; i < tmp3; i++) {
                stringBuilder2.append(cha4);
            }

            add2 = Integer.parseInt(stringBuilder2.toString());
        }

        System.out.print(add1 + add2);

    }
}
