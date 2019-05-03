package com.zy.glidedemo.myadapter;

public class MessageEvent {
    String title;
    String url;

    public MessageEvent(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
