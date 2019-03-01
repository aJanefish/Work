package com.algorithm.algorithm;

import com.algorithm.utils.P;
import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.Random;

/**
 * 归并排序
 * 稳定
 */
public class MergeSortDemo {

    public static void main(String args[]) {
        P.pln("归并排序");
        int tmp =19;
        int[] ints = new int[tmp];
        Random random = new Random();
        for (int i = 0; i < tmp; i++) {
            ints[i] = random.nextInt(tmp * 10);
        }

        //ints = new int[]{22, 5, 3};
        P.pln(Arrays.toString(ints));
        mergeSort1(ints, 1);

        //merag(ints, 0, 1, 2);
        P.pln(Arrays.toString(ints));
    }

    private static void mergeSort1(@NotNull int[] dates, int step) {
        if (dates == null) return;
        if (dates.length == 0 || dates.length == 1) return;

        int mStep = step << 1;
        int length = dates.length;
        int mid = length / mStep;

        int c = length & (mStep - 1);

        if (mid == 0) {
            return;
        }

        for (int i = 0; i < mid; i++) {
            int start1 = i * mStep;
            int start2 = start1 + step;
            int end = start2 + step;

            merag(dates, start1, start2, end);

        }

        if (c != 0) {
            merag(dates, length - c - 2*step, length - c, length);
        }


        mergeSort1(dates, mStep);

        //
    }


    private static void merag(int dates[], int start1, int start2, int end) {
        //[start1,start2)  [start2,end)
        P.pln(start1 + "：" + start2 + "：" + end);
        int[] tmp = new int[end - start1];
        int tmpIndex = 0;
        int tmpStart1 = start1;
        int tmpStart2 = start2;
        while (tmpStart1 < start2 && tmpStart2 < end) {

            if (dates[tmpStart1] < dates[tmpStart2]) {
                tmp[tmpIndex] = dates[tmpStart1];
                tmpStart1++;
                tmpIndex++;
            } else {
                tmp[tmpIndex] = dates[tmpStart2];
                tmpStart2++;
                tmpIndex++;
            }
        }

        if (tmpStart1 == start2) {

            for (int x = tmpStart2; x < end; ) {
                tmp[tmpIndex++] = dates[x++];
            }

        } else {
            for (int x = tmpStart1; x < start2; ) {
                tmp[tmpIndex++] = dates[x++];
            }
        }
        //P.pln(Arrays.toString(tmp));
        System.arraycopy(tmp, 0, dates, start1, tmp.length);

    }
}
