package com.algorithm.zytest;

import java.util.Scanner;

public class Test019 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numberOfStar = scan.nextInt();
        String ch = scan.next();
        int maxLine = 0;
        int total = 0;
        for (maxLine = 1; ; maxLine += 2) {

            if (maxLine == 1) {
                total += maxLine;
            } else {
                total += 2 * maxLine;
            }
            if (total > numberOfStar) {
                break;
            }
        }
        total -= 2 * maxLine;
        maxLine -= 2;


        //System.out.println(maxLine + ":" + total);
        int numLine = show_down(maxLine, ch.charAt(0));
        show_up(maxLine, ch.charAt(0), numLine);

        System.out.println(numberOfStar - total);
    }

    private static void show_up(int maxLine, char charAt, int numLine) {
        numLine -= 2;
        for (int line = 3; line <= maxLine; line += 2) {
            for (int i1 = 0; i1 < numLine; i1++) {
                System.out.print(" ");
            }
            numLine--;

            for (int i = 0; i < line; i++) {
                System.out.print(charAt);
            }
            System.out.println();
        }
    }


    /**
     * down
     */
    public static int show_down(int maxLine, char ch) {
        int numLine = 0;
        for (int line = maxLine; line >= 0; line -= 2) {

            int empty = maxLine - line;
            for (int i = 0; i < numLine; i++) {
                System.out.print(" ");
            }
            numLine++;

            for (int i = 0; i < line; i++) {
                System.out.print(ch);
            }
            System.out.println();
        }

        return numLine--;

    }
}
