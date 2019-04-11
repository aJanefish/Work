package com.stream;

import com.utils.P;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GZipAllFiles {

    public final static int THREAD_COUNT = 4;
    private static ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
    public static void main(String args[]) {


        File file = new File("src");
        P.pln(file);
        gz(file);
        pool.shutdown();
//        pool.submit(new Runnable() {
//            @Override
//            public void run() {
//                P.pln("shutdown .....");
//            }
//        });
    }


    public static void gz(File file){
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if(files[i].isDirectory()){
                        gz(files[i]);
                    }else {
                        GZipRunnable gZipRunnable = new GZipRunnable(files[i]);
                        pool.submit(gZipRunnable);
                    }
                }
            }else {
                GZipRunnable gZipRunnable = new GZipRunnable(file);
                pool.submit(gZipRunnable);
            }
        }


    }
}
