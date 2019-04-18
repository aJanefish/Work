package com.mode.abfactory.tablefactory;

import com.mode.abfactory.factory.Item;
import com.mode.abfactory.factory.Page;

import java.util.Iterator;

public class TablePage extends Page {
    public TablePage(String title, String author) {
        super(title, author);
    }

    @Override
    protected String makeHTML() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<html>\n<head><title>" + title + "</title></head>\n");
        buffer.append("<body>\n");
        buffer.append("<h1>" + title + "</h1>\n");
        buffer.append("<table width=\"80%\" border=\"2\">\n");
        Iterator<Item> itemIterator = content.iterator();
        while (itemIterator.hasNext()) {
            Item item = itemIterator.next();
            buffer.append("<tr>"+item.makeHTML()+"</tr>");
        }
        buffer.append("</table>\n");
        buffer.append("<hr><address>"+author+"</address>");
        buffer.append("</body></html>\n");

        return buffer.toString();
    }
}
