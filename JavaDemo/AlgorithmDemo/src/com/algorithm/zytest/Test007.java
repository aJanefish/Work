package com.algorithm.zytest;

import com.algorithm.utils.P;

import java.util.*;

public class Test007 {

//    static class Note{
//        int id;
//        int de;
//        int talent;
//
//        public Note(int id, int de, int talent) {
//            this.id = id;
//            this.de = de;
//            this.talent = talent;
//        }
//    }

    private static TreeSet<Candidate> treeSet;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int L = sc.nextInt();
        int H = sc.nextInt();
        treeSet = new TreeSet<>();

//        int N = 14;
//        int L = 60;
//        int H = 80;


        //第一类 才德兼备                          de >= H  talent >= H
        //第二类 德胜才                            de >= H  talent < H
        //第三类 "才德兼亡"但尚有"德胜才"者         de<H  talent<H   de>= talent
        //第四类  其他                                de < H




//        List<Note> list = new ArrayList<>();
//        list.add(new Note(10000001,64,90));
//        list.add(new Note(10000002,90,60));
//        list.add(new Note(100000011,85,80));
//        list.add(new Note(10000003,85,80));
//        list.add(new Note(10000004,80,85));
//        list.add(new Note(10000005,82,77));
//        list.add(new Note(10000006,83,76));
//        list.add(new Note(10000007,90,78));
//        list.add(new Note(10000008,75,79));
//        list.add(new Note(10000009,59,90));
//        list.add(new Note(100000010,88,45));
//        list.add(new Note(100000012,80,100));
//        list.add(new Note(100000013,90,99));
//        list.add(new Note(100000014,66,60));




        //P.pln(list.size());
        for (int i = 0; i < N; i++) {
            int id = sc.nextInt();
            int de = sc.nextInt();
            int talent = sc.nextInt();
//            Note note = list.get(i);
//            int id = note.id;
//            int de = note.de;
//            int talent = note.talent;
            if (de >= L && talent >= L) {
                P.pln(i);
                if (de >= H && talent >= H) {//达不到最低要求 不要
                    boolean flag = treeSet.add(new Candidate(id, de, talent, 1));
                    P.pln(i+":"+flag);
                } else if (de >= H) {
                    boolean flag = treeSet.add(new Candidate(id, de, talent, 2));
                    P.pln(i+":"+flag);
                } else if (de >= talent) {
                    boolean flag = treeSet.add(new Candidate(id, de, talent, 3));
                    P.pln(i+":"+flag);
                } else {
                    boolean flag =  treeSet.add(new Candidate(id, de, talent, 4));
                    P.pln(i+":"+flag);
                }
            }
        }

        System.out.println(treeSet.size());

        for (Candidate candidate : treeSet) {
            System.out.println(candidate);
        }
    }

    static class Candidate implements Comparable {
        int id;
        int de;
        int talent;
        int level;

        int sum;


        public Candidate(int id, int de, int talent, int level) {
            this.id = id;
            this.de = de;
            this.talent = talent;
            this.level = level;

            this.sum = de + talent;
        }


        @Override
        public int compareTo(Object o) {
            int tmp = 0;
            Candidate candidate = (Candidate) o;
            if (this.level == candidate.level) {

                if (this.sum == candidate.sum) {

                    if (this.de == candidate.de) {

                        tmp = candidate.id - this.id;

                    } else {
                        tmp = this.de - candidate.de;
                    }

                } else {
                    tmp = this.sum - candidate.sum;
                }

            } else {
                tmp = candidate.level - this.level;
            }
            return -tmp;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Candidate candidate = (Candidate) o;
            return id == candidate.id &&
                    de == candidate.de &&
                    talent == candidate.talent;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, de, talent);
        }

        @Override
        public String toString() {
            return id + " " + de + " " + talent;
        }
    }
}
