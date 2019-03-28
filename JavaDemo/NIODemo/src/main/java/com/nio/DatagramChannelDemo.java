package com.nio;

import com.util.P;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Java NIO 中的DatagramChannel 是一个能收发UDP包的通道。因为UDP是无连接的网络协议，所以不能像
 * 其他通道那样读取和写入。他发送和接受的是数据包。
 */
public class DatagramChannelDemo {
    public static void main(String args[]) throws IOException {
        test1();
    }

    private static void test1() throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        P.pln("bind");
        channel.socket().bind(new InetSocketAddress(9999));
        P.pln("bind ed");
        receive(channel);
        send(channel);
    }


    //receive()方法会将接收到的数据包内容复制到指定的Buffer. 如果Buffer容不下收到的数据，多出的数据将被丢弃。
    private static void receive(DatagramChannel channel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        byteBuffer.clear();
        channel.receive(byteBuffer);
        P.pln(byteBuffer.position());
    }

    private static void send(DatagramChannel channel) throws IOException {
        String newData = "New String to write to file..." + System.currentTimeMillis() + "\n";
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        byteBuffer.clear();
        byteBuffer.put(newData.getBytes());
        byteBuffer.flip();

        int bytesSent = channel.send(byteBuffer, new InetSocketAddress("192.168.201.154", 9999));
        P.p("bytesSent:" + bytesSent);
    }
}
