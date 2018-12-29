package com;

import com.utils.Print;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo001 implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static int ii = 0;

    @Override
    public void run() {

        Print.P("wait lock");
        lock.lock();
        //支持重入锁
        lock.lock();
        lock.lock();

        try {
            //查询当前线程保持此锁的次数。
            int count = lock.getHoldCount();
            Print.P(this + " , count:" + count);
            //返回目前拥有此锁的线程，如果此锁不被任何线程拥有，则返回 null。
            //protected  Thread   getOwner();
            //返回正等待获取此锁的线程估计数。
            int quene = lock.getQueueLength();
            Print.P("quene:"+quene);
            //查询是否有些线程正在等待获取此锁。
            //boolean hasQueuedThreads();
            boolean has = lock.hasQueuedThreads();
            Print.P("has:"+has);
            //查询当前线程是否保持此锁。
            boolean currentLock = lock.isHeldByCurrentThread();
            Print.P("currentLock:"+currentLock);

            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boolean isLocked = lock.isLocked();
            Print.P("isLocked 1:"+isLocked);
            //执行两次解锁
            lock.unlock();
            lock.unlock();
            lock.unlock();
            //查询此锁是否由任意线程保持。
            isLocked = lock.isLocked();
            Print.P("isLocked 2:"+isLocked);
            Print.P("finally");
        }




    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo001 tl = new ReentrantLockDemo001();
        Thread t1 = new Thread(tl);
        Thread t2 = new Thread(tl);
        Thread t3 = new Thread(tl);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        //输出结果：20000000
    }
}

////查询当前线程保持此锁的次数。
//int getHoldCount()
//
////返回目前拥有此锁的线程，如果此锁不被任何线程拥有，则返回 null。
//protected  Thread   getOwner();
//
////返回一个 collection，它包含可能正等待获取此锁的线程，其内部维持一个队列，这点稍后会分析。
//protected  Collection<Thread>   getQueuedThreads();
//
////返回正等待获取此锁的线程估计数。
//int getQueueLength();
//
//// 返回一个 collection，它包含可能正在等待与此锁相关给定条件的那些线程。
//protected  Collection<Thread>   getWaitingThreads(Condition condition);
//
////返回等待与此锁相关的给定条件的线程估计数。
//int getWaitQueueLength(Condition condition);
//
//// 查询给定线程是否正在等待获取此锁。
//boolean hasQueuedThread(Thread thread);
//
////查询是否有些线程正在等待获取此锁。
//boolean hasQueuedThreads();
//
////查询是否有些线程正在等待与此锁有关的给定条件。
//boolean hasWaiters(Condition condition);
//
////如果此锁的公平设置为 true，则返回 true。
//boolean isFair()
//
////查询当前线程是否保持此锁。
//boolean isHeldByCurrentThread()
//
////查询此锁是否由任意线程保持。
//boolean isLocked()

