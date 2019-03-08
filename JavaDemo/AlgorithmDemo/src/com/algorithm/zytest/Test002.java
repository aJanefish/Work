package com.algorithm.zytest;


import com.algorithm.algorithm.PriorityQueueDemo;
import com.algorithm.utils.P;

import java.util.Arrays;

/**
 * 给定两个数字（0-9）字符串（长度不限）求它们的乘积。
 */
public class Test002 {

    public static void main(String[] args) throws Exception {
        String values1 = "12341546132457645124578595645356789";
        String values2 = "9876543785364535635478364564564364534534554345345421";
        String sum = addString(values1, values2);

        P.pln(values1 + "+" + values2 + "=" + sum);
        String mul = multiplication(values1, values2);
        P.pln(mul.length());
        P.pln(mul);
    }

    private static String multiplication(String values1, String values2) throws Exception {
        String longValues = values1.length() > values2.length() ? values1 : values2;
        String shortValues = longValues == values1 ? values2 : values1;
        P.pln(longValues + ":" + shortValues);
        String sum = "";
        for (int i = shortValues.length() - 1; i >= 0; i--) {
            char ch = shortValues.charAt(i);
            int mul = charToInt(ch);
            if (mul > 0) {
                String values = upgrade(longValues, shortValues.length() - 1 - i);
                P.pln(values);
                for (int i1 = 0; i1 < mul; i1++) {
                    sum = addString(sum, values);
                }
            }
        }

        return sum;
    }

    /**
     * 提升values等级
     * 123 0 =  123
     * 123 1 =  1230
     * 123 2 =  12300
     * 123 3 =  123000
     * 123 4 =  1230000
     */
    private static String upgrade(String values, int grade) {
        if (grade == 0) {
            return values;
        }
        StringBuilder stringBuilder = new StringBuilder(values);
        for (int i = 0; i < grade; i++) {
            stringBuilder.append(0);
        }
        return stringBuilder.toString();
    }


    private static String addString(String add1, String add2) throws Exception {
        add1 = deleteZero(add1);
        add2 = deleteZero(add2);
        int addLength1 = add1.length();
        int addLength2 = add2.length();
        int length = addLength1 > addLength2 ? addLength1 : addLength2;
        char[] chars = new char[length + 1];

        int tmp1 = addLength1 - 1;
        int tmp2 = addLength2 - 1;
        int tmp3 = chars.length - 1;
        boolean carry = false;
        while ((tmp1 >= 0) && (tmp2 >= 0)) {

            int a1 = charToInt(add1.charAt(tmp1));
            int a2 = charToInt(add2.charAt(tmp2));
            //P.pln(a1 + "+" + a2);
            int sum = a1 + a2;

            if (carry) {
                chars[tmp3] = intToChar((sum + 1) % 10);
            } else {
                chars[tmp3] = intToChar(sum % 10);
            }
            carry = (sum / 10) > 0 ? true : false;

            tmp1--;
            tmp2--;
            tmp3--;
        }
        //P.pln(tmp1 + ":" + tmp2 + ":" + tmp3 + ":" + carry);
        if (tmp1 < 0) {
            while (tmp2 >= 0) {
                if (carry) {
                    int a2 = add2.charAt(tmp2) - '0';
                    chars[tmp3] = intToChar((a2 + 1) % 10);
                    carry = (a2 + 1) >= 10;
                } else {
                    chars[tmp3] = add2.charAt(tmp2);
                }

                tmp2--;
                tmp3--;

            }
        } else {
            while (tmp1 >= 0) {
                if (carry) {
                    int a1 = add1.charAt(tmp1) - '0';
                    chars[tmp3] = intToChar((a1 + 1) % 10);
                    carry = (a1 + 1) >= 10;
                } else {
                    chars[tmp3] = add1.charAt(tmp1);
                }

                tmp1--;
                tmp3--;

            }
        }

        if (carry) {
            chars[tmp3] = intToChar(1);
            carry = false;
        }

        //P.pln(Arrays.toString(chars));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            stringBuilder.append((char) chars[i]);
        }

        return deleteZero(stringBuilder.toString());

    }

    private static int charToInt(char ch) {
        return ch - '0';
    }

    private static char intToChar(int i) throws Exception {
        if (i < 0 || i > 9) {
            throw new Exception("values is not defined");
        }
        return (char) ('0' + i);
    }

    private static String deleteZero(String values) {
        int minZero = 0;
        for (int i = 0; i < values.length(); i++) {
            char ch = values.charAt(i);
            int tmp = ch - '0';
            if (tmp < 1 || tmp > 9) {
                minZero = i + 1;
            } else {
                break;
            }
        }
        return values.substring(minZero);
    }
}
