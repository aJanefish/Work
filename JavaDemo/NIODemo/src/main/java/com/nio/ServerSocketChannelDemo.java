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
        blockingTest();
        //nonBlockingTest();


    }

    /**
     * 非阻塞模式
     */
    private static void nonBlockingTest() throws IOException, InterruptedException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));

        while (true) {
            P.pln("test1 accept...");
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel == null) {
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

                int flag = 0;
                while (true) {
                    try {
//                        String newData = "New String to write to client..." + System.currentTimeMillis() + "\n";
//
//                        ByteBuffer writeBuf = ByteBuffer.allocate(48);
//                        writeBuf.clear();
//                        writeBuf.put(newData.getBytes());
//                        writeBuf.putChar('A');
//                        writeBuf.flip();
//                        while (writeBuf.hasRemaining()) {
//
//                            P.pln("position:" + writeBuf.position());
//                            socketChannel.write(writeBuf);
//                        }

                        ByteBuffer buf = ByteBuffer.allocate(48);
                        int bytesRead = socketChannel.read(buf);
                        while (bytesRead != -1) {
                            buf.flip();
                            while (buf.hasRemaining()) {
                                P.p((char) buf.get());
                            }
                            buf.clear();
                            bytesRead = socketChannel.read(buf);
                            P.pln();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        flag++;
                        if (flag > 100) {
                            break;
                        }
                    }
                }

                try {
                    if (socketChannel != null) {
                        socketChannel.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
