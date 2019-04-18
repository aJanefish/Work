package com.mode.builder;


import com.utils.P;

public class Main {
    public static void main(String args[]) {
//        if (args.length != 1) {
//            usage();
//            System.exit(0);
//        }
        int id = 0;
        if (id == 1) {
            TextBuilder textBuilder = new TextBuilder();
            Director director = new Director(textBuilder);
            director.constriuct();
            String result = textBuilder.getResult();
            P.pln(result);
        }else {
            HTMLBuilder htmlBuilder = new HTMLBuilder();
            Director director = new Director(htmlBuilder);
            director.constriuct();
            String filename = htmlBuilder.getResult();
            P.pln(filename+"文档编写完成");
        }

    }

    private static void usage() {
        P.pln("Usage: java Main plain 编写纯文本文档");
        P.pln("Usage: java Main html 编写html文档");

    }
}
