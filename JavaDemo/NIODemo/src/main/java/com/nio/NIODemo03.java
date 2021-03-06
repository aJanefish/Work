package com.nio;

import com.util.P;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NIODemo03 {

    private static ExecutorService pool = Executors.newCachedThreadPool();
    public static void main(String args[]) {
        P.pln("NIO NIODemo03");
        //client1();

        int tmp = 10000;
        for (int i = 0; i < tmp; i++) {
            int finalI = i;
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    client2();
                }
            });
        }
    }

    private static void client2() {

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);

        SocketChannel socketChannel = null;
        try {
            //连接网络
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("192.168.201.153", 8080));
            if (socketChannel.finishConnect()) {
                int i = 0;
                while (true) {
                    TimeUnit.SECONDS.sleep(5);
                    String info = "I'm " + i++ + "-th information from client";
                    buffer.clear();
                    buffer.put(info.getBytes());
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        System.out.println(buffer);
                        //写入到buffer
                        socketChannel.write(buffer);
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socketChannel != null) {
                    socketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 客户端NIO Demo
     */
    private static void client1() {

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);

        SocketChannel socketChannel = null;
        try {
            //连接网络
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("192.168.201.154", 8080));
            if (socketChannel.finishConnect()) {

                int i = 0;
                while (true) {
                    TimeUnit.SECONDS.sleep(5);
                    String info = "I'm " + i++ + "-th information from client";
                    buffer.clear();
                    buffer.put(info.getBytes());
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        System.out.println(buffer);
                        //写入到buffer
                        socketChannel.write(buffer);
                    }

                    //读取buffer
                    socketChannel.read(readBuffer);
                    readBuffer(readBuffer);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socketChannel != null) {
                    socketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void readBuffer(ByteBuffer readBuffer) {
        readBuffer.flip();
        P.pln("client - readBuffer - start");
        while (readBuffer.hasRemaining()) {
            System.out.print((char) readBuffer.get());
            //readBuffer.getInt();
        }
        P.pln("client - readBuffer - end");
        readBuffer.compact();

    }
}
