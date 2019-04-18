package com.mode.abfactory.tablefactory;

import com.mode.abfactory.factory.Factory;
import com.mode.abfactory.factory.Link;
import com.mode.abfactory.factory.Page;
import com.mode.abfactory.factory.Tray;

public class TableFactory extends Factory {
    @Override
    public Link createLink(String caption, String url) {
        return new TableLink(caption,url);
    }

    @Override
    public Tray createTray(String caption) {
        return new TableTray(caption);
    }

    @Override
    public Page createPage(String title, String author) {
        return new TablePage(title,author);
    }
}
