package zy.walk.com.thewalkers.imagesandanimations.event;

import android.animation.Animator;
import android.view.animation.Animation;


/**
 * 学习自定义属性动画
 * */
public class DiyAnimationEvent {


    public Animator[] animators;

    public enum Type{
        Animation,Animator,NONE,MENU,TEST,PropertyValuesHolder,AnimatorSet1,AnimatorSet2,Listener
        ,AnimatorListenerAdapter,UpdateListener,XML
    }

    public String intent;
    public String values;
    public Animation animation;
    public Type type = Type.NONE;


    public DiyAnimationEvent(String intent, String values, Type type) {
        this.intent = intent;
        this.values = values;
        this.type = type;
    }

    public DiyAnimationEvent(String intent, String values, Animator ... vas) {
        this.intent = intent;
        this.values = values;
        this.animators = vas;
        this.type = Type.Animator;
    }

    public DiyAnimationEvent(String intent, String values, Animation animation) {
        this.intent = intent;
        this.values = values;
        this.animation = animation;
        this.type = Type.Animation;
    }
}
