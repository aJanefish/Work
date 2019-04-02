package com.nio;

import com.util.P;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelDemo {
    public static void main(String args[]) throws IOException {
        P.pln("SocketChannel Demo");
        //test1();
        //test2();
        test3();
    }


    /**
     * 非阻塞模式
     */
    private static void test2() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("192.168.201.154", 9999));


        while (!socketChannel.finishConnect()) {
            P.pln("connect...");
            P.pln("finishConnect:" + socketChannel.finishConnect());
        }
        P.pln("finishConnect:" + socketChannel.finishConnect());

        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = socketChannel.read(buf);
        while (bytesRead != -1) {
            buf.flip();
            while (buf.hasRemaining()) {
                P.p((char) buf.get());
            }
            buf.clear();
            bytesRead = socketChannel.read(buf);
        }
    }

    /**
     * 阻塞模式
     */
    private static void test1() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("192.168.201.154", 9999));

        P.pln("finishConnect:" + socketChannel.finishConnect());

        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = socketChannel.read(buf);
        while (bytesRead != -1) {
            buf.flip();
            while (buf.hasRemaining()) {
                P.p((char) buf.get());
            }
            buf.clear();
            bytesRead = socketChannel.read(buf);
        }
    }


    /**
     * 阻塞模式
     */
    private static void test3() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("192.168.201.121", 8989));

        P.pln("finishConnect:" + socketChannel.finishConnect());

        String info = "I'm " + 0 + "-th information from client";
        ByteBuffer buffer = ByteBuffer.allocate(48);
        buffer.clear();
        buffer.put(info.getBytes());
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.println(buffer);
            //写入到buffer
            socketChannel.write(buffer);
        }
        socketChannel.close();
    }
}
