package com.mode.abfactory.listfactory;

import com.mode.abfactory.factory.Item;
import com.mode.abfactory.factory.Tray;

import java.util.Iterator;

public class ListTray extends Tray {
    public ListTray(String caption) {
        super(caption);
    }

    @Override
    public String makeHTML() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<li>\n");
        buffer.append(caption + "\n");
        buffer.append("<ul>\n");
        Iterator<Item> itemIterator = tray.iterator();
        while (itemIterator.hasNext()) {
            Item item = itemIterator.next();
            buffer.append(item.makeHTML());
        }
        buffer.append("</ul>\n");
        buffer.append("</li>\n");
        
        return buffer.toString();
    }
}
