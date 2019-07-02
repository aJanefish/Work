package com.http;

import com.util.P;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class HttpCaseMain {
    //https://free-api.heweather.com/s6/air/now?location=beijing&key=f464c53cb02240a194640685ee425116
    private static final String url = "https://free-api.heweather.com/s6/air/now?location=beijing&key=f464c53cb02240a194640685ee425116";
    Socket socket = new Socket();

    public static void main(String[] args) {
        try {
            InetAddress inetAddress  = InetAddress.getByName(url);
            P.pln(inetAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
