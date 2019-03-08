package com.algorithm.zytest;

import java.util.HashMap;
import java.util.Scanner;

public class Test017 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String firstAddress = scan.next();
        int N = scan.nextInt();
        int K = scan.nextInt();

        HashMap<String, Note> itemsHashMap = new HashMap<>();
        //int stat

        for (int i = 0; i < N; i++) {

            String address = scan.next();
            int data = scan.nextInt();
            String nextAddress = scan.next();
            itemsHashMap.put(address, new Note(address, data, nextAddress));
        }

        //根据得到的数据 拍好顺序
        Note[] itemsList = new Note[N];
        //int realN = 0;
        for (int i = 0; i < N; i++) {
            Note note = itemsHashMap.get(firstAddress);
            itemsList[i] = note;
            firstAddress = note.nextAddress;
            if (firstAddress.equals("-1")) {
                N = i + 1;
                break;
            }

        }


        if (N < K) {
            for (int i = 0; i < N; i++) {
                System.out.println(itemsList[i]);
            }
            return;
        }


        for (int i = 0; i * K + K < N; i++) {
            //倒换
            //i*k + (i+1)*k

            for (int reverse_start = i * K, reverse_end = i * K + K - 1; reverse_start < reverse_end; reverse_start++, reverse_end--) {
                Note tmp = itemsList[reverse_start];
                itemsList[reverse_start] = itemsList[reverse_end];
                itemsList[reverse_end] = tmp;

            }
        }


        for (int i = 0; i + 1 < N; i++) {
            itemsList[i].nextAddress = itemsList[i + 1].address;
            System.out.println(itemsList[i]);
        }
        itemsList[N - 1].nextAddress = "-1";
        System.out.println(itemsList[N - 1]);


    }


    static class Note {
        String address;
        int data;
        String nextAddress;

        public Note(String address, int data, String nextAddress) {
            this.address = address;
            this.data = data;
            this.nextAddress = nextAddress;
        }

        @Override
        public String toString() {
            return address + " " + data + " " + nextAddress;
        }
    }
}
/**
 * 给定一个常数K以及一个单链表L，请编写程序将L中每K个结点反转。例如：给定L为1→2→3→4→5→6，K为3，则输出应该为
 * 3→2→1→6→5→4；如果K为4，则输出应该为4→3→2→1→5→6，即最后不到K个元素不反转。
 * <p>
 * 输入描述:
 * 每个输入包含1个测试用例。每个测试用例第1行给出第1个结点的地址、结点总个数正整数N(<= 105)、以及正整数K(<=N)，即要求反转的
 * 子链结点的个数。结点的地址 是5位非负整数，NULL地址用-1表示。
 * <p>
 * 接下来有N行，每行格式为：
 * <p>
 * Address Data Next
 * <p>
 * 其中Address是结点地址，Data是该结点保存的整数数据，Next是下一结点的地址。
 * <p>
 * <p>
 * 输出描述:
 * 对每个测试用例，顺序输出反转后的链表，其上每个结点占一行，格式与输入相同。
 * <p>
 * 输入例子:
 * 00100 6 4
 * 00000 4 99999
 * 00100 1 12309
 * 68237 6 -1
 * 33218 3 00000
 * 99999 5 68237
 * 12309 2 33218
 * <p>
 * 输出例子:
 * 00000 4 33218
 * 33218 3 12309
 * 12309 2 00100
 * 00100 1 99999
 * 99999 5 68237
 * 68237 6 -1
 */
