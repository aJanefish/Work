package com.algorithm.redblack;

import com.algorithm.utils.Print;

public class RedBlackTree {


    public static final int NONE = 50;
    private RedBlackNode mBoot;
    private int size = 0;

    public RedBlackTree() {

        this.mBoot = new RedBlackNode(null, null, null, NONE, Color.BLACK);
    }


    /*
     * 新建结点(key)，并将其插入到红黑树中
     *
     * 参数说明：
     *     key 插入结点的键值
     */
    public void insert(int key) {
        RedBlackNode node = new RedBlackNode(null, null, null, key, Color.BLACK);

        // 如果新建结点失败，则返回。
        if (node != null)
            insert(node);
    }

    private void insert(RedBlackNode node) {

        int cmp;
        RedBlackNode y = null;
        RedBlackNode x = this.mBoot;

        // 1. 将红黑树当作一颗二叉查找树，将节点添加到二叉查找树中。
        while (x != null) {
            y = x;
            cmp = (node.values - x.values);
            if (cmp < 0)
                x = x.left;
            else
                x = x.right;
        }


        cmp = (node.values - y.values);
        if (cmp == 0) {
            Print.println("该Node已经添加:" + node);
            return;
        } else if (cmp < 0) {
            y.left = node;
        } else {
            y.right = node;
        }

        node.parent = y;
        node.color = Color.RED;

        Print.println(node);
        insertFixUp(node);
    }

    /*
     * 红黑树插入修正函数
     *
     * ①、插入节点的父节点和其叔叔节点（祖父节点的另一个子节点）均为红色。
     * ②、插入节点的父节点是红色的，叔叔节点是黑色的，且插入节点是其父节点的右子节点。  左旋
     * ③、插入节点的父节点是红色的，叔叔节点是黑色的，且插入节点是其父节点的左子节点。  右旋
     *
     * 参数说明：
     *     node 插入的结点        // 对应《算法导论》中的z
     */
    private void insertFixUp(RedBlackNode node) {
        RedBlackNode parent, gparent;
        // 若“父节点存在，并且父节点的颜色是红色”
        while ((parent = node.parent) != null && parent.isRed()) {
            gparent = parent.parent;
        }
    }


    public void show() {
        Print.println("show");
        int depth = 0;
        show(mBoot, depth);
    }

    //显示红黑树
    //中序遍历  前序遍历  后序遍历
    private void show(RedBlackNode boot, int depth) {
//        StringBuffer stringBuffer =  new StringBuffer();
//        for (int i = 0; i < depth; i++) {
//            stringBuffer.append(" ");
//        }

//        Print.println("depth:" + depth + " boot:" + boot + " , LeftChild:" + boot.getLeftChild() + ", RightChild: " + boot.getRightChild());
//
//        if (boot.getLeftChild() != null && boot.getLeftChild() != NIL) {
//            show(boot.getLeftChild(), depth + 1);
//        } else {
//            Print.println(depth + " : " + boot + " :LeftChild == NIL: " + boot.getLeftChild());
//        }
//
//        if (mBoot == boot) {
//            Print.println(depth + " : " + "BOOT:" + boot);
//        } else {
//            Print.println(depth + " : " + boot);
//        }
//
//
//        if (boot.getRightChild() != null && boot.getRightChild() != NIL) {
//            show(boot.getRightChild(), depth + 1);
//        } else {
//            Print.println(depth + " : " + boot + " RightChild == NIL " + boot.getRightChild());
//        }

    }

    /*
     * 对红黑树的节点(x)进行左旋转
     *
     * 左旋示意图(对节点x进行左旋)：
     *      px                              px
     *     /                               /
     *    x                               y
     *   /  \      --(左旋)-.            / \                #
     *  lx   y                          x  ry
     *     /   \                       /  \
     *    ly   ry                     lx  ly
     *
     *
     */
    private void leftRotate(RedBlackNode x) {
        RedBlackNode y = x.left;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }

        // 将 “x的父亲” 设为 “y的父亲”
        y.parent = x.parent;

        if (x.parent == null) {
            this.mBoot = y;            // 如果 “x的父亲” 是空节点，则将y设为根节点
        } else {
            if (x.parent.left == x)
                x.parent.left = y;     // 如果 x是它父节点的左孩子，则将y设为“x的父节点的左孩子”
            else
                x.parent.right = y;    // 如果 x是它父节点的左孩子，则将y设为“x的父节点的左孩子”
        }


        // 将 “x” 设为 “y的左孩子”
        y.left = x;
        // 将 “x的父节点” 设为 “y”
        x.parent = y;
    }

    /*
     * 对红黑树的节点(y)进行右旋转
     *
     * 右旋示意图(对节点y进行左旋)：
     *            py                               py
     *           /                                /
     *          y                                x
     *         /  \      --(右旋)-.             /  \                     #
     *        x   ry                           lx   y
     *       / \                                   / \                   #
     *      lx  rx                                rx  ry
     *
     */
    private void rightRotate(RedBlackNode y) {
        RedBlackNode x = y.left;

        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }

        x.parent = y.parent;
        if (y.parent == null) {
            this.mBoot = x;
        } else {
            if (y.parent.left == y) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        }


        x.right = y;
        y.parent = x;
    }


}
