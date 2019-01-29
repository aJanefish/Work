package com.algorithm.algorithm;


import com.algorithm.redblack.TreeMap;
import com.algorithm.utils.Print;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


/**
 * 红黑树
 * 自平衡的二叉查找树
 * <p>
 * 1.节点是红色或黑色。
 * 2.根节点是黑色。
 * 3.每个叶子节点都是黑色的空节点（NIL节点）。
 * 4 每个红色节点的两个子节点都是黑色。(从每个叶子到根的所有路径上不能有两个连续的红色节点)
 * 5.从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点。
 */
public class RedBlackTreeDemo {



    public static void main(String[] args) {
        Print.println("红黑树");

        //红黑树添加测试
        addTest();

        //红黑树删除元素测试
        deleteTest();


    }

    private static void deleteTest() {
        Print.println("delete Test ---------------- 红黑树删除元素测试");
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        Print.println(treeMap);
        Print.println(treeMap.keySet());
        treeMap.put(50,50);

        int tmp = 10;

        int[] ints = new int[]{39,4,42,46,17,8,18,9,1,70,30};
        Random random = new Random();
        for (int i = 0; i <= tmp; i++) {
            treeMap.put(ints[i],ints[i]);
        }

        Print.println(treeMap);
        TreeMap.Entry<Integer, Integer> root = treeMap.getRoot();
        Print.println("BOOT:"+root);
        Print.println("left:"+root.left);
        Print.println("right:"+root.right);

        treeMap.remove(39);

        Print.println(treeMap);
        Print.println("BOOT:"+root);
        Print.println("left:"+root.left);
        Print.println("right:"+root.right);
    }

    //红黑树添加测试
    private static void addTest() {
        Print.println("add Test ---------------- 红黑树添加测试");
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        Print.println(treeMap);
        Print.println(treeMap.keySet());
        treeMap.put(50,50);

        int tmp = 10;

        int[] ints = new int[]{39,4,42,46,17,8,18,9,1,70,30};
        Random random = new Random();
        for (int i = 0; i <= tmp; i++) {
            //int t = random.nextInt(100);
            //Print.println(i+":"+t);
            treeMap.put(ints[i],ints[i]);
        }

        //treeMap.put(1,1);
        //treeMap.put(1,1);



        Print.println(treeMap);

        Print.println("keySet:"+treeMap.keySet());


        TreeMap.Entry<Integer, Integer> root = treeMap.getRoot();
        Print.println("BOOT:"+root);
        Print.println("left:"+root.left);
        Print.println("right:"+root.right);
        printTree(root);


        treeMap.remove(18);

        printTree(root);
    }

    public  static void printTree(TreeMap.Entry root) {
        Print.println("TREE--------------------");
        Queue<TreeMap.Entry> queue = new LinkedList<>();
        queue.offer(root);
        TreeMap.Entry last = root;	//记录当前层最后一个节点
        TreeMap.Entry nlast = root;	//记录下一层的最后一个节点
        while (!queue.isEmpty()) {
            TreeMap.Entry t = queue.peek();
            System.out.print(queue.poll() + " ");
            if (t.left != null) {
                queue.offer(t.left);
                nlast = t.left;
            }
            if (t.right != null) {
                queue.offer(t.right);
                nlast = t.right;
            }
            // 如果当前输出结点是最右结点，那么换行
            if (last == t) {
                System.out.println();
                last = nlast;
            }
        }
    }



}
