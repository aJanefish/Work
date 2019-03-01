package com;

/**
 * 普通内部类，局部内部类，匿名内部类，嵌套内部类
 * */

public class InnerDemo extends WithInner.Inner {

    public InnerDemo(WithInner withInner) {
        withInner.super(); //必须有这句调用
    }

    public static void main(String[] args){

    }



}

class WithInner {
    class Inner{

    }
}
