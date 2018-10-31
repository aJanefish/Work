package com.zy.demo.thread;

public class MyRunnable implements Runnable {
    private int id = 0;

    public MyRunnable(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println(id+" --- "+this+","+System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
