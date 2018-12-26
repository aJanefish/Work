package com.zy.main;

import com.zy.test.Member;
import com.zy.test.TableCreator;
import com.zy.utils.Print;

public class AnnotationMain {
    static {
        Print.P("static AnnotationMain");
    }

    private void show(){
        Print.P("show:"+this);
    }

    public static void main(String[] ages){
        Print.P("AnnotationMain ----------- main");
        test1();

    }

    private static void test1() {
        Print.P(Member.class);
        Print.P(Member.class.getName());
        Print.P("----------------------------------------");
        try {
            TableCreator.createTableSql(Member.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {

        }

    }
}
