package jvm;

import utils.P;


/**
 * 同一个Class 不同的类加载器加载出来的class文件
 * instanceof 结果为False
 * */

public class JvmStaticDemo {

    static {
        if(true){
            //while (true){
                P.pln("JvmStaticDemo Static");
            //}
        }
    }
}
