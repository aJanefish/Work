package com.algorithm.zytest;

/**
 * 题目描述
 * 大家应该都会玩“锤子剪刀布”的游戏：
 * 现给出两人的交锋记录，请统计双方的胜、平、负次数，并且给出双方分别出什么手势的胜算最大。
 *
 * 输入描述:
 * 输入第1行给出正整数N（<=105），即双方交锋的次数。
 * 随后N行，每行给出一次交锋的信息，即甲、乙双方同时给出的的手势。
 * C代表“锤子”、J代表“剪刀”、B代表“布”，
 *
 * 第1个字母代表甲方，第2个代表乙方，中间有1个空格。
 *
 *
 * 输出描述:
 * 输出第1、2行分别给出甲、乙的胜、平、负次数，数字间以1个空格分隔。第3行给出两个字母，分别代表甲、乙获胜次数最多的手势，中间有1个空格。如果解不唯
 * 一，则输出按字母序最小的解。
 *
 * 输入例子:
 * 10
 * C J
 * J B
 * C B
 * B B
 * B C
 * C C
 * C B
 * J B
 * B C
 * J J
 *
 * 输出例子:
 * 5 3 2
 * 2 3 5
 * B B
 * */
import java.util.Scanner;

public class Test010 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int aCwin = 0;
        int aJwin = 0;
        int aBwin = 0;

        int bCwin = 0;
        int bJwin = 0;
        int bBwin = 0;


        for (int i = 0; i < N; i++) {
            String a = sc.next();
            String b = sc.next();

            char a1 = a.charAt(0);
            char b1 = b.charAt(0);

            if (a1 == b1) {

            } else {
                if (a1 == 'C' && b1 == 'J') {
                    aCwin++;
                } else if (a1 == 'J' && b1 == 'B') {
                    aJwin++;
                } else if (a1 == 'B' && b1 == 'C') {
                    aBwin++;
                } else if (b1 == 'C' && a1 == 'J') {
                    bCwin++;
                } else if (b1 == 'J' && a1 == 'B') {
                    bJwin++;
                } else {
                    bBwin++;
                }


            }
        }

        int aWin = aBwin + aJwin + aCwin;
        int bWin = bBwin + bCwin + bJwin;

        int pin = N - aWin - bWin;
        System.out.println(aWin + " " + pin + " " + bWin);
        System.out.println(bWin + " " + pin + " " + aWin);

        show(aBwin, aJwin, aCwin, 1);
        show(bBwin, bJwin, bCwin, 2);

    }

    /**
     * B J C
     */
    private static void show(int b, int j, int c, int tmp) {

        if (b == j) {
            if (b >= c) {
                System.out.print('B');
            } else {
                System.out.print('C');
            }

        } else if (b > j) {

            if (b >= c) {
                System.out.print('B');
            } else {
                System.out.print('C');
            }

        } else {
            if (c >= j) {
                System.out.print('C');
            } else {
                System.out.print('J');
            }
        }

        if(tmp == 1){
            System.out.print(" ");
        }
    }


}
