package com.algorithm.algorithm;

import com.algorithm.utils.P;

import java.util.LinkedHashMap;
import java.util.Map;

class Data<T> {
    public Data<T> priv;
    public Data<T> next;
    public T values;


    public Data(Data<T> priv, Data<T> next, T values) {
        this.priv = priv;
        this.next = next;
        this.values = values;
    }

    @Override
    public String toString() {
        return "Data{" +
                "t=" + values +
                '}';
    }
}

public class LRUDemo<T> {

    Data<T> head;

    public LRUDemo() {

    }

    public void add(T t) {
        if (head == null) {
            head = new Data<>(null, null, t);
            head.priv = null;
            head.next = null;
            return;
        }

        Data<T> data = new Data<>(null, head, t);
        head.priv = data;
        head = data;

    }

    public void show() {
        Data<T> tmp = head;
        P.p("[");
        while (tmp != null) {
            P.p(tmp.values);
            tmp = tmp.next;
            if (tmp != null) {
                P.p(",");
            }
        }
        P.p("]\n");
    }


    public T get(T t) {
        Data<T> tmp = head;
        while (tmp != null) {
            if (t.equals(tmp.values)) {
                //移动tmp元素移到头部

                if (tmp == head) {

                } else if (tmp.next == null) {
                    Data<T> priv = tmp.priv;
                    priv.next = null;
                    tmp.priv = null;

                    head.priv = tmp;
                    tmp.next = head;
                    head = tmp;
                } else {

                    Data<T> priv = tmp.priv;
                    Data<T> next = tmp.next;

                    //删除tmp位置
                    priv.next = next;
                    next.priv = priv;

                    //tmp 移到head
                    tmp.priv = null;
                    tmp.next = head;
                    head.priv = tmp;

                    head = tmp;
                }


                return t;
            }
            tmp = tmp.next;
        }
        return null;
    }

    public static void main(String[] args) {
        P.pln("LRU");
        LRUDemo<Integer> lruDemo = new LRUDemo<>();
        lruDemo.add(1);
        lruDemo.add(2);
        lruDemo.add(3);
        lruDemo.add(4);
        lruDemo.add(5);
        lruDemo.add(6);
        lruDemo.get(6);

        lruDemo.show();
        lruDemo.get(3);
        lruDemo.show();

        lruDemo.add(7);

        lruDemo.show();
        lruDemo.get(4);
        lruDemo.show();
        LinkedHashMap<Integer,Integer> linkedHashMap = new LinkedHashMap<>(0, 0.75f, true);
        //LinkedHashMap<Integer,Integer> linkedHashMap = new LinkedHashMap<Integer,Integer>(true);
        linkedHashMap.put(1,1);
        linkedHashMap.put(2,2);
        linkedHashMap.put(3,3);
        linkedHashMap.put(4,4);
        linkedHashMap.put(5,5);
        P.pln(linkedHashMap);
        linkedHashMap.get(3);
        linkedHashMap.get(4);
        P.pln(linkedHashMap);
        for (Map.Entry<Integer, Integer> entry : linkedHashMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());

        }
    }
}
