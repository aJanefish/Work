package com.zy.demo;


import com.zy.demo.test.LinkedHashMapTest;
import com.zy.demo.thread.ThreadTest;

public class MyMain {




    public static  void main(String[] args){
        System.out.println("Test Start");

        //LinkedHashMapTest.test1();
        ThreadTest.executor(ThreadTest.executor1);


    }



}
