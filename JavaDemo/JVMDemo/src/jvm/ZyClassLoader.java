package jvm;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;


public class ZyClassLoader extends ClassLoader {

    private File objFile;

    public ZyClassLoader() {
        super(ClassLoader.getSystemClassLoader());
    }


    public File getObjFile() {
        return objFile;
    }

    public void setObjFile(File objFile) {
        this.objFile = objFile;
    }


    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
//        byte[] b = loadClassData(name);
//        //return super.findClass(name);
//        if(objFile != null){
//           // b
        byte[] b = new byte[0];
        try {
            b = getClassFileBytes(getObjFile());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return defineClass(name, b, 0, b.length);
    }


    /**
     * 把CLASS文件转成BYTE
     *
     * @throws Exception
     */
    private byte[] getClassFileBytes(File file) throws Exception {
        //采用NIO读取
        FileInputStream fis = new FileInputStream(file);
        FileChannel fileC = fis.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel outC = Channels.newChannel(baos);
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while (true) {
            int i = fileC.read(buffer);
            if (i == 0 || i == -1) {
                break;
            }
            buffer.flip();
            outC.write(buffer);
            buffer.clear();
        }
        fis.close();
        return baos.toByteArray();
    }


    @Override
    public String toString() {
        return "ZyClassLoader{" +
                "objFile=" + objFile +
                '}';
    }
}
