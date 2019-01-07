package com.blocking.queue;

/**
 * LinkedBlockingQueue是一个由链表实现的有界队列阻塞队列，但大小默认值为Integer.MAX_VALUE
 * 所以我们在使用LinkedBlockingQueue时建议手动传值，为其提供我们所需的大小，避免队列过大造成机器负载或者内存爆满等情况
 * */

import com.blocking.queue.utils.Print;

import java.util.AbstractQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


public class LinkedBlockingQueueDemo {

    private static int capacity = 1;
    private final static LinkedBlockingQueue<LinkedApple> queue= new LinkedBlockingQueue<LinkedApple>(capacity);
    public static void main(String[] args){
        new Thread(new LinkedProducer(queue,capacity)).start();
        //new Thread(new Producer(queue)).start();
        //new Thread(new Consumer(queue)).start();
        new Thread(new LinkedConsumer(queue,capacity)).start();
    }



}


class LinkedApple {
    public static int id = 0;
    private String name = "apple";
    private int tmpID;

    public LinkedApple() {
        tmpID = id++;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "name='" + name + '\'' +
                ", tmpID=" + tmpID +
                '}';
    }
}

/**
 * 生产者线程
 */
class LinkedProducer implements Runnable {
    private final AbstractQueue<LinkedApple> mAbq;
    private final int capacity;

    LinkedProducer(AbstractQueue<LinkedApple> arrayBlockingQueue, int capacity) {
        this.mAbq = arrayBlockingQueue;
        this.capacity = capacity;
    }

    @Override
    public void run() {
        while (true) {
            Produce();

            if (Apple.id > 5) {
                return;
            }
        }
    }

    private void Produce() {
        try {
            Print.P("Produce  -- start");
            LinkedApple apple = new LinkedApple();
            if (mAbq.size() < capacity) {

            } else {
                System.out.println("队列已满,需要等待资源释放...");
            }
            if (mAbq instanceof ArrayBlockingQueue) {
                ((ArrayBlockingQueue<LinkedApple>) mAbq).put(apple);
            } else {
                ((LinkedBlockingQueue<LinkedApple>) mAbq).put(apple);
            }

            System.out.println("生产:" + apple);
            Print.P("Produce  -- end\n\n");
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }
    }
}

/**
 * 消费者线程
 */
class LinkedConsumer implements Runnable {

    private final int capacity;
    private AbstractQueue<LinkedApple> mAbq;

    LinkedConsumer(AbstractQueue<LinkedApple> abstractQueue, int capacity) {
        this.mAbq = abstractQueue;
        this.capacity = capacity;
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                comsume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Apple.id > 5) {
                return;
            }
        }
    }

    private void comsume() throws InterruptedException {

        String tmp = "\t\t\t\t\t\t\t\t\t\t\t\t\t";
        Print.P(tmp + "Consumer...start");
        if (mAbq.size() == 0) {
            Print.P(tmp + "队列为空,需要生产者生成资源...");
        }
        LinkedApple apple;
        if (mAbq instanceof ArrayBlockingQueue) {
            apple = ((ArrayBlockingQueue<LinkedApple>) mAbq).take();
        } else {
            apple = ((LinkedBlockingQueue<LinkedApple>) mAbq).take();
        }
        System.out.println(tmp + "消费:Apple=" + apple);
        Print.P(tmp + "Consumer...end\n\n");
    }
}


