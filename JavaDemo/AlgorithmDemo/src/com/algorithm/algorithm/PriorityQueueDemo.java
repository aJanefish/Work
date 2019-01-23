package com.algorithm.algorithm;

import com.algorithm.utils.Print;

import java.util.Arrays;

/**
 * 优先队列
 * <p>
 * 最大优先队列，无论入队顺序，当前最大的元素优先出队。
 * 最小优先队列，无论入队顺序，当前最小的元素优先出队。
 */
public class PriorityQueueDemo {

    private int[] array;
    private int length = 1 << 5;
    private int size = 0;

    public PriorityQueueDemo() {

    }

    public void enQueue(int key) {
        if (size >= length) {
            resize();
        }
        if (this.array == null) {
            this.array = new int[length];
            this.array[size] = key;
            size++;
            return;
        }

        this.array[size] = key;
        size++;
        upAdjust();

    }

    public int deQueue() throws Exception {
        if(size<=0){
            throw new Exception("the queue is empty !");
        }

        int tmp = array[0];
        array[0] = array[size-1];
        array[size-1] = tmp;
        size--;
        downAdjust();

        return tmp;
    }


    //上浮调整
    private void upAdjust() {
        int childIndex = size - 1;
        int parentIndex = (childIndex - 1) >> 1;
        while (childIndex >= 0 && parentIndex >= 0) {
            if (this.array[childIndex] > this.array[parentIndex]) {
                int tmp = this.array[childIndex];
                this.array[childIndex] = this.array[parentIndex];
                this.array[parentIndex] = tmp;

                childIndex = parentIndex;
                parentIndex = (childIndex - 1) >> 1;

            } else {
                break;
            }
        }
    }

    //下沉调整
    private void downAdjust() {
        int parentIndex = 0;
        int childIndex = (parentIndex << 1) + 1;
        //Print.println("\n\n"+parentIndex + ": "+ childIndex);
        while (childIndex < size) {
            //若果左孩子 小于 右孩子
            if (childIndex + 1 < size && array[childIndex] < array[childIndex + 1]) {
                childIndex++;
            }

            //Print.println(""+parentIndex + ": "+ childIndex);

            if (array[parentIndex] < array[childIndex]) {
                int tmp = array[parentIndex];
                array[parentIndex] = array[childIndex];
                array[childIndex] = tmp;

                parentIndex = childIndex;
                childIndex = (childIndex << 1) + 1;
            } else {
                break;
            }

        }
    }

    public void show(){
        int[] ints = Arrays.copyOf(array,size);
        Print.println(Arrays.toString(ints));
    }


    /**
     * resize
     */
    private void resize() {
        //队列容量翻倍
        int newSize = this.length << 1;
        this.array = Arrays.copyOf(this.array, newSize);
    }

    public static void main(String[] args) throws Exception {
        Print.println("优先队列");
        PriorityQueueDemo priorityQueueDemo = new PriorityQueueDemo();
        int tmp =10;
        for (int i = 0; i <= tmp; i++) {
            priorityQueueDemo.enQueue(i);
        }
        priorityQueueDemo.show();


        int queue = priorityQueueDemo.deQueue();
        Print.println(queue);
        priorityQueueDemo.show();

        queue = priorityQueueDemo.deQueue();
        Print.println(queue);
        priorityQueueDemo.show();

        queue = priorityQueueDemo.deQueue();
        Print.println(queue);
        priorityQueueDemo.show();

        queue = priorityQueueDemo.deQueue();
        Print.println(queue);
        priorityQueueDemo.show();

        queue = priorityQueueDemo.deQueue();
        Print.println(queue);
        priorityQueueDemo.show();

        queue = priorityQueueDemo.deQueue();
        Print.println(queue);
        priorityQueueDemo.show();

        queue = priorityQueueDemo.deQueue();
        Print.println(queue);
        priorityQueueDemo.show();


        queue = priorityQueueDemo.deQueue();
        Print.println(queue);
        priorityQueueDemo.show();

        queue = priorityQueueDemo.deQueue();
        Print.println(queue);
        priorityQueueDemo.show();

        queue = priorityQueueDemo.deQueue();
        Print.println(queue);
        priorityQueueDemo.show();

        queue = priorityQueueDemo.deQueue();
        Print.println(queue);
        priorityQueueDemo.show();



    }
}
