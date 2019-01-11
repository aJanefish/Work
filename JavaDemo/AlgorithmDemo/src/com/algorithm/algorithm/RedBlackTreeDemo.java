package com.algorithm.algorithm;

import com.algorithm.utils.Print;

import java.util.HashMap;

/**
 * 红黑树
 * 自平衡的二叉查找树
 *
 * 1.节点是红色或黑色。
 * 2.根节点是黑色。
 * 3.每个叶子节点都是黑色的空节点（NIL节点）。
 * 4 每个红色节点的两个子节点都是黑色。(从每个叶子到根的所有路径上不能有两个连续的红色节点)
 * 5.从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点。
 * */
public class RedBlackTreeDemo {

    private RedBlackNode boot;

    public RedBlackTreeDemo() {
        this.boot = new RedBlackNode(0, Color.BLACK);
    }

    private void add(int valuse){
        Print.println("add:"+valuse);

    }

    public static void main(String[] args) {
        Print.println("红黑树");
        //HashMap<Integer,Integer> hashMap = new HashMap<>();
        RedBlackTreeDemo redBlackTreeDemo = new RedBlackTreeDemo();
        int tmp = 10;
        for (int i = 0; i < tmp; i++) {
            redBlackTreeDemo.add(i);
        }
    }

    enum  Color{
        RED,BLACK
    }

    static final class RedBlackNode{
        public static final  RedBlackNode NIL = new RedBlackNode();



        private RedBlackNode leftChild = NIL;
        private RedBlackNode rightChild = NIL;
        private int values;
        private Color color = Color.BLACK;

        public RedBlackNode(int values, Color color) {
            this.values = values;
            this.color = color;
        }

        private RedBlackNode() {

        }
    }
}
