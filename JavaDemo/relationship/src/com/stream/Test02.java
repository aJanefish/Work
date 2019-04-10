package com.stream;

import com.utils.P;

public class Test02 {
    public Test02() {
    }

    public synchronized String string(String as[]){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(1);
        stringBuffer.append(2);
        stringBuffer.append(3);
        stringBuffer.append(4);
        stringBuffer.append(5);
        stringBuffer.append(6);
        stringBuffer.append(7);
        stringBuffer.append(8);
        stringBuffer.append(9);
        stringBuffer.append(10);
        stringBuffer.append(11);

        synchronized (this){
            int tx = 10;
            tx++;
            P.pln(tx);
        }




        return stringBuffer.toString();
    }
}
