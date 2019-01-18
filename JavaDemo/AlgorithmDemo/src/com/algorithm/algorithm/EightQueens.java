package com.algorithm.algorithm;

import com.algorithm.utils.Print;

/**
 * 国际象棋中的皇后，可以横向、纵向、斜向移动。如何在一个8X8的棋盘上放置8个皇后，
 * 使得任意两个皇后都不在同一条横线、竖线、斜线方向上？
 */
public class EightQueens {
    private int[][] checkerboard = new int[8][8];

    public EightQueens() {
        Print.println(checkerboard.getClass());
    }


    public static void main(String[] args) {
        Print.println("八个皇后");
        EightQueens eightQueens = new EightQueens();
        eightQueens.show();
        boolean check = eightQueens.game(0, 0, eightQueens.checkerboard);
        Print.println("check:" + check);
    }

    //递归回溯
    public boolean game(int queenX, int queenY, int[][] checkerboard) {

        int w = checkerboard.length;
        Print.println("queenY:" + queenY);
        //改变表格的状态
        int[][] tmpCheckerboard;
        for (int i = 0; i < w; i++) {
            //[i,queenY] 该位置是否有主人
            //Print.println("["+i+","+queenY+"]");
            if (portion(i, queenY, checkerboard)) {
                continue;
            }

            tmpCheckerboard = copy(checkerboard);
            //改变状态,获取领地
            domain(i, queenY, tmpCheckerboard);

            //最后一行的皇后问题
            if (queenY == 7) {
                show(tmpCheckerboard);
                return true;
            } else {
                boolean success = game(0, queenY + 1, tmpCheckerboard);
                if (success) {
                    return true;
                }
            }

        }

        return false;
    }

    private int[][] copy(int[][] checkerboard) {

        int w = checkerboard.length;
        int h = checkerboard[0].length;
        int[][] tmpCheckerboard = new int[w][h];

        for (int i = 0; i < h; i++) {
            for (int i1 = 0; i1 < w; i1++) {
                tmpCheckerboard[i1][i] = checkerboard[i1][i];
            }
        }
        return tmpCheckerboard;
    }


    //判断该领地是否有主人
    public boolean portion(int queenX, int queenY, int[][] tmpCheckerboard) {
        return tmpCheckerboard[queenX][queenY] > 0;
    }

    //行驶主权,获得领地
    public boolean domain(int queenX, int queenY, int[][] tmpCheckerboard) {
        if (portion(queenX, queenY, tmpCheckerboard)) {
            return false;
        }
        //获取皇位
        tmpCheckerboard[queenX][queenY] = 8;
        //获得领地
        int w = tmpCheckerboard.length;
        for (int i = 0; i < w; i++) {
            if (i != queenY)
                tmpCheckerboard[queenX][i] = 1;
            if (i != queenX)
                tmpCheckerboard[i][queenY] = 1;
        }
        int tmpQueenX = queenX;
        int tmpQueenY = queenY;
        while (tmpQueenX > 0 && tmpQueenY > 0) {
            tmpQueenX--;
            tmpQueenY--;

            tmpCheckerboard[tmpQueenX][tmpQueenY] = 1;
        }

        tmpQueenX = queenX;
        tmpQueenY = queenY;
        while (tmpQueenX < w - 1 && tmpQueenY > 0) {
            tmpQueenX++;
            tmpQueenY--;

            tmpCheckerboard[tmpQueenX][tmpQueenY] = 1;
        }

        tmpQueenX = queenX;
        tmpQueenY = queenY;
        while (tmpQueenX < w - 1 && tmpQueenY < w - 1) {
            tmpQueenX++;
            tmpQueenY++;
            //Print.println(tmpQueenX + ":" + tmpQueenY);

            tmpCheckerboard[tmpQueenX][tmpQueenY] = 1;
        }

        tmpQueenX = queenX;
        tmpQueenY = queenY;
        while (tmpQueenX > 0 && tmpQueenY < w - 1) {
            tmpQueenX--;
            tmpQueenY++;

            tmpCheckerboard[tmpQueenX][tmpQueenY] = 1;
        }

        return true;
    }

    //行驶主权,获得领地
    public boolean domain(int queenX, int queenY) {
        if (portion(queenX, queenY, checkerboard)) {
            return false;
        }
        //获取皇位
        checkerboard[queenX][queenY] = 8;
        //获得领地
        int w = checkerboard.length;
        for (int i = 0; i < w; i++) {
            if (i != queenY)
                checkerboard[queenX][i] = 1;
            if (i != queenX)
                checkerboard[i][queenY] = 1;
        }
        int tmpQueenX = queenX;
        int tmpQueenY = queenY;
        while (tmpQueenX > 0 && tmpQueenY > 0) {
            tmpQueenX--;
            tmpQueenY--;

            checkerboard[tmpQueenX][tmpQueenY] = 1;
        }

        tmpQueenX = queenX;
        tmpQueenY = queenY;
        while (tmpQueenX < w - 1 && tmpQueenY > 0) {
            tmpQueenX++;
            tmpQueenY--;

            checkerboard[tmpQueenX][tmpQueenY] = 1;
        }

        tmpQueenX = queenX;
        tmpQueenY = queenY;
        while (tmpQueenX < w - 1 && tmpQueenY < w - 1) {
            tmpQueenX++;
            tmpQueenY++;
            Print.println(tmpQueenX + ":" + tmpQueenY);

            checkerboard[tmpQueenX][tmpQueenY] = 1;
        }


        tmpQueenX = queenX;
        tmpQueenY = queenY;
        while (tmpQueenX > 0 && tmpQueenY < w - 1) {
            tmpQueenX--;
            tmpQueenY++;

            checkerboard[tmpQueenX][tmpQueenY] = 1;
        }

        return true;
    }


    private void show(int[][] checkerboard) {
        int w = checkerboard.length;
        int h = checkerboard[0].length;
        Print.println();
        for (int i = 0; i < h; i++) {
            for (int i1 = 0; i1 < w; i1++) {
                Print.print(checkerboard[i1][i] + "  ");
            }
            Print.println();
        }
    }

    private void show() {
        int w = checkerboard.length;
        int h = checkerboard[0].length;
        Print.println();
        for (int i = 0; i < h; i++) {
            for (int i1 = 0; i1 < w; i1++) {
                Print.print(checkerboard[i1][i] + "  ");
            }
            Print.println();
        }
    }


}
