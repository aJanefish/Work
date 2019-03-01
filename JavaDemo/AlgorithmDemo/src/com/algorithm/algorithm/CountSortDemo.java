package com.algorithm.algorithm;

import com.algorithm.utils.P;

import java.util.Arrays;
import java.util.Random;

/**
 * 计数排序
 * <p>
 * 知道一定范围内的数据
 */

public class CountSortDemo {


    public static void main(String[] args) {

        int tmp = 20;
        int[] ints = new int[tmp];
        Random random = new Random();
        for (int i = 0; i < tmp; i++) {
            ints[i] = random.nextInt(20);
        }

        int[] ints1 = Arrays.copyOf(ints, ints.length);
        P.pln(Arrays.toString(ints));
        countSort(ints, 0, 20);

        P.pln(Arrays.toString(ints));
        ints1 = countSort(ints1);
        P.pln(Arrays.toString(ints1));
    }



    public static int[] countSort(int[]a){
        int b[] = new int[a.length];
        int max = a[0],min = a[0];
        for(int i:a){
            if(i>max){
                max=i;
            }
            if(i<min){
                min=i;
            }
        }//这里k的大小是要排序的数组中，元素大小的极值差+1
        int k=max-min+1;
        int c[]=new int[k];
        for(int i=0;i<a.length;++i){
            c[a[i]-min]+=1;//优化过的地方，减小了数组c的大小
        }
        P.pln(Arrays.toString(c));
        for(int i=1;i<c.length;++i){
            c[i]=c[i]+c[i-1];
        }
        P.pln(Arrays.toString(c));

        P.pln("min:"+min);
        for(int i=a.length-1;i>=0;--i){
            P.pln(a[i]+" : "+c[a[i] - min]);
            b[--c[a[i]-min]]=a[i];//按存取的方式取出c的元素

            P.pln(Arrays.toString(c));
            P.pln(Arrays.toString(b));
        }
        P.pln(Arrays.toString(c));
        return b;
    }


    /**
     * @param array 数组
     * @param min   数据最小值
     * @param max   数据最大值
     */

    private static void countSort(int[] array, int min, int max) {
        int[] newArray = new int[max - min];
        int length = array.length;
        for (int i = 0; i < length; i++) {
            newArray[array[i]]++;
        }
        P.pln("newArray:" + Arrays.toString(newArray));
        int tmp = 0;
        for (int i = 0; i < newArray.length; i++) {
            if (newArray[i] == 0) {
                continue;
            } else {
                while (newArray[i] > 0) {
                    newArray[i]--;
                    array[tmp] = i;
                    tmp++;
                }
            }
        }

        P.pln("newArray:" + Arrays.toString(newArray));

    }
}
