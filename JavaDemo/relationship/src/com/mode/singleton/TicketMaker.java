package com.mode.singleton;

public class TicketMaker {
    private TicketMaker() {
    }
    private int ticket = 10000;
    private static TicketMaker  ticketMaker = new TicketMaker();

    public static TicketMaker getInstance() {
        return ticketMaker;
    }

    public int getNextTicketNumber(){
        return ticket++;
    }
}
