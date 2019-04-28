package com.zy.myapplication;

class MessageEvent {
    private int id;
    private String des;

    public MessageEvent() {
        this.des = "";
        this.id = 5000;
    }

    public MessageEvent(int id, String des) {
        this.id = id;
        this.des = des;
    }


    @Override
    public String toString() {
        return "MessageEvent{" +
                "id=" + id +
                ", des='" + des + '\'' +
                '}';
    }
}
