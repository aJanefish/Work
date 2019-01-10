package com.algorithm.algorithm;


import com.algorithm.utils.Print;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * A*寻路算法
 */
public class Demo005 {
    private static int MAXX = 10;
    private static int MAXY = 7;
    private static Object[][] objects = new Object[MAXX][MAXY];

    private static boolean[][] booleans = new boolean[MAXX][MAXY];

    static {

        int tmpY = objects[0].length;
        int tmpX = objects.length;
        Print.println(tmpX + " - " + tmpY);
        for (int i = 0; i < tmpY; i++) {
            for (int i1 = 0; i1 < tmpX; i1++) {
                objects[i1][i] = 0;
            }
        }

        //障碍物
        objects[3][0] = 1;
        objects[3][1] = 1;
        objects[3][2] = 1;
        objects[3][3] = 1;
        objects[3][4] = 1;
        objects[3][5] = 1;
        objects[4][5] = 1;
        objects[5][5] = 1;
        objects[6][5] = 1;
        objects[7][5] = 1;
        objects[7][4] = 1;
        objects[7][3] = 1;
        objects[7][2] = 1;
        objects[7][1] = 1;
        objects[7][0] = 1;
        //objects[3][6] = 1;


        //起点  和  目标
        objects[1][2] = 2;
        objects[5][2] = 3;
    }

    private static MyNode startNode;
    private static MyNode endNode;
    private static List<MyNode> barrierListNode = new ArrayList<>();

    public static void main(String[] args) {
        show();
        init();
        test1();

    }


    //A* 个人 算法实现 扫描
    private static void test1() {

        List<MyNode> CloseList = new ArrayList<>();
        List<MyNode> OpenList = new ArrayList<>();

        OpenList.add(startNode);
        int tmpNum = 0;
        while (!OpenList.contains(endNode)) {

            //从OpenList 中找出F最小的Node
            MyNode current = findMinNode(OpenList);
            if (current == null) {
                Print.println("current == null");
                break;
            }
            //移除OpenList
            OpenList.remove(current);


            //找到当前点的Open Node
            List<MyNode> currentOpenList = findCurrentOpenList(current);
            Print.println(tmpNum++);
            showBoolean();
            Print.println(current);

            Print.println("currentOpenList:" + currentOpenList.size() + " : " + currentOpenList);
            if (currentOpenList.size() == 0) {
                CloseList.remove(current);
            } else {
                if (!CloseList.contains(current)) {
                    CloseList.add(current);
                }

                OpenList.addAll(currentOpenList);
            }

            Print.println(CloseList.size() + " : " + CloseList + " \n\n\n");
        }

        Print.println(OpenList.contains(endNode));

        if (OpenList.contains(endNode)) {
            CloseList.add(OpenList.get(OpenList.indexOf(endNode)));

            //排序 按照G的大小
            CloseList.sort(new Comparator<MyNode>() {
                @Override
                public int compare(MyNode o1, MyNode o2) {
                    return o1.getG() - o2.getG();
                }
            });

            //CloseList.add();
            Print.println("成功");
            //生成路径
            List<MyNode> path = new ArrayList<>();

            createPath(CloseList, path, 0);
            Print.println();
        } else {
            Print.println("失败");
        }

        Print.println("CloseList:" + CloseList.size() + " : " + CloseList);
        Print.println("OpenList:" + OpenList.size() + " : " + OpenList);

        showCloseList(CloseList);
        showBoolean();
    }

    private static void showCloseList(List<MyNode> closeList) {


        int maxG = closeList.get(closeList.size() - 1).getG();
        for (int i = 0; i <= maxG; i++) {
            List<MyNode> list = findCloseListByG(closeList, i);
            Print.println(i + ":" + list);
        }
    }

    //根据G查找 MYNode
    private static List<MyNode> findCloseListByG(List<MyNode> closeList, int g) {
        List<MyNode> list = new ArrayList<>();
        for (MyNode myNode : closeList) {
            if (myNode.getG() == g) {
                list.add(myNode);
            }
        }
        return list;
    }


