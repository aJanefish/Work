package com.algorithm.classicalalgorithm;

import com.algorithm.utils.P;

/**
 * 河内之塔
 * 汉诺塔
 */
public class TowersOfHanoi {

    private static char A = 'A';
    private static char B = 'B';

    private static char C = 'C';


    public static void main(String[] args) {
        //A --> C
        hanoi(6, A, C, B);
        P.pln("totalMove:"+totalMove);
    }


    /**
     * @param hanNum
     * @param start  圆盘hanNum 的初始位置
     * @param end    圆盘hanNum 的目标位置
     * @param assist 圆盘hanNum 的辅助位置
     */
    private static double totalMove = 0;
    private static void hanoi(int hanNum, char start, char end, char assist) {
        if (hanNum == 1) {
            P.pln(hanNum + " : " + start + " : " + end);
            totalMove ++;
        } else {

            hanoi(hanNum - 1, start, assist, end);
            P.pln(hanNum + " : " + start + " : " + end);
            totalMove ++;
            hanoi(hanNum - 1, assist, end, start);

        }

    }
}
