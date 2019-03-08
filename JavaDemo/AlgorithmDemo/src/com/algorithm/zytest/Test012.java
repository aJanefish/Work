package com.algorithm.zytest;

import com.algorithm.utils.P;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.TreeSet;

public class Test012 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tmp = sc.nextInt();
        int total_kuCun = sc.nextInt();

        int[] kucun = new int[tmp];
        int[] price = new int[tmp];

        for (int i = 0; i < tmp; i++) {
            kucun[i] = sc.nextInt();
        }

        for (int i = 0; i < tmp; i++) {
            price[i] = sc.nextInt();
        }
        TreeSet<Note> treeSet = new TreeSet<>();
        for (int i = 0; i < tmp; i++) {
            treeSet.add(new Note(kucun[i], price[i]));
        }

        double total_price = 0;
        for (Note note : treeSet) {
            //System.out.println(note);

            if (total_kuCun >= note.kuCun) {
                total_price += note.total_price;
                total_kuCun -= note.kuCun;
            } else {

                total_price+= note.price*total_kuCun;
                break;
            }
        }

        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(total_price));
    }


    static class Note implements Comparable {
        int kuCun;
        int total_price;
        double price;

        public Note(int kuCun, int total_price) {
            this.kuCun = kuCun;
            this.total_price = total_price;
            price = (double) total_price / (double) kuCun;

        }

        @Override
        public int compareTo(Object o) {
            Note note = (Note) o;
            if (this.price > ((Note) o).price) {
                return -1;
            } else {
                return 1;
            }
        }

        @Override
        public String toString() {
            return "Note{" +
                    "kuCun=" + kuCun +
                    ", total_price=" + total_price +
                    ", price=" + price +
                    '}';
        }
    }
}
