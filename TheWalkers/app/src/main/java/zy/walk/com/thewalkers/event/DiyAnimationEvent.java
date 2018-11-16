package zy.walk.com.thewalkers.event;

import android.view.animation.Animation;

/**
 * 学习自定义属性动画
 * */
public class DiyAnimationEvent {

    public String intent;
    public String values;
    public Animation animation;


    public DiyAnimationEvent(String intent, String values, Animation animation) {
        this.intent = intent;
        this.values = values;
        this.animation = animation;
    }
}
