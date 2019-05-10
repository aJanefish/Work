package zy.walk.com.thewalkers;


import zy.walk.com.thewalkers.activity.BaseActivity;
import zy.walk.com.thewalkers.viewinjection.ViewField;
import zy.walk.com.thewalkers.viewinjection.ViewMethod;
import zy.walk.com.thewalkers.viewinjection.ViewUtils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


/**
 *
 */

public class AnimationsActivity extends BaseActivity {


    private String TAG = "AnimationsActivity";

    @ViewField(R.id.activity_animations_animation)
    Button animation_button;


    @ViewField(R.id.activity_animations_image_view)
    ImageView imageView;
    private AnimationDrawable frameAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations);
        ViewUtils.register(this);

    }

    @Override
    public void initView() {
        if (frameAnim == null) {
            frameAnim = new AnimationDrawable();
            // 为AnimationDrawable添加动画帧
            int duration = 200;
            frameAnim.addFrame(getResources().getDrawable(R.drawable.ic_sentiment_dissatisfied_black_24dp), duration);
            frameAnim.addFrame(getResources().getDrawable(R.drawable.ic_settings_input_svideo_black_24dp), duration);
            frameAnim.addFrame(getResources().getDrawable(R.drawable.ic_sentiment_very_satisfied_black_24dp), duration);
            frameAnim.addFrame(getResources().getDrawable(R.drawable.ic_sentiment_very_dissatisfied_black_24dp), duration);
            frameAnim.addFrame(getResources().getDrawable(R.drawable.ic_sentiment_neutral_black_24dp), duration);
            frameAnim.addFrame(getResources().getDrawable(R.drawable.ic_sentiment_satisfied_black_24dp), duration);
            frameAnim.setOneShot(false);

            // 设置ImageView的背景为AnimationDrawable
            imageView.setImageDrawable(frameAnim);
        }
    }

    @Override
    public void initDate() {

    }

    @Override
    protected String getLog() {
        return TAG;
    }


    /**
     * 补间动画测试
     */
    @ViewMethod(R.id.activity_animations_bujian)
    public void bujian(View view) {
        Animation translateAnimation2 = new TranslateAnimation(0, 500, 0, 500);
        translateAnimation2.setDuration(3000);
        translateAnimation2.setRepeatCount(Animation.ABSOLUTE);
        animation_button.startAnimation(translateAnimation2);
    }

    /**
     * 属性动画测试
     */
    @ViewMethod(R.id.activity_animations_shuxing_translationX)
    public void translationX(View view) {
        ObjectAnimator animator = null;
        float curTranslationX = animation_button.getTranslationX();
        animator = ObjectAnimator.ofFloat(animation_button, "translationX", curTranslationX, -500f, curTranslationX);
        animator.setDuration(5000);
        animator.start();
        //animation_button.setTranslationZ(100);
    }
    /**
     * 属性动画测试
     */
    @ViewMethod(R.id.activity_animations_shuxing_alpha)
    public void alpha(View view) {
        ObjectAnimator animator = null;
        animator = ObjectAnimator.ofFloat(animation_button, "alpha", 1f, 0f, 1f);
        animator.setDuration(5000);
        animator.start();
        //animation_button.setAlpha(0.5f);
    }

    /**
     * 属性动画测试
     */
    @ViewMethod(R.id.activity_animations_shuxing_rotation)
    public void rotation(View view) {
        ObjectAnimator animator = null;
        animator = ObjectAnimator.ofFloat(animation_button, "rotation", 0f, 360f);
        animator.setDuration(5000);
        animator.start();
        //animation_button.setRotation(100f);
    }

    /**
     * 属性动画测试
     */
    @ViewMethod(R.id.activity_animations_shuxing_scaleY)
    public void scaleY(View view) {
        ObjectAnimator animator = null;
        animator = ObjectAnimator.ofFloat(animation_button, "scaleY", 1f, 3f, 1f);
        animator.setDuration(5000);
        animator.start();
        //animation_button.setScaleX(2f);
    }

    /**
     * 属性动画测试
     */
    @ViewMethod(R.id.activity_animations_shuxing_zuhe)
    public void zuhe(View view) {

        float curTranslationX = animation_button.getTranslationX();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(animation_button, "translationX", curTranslationX, -500f, curTranslationX);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(animation_button, "alpha", 1f, 0f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(animation_button, "rotation", 0f, 360f);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(animation_button, "scaleY", 1f, 3f, 1f);

        AnimatorSet animSet = new AnimatorSet();
        animSet.play(animator1).with(animator2).after(animator3).before(animator4);
        animSet.setDuration(5000);
        animSet.start();
    }


    /**
     * 逐帧动画测测试
     */
    private boolean start = false;

    @ViewMethod(R.id.activity_animations_zhuzhen)
    public void zhuzhen(View view) {
        if (!start) {
            start = !start;
            if (frameAnim != null && !frameAnim.isRunning()) {
                frameAnim.start();
                Toast.makeText(AnimationsActivity.this, "开始播放", Toast.LENGTH_LONG).show();
            }
        } else {
            start = !start;
            if (frameAnim != null && frameAnim.isRunning()) {
                frameAnim.stop();
                Toast.makeText(AnimationsActivity.this, "停止播放", Toast.LENGTH_LONG).show();
            }
        }
    }


}
