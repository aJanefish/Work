package com;

import com.utils.Print;
import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS
 * CAS的全称是Compare And Swap 即比较交换，其算法核心思想如下
 * 执行函数：CAS(V,E,N)
 * 其包含3个参数
 * V表示要更新的变量
 * E表示预期值
 * N表示新值
 * 如果V值等于E值，则将V的值设为N。
 * 若V值和E值不同，则说明已经有其他线程做了更新，则当前线程什么都不做。通
 * 俗的理解就是CAS操作需要我们提供一个期望值，当期望值与当前线程的变量值相同时，说明还没线程修改该值，当前线程可以进行修改，也就是执行CAS操作，
 * 但如果期望值与当前线程不符，则说明该值已被其他线程修改，此时不执行更新操作，但可以选择重新读取该变量再尝试再次修改该变量，也可以放弃操作
 * <p>
 * 作者：zejian_
 * 来源：CSDN
 * 原文：https://blog.csdn.net/javazejian/article/details/72772470
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 */
public class AtomicMain {
    String name = "AtomicMain";

    private AtomicMain(String name) {
        this.name = name;
    }

    public static void main(String[] ages) {
        AtomicInteger atomicInteger = new AtomicInteger(1);


        Unsafe unsafe = Unsafe.getUnsafe();
        Print.P(unsafe);

    }


    @Override
    public String toString() {
        return "AtomicMain{" +
                "name='" + name + '\'' +
                '}';
    }
}
