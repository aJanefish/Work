package com.address;

import com.utils.P;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
    public static void main(String args[]) throws UnknownHostException {
        test1();
        test2();
        test3();
    }

    private static void test3() throws UnknownHostException {
        InetAddress address = InetAddress.getByName("14.215.177.38");
        show(address);
    }

    private static void test2() throws UnknownHostException {
        InetAddress address = InetAddress.getByName("www.baidu.com");
        show(address);

        InetAddress[] addresss = InetAddress.getAllByName("www.baidu.com");
        show(addresss);
    }


    /**
     * 获取本机Address
     * */
    private static void test1() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        show(address);
    }

    private static void show(InetAddress address){
        P.pln(address+" -----------------"+address.getClass());
        P.pln(address.getCanonicalHostName());
        P.pln(address.getHostAddress());
        P.pln(address.getHostName());
    }

    private static void show(InetAddress[] addresss) {
        P.pln("addresss.length:"+addresss.length);
    }

}
