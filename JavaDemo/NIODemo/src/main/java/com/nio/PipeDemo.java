package com.nio;

import com.util.P;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.util.concurrent.TimeUnit;

/**
 * Java NIO 管道是2个线程之间的单向数据连接。Pipe有一个source通道和一个sink通道。数据会被写到sink通道，从source通道读取。
 */
public class PipeDemo {

    public static void main(String args[]) throws IOException {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sinkChannel = pipe.sink();
        Pipe.SourceChannel sourceChannel = pipe.source();

        pipeWriteThread(sinkChannel);
        pipeReadThread(sourceChannel);

    }

    private static void pipeReadThread(Pipe.SourceChannel sourceChannel) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                ByteBuffer buf = ByteBuffer.allocate(48);
                while (true) {
                    try {
                        P.pln("read start");
                        int bytesRead = sourceChannel.read(buf);
                        while (bytesRead != -1) {

                            buf.flip();
                            P.p("\t\t\t\t\tsource:");
                            while (buf.hasRemaining()) {
                                P.p((char) buf.get());
                            }
                            buf.clear();
                            P.pln();
                            bytesRead = sourceChannel.read(buf);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    P.pln("read end");
//                    try {
//                        TimeUnit.SECONDS.sleep(4);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        }).start();
    }

    private static void pipeWriteThread(Pipe.SinkChannel sinkChannel) {
        ByteBuffer buf = ByteBuffer.allocate(48);

        new Thread(new Runnable() {
            @Override
            public void run() {
                String newData;
                while (true) {
                    try {
                        newData = "New String to write to file..." + System.currentTimeMillis();
                        P.pln("sink:" + newData);
                        buf.clear();
                        buf.put(newData.getBytes());
                        buf.flip();

                        while (buf.hasRemaining()) {
                            sinkChannel.write(buf);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


}
