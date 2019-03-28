package com.nio;

import com.util.P;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 *
 */
public class SelectorDemo {
    private static final int PORT = 8080;
    private static final long TIMEOUT = 3000;

    public static void main(String args[]) {
        P.pln("Selector Demo");
        selector();
    }

    private static void selector() {
        Selector selector = null;
        ServerSocketChannel ssc = null;
        try {
            selector = Selector.open();
            ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(PORT));
            ssc.configureBlocking(false);
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {

                if (selector.select(TIMEOUT) == 0) {
                    System.out.println("waiting ==");
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                P.pln("selectionKeys:" + selectionKeys.size());
                Iterator<SelectionKey> iter = selectionKeys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    P.pln("key:" + key.readyOps() + "-" + key.interestOps());

                    iter.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (selector != null) {
                    selector.close();
                }
                if (ssc != null) {
                    ssc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
