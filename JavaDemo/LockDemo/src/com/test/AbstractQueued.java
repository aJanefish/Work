package com.test;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;


public class AbstractQueued {
    private static final Unsafe unsafe;

    private transient volatile SynchNode head;
    private transient volatile SynchNode tail;


    private static final long headOffset;

    private static final long tailOffset;

    static {

        Class<Unsafe> unsafeClass = Unsafe.class;
        Unsafe unsafe1 = null;
        try {
            Field theUnsafe = unsafeClass.getDeclaredField("theUnsafe");
            unsafe1 = (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            unsafe = unsafe1;
        }

        try {
            headOffset = unsafe.objectFieldOffset
                    (AbstractQueuedSynchronizer.class.getDeclaredField("head"));

            tailOffset = unsafe.objectFieldOffset
                    (AbstractQueuedSynchronizer.class.getDeclaredField("tail"));

        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    /**
     * Creates and enqueues node for current thread and given mode.
     *
     * @param mode Node.EXCLUSIVE for exclusive, Node.SHARED for shared
     * @return the new node
     */
    private SynchNode addWaiter(SynchNode mode) {
        SynchNode node = new SynchNode(Thread.currentThread(), mode);
        // Try the fast path of enq; backup to full enq on failure
        SynchNode pred = tail;
        if (pred != null) {
            node.prev = pred;
            if (compareAndSetTail(pred, node)) {
                pred.next = node;
                return node;
            }
        }
        enq(node);
        return node;
    }

    /**
     * Inserts node into queue, initializing if necessary. See picture above.
     * @param node the node to insert
     * @return node's predecessor
     */
    private SynchNode enq(final SynchNode node) {
        for (;;) {
            SynchNode t = tail;
            if (t == null) { // Must initialize
                if (compareAndSetHead(new SynchNode()))
                    tail = head;
            } else {
                node.prev = t;
                if (compareAndSetTail(t, node)) {
                    t.next = node;
                    return t;
                }
            }
        }
    }


    /**
     * Sets head of queue to be node, thus dequeuing. Called only by
     * acquire methods.  Also nulls out unused fields for sake of GC
     * and to suppress unnecessary signals and traversals.
     *
     * @param node the node
     */
    private void setHead(SynchNode node) {
        head = node;
        node.thread = null;
        node.prev = null;
    }



    private final boolean compareAndSetHead(SynchNode update) {
//         if(head == null) head = update;
        return unsafe.compareAndSwapObject(this, headOffset, null, update);
    }

    private final boolean compareAndSetTail(SynchNode expect, SynchNode update) {
//        if(tail == expect){
//            tail = update;
//        }
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

}
