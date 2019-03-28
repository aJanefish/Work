package com.nio;

import com.util.P;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO Channel
 * JAVA NIO的通道类似流，又有些不同：
 * 既可以从通道中读取数据，有可以写数据到通道。但流的读写通常是单向的。
 * 通道可以异步大的读写
 * 通道中的数据总要先读到一个Buffer,或者总要从一个Buffer中写入。
 * <p>
 * FileChannel 从文件中读写数据
 * DatagramChannel 通过UDP读写网络中的数据
 * SocketChannel 能通过TCP读写网络中数据
 * ServerSocketChannel 可以监听新进来的TCP连接，想Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel
 */
public class ChannelDemo {
    public static void main(String args[]) throws IOException {
        P.pln("Channel Demo ");
        fileChannelTest1();
        fileChannelTest2();
    }

    /**
     * FileChannel的transferFrom()方法可以将数据从源通道传输到FileChannel中
     * FileChannel的transferTo()方法将数据从FileChannel传输到其他的channel中
     */
    private static void fileChannelTest2() {
        P.pln("fileChannelTest2 ------");
        try {
            RandomAccessFile fromFile = new RandomAccessFile("data/fromFile.txt", "rw");
            FileChannel fromChannel = fromFile.getChannel();
            RandomAccessFile toFile = new RandomAccessFile("data/toFile.txt", "rw");
            FileChannel toChannel = toFile.getChannel();
            long position = 0;
            long count = fromChannel.size();

            P.pln(position + ":" + count);
            toChannel.transferFrom(fromChannel, position, count);
            //toChannel.transferTo(position,count,fromChannel);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void fileChannelTest1() throws IOException {
        RandomAccessFile accessFile = getFileChannel();
        FileChannel fileChannel = accessFile.getChannel();
        P.pln(fileChannel);

        readChannel(fileChannel,true);
        P.pln();
        //writeChannel(fileChannel);


        fileChannel.close();
        accessFile.close();
        P.pln();
    }

    private static void writeChannel(FileChannel fileChannel) throws IOException {
        String newData = "New String to write to file..." + System.currentTimeMillis() + "\n";

        ByteBuffer writeBuf = ByteBuffer.allocate(48);
        writeBuf.clear();
        writeBuf.put(newData.getBytes());
        writeBuf.flip();
        while (writeBuf.hasRemaining()) {
            P.pln("position:" + writeBuf.position());
            fileChannel.write(writeBuf);
        }
    }


    private static void readChannel(FileChannel fileChannel,boolean truncate) throws IOException {
        if (fileChannel == null) {
            P.pln("fileChannel is null");
            return;
        }
        //FileChannel的truncate方法

        if (truncate) {
            long size = fileChannel.size();
            fileChannel.truncate(size / 2);
        }

        ByteBuffer readBuffer = ByteBuffer.allocate(48);
        int bytesRead = fileChannel.read(readBuffer);
        while (bytesRead != -1) {
            readBuffer.flip();
            while (readBuffer.hasRemaining()) {
                P.p((char) readBuffer.get());
            }
            readBuffer.clear();
            bytesRead = fileChannel.read(readBuffer);
        }


    }

    private static RandomAccessFile getFileChannel() {
        RandomAccessFile accessFile = null;
        try {
            accessFile = new RandomAccessFile("src/nio.txt", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return accessFile;
    }

}
