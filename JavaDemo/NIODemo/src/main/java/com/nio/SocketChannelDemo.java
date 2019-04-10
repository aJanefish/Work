package com.nio;

import com.util.P;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SocketChannelDemo {

    private static ExecutorService pool = Executors.newCachedThreadPool();

    public static void main(String args[]) throws IOException {
        P.pln("SocketChannel Demo");
        //test1();
        //test2();


        int tmp = 10000;
        for (int i = 0; i < tmp; i++) {
            int finalI = i;
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        test3(finalI);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
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
    private static void test3(int i) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("192.168.201.153", 9999));

        P.pln("finishConnect:" + socketChannel.finishConnect());

        String info = "I'm " + i + "-th information from client";
        ByteBuffer buffer = ByteBuffer.allocate(48);
        int flag = 0;
        while (true) {
            buffer.clear();
            buffer.put(info.getBytes());
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.println(Thread.currentThread()+" - "+flag);
                //写入到buffer
                socketChannel.write(buffer);
            }

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

        socketChannel.close();
    }
}
