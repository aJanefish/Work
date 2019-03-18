package com.mode;

import com.mode.factory.IDCardFactory;
import com.mode.factory.Product;
import com.mode.factory.TelevisionFactory;
import com.mode.template.AbstractDisplay;
import com.mode.template.CharDisplay;
import com.mode.template.StringDisplay;
import com.utils.P;

public class ModeMain {

    public static void main(String args[]) {
        //
        templateTest();
        factoryTest();
    }

    private static void factoryTest() {
        P.pln("factoryTest ----------------");
        IDCardFactory idCardFactory = new IDCardFactory();
        Product product1 = idCardFactory.create("小明");
        Product product2 = idCardFactory.create("小丽");
        Product product3 = idCardFactory.create("李华");

        product1.use();
        product2.use();
        product3.use();
        idCardFactory.show();

        TelevisionFactory televisionFactory = new TelevisionFactory();
        Product product4 = televisionFactory.create("长虹1");
        Product product5 = televisionFactory.create("长虹2");
        Product product6 = televisionFactory.create("长虹2");
        product4.use();
        product5.use();
        product6.use();

        televisionFactory.show();

    }

    private static void templateTest() {
        P.pln("templateTest ----------------");
        AbstractDisplay d1 = new CharDisplay('H');
        d1.disPlay();

        AbstractDisplay d2 = new StringDisplay("Hello,World!");
        d2.disPlay();

        AbstractDisplay d3 = new StringDisplay("你好,世界");
        d3.disPlay();
    }
}
