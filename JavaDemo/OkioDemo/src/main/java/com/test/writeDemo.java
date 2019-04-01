package com.test;

import com.util.FileUtils;
import okio.*;

import java.io.File;
import java.io.IOException;

public class writeDemo {
    public static void main(String args[]) throws IOException {
        File file = FileUtils.newFile("file1");
        writeTest1(file);

        writeTest2();
    }

    private static void writeTest2() throws IOException {
        File from = FileUtils.newFile("file1");
        File to = FileUtils.newFile("file" + System.currentTimeMillis()+".txt");

        //创建buffer

        BufferedSource source = Okio.buffer(Okio.source(from));
        BufferedSink sink = Okio.buffer(Okio.sink(to));
        //copy数据
        sink.writeAll(source);
        //关闭资源
        sink.close();
        source.close();
    }


    // Okio
    public static void writeTest1(File file) {
        try {

            Sink sink = Okio.sink(file);

            BufferedSink bufferedSink = Okio.buffer(sink);
            bufferedSink.writeUtf8("write string by utf-8.\n")
                    .write(ByteString.encodeUtf8("work"))
                    .write("\nni hao ma ?\n".getBytes())
                    .write("1234567890".getBytes())
                    .close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
