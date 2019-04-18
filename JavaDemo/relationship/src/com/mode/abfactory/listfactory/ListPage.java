package com.mode.abfactory.listfactory;

import com.mode.abfactory.factory.Item;
import com.mode.abfactory.factory.Page;

import java.util.Iterator;

public class ListPage extends Page {
    public ListPage(String title, String author) {
        super(title, author);
    }

    @Override
    protected String makeHTML() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<html>\n<head><title>" + title + "</title></head>\n");
        buffer.append("<body>\n");
        buffer.append("<h1>" + title + "</h1>\n");
        buffer.append("<ul>\n");
        Iterator<Item> itemIterator = content.iterator();
        while (itemIterator.hasNext()) {
            Item item = itemIterator.next();
            buffer.append(item.makeHTML());
        }
        buffer.append("</ul>\n");
        buffer.append("<hr>\n<address>"+author+"</address>\n");
        buffer.append("</body></html>");
        return buffer.toString();
    }
}
