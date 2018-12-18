package zy.walk.com.thewalkers.imagesandanimations.event;

import com.daimajia.androidanimations.library.Techniques;

public class DaimajiaEvent {

    public String intent;
    public Techniques techniques;

    public DaimajiaEvent(String intent, Techniques techniques) {
        this.intent = intent;
        this.techniques = techniques;
    }
}
