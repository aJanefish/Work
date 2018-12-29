package com;

//AbstractQueuedSynchronizer又称为队列同步器(后面简称AQS)，它是用来构建锁或其他同步组件的基础框架
//内部通过一个int类型的成员变量state来控制同步状态,当state=0时，则说明没有任何线程占有共享资源的锁，
// 当state=1时，则说明有线程目前正在使用共享变量，其他线程必须加入同步队列进行等待，
// AQS内部通过内部类Node构成FIFO的同步队列来完成线程获取锁的排队工作，
// 同时利用内部类ConditionObject构建等待队列，
// 当Condition调用wait()方法后，线程将会加入等待队列中，
// 而当Condition调用signal()方法后，线程将从等待队列转移动同步队列中进行锁竞争


import com.utils.Print;

public class AQSDemo {
    public static void main(String[] ages){
        Print.P("AQSDemo main:"+Thread.currentThread());

    }
}
