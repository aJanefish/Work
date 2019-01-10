package com.algorithm.algorithm;

import com.algorithm.utils.Print;

/**
 * 判断单链表是否有环
 * */

class Note{
    public char value;
    private int numShow = 0;

    public Note(char value, Note next) {
        this.value = value;
        this.next = next;
    }

    public Note(char value) {
        this.value = value;
    }

    public int getNumShow() {
        return numShow;
    }

    public Note next;

    @Override
    public String toString() {
        numShow ++;
        return "Note{" +
                "value=" + value +
                '}';
    }
}

public class Demo004 {

    //12 8
    public static void main(String[] args) {
        Print.println("判断单链表是否有环");
        //创建一个链表
        Note headNote = new Note('A');
        createLinkedNote(headNote);
        Print.println(headNote);
        for(Note head = headNote;head != null; head = head.next){
            Print.println(head+"-->");

            if(head.getNumShow() >= 3){
                break;
            }
        }

        test1(headNote);
    }

    //判断链表是否是环
    //思路：两个引用,一个跑得快，一个跑的慢 如果是环。总会有相遇的时候
    private static void test1(Note headNote) {
        Print.println("test1------------------------------");
        Note note1 = headNote.next;
        Note note2 = headNote.next.next;
        for(;note1 != null && note2 != null && note1 != note2; ){
            Print.println(note1+" - "+note2);

            note1 = note1.next;
            if(note2.next == null){
                note2 = note2.next;
                break;
            }
            note2 = note2.next.next;
        }
        Print.println(note1+" - "+note2);
        Print.println("note1:"+note1);
        Print.println("note2:"+note2);
    }

    private static void createLinkedNote(Note headNote) {
        Note noteB = new Note('B');
        headNote.next = noteB;


        Note noteC = new Note('C');
        noteB.next = noteC;

        Note noteD = new Note('D');
        noteC.next = noteD;

        Note noteE = new Note('E');
        noteD.next = noteE;

        Note noteF =  new Note('F');
        noteE.next = noteF;


        Note noteG =  new Note('G');
        noteF.next = noteG;



        noteG.next = noteC;
    }


}
