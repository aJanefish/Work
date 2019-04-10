package com.stream;

import com.utils.P;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {
    public static void main(String args[]) throws ExecutionException, InterruptedException {
        int[] data = new int[]{1,2,3,4,5,6,7,8,9,10,12,1324,24,13251,364,3564,37,3753};
        P.pln(max(data));

    }

    public static int max(int data[]) throws ExecutionException, InterruptedException {
        if (data.length == 1) {
            return data[0];
        } else if (data.length == 0) {
            throw new IllegalArgumentException();
        }

        FindMaxTask findMaxTask1 = new FindMaxTask(data, 0, data.length / 2);
        FindMaxTask findMaxTask2 = new FindMaxTask(data, data.length/2, data.length);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future1 = executorService.submit(findMaxTask1);
        Future<Integer> future2 = executorService.submit(findMaxTask2);



        return Math.max(future1.get(),future2.get());
    }
}
