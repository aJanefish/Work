package com.zy.demo.okio;

public class OkioDemo {

    private static void create_writer() {
        String filename = "create.txt";
        boolean isCreate = false;
        Sink sink;
        BufferedSink bSink = null;
        try {
            //判断文件是否存在，不存在，则新建！
            File file = new File(filename);
            if (!file.exists()) {
                isCreate = file.createNewFile();
            } else {
                isCreate = true;
            }
            //写入操作
            if (isCreate) {
                sink = Okio.sink(file);
                bSink = Okio.buffer(sink);
                bSink.writeUtf8("1");
                bSink.writeUtf8("\n");
                bSink.writeUtf8("this is new file!");
                bSink.writeUtf8("\n");
                bSink.writeString("我是每二条", Charset.forName("utf-8"));
                bSink.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bSink) {
                    bSink.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
