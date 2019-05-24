package com.diy;

import com.utils.P;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class DiyReentrantLock {


    static final class Node {
        static final Node SHARED = new Node();
        static final Node EXCLUSIVE = null;
        static final int CANCELLED = 1;
        static final int SIGNAL = -1;
        static final int CONDITION = -2;
        static final int PROPAGATE = -3;

        volatile int waitStatus;

        volatile Node prev;

        volatile Node next;

        volatile Thread thread;

        Node nextWaiter;


        final boolean isShared() {
            return nextWaiter == SHARED;
        }

        final Node predecessor() throws NullPointerException {
            Node p = prev;
            if (p == null)
                throw new NullPointerException();
            else
                return p;
        }

        Node() {    // Used to establish initial head or SHARED marker
        }

        Node(Thread thread, Node mode) {     // Used by addWaiter
            this.nextWaiter = mode;
            this.thread = thread;
        }

        Node(Thread thread, int waitStatus) { // Used by Condition
            this.waitStatus = waitStatus;
            this.thread = thread;
        }


        @Override
        public String toString() {
            return "Node{" +
                    "waitStatus=" + waitStatus +
                    ", thread=" + thread +
                    ", nextWaiter=" + (nextWaiter == SHARED ? "SHARED" : "EXCLUSIVE") +
                    '}';
        }
    }


    private static Unsafe unsafe = null;
    private boolean fair = false;
    private transient volatile Node head;
    private transient volatile Node tail;
    private volatile int state = 0;
    private transient Thread exclusiveOwnerThread;

    public DiyReentrantLock() {
        this.fair = false;
    }

    public DiyReentrantLock(boolean fair) {
        this.fair = fair;
    }


    public void show() {

        int i = 0;
        StringBuilder stringBuilder = new StringBuilder("[");
        for (Node node = head; node != null; node = node.next) {
            if (node == head) {
                stringBuilder.append("head = " + node + ",\n");
            } else if (node == tail) {
                stringBuilder.append("tail = " + node + ",\n");
            } else {
                stringBuilder.append(i++ + " = " + node + ",\n");
            }

        }
        stringBuilder.append("]");
        P.pln(stringBuilder.toString());

    }

    public void addShare() {
        addWaiter(Node.SHARED);
    }

    public void addExcluSive() {
        addWaiter(Node.EXCLUSIVE);
    }


    private Node addWaiter(Node mode) {
        Node node = new Node(Thread.currentThread(), mode);
        // Try the fast path of enq; backup to full enq on failure
        Node pred = tail;
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

    private Node enq(Node node) {
        for (; ; ) {
            Node t = tail;
            if (t == null) { // Must initialize
                if (compareAndSetHead(new Node()))
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
     * CAS head field. Used only by enq.
     */
    private boolean compareAndSetHead(Node update) {
        return unsafe.compareAndSwapObject(this, headOffset, null, update);
    }

    private boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }


    private int getState() {
        return state;
    }

    public void lock() {
        if (fair) {
            acquire(1);
        } else {
            if (compareAndSetState(0, 1))
                setExclusiveOwnerThread(Thread.currentThread());
            else
                acquire(1);
        }
    }

    private void acquire(int arg) {
        boolean acquire = tryAcquire(arg);
        P.pln("acquire:" + acquire);
    }


    /**
     * 公平锁
     */
    private boolean tryAcquire(int acquires) {

        Thread current = Thread.currentThread();
        int c = getState();
        if (c == 0) {
            if (compareAndSetState(0, acquires)) {
                setExclusiveOwnerThread(current);
                return true;
            }
        } else if (current == getExclusiveOwnerThread()) {
            int nextc = c + acquires;
            if (nextc < 0)
                throw new Error("Maximum lock count exceeded");
            setState(nextc);
            return true;
        }
        return false;

    }


    private void setState(int newState) {
        state = newState;
    }

    private void setExclusiveOwnerThread(Thread thread) {
        exclusiveOwnerThread = thread;
    }

    private Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }


    private boolean compareAndSetState(int expect, int update) {
        // See below for intrinsics setup to support this
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    private static final long stateOffset;

    private static final long tailOffset;

    private static final long headOffset;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        P.pln("unsafe:" + unsafe);

        try {
            stateOffset = unsafe.objectFieldOffset(
                    DiyReentrantLock.class.getDeclaredField("state"));
            tailOffset = unsafe.objectFieldOffset(
                    DiyReentrantLock.class.getDeclaredField("tail"));
            headOffset = unsafe.objectFieldOffset(
                    DiyReentrantLock.class.getDeclaredField("head"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }
}
