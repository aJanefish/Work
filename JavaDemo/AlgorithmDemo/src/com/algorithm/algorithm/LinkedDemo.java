package com.algorithm.algorithm;

import com.algorithm.utils.P;
import com.sun.istack.internal.NotNull;

import java.util.Objects;

/**
 * 链表相关的算法和操作
 */
class LinkNote {
    public int values;
    public LinkNote next = null;

    public LinkNote(int values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkNote)) return false;
        LinkNote linkNote = (LinkNote) o;
        return values == linkNote.values;
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }

    @Override
    public String toString() {
        return "LinkNote{" +
                "values=" + values +
                ", hash=" + (this == null ? "null" : "" + hashCode()) +
                ", next=" + (next == null ? "null" : "" + next.hashCode()) +
                '}';
    }
}


public class LinkedDemo {

    private LinkNote head = null;

    public LinkedDemo(LinkNote head) {
        this.head = head;
    }

    public LinkedDemo() {
    }


    public static void main(String args[]) throws Exception {
        LinkedDemo linkedDemo = new LinkedDemo();
        int tmp = 10;
        for (int i = 0; i < tmp; i++) {
            linkedDemo.addNote(new LinkNote(i));
        }
        linkedDemo.show_1();

        linkedDemo.deleteNote(new LinkNote(0));
        linkedDemo.show_2();
        linkedDemo.showHead();
        linkedDemo.reversa();

        linkedDemo.show_2();
        linkedDemo.showHead();


        linkedDemo.reversa_1();
        linkedDemo.show_2();
        linkedDemo.showHead();


        linkedDemo.reversa_2();
        linkedDemo.show_2();
        linkedDemo.showHead();


        linkedDemo.reversa_3();
        linkedDemo.show_2();
        linkedDemo.showHead();
    }


    public void showHead() {
        P.pln("head:" + head);
    }


    //三指示法
    private void reversa_3() throws Exception {
        //单链表为空或只有一个节点，直接返回原单链表
        if (head == null || head.next == null) {
            return;
        }

        LinkNote preNote = head;
        LinkNote curNote = head.next;
        LinkNote nextNote = curNote.next;

        preNote.next =  null;
        while (true){

            if(nextNote != null){
                curNote.next = preNote;

                preNote = curNote;
                curNote = nextNote;
                nextNote = nextNote.next;
            }else {

                curNote.next = preNote;
                head = curNote;
                break;
            }

        }


    }


    //新建链表，头节点插入法
    private void reversa_2() throws Exception {
        P.pln("reversa_2------------------");

        if (head == null) {
            throw new Exception("Head is Empty");
        }

        LinkNote newPrev = null;
        LinkNote note = head;

        while (true) {
            if (note != null) {

                LinkNote tmpNote = note;
                note = note.next;
                deleteNote(tmpNote);

                tmpNote.next = newPrev;
                newPrev = tmpNote;


            } else {
                break;
            }
        }

        head = newPrev;
    }


    //就地反转法
    private void reversa_1() throws Exception {
        P.pln("reversa_1------------------");
        if (head == null) {
            throw new Exception("Head is Empty");
        }
        LinkNote note = head.next;
        while (true) {
            LinkNote note1 = null;
            if (note != null) {
                note1 = note;
                note = note.next;

                deleteNote(note1);
                addHead(note1);
            } else {
                break;
            }
        }
    }

    //单链表反转
    private void reversa() {
        P.pln("reversa...........");
        LinkNote lastNote = reversa(head);
        lastNote.next = null;

        P.pln("lastNote:" + lastNote);
    }

    //单链表反转
    private LinkNote reversa(LinkNote linkNote) {

        //P.pln("reversa...........");

        LinkNote priNote = null;
        if (linkNote.next != null) {
            priNote = reversa(linkNote.next);

            //P.p(priNote);
            priNote.next = linkNote;
            priNote = priNote.next;
        } else {
            priNote = linkNote;
            head = linkNote;
        }

        //P.pln(":" + linkNote);

        return priNote;
    }


    private void addHead(@NotNull LinkNote note) {
        if (head == null) {
            head = note;
            return;
        }

        note.next = head;
        head = note;
    }

    private void addNote(@NotNull LinkNote note) {
        if (head == null) {
            head = note;
            return;
        }
        LinkNote linkNote = head;
        while (true) {

            if (linkNote.next == null) {
                linkNote.next = note;

                break;
            } else {
                linkNote = linkNote.next;
            }
        }
    }

    private void deleteNote(@NotNull LinkNote note) throws Exception {
        if (head == null) {
            throw new Exception("Head is Empty");
        }
        LinkNote linkNote = head;
        LinkNote priNote = null;
        while (true) {
            if (note.equals(linkNote)) {
                if (priNote == null) {
                    head = linkNote.next;
                } else {
                    priNote.next = linkNote.next;
                }
                break;
            }
            if (linkNote.next != null) {
                priNote = linkNote;
                linkNote = linkNote.next;
            } else {
                break;
            }

        }
    }


    private void show_2() {
        P.pln("show2...............");
        show_3(head);
    }

    private void show_3(LinkNote note) {
        P.pln(note);
        if (note.next != null) {
            show_3(note.next);
        }
    }


    private void show_1() {
        if (head == null) {
            P.pln("" + head);
            return;
        }
        P.pln("show------------");
        LinkNote linkNote = head;
        while (true) {

            P.pln(linkNote);

            if (linkNote.next != null) {
                linkNote = linkNote.next;
            } else {
                break;
            }
        }
    }
}
