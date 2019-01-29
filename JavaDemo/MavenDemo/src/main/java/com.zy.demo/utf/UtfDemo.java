package com.zy.demo.utf;

import com.zy.demo.thread.ThreadTest;
import com.zy.demo.utils.Print;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * 可通过 byte[] bytes=“xxxx”.getBytes("utf-8")得到字符串通过utf-8解析到字节数组。
 * utf-8编码格式下，计算机采用1个字节存储ASCII范围内的字符，采用3个字节储存中文字符。
 * UTF-8是一种变长字节编码方式。对于某一个字符的UTF-8编码，如果只有一个字节则其最高二进制位为0；
 * 如果是多字节，其第一个字节从最高位开始，连续的二进制位值为1的个数决定了其编码的位数，其余各字节均以10开头。
 * UTF-8最多可用到6个字节。
 * <p>
 * <p>
 * 如表：
 * 1字节 0xxxxxxx
 * 2字节 110xxxxx 10xxxxxx
 * 3字节 1110xxxx 10xxxxxx 10xxxxxx
 * 4字节 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * 5字节 111110xx 10xxxxxx 10xxxxxx 10xxxxxx 10xxxxxx
 * 6字节 1111110x 10xxxxxx 10xxxxxx 10xxxxxx 10xxxxxx 10xxxxxx
 * <p>
 * 注意:计算中中utf-8编码存储多字节字符时，并未将8个二进制位的首位作为符号位，如直接输出，得到的将是负数
 */
public class UtfDemo {

    public static void main(String[] args) {
        System.out.println("Test Start");
        UtfDemo utfDemo = new UtfDemo();
        char cha = 'H';
        Print.println(cha + ":" + (int) cha);
        byte[] ss = utfDemo.toUtf_8("H");
        Print.println("bss长度:" + ss.length);
        Print.println(Arrays.toString(ss));
        System.out.println("二进制:" + intTp2(5));

        int[] array = {76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59};
        char[] chars = intToChar(array);
        Print.println(Arrays.toString(chars));
        String[]  array1 = {"28", "29", "56", "64","65","3b"};
        hexToChar(array1);

//        String[] array2 = {"28", "49", "4c", "6a", "61", "76","61","2f","6c","61","6e","67","2f","53","74","72","69","6e","67","3b","29","56"};
//        hexToChar(array2);
//
        String[] array2 = {"6a", "61", "76", "61", "2f", "6c","61","6e","67","2f","4f","62","6a","65","63","74","67","3b","29","56"};
        hexToChar(array2);
        /**/
    }


    private static void hexToChar(String[]  array) {
        int[] ints = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            ints[i] = Integer.parseInt(array[i],16);
        }
        Print.println(Arrays.toString(array));
        Print.println(Arrays.toString(ints));
        char[] chars = intToChar(ints);
        Print.println(Arrays.toString(chars));
    }

    public static char[] intToChar(int[] array) {
        char[] chars = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            chars[i] =  (char) array[i];
        }

        return chars;
    }


    //int 转二进制
    private static String intTp2(int date) {
        int data = 10;
        String binaryStr = java.lang.Integer.toBinaryString(date);
        //System.out.println("the result is : " + binaryStr);
        return binaryStr;
    }

    private byte[] toUtf_8(String values) {
        byte[] bss = new byte[0];
        try {
            //字符是一个字节
            bss = values.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bss;
    }
}
