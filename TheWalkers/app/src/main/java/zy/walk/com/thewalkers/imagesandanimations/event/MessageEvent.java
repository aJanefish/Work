package zy.walk.com.thewalkers.imagesandanimations.event;

public class MessageEvent {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int CENTER = 2;

    public String title;
    public boolean isComing;
    public int type = LEFT;

    public MessageEvent(String title, boolean isComing) {
        this.title = title;
        this.isComing = isComing;
    }

    public MessageEvent(String title, int type) {
        this.title = title;
        this.type = type;
    }
}
