package com.zy.demo.utf;

import com.zy.demo.thread.ThreadTest;

import java.io.UnsupportedEncodingException;

/**
 * 可通过 byte[] bytes=“xxxx”.getBytes("utf-8")得到字符串通过utf-8解析到字节数组。
 * utf-8编码格式下，计算机采用1个字节存储ASCII范围内的字符，采用3个字节储存中文字符。
 * UTF-8是一种变长字节编码方式。对于某一个字符的UTF-8编码，如果只有一个字节则其最高二进制位为0；
 * 如果是多字节，其第一个字节从最高位开始，连续的二进制位值为1的个数决定了其编码的位数，其余各字节均以10开头。
 * UTF-8最多可用到6个字节。
 *
 *
 * 如表：
 * 1字节 0xxxxxxx
 * 2字节 110xxxxx 10xxxxxx
 * 3字节 1110xxxx 10xxxxxx 10xxxxxx
 * 4字节 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * 5字节 111110xx 10xxxxxx 10xxxxxx 10xxxxxx 10xxxxxx
 * 6字节 1111110x 10xxxxxx 10xxxxxx 10xxxxxx 10xxxxxx 10xxxxxx
 *
 * 注意:计算中中utf-8编码存储多字节字符时，并未将8个二进制位的首位作为符号位，如直接输出，得到的将是负数
 * */
public class UtfDemo {

    public static  void main(String[] args){
        System.out.println("Test Start");
        UtfDemo utfDemo= new UtfDemo();
        utfDemo.test5();

    }

    private void test5(){
        byte[] bss = new byte[0];
        try {
            //中文是三个字节
            bss = "宇".getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("bss长度:"+bss.length);//输出:27，一个中文用三个字节存储。

        //输出:-24 -65 -103 -26 -104 -81 -28 -72 -128 -28 -72 -86 -25 -91 -98 -27 -91 -121 -25 -102 -124 -28 -72 -106 -25 -107 -116
        for (byte b:bss) {
            System.out.println(b+" "+intTp2(b)+", "+Integer.toBinaryString(b&0xff));
        }
    }


    private static String intTp2(int date){
        int data = 10;
        String binaryStr = java.lang.Integer.toBinaryString(date);
        //System.out.println("the result is : " + binaryStr);
        return binaryStr;

//        byte results[] = new byte[0];
//        try {
//            results = binaryStr.getBytes("utf8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        for(int i = 0;i < results.length ; i++){
//            System.out.println("the " + i +  " result is : " + results[i]);//"1"的ascii码是49。
//        }

    }


    private void test4(){
        byte[] bss = new byte[0];
        try {
            //中文是三个字节
            bss = "张".getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("bss长度:"+bss.length);//输出:27，一个中文用三个字节存储。

        //输出:-24 -65 -103 -26 -104 -81 -28 -72 -128 -28 -72 -86 -25 -91 -98 -27 -91 -121 -25 -102 -124 -28 -72 -106 -25 -107 -116
        for (byte b:bss) {
            System.out.println(b+" "+intTp2(b)+", "+Integer.toBinaryString(b&0xff));
        }
    }

    private void test3(){
        byte[] bss = new byte[0];
        try {
            //中文是三个字节
            bss = "lovs3".getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("bss长度:"+bss.length);//输出:27，一个中文用三个字节存储。

        //输出:-24 -65 -103 -26 -104 -81 -28 -72 -128 -28 -72 -86 -25 -91 -98 -27 -91 -121 -25 -102 -124 -28 -72 -106 -25 -107 -116
        for (byte b:bss) {
            System.out.println(b+" "+intTp2(b)+", "+Integer.toBinaryString(b&0xff));
        }
    }

    private void test2(){
        byte[] bss = new byte[0];
        try {
            //中文是三个字节
            bss = "我".getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("bss长度:"+bss.length);//输出:27，一个中文用三个字节存储。

        //输出:-24 -65 -103 -26 -104 -81 -28 -72 -128 -28 -72 -86 -25 -91 -98 -27 -91 -121 -25 -102 -124 -28 -72 -106 -25 -107 -116
        for (byte b:bss) {
            System.out.print(b+" ");
        }
    }

    private void test1(){
        byte[] bss = new byte[0];
        try {
            //字符是一个字节
            bss = "s".getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("bss长度:"+bss.length);//输出:27，一个中文用三个字节存储。

        //输出:-24 -65 -103 -26 -104 -81 -28 -72 -128 -28 -72 -86 -25 -91 -98 -27 -91 -121 -25 -102 -124 -28 -72 -106 -25 -107 -116
        for (byte b:bss) {
            System.out.print(b+" ");
        }
    }
}