    private static void createPath(List<MyNode> closeList, List<MyNode> path, int g) {

        if (path.size() != 0 && path.contains(endNode)) {
            //Print.println("path:" + path.size() + " : " + path);
            showPath(path);
            return;
        }

        //根据当前走的步数 找到相关的Node
        List<MyNode> tmpList = findCloseListByG(closeList, g);
        //找最小F List
        List<MyNode> minF_List = findMinF(tmpList);
        Print.println(g + " : " + tmpList.size() + ":minF_List:" + minF_List.size() + ":" + minF_List);
        for (MyNode myNode : minF_List) {

            //克隆path 不然会相互干扰
            List<MyNode>  tmpPath= cloneList(path);

            //path为空 直接添加
            if(tmpPath.size() == 0){
                tmpPath.add(myNode);
                createPath(closeList, tmpPath, g + 1);
            }else {
                MyNode current = tmpPath.get(tmpPath.size() - 1);
                if(current.isNeighbor(myNode)){
                    tmpPath.add(myNode);
                    createPath(closeList, tmpPath, g + 1);
                }else {
                    //连接不上
                    Print.println(current+"--<对不上>--"+myNode);
                }
            }
        }
    }

    private static void showPath(List<MyNode> path) {
        Print.println("Paht:"+path.size());
        for (MyNode myNode : path) {
            Print.print("["+myNode.getX()+","+myNode.getY()+"]->");
        }
        Print.println();
    }

    private static List<MyNode> cloneList(List<MyNode> list) {
        List<MyNode> tmpList = new ArrayList<>();
        for (MyNode myNode : list) {
            tmpList.add(myNode.clone());
        }
        return tmpList;
    }

    private static List<MyNode> findMinF(List<MyNode> tmpList) {
        List<MyNode> list = new ArrayList<>();
        if (tmpList.size() == 0) {
            return list;
        }
        if(tmpList.size() == 1){
            return tmpList;
        }
        sortList(tmpList);
        int minF = tmpList.get(0).getF();
        for (MyNode myNode : tmpList) {
            if (myNode.getF() == minF) {
                list.add(myNode);
            }
        }

        return list;
    }

    private static void sortList(List<MyNode> tmpList) {
        //排序 按照G的大小
        tmpList.sort(new Comparator<MyNode>() {
            @Override
            public int compare(MyNode o1, MyNode o2) {
                return o1.getF() - o2.getF();
            }
        });
    }

    private static List<MyNode> findCurrentOpenList(MyNode current) {
        //current 的 openNode
        List<MyNode> list = new ArrayList<>();
        //上下左右
        MyNode left = current.getLeft();
        if (left != null) {
            //判断该Node 时候否是障碍Node 是否是 已经标记Node
            if (!isUse(left)) {
                //计算H
                left.setH(endNode);
                list.add(left);
            }
            //Print.println("left:"+left);

        }
        MyNode right = current.getRight(MAXX);
        if (right != null) {
            //判断该Node 时候否是障碍Node 是否是 已到Node
            if (!isUse(right)) {
                //计算H
                right.setH(endNode);
                list.add(right);
            }
            //Print.println("right:"+right);
        }

        MyNode up = current.getUp();
        if (up != null) {
            if (!isUse(up)) {
                up.setH(endNode);
                list.add(up);
            }
            //Print.println("up:"+up);
        }

        MyNode down = current.getDown(MAXY);
        if (down != null) {
            if (!isUse(down)) {
                down.setH(endNode);
                list.add(down);
            }
            //Print.println("down:"+down);
        }

        return list;

    }

    private static MyNode findMinNode(List<MyNode> List) {
        MyNode minNode = null;
        for (MyNode myNode : List) {
            if (minNode == null) {
                minNode = myNode;
                continue;
            }
            if (minNode.getF() > myNode.getF()) {
                minNode = myNode;
            }
        }
        return minNode;

    }


