package com.algorithm.zytest;

import java.util.Scanner;

/**
 * 给定一个字符串（数字或大小写字母）, 找出最长的对称的子串（如有多个，输出任意一个）。
 * 例如：
 * 输入：“abbaad”
 * 输出：“abba”
 */

public class Test01 {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String tmp = sc.nextLine();

        longStr(tmp);
    }

    private static void longStr(String abc) {
        int length = abc.length();
        if(length == 1){
            System.out.println(abc);
            return;

        }

        for (int testLength = length; testLength > 0; testLength--) {

            for (int x = 0; x < length - testLength; x++) {
                String tmp = abc.substring(x, x + testLength + 1);
                boolean flag = isSymmetry(tmp);
                //P.pln(tmp + ":" + flag);
                if (flag) {
                    System.out.println(tmp);
                    return;
                }
            }
        }
    }

    public static boolean isSymmetry(String str) {

        int tmpStart = 0;
        int tmpEnd = str.length() - 1;

        boolean flag = false;
        while (str.charAt(tmpStart) == str.charAt(tmpEnd)) {
            if (tmpStart == (tmpEnd - 1)) {
                flag = true;
                break;
            }

            tmpStart++;
            tmpEnd--;

            if (tmpStart == tmpEnd) {
                flag = true;
                break;
            }
        }

        return flag;
    }


}
