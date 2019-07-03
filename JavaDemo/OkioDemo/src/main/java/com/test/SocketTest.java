package com.test;

import com.util.P;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class SocketTest {

    public static void main(String[] args) {
        //Socket s = new Socket("127.0.0.1",8888);
        Socket socket = new Socket();
        //InetSocketAddress inetSocketAddress = new InetSocketAddress();
        //socket.connect();

        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 4567);
        P.pln(inetSocketAddress.getAddress());
        try {
            InetAddress inetAddress = InetAddress.getByName("localhost");
            P.pln(inetAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
