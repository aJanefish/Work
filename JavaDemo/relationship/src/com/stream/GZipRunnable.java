package com.stream;

import com.utils.P;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class GZipRunnable implements Runnable {
    private final File input;
    private final String PARNET = "gztest";

    public GZipRunnable(File input) {
        this.input = input;
    }

    @Override
    public void run() {
        //不压缩已经压缩的文件
        if (!input.getName().endsWith(".gz")) {
            File output = null;
            String parent = null;
            if (input.getParent() == null) {
                parent = PARNET;
                output = new File(parent, input.getName() + ".gz");
            } else {
                parent = PARNET + "/" + input.getParent();
                output = new File(parent, input.getName() + ".gz");

            }
            P.pln(input.getParent() + " - " + output.getName());
            if (!output.exists()) {//不覆盖已经存在的文件
                new File(parent).mkdirs();
                try (
                        BufferedInputStream in = new BufferedInputStream(new FileInputStream(input));
                        OutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(output)));
                ) {
                    int b;
                    while ((b = in.read()) != -1) out.write(b);
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
