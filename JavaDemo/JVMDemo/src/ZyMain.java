import jvm.JvmStaticDemo;
import jvm.ZyClassLoader;
import utils.P;

import java.io.File;
import java.net.URL;

public class ZyMain {

    public static void main(String args[]) {

        //test1();
        //test2();
        test3();
    }

    private static void test3() {

        ZyClassLoader zyClassLoader = new ZyClassLoader();
        P.pln("zyClassLoader:"+zyClassLoader);
        File newFile = new File(Worker.class.getResource("").getFile() + "Worker.class");
        P.pln("newFile:" + newFile);
        zyClassLoader.setObjFile(newFile);
        try {
            Class<Worker> aClass = (Class<Worker>) zyClassLoader.loadClass("Worker");
            P.pln(aClass + ":" + aClass.getClassLoader());
            Worker worker = new Worker();
            if (worker instanceof Worker) {
                P.pln("1 worker instanceof Worker:" + worker.getClass().getClassLoader());
            }
            Worker worker1 = aClass.newInstance();
            if (worker1 instanceof Worker) {
                P.pln("2 worker instanceof Worker:" + worker.getClass().getClassLoader());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    private static void test2() {
        Class<Worker> workerClass = Worker.class;
        URL url = workerClass.getResource("");
        String urlFile = url.getFile();
        P.pln(url);
        P.pln(urlFile);
    }

    private static void test1() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                P.pln(this + ":run start");
                JvmStaticDemo jvmStaticDemo = new JvmStaticDemo();
                P.pln(this + ":run  end:" + jvmStaticDemo);
            }
        };


        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();


    }
}

