package com.nio;

import com.util.P;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class ServerSocketChannelDemo {
    public static void main(String args[]) throws IOException, InterruptedException {
        P.pln("ServerSocketChannel Demo");
        //blockingTest();
        nonBlockingTest();


    }

    /**
     * 非阻塞模式
     * */
    private static void nonBlockingTest() throws IOException, InterruptedException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));

        while (true) {
            P.pln("test1 accept...");
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel == null){
                TimeUnit.SECONDS.sleep(3);
                continue;
            }

            //do something with socketChannel...
            handleWriteChannel(socketChannel);
        }
    }

    /**
     * 阻塞模式
     */
    private static void blockingTest() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(9999));

        while (true) {
            P.pln("test1 accept...");
            SocketChannel socketChannel = serverSocketChannel.accept();

            //do something with socketChannel...
            handleWriteChannel(socketChannel);
        }
    }

    private static void handleWriteChannel(SocketChannel socketChannel) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String newData = "New String to write to client..." + System.currentTimeMillis() + "\n";

                    ByteBuffer writeBuf = ByteBuffer.allocate(48);
                    writeBuf.clear();
                    writeBuf.put(newData.getBytes());
                    writeBuf.flip();
                    while (writeBuf.hasRemaining()) {

                        P.pln("position:" + writeBuf.position());
                        socketChannel.write(writeBuf);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if(socketChannel != null){
                            socketChannel.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
