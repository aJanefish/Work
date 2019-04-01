package com.pattern;

import com.utils.P;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Example01 {
    public static void main(String args[]) {
        test1();
        test2();
        test3();
        test4();
    }

    private static void test4() {
        show(4);
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("22bb23");
        P.pln(m.find());//返回true
        Matcher m2 = p.matcher("aa2223");
        P.pln(m2.find());//返回true
        Matcher m3 = p.matcher("aa2223bb");
        P.pln(m3.find());//返回true
        Matcher m4 = p.matcher("aabb");
        P.pln(m4.find());//返回false
    }

    private static void test3() {
        show(3);
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("22bb23");
        P.pln(m.matches());//返回false,因为bb不能被\d+匹配,导致整个字符串匹配未成功.
        Matcher m2 = p.matcher("2223");
        P.pln(m2.matches());//返回true,因为\d+匹配到了整个字符串


        Matcher m11 = p.matcher("22bb23");
        P.pln(m11.lookingAt());//返回true,因为\d+匹配到了前面的22
        Matcher m22 = p.matcher("aa2223");
        P.pln(m22.lookingAt());//返回false,因为\d+不能匹配前面的aa
    }

    private static void test2() {
        show(2);
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("22b1b23");
        Pattern pattern = m.pattern();//返回p 也就是返回该Matcher对象是由哪个Pattern对象的创建的
        P.pln(p + " - " + pattern);
        P.pln(m.matches() + ":" + m.lookingAt());
        while (m.find()) {
            P.pln(m.start() + " . " + m.end());
        }
    }

    private static void test1() {
        show(1);
        Pattern p = Pattern.compile("\\d+");
        String[] str = p.split("我的QQ是:456456我的电话是:0532214我的邮箱是:aaa@aaa.com");
        P.pln(Arrays.toString(str));
    }

    public static void show(int i) {
        P.pln("test..........................." + i);
    }
}
