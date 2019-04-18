package com;

import com.sun.istack.internal.NotNull;
import com.utils.P;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

class Base
{
    public void method()
    {
        System.out.println("Base");
    }
}
class Son extends Base
{
    public void method()
    {
        System.out.println("Son");
    }

    public void methodB()
    {
        System.out.println("SonB");
    }
}
public class Test
{
    public static void main(String[] args)
    {
        Son base = new Son();
        base.method();
        base.methodB();

        int d[][] =new int[10][10];
        int []d1[] =new int[10][10];

    }
}

