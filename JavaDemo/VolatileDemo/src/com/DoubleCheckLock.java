package com;

import com.utils.Print;

import java.util.concurrent.TimeUnit;

//单例模式
public class DoubleCheckLock {

    //禁止指令重排优化
    private volatile static DoubleCheckLock instance;

    private DoubleCheckLock(){

    }

    public static DoubleCheckLock getInstance(int tmp){
        //第一次检测
        if (instance==null){
            //同步
            Print.P(tmp+",1:"+instance);
            synchronized (DoubleCheckLock.class){
                Print.P(tmp+",2:"+instance);
                if (instance == null){
                    Print.P(tmp+",3:"+instance);
                    //多线程环境下可能会出现问题的地方
                    instance = new DoubleCheckLock();
                    Print.P(tmp+",4:"+instance);
                }
            }
        }
        return instance;
    }
}

