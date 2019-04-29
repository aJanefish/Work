package com.zy;

import java.io.*;
import java.util.Arrays;

/**
 * Class 文件导入成Bytes
 * */
public class ClassLoadTest {

    public static void main(String args[]){
        byte[] classData = loadClassData("E:\\jvm\\Jobs.class");
        System.out.println(classData);
        if (classData == null)
            return;
        System.out.println(classData.length);
        System.out.println(Arrays.toString(classData));
    }

    private  static byte[] loadClassData(String name) {

        File file = new File(name);
        InputStream in=null;
        ByteArrayOutputStream out=null;
        try {
            in = new FileInputStream(file);
            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length=0;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(in!=null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try{
                if(out!=null) {
                    out.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
