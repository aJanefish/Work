package com.mode.abfactory.listfactory;

import com.mode.abfactory.factory.Factory;
import com.mode.abfactory.factory.Link;
import com.mode.abfactory.factory.Page;
import com.mode.abfactory.factory.Tray;

public class ListFactory extends Factory {
    @Override
    public Link createLink(String caption, String url) {
        return new ListLink(caption,url);
    }

    @Override
    public Tray createTray(String caption) {
        return new ListTray(caption);
    }

    @Override
    public Page createPage(String title, String author) {
        return new ListPage(title,author);
    }
}
