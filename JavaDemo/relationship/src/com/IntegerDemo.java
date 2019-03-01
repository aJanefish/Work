package com;

import com.utils.P;

/**
 * 1.Integer 是int的包装类，int则是java的一种基本数据类型
 * 2.Integer变量必须实例化后才能使用，而int变量不需要
 * 3.Integer实际是对象的引用，当new一个Integer时，实际上是生成一个指针指向此对象；而int则是直接存储数据值
 * 4.integer的默认值是null，int的默认值是0
 *
 * */
public class IntegerDemo {
    public static void main(String[] args) {
        P.pln("relationship");
        //int和Integer的区别
        // 4 1 8
        Integer i1 =  1;
        Integer i2 =  1;

        Integer i3 =  128;
        Integer i4 =  128;
        P.pln(i1 == i2);
        P.pln(i3 == i4);

        Integer i5 =  new Integer(128);
        Integer i6 =  new Integer(128);
        P.pln(i5 == i6);

        Integer i7 =  new Integer(1);
        Integer i8 =  new Integer(1);
        P.pln(i7 == i8);

        Integer i9 =  Integer.valueOf(1);
        Integer i10 =  Integer.valueOf(1);
        Integer i99 = new Integer(1);
        P.pln((i9 == i10) +" : "+( i1 == i9) +" : "+(i1 == i99));

        Integer i11 =  Integer.valueOf(12222);
        Integer i12 =  Integer.valueOf(12222);
        P.pln(i11 == i12);
    }
}
