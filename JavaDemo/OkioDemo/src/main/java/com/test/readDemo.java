package com.test;

import com.util.FileUtils;
import com.util.P;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;

public class readDemo {
    private static File from = FileUtils.newFile("file1");

    public static void main(String args[]) throws IOException {
        testRead1();
        testRead2();
        testRead3();
    }

    private static void testRead3() throws IOException  {
        P.pln("test3...........................");

    }

    //http://localhost:4567/blog/1
    private static void testRead2() throws IOException {
        P.pln("test2...........................");
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("192.168.201.153", 9999));
        Source source = Okio.source(socket);
        BufferedSource buffer = Okio.buffer(source);
        String string = buffer.readString(Charset.forName("utf-8"));
        P.pln(string);
    }

    private static void testRead1() throws IOException {
        P.pln("test1...........................");
        //创建buffer
        BufferedSource source = Okio.buffer(Okio.source(from));
        P.pln(source);
        String string = source.readString(Charset.forName("utf-8"));
        P.pln(string);
    }

}

