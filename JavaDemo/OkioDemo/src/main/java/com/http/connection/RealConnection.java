package com.http.connection;

import com.http.Request;
import com.http.chain.Interceptor;
import com.util.P;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;

public class RealConnection {

    private Socket rawSocket;

    public RealConnection(Request request) {

        P.pln("RealConnection:" + request);
        InetSocketAddress inetSocketAddress = InetSocketAddress.createUnresolved(request.host(), request.port());
        P.pln("inetSocketAddress:" + inetSocketAddress);


        rawSocket = new Socket();
        try {
            InetAddress[] inetAddresses = InetAddress.getAllByName(request.host());
            P.pln(inetAddresses.length);
            P.pln(Arrays.toString(inetAddresses));
            //new InetSocketAddress(inetAddress, socketPort)

            InetSocketAddress inetSocketAddress1 = null;
            if (inetAddresses.length > 1) {
                inetSocketAddress1 = new InetSocketAddress(inetAddresses[0], request.port());
            }
            if (inetSocketAddress1 != null){
                rawSocket.connect(inetSocketAddress1, 10000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            P.pln("rawSocket:" + rawSocket);
        }
    }
}
