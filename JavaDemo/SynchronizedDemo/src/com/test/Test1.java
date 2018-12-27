package com.test;

//synchronized作用于实例方法
//所谓的实例对象锁就是用synchronized修饰实例对象中的实例方法，注意是实例方法不包括静态方法
public class Test1  implements Runnable{

    //共享资源(临界资源)
    public static int i=0;

    /**
     * synchronized 修饰实例方法
     */
    public synchronized void increase(){
        i++;
    }


    @Override
    public void run() {
        for(int j=0;j<100000;j++){
            increase();
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
