package com.algorithm.algorithm;

import com.algorithm.utils.Print;


import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;

public class QuickSortDemo {

    private static Random random = new Random();

    //自己实现
    //[start,end]
    private static void quickSortDiy(int array[], int start, int end) {
        if (start >= end) {
            return;
        }
        //[start,end]
        //找基准元素 随机选择
        int index = partitionDiy(array, start, end);

        //分治法
        //[start,index-1] index  [index+1,end]


        quickSortDiy(array, start, index - 1);


        quickSortDiy(array, index + 1, end);

    }

    private static int partitionDiy(int[] array, int start, int end) {
        //[start,end]
        //Print.println("start:" + start + ", end:" + end);

        int pivot = start;
        int pivotValues = array[start];
        //Print.println("pivot:" + pivot + ",pivotValues:" + pivotValues);
        //数组划分
        //1.挖坑法
        int index = start;
        boolean left_to_right = false;
        for (int left = start, right = end - 1; left <= right; ) {
            //占位移到第一格 start
            if (left == start) {
                if (pivot != start) {
                    array[pivot] = array[start];
                    array[start] = pivotValues;
                }
                left++;
                continue;
            }

            if (left_to_right) {
                if (array[left] > pivotValues) {
                    array[index] = array[left];
                    index = left;
                    left_to_right = false;
                }
                left++;
            } else {
                if (array[right] < pivotValues) {
                    array[index] = array[right];
                    index = right;
                    left_to_right = true;
                }
                right--;
            }
            //Print.println(Arrays.toString(array));
        }
        array[index] = pivotValues;
        return index;
    }


    //采用网上的方法
    //[start,end]
    private static void quickSortOne(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }

        //找基准元素
        int index = partitionOne(array, start, end);
        //[start,index -1] index [ index +1  ,end]

        quickSortOne(array, start, index - 1);

        quickSortOne(array, index + 1, end);

    }


    private static int partitionOne(int[] arr, int startIndex, int endIndex) {
        // 取第一个位置的元素作为基准元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        // 坑的位置，初始等于pivot的位置
        int index = startIndex;
        //大循环在左右指针重合或者交错时结束
        while (right >= left) {
            //right指针从右向左进行比较
            while (right >= left) {
                if (arr[right] < pivot) {
                    arr[left] = arr[right];
                    index = right;
                    left++;
                    break;
                }
                right--;
            }
            //left指针从左向右进行比较
            while (right >= left) {
                if (arr[left] > pivot) {
                    arr[right] = arr[left];
                    index = left;
                    right--;
                    break;
                }
                left++;
            }
        }
        arr[index] = pivot;
        return index;
    }


    //采用网上的方法
    private static void quickSortTwo(int[] array, int start, int end) {
        //[start,end]
        if (start >= end) {
            return;
        }
        //[start,end]
        //找基准元素
        int index = partitionTwo(array, start, end);
        //[start,index-1]  index [index +1 ,end]

        quickSortTwo(array, start, index - 1);


        quickSortTwo(array, index + 1, end);

    }

    //指针交换法 获取基准点 获取中间点
    //[startIndex,endIndex]
    private static int partitionTwo(int[] arr, int startIndex, int endIndex) {
        //Print.println("startIndex: "+startIndex+" ,endIndex: "+endIndex);
        // 取第一个位置的元素作为基准元素
        int pivotValues = arr[startIndex];
        int left = startIndex + 1;
        int right = endIndex;
        while (left != right) {

            while (left < right && arr[right] >= pivotValues) {/**/
                right--;
            }

            while (left < right && arr[left] <= pivotValues) {
                left++;
            }


            //交换left和right指向的元素
            if (left < right) {
                int p = arr[left];
                arr[left] = arr[right];
                arr[right] = p;
            }
            //Print.println(left + " : " + right);

        }
        arr[startIndex] = arr[left];
        arr[left] = pivotValues;
        //Print.println(left + " : " + right);
        return left;
    }


    public static void main(String[] args) {
        //Print.println("快速排序");
        //Print.println("分治法");
        //Print.println("快速排序在每一轮挑选一个基准元素，并让其他比它大的元素移动到数列一边，比它小的元素移动到数列的另一边，从而把数列拆解成了两个部分");
        int tmp = 1000000;
        int[] ints = new int[tmp];
        Random random = new Random();
        for (int i = 0; i < tmp; i++) {
            ints[i] = random.nextInt(100);
        }
        //Print.println(Arrays.toString(ints));




        int tmpNum = 10;
        for (int i = 0; i < tmpNum; i++) {
            int[] ints1 = Arrays.copyOf(ints, ints.length);
            long start = System.currentTimeMillis();
            quickSortDiy(ints1, 0, ints1.length - 1);
            long end = System.currentTimeMillis();
            Print.println(i+" 快速排序耗时 DIY:" + (end - start));
        }

        for (int i = 0; i < tmpNum; i++) {
            int[] ints2 = Arrays.copyOf(ints, ints.length);
            long start = System.currentTimeMillis();
            quickSortOne(ints2, 0, ints2.length - 1);
            long end = System.currentTimeMillis();
            Print.println(i+" , 快速排序耗时1 :" + (end - start));
        }



        for (int i = 0; i < tmpNum; i++) {
            int[] ints3 = Arrays.copyOf(ints, ints.length);
            long start = System.currentTimeMillis();
            quickSortTwo(ints3, 0, ints3.length - 1);
            long end = System.currentTimeMillis();
            Print.println("快速排序耗时2 :" + (end - start));
        }

        for (int i = 0; i < tmpNum; i++) {
            int[] ints4 = Arrays.copyOf(ints, ints.length);
            long start = System.currentTimeMillis();
            quickSortThree(ints4, 0, ints4.length - 1);
            long end = System.currentTimeMillis();
            Print.println("非递归实现快速排序 :" + (end - start));
        }


        //Print.println(Arrays.toString(ints4));
    }


    //非递归实现快速排序 使用堆栈保存数据
    //[start,end]
    private static void quickSortThree(int[] array, int start, int end) {

        //插入堆栈
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(start, end));
        Point point = stack.pop();
        while (point != null) {
            //Print.println(point);
            if(point.start>=point.end){
                try {
                    point = stack.pop();
                } catch (EmptyStackException e) {
                    point = null;
                }
                continue;
            }
            //找基准元素
            int index = partitionTwo(array, point.start, point.end);
            //Print.println("index:" + index);
            //[point.start,index-1]  index [index +1 ,point.end]


            stack.push(new Point(point.start, index - 1));

            stack.push(new Point(index + 1, point.end));


            try {
                point = stack.pop();
            } catch (EmptyStackException e) {
                point = null;
            }
        }

    }

    static class Point {
        int start;
        int end;

        public Point(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }


}
