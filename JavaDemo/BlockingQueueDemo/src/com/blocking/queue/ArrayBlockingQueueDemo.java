package com.blocking.queue;

import com.blocking.queue.utils.Print;
import sun.dc.pr.PRError;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueDemo {

    private static int capacity = 1;
    private final static ArrayBlockingQueue<Apple> queue= new ArrayBlockingQueue<>(capacity);
    public static void main(String[] args){
        new Thread(new Producer(queue,capacity)).start();
        //new Thread(new Producer(queue)).start();
        //new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue,capacity)).start();
    }
}

class Apple {
    public static int id = 0;
    private String name = "apple";
    private int tmpID;
    public Apple(){
        tmpID = id ++;
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
class Producer implements Runnable{
    private final ArrayBlockingQueue<Apple> mAbq;
    private final int capacity;

    Producer(ArrayBlockingQueue<Apple> arrayBlockingQueue, int capacity){
        this.mAbq = arrayBlockingQueue;
        this.capacity =capacity;
    }

    @Override
    public void run() {
        while (true) {
            Produce();

            if(Apple.id > 5){
                return;
            }
        }
    }

    private void Produce(){
        try {
            Print.P("Produce  -- start");
            Apple apple = new Apple();
            if(mAbq.size() < capacity){

            }else {
                System.out.println("队列已满,需要等待资源释放...");
            }
            mAbq.put(apple);
            System.out.println("生产:"+apple);
            Print.P("Produce  -- end\n\n");
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {

        }
    }
}

/**
 * 消费者线程
 */
class Consumer implements Runnable{

    private final int capacity;
    private ArrayBlockingQueue<Apple> mAbq;
    Consumer(ArrayBlockingQueue<Apple> arrayBlockingQueue, int capacity){
        this.mAbq = arrayBlockingQueue;
        this.capacity =capacity;
    }

    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                comsume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(Apple.id > 5){
                return;
            }
        }
    }

    private void comsume() throws InterruptedException {

        String tmp = "\t\t\t\t\t\t\t\t\t\t\t\t\t";
        Print.P(tmp+"Consumer...start");
        if(mAbq.size() == 0){
            Print.P(tmp+"队列为空,需要生产者生成资源...");
        }
        Apple apple = mAbq.take();
        System.out.println(tmp+"消费:Apple="+apple);
        Print.P(tmp+"Consumer...end\n\n");
    }
}

