package com.test;

import com.util.P;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class SocketTest {

    public static void main(String[] args) throws UnknownHostException {
        //Socket s = new Socket("127.0.0.1",8888);
        Socket socket = new Socket();
        //InetSocketAddress inetSocketAddress = new InetSocketAddress();
        //socket.connect();

        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 4567);
        P.pln(inetSocketAddress.getAddress());
        showAddress("localhost");
        showAddress("www.baidu.com");
        showAddress("www.googletagservices.com");
    }

    private static void showAddress(String host) throws UnknownHostException {
        StringBuilder stringBuilder = new StringBuilder();
        InetAddress[] inetAddresses = InetAddress.getAllByName(host);
        stringBuilder.append(host).append("\n");
        stringBuilder.append("inetAddresses length:").append(inetAddresses.length).append("\n");
        for (int i = 0; i < inetAddresses.length; i++) {
            stringBuilder.append(i).append(" - ").append(inetAddresses[i]).append("\n");
        }
        InetSocketAddress inetSocketAddress = new InetSocketAddress(host,443);
        stringBuilder.append(inetSocketAddress).append("\n");
        P.pln(stringBuilder.toString());
    }

}
