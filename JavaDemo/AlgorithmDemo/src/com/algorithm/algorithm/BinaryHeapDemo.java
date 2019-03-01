package com.algorithm.algorithm;

import com.algorithm.utils.P;

import java.util.Arrays;

/**
 * 二叉堆 (最大堆 最小堆)
 * 完全二叉树,根最小.存储时使用层序.
 */
public class BinaryHeapDemo {
    private int size = 0;
    private int[] array;

    public BinaryHeapDemo(int lenght) {
        this.array = new int[lenght];
    }

    private void add(int values) {

        array[size] = values;
        size++;
        //最小堆平衡
        relaseAdd();
    }

    private boolean delete(int values) {
        //判断有没有
        boolean isContain = false;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] == values) {
                isContain = true;
                index = i;
                break;
            }
        }

        if (isContain) {
            if (index == (size - 1)) {
                //如果删除的是最有一个数
                array[index] = 0;
                size--;
            } else {
                //把最后一个数 移到删除的 位置
                array[index] = array[size - 1];
                size--;
                relaseDelete(index);
            }
        }
        return isContain;
    }


    private void relaseDelete(int index) {

        boolean isEnd = true;

        int parent = index;
        while (isEnd){
            isEnd = false;

            //判断是不是叶子结点
            int leftChild = parent*2 +1;
            int rightChild = parent*2 +2;

            if(rightChild<size){

                if(array[leftChild] < array[rightChild]){
                    if(array[leftChild] < array[parent]){
                        int tmp = array[parent];
                        array[parent] = array[leftChild];
                        array[leftChild] = tmp;

                        //继续循环 parent 指向左孩子
                        isEnd = true;
                        parent = leftChild;
                    }
                }else {
                    if(array[rightChild] < array[parent]){
                        int tmp = array[parent];
                        array[parent] = array[rightChild];
                        array[rightChild] = tmp;

                        //继续循环 parent 指向右孩子
                        isEnd = true;
                        parent = rightChild;
                    }
                }

            }else if(leftChild<size){
                if(array[parent] > array[leftChild]){
                    int tmp = array[parent];
                    array[parent] = array[leftChild];
                    array[leftChild] = tmp;
                }
            }else {
                //do nothing
            }
        }
    }

    private void relaseAdd() {
        int parent;
        int child = size - 1;
        if ((child & 0x01) == 1) {//奇数
            parent = (child - 1) >> 1;
        } else {//偶数
            parent = (child - 2) >> 1;
        }

        //P.pln("\n\n"+parent+":"+child);
        while (parent >= 0 && child >= 0) {
            if (array[child] < array[parent]) {
                int tmp = array[child];
                array[child] = array[parent];
                array[parent] = tmp;

                child = parent;

                if ((child & 0x01) == 1) {//奇数
                    parent = (child - 1) >> 1;
                } else {//偶数
                    parent = (child - 2) >> 1;
                }
                //P.pln(parent+":"+child);
            } else {
                //满足最小堆 ,退出
                break;
            }
        }
    }

    private void show() {
        int[] arr = Arrays.copyOf(array, size);
        P.pln(Arrays.toString(arr));
    }


    public static void main(String[] args) {
        P.pln("二叉堆");
        BinaryHeapDemo binaryHeapDemo = new BinaryHeapDemo(16);
        binaryHeapDemo.add(10);
        binaryHeapDemo.add(9);
        binaryHeapDemo.add(8);
        binaryHeapDemo.add(7);
        binaryHeapDemo.add(6);
        binaryHeapDemo.add(5);
        binaryHeapDemo.add(4);
        binaryHeapDemo.add(3);
        binaryHeapDemo.add(2);
        binaryHeapDemo.add(1);
        binaryHeapDemo.add(0);
        binaryHeapDemo.show();
        P.pln(binaryHeapDemo.size);
        binaryHeapDemo.delete(0);
        binaryHeapDemo.show();
    }
}
