package com.mode.abfactory;

import com.mode.abfactory.factory.Factory;
import com.mode.abfactory.factory.Link;
import com.mode.abfactory.factory.Page;
import com.mode.abfactory.factory.Tray;
import com.mode.abfactory.listfactory.ListFactory;
import com.mode.abfactory.tablefactory.TableFactory;

public class Main {
    public static void main(String args[]) {

        test1(ListFactory.class.getName(),"LinkPage");
        test1(TableFactory.class.getName(),"TablePage");

    }


    private static void test1(String className,String pageName) {
        Factory factory = Factory.getFactory(className);
        Link people = factory.createLink("人明日报", "http://www.people.com.cn/");
        Link gmw = factory.createLink("光明日报", "http://www.gmw.cn/");

        Link us_yahoo = factory.createLink("Yahoo!", "http://www.yahoo.com/");
        Link jp_yahoo = factory.createLink("yahoo!japan", "http://www.yahoo.co.jp/");

        Link excite = factory.createLink("Excite", "http://www.excite.com/");

        Link google = factory.createLink("Google", "http://www.google.com/");


        Tray trayNews = factory.createTray("日报");
        trayNews.add(people);
        trayNews.add(gmw);


        Tray trayyahoo = factory.createTray("Yahoo!");
        trayNews.add(jp_yahoo);
        trayNews.add(us_yahoo);


        Tray traySearch = factory.createTray("检索引擎");
        traySearch.add(trayyahoo);
        traySearch.add(excite);
        traySearch.add(google);


        Page page = factory.createPage(pageName, "张宇");
        //page.add(people);
        page.add(trayNews);
        page.add(traySearch);
        page.output();
    }

}
