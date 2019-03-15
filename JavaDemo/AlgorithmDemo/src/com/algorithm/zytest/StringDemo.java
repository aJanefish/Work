package com.algorithm.zytest;


import com.algorithm.utils.P;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class StringDemo {

    static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
    //合并两个排序的整数数组A和B变成一个新的数组。新数组也要有序。

    public static void main(String args[]) {

        StringDemo stringDemo = new StringDemo();
//        TreeNode root = new TreeNode(20);
//        root.left = new TreeNode(8);
//        root.right = new TreeNode(22);
//        root.left.left = new TreeNode(4);
//        root.left.right = new TreeNode(12);
//
        List<Map.Entry<Integer, Double>> lists = stringDemo.dicesSum(500);
        System.out.println(lists);

        stringDemo.show();
        show();

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(1, 1);
        hashMap.put(1, 3);

        hashMap.put(null, 2);
        hashMap.put(null, 4);

        P.pln(hashMap);
    }

    private static void show() {
        P.pln("static show");
    }


    private List<Map.Entry<Integer, Double>> dicesSum(int num) {

        int sum = 0;
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            //P.pln(x + ":" + y);
            if (x * x + y * y < 1) {
                sum++;
            }
        }
        show();

        P.pln((double) 4 * (double) sum / (double) num);
        return null;
    }

    //for(i = 1; i < N; i++) {
    //x = (double) rand() / RAND_MAX;
    //y = (double) rand() / RAND_MAX;
    //if((x * x + y * y) < 1)
    //sum++;
    //}
    //printf("PI = %f\n", (double) 4 * sum / N


}