    private static boolean isUse(MyNode myNode) {
//        if (closeList.contains(myNode) || barrierListNode.contains(myNode) || openList.contains(myNode)) {
//            return true;
//        }


        if (booleans[myNode.getX()][myNode.getY()]) {
            return true;
        }
        booleans[myNode.getX()][myNode.getY()] = true;
        return false;
    }


    //查找是否已经到了目标点
    private static boolean success(List<MyNode> closeList) {
        if (closeList.size() == 0) {
            return false;
        }
        MyNode myNode = closeList.get(closeList.size() - 1);
        if (endNode.equals(myNode)) {
            return true;
        }

        return false;
    }


    //初始化迷宫数据
    private static void init() {
        Print.println("init --------------");
        int tmpY = objects[0].length;
        int tmpX = objects.length;
        for (int i = 0; i < tmpY; i++) {
            for (int i1 = 0; i1 < tmpX; i1++) {
                Object object = objects[i1][i];
                if (object instanceof Integer) {

                    switch ((Integer) object) {
                        case 1:
                            barrierListNode.add(new MyNode(i1, i));
                            booleans[i1][i] = true;
                            break;
                        case 2:
                            booleans[i1][i] = true;
                            startNode = new MyNode(i1, i, 0);
                            break;
                        case 3:
                            endNode = new MyNode(i1, i);
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        showBoolean();

        startNode.setH(endNode);
        Print.println(startNode);
        Print.println(endNode);
        Print.println(barrierListNode + "\n\n");
    }

    private static void showBoolean() {

        int tmpY = booleans[0].length;
        int tmpX = booleans.length;
        for (int i = 0; i < tmpY; i++) {
            for (int i1 = 0; i1 < tmpX; i1++) {
                Object object = booleans[i1][i];
                Print.print(object + " ");
            }
            Print.println();
        }

    }

    private static void show() {
        int tmpY = objects[0].length;
        int tmpX = objects.length;
        Print.println(tmpX + " - " + tmpY);
        for (int i = 0; i < tmpY; i++) {
            for (int i1 = 0; i1 < tmpX; i1++) {
                Object object = objects[i1][i];
                Print.print(object + " ");
            }
            Print.println();
        }
    }
}


class MyNode {
    private int x;
    private int y;
    private int F;
    private int G;//从起到走到当前格的距离
    private int H;//当前格走到目标格的距离，不包括障碍

    public MyNode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public MyNode(int x, int y, int g) {
        this.x = x;
        this.y = y;
        G = g;
    }


    private MyNode(int x, int y, int f, int g, int h) {
        this.x = x;
        this.y = y;
        F = f;
        G = g;
        H = h;
    }

    public int getF() {
        return F;
    }

    public int getG() {
        return G;
    }

    public void setG(int g) {
        G = g;
    }

    public int getH() {
        return H;
    }


    public MyNode clone() {
        return new MyNode(x, y, F, G, H);
    }

    public boolean isNeighbor(MyNode node) {
        if (x == node.x && (y + 1) == node.y) return true;
        else if (x == node.x && (y - 1) == node.y) return true;
        else if ((x + 1) == node.x && y == node.y) return true;
        else if ((x - 1) == node.x && y == node.y) return true;

        return false;
    }

    public void setH(MyNode endNode) {
        H = Math.abs(endNode.x - x) + Math.abs(endNode.y - y);
        //计算F
        F = G + H;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public MyNode getUp() {
        if (y == 0) {
            return null;
        }
        return new MyNode(x, y - 1, G + 1);
    }

    public MyNode getDown(int max) {
        if (y + 1 == max) {
            return null;
        }
        return new MyNode(x, y + 1, G + 1);
    }

    public MyNode getLeft() {
        if (x == 0) {
            return null;
        }
        return new MyNode(x - 1, y, G + 1);
    }

    public MyNode getRight(int max) {
        if (x + 1 == max) {
            return null;
        }
        return new MyNode(x + 1, y, G + 1);
    }


    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", F=" + F +
                ", G=" + G +
                ", H=" + H +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyNode myNode = (MyNode) o;
        return x == myNode.x &&
                y == myNode.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
