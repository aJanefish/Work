package zy.walk.com.thewalkers;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import zy.walk.com.thewalkers.activity.BaseActivity;
import zy.walk.com.thewalkers.adapter.DaimajiaAdapter;
import zy.walk.com.thewalkers.adapter.DiyAnimationAdapter;
import zy.walk.com.thewalkers.event.DaimajiaEvent;
import zy.walk.com.thewalkers.event.DiyAnimationEvent;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * */

public class AnimationsActivity extends BaseActivity {

    private Button animaion_button;
    private RecyclerView animaion_daimajia_recysler_view;
    private RecyclerView animaion_zhangyu_recycler_view;

    private YoYo.YoYoString rope;
    private TextView text_view_values;
    private ImageView image_vew_menu;
    private ImageView image_vew_dissatisfied;
    private ImageView image_vew_neutral;
    private ImageView image_vew_very_satisfied;
    private ImageView image_vew_satisfied;
    private ImageView image_vew_very_dissatisfied;
    private boolean isOpen = false;
    private ArrayList<ImageView> menus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations);


    }

    private void initDiy() {
        List<DiyAnimationEvent> list = new ArrayList<>();
        Animation animator = AnimationUtils.loadAnimation(AnimationsActivity.this, R.anim.anim_alpha_diy);
        list.add(new DiyAnimationEvent("XML alpha","alpha",animator));
        Animation animator1 = AnimationUtils.loadAnimation(AnimationsActivity.this, R.anim.anim_scale_diy);
        list.add(new DiyAnimationEvent("XML scale","scale",animator1));
        Animation animator2 = AnimationUtils.loadAnimation(AnimationsActivity.this, R.anim.anim_translate_diy);
        list.add(new DiyAnimationEvent("XML translate","translate",animator2));
        Animation animator3 = AnimationUtils.loadAnimation(AnimationsActivity.this, R.anim.anim_rotate_diy);
        LinearInterpolator lin = new LinearInterpolator();
        animator3.setInterpolator(lin);
        list.add(new DiyAnimationEvent("XML rotate","rotate",animator3));

        Animation animator4 = AnimationUtils.loadAnimation(AnimationsActivity.this, R.anim.anim_diy);
        list.add(new DiyAnimationEvent("XML diy","diy",animator4));

        list.add(new DiyAnimationEvent("JAVA Menu","组合动画,菜单模型,AnimatorSet实现",DiyAnimationEvent.Type.MENU));
        list.add(new DiyAnimationEvent("JAVA PropertyValuesHolder","PropertyValuesHolder 用于各种组合动画,非常方便",DiyAnimationEvent.Type.PropertyValuesHolder));
        list.add(new DiyAnimationEvent("JAVA AnimatorSet1","AnimatorSet 用于各种组合动画,非常方便",DiyAnimationEvent.Type.AnimatorSet1));
        list.add(new DiyAnimationEvent("JAVA AnimatorSet2","AnimatorSet 用于各种组合动画,非常方便",DiyAnimationEvent.Type.AnimatorSet2));
        list.add(new DiyAnimationEvent("JAVA Listener","Listener监听器可以实现多种组合动画",DiyAnimationEvent.Type.Listener));
        list.add(new DiyAnimationEvent("AnimatorListenerAdapter","监听器可以实现多种组合动画",DiyAnimationEvent.Type.AnimatorListenerAdapter));
        list.add(new DiyAnimationEvent("UpdateListener","UpdateListener通过数值变换实现动画",DiyAnimationEvent.Type.UpdateListener));
        list.add(new DiyAnimationEvent("JAVA XML","XML实现组合动画",DiyAnimationEvent.Type.XML));


        list.add(new DiyAnimationEvent("JAVA Test","用于测试动画",DiyAnimationEvent.Type.TEST));


        //AnimatorSet 组合动画
        list.add(new DiyAnimationEvent("Flash","Flash(闪光)",ObjectAnimator.ofFloat(animaion_button, "alpha", 1, 0, 1, 0, 1)));
        list.add(new DiyAnimationEvent("Pulse","Pulse(脉冲)",
                ObjectAnimator.ofFloat(animaion_button, "scaleY", 1, 1.1f, 1),
                ObjectAnimator.ofFloat(animaion_button, "scaleX", 1, 1.1f, 1))
        );

        list.add(new DiyAnimationEvent("RubberBand","RubberBand(橡皮圈)",
                ObjectAnimator.ofFloat(animaion_button, "scaleX", 1, 1.25f, 0.75f, 1.15f, 1),
                ObjectAnimator.ofFloat(animaion_button, "scaleY", 1, 0.75f, 1.25f, 0.85f, 1)
        ));

        list.add(new DiyAnimationEvent("Shake","Shake(摇)",
                ObjectAnimator.ofFloat(animaion_button, "translationX", 0, 25, -25, 25, -25, 15, -15, 6, -6, 0)

        ));

        list.add(new DiyAnimationEvent("Swing","Swing(摇摆不定的)",
                ObjectAnimator.ofFloat(animaion_button, "rotation", 0, 10, -10, 6, -6, 3, -3, 0)

        ));

        float width = animaion_button.getWidth();
        float one = (float) (width / 100.0);

        list.add(new DiyAnimationEvent("Wobble","Wobble(摆动)",
                ObjectAnimator.ofFloat(animaion_button, "translationX", 0 * one, -25 * one, 20 * one, -15 * one, 10 * one, -5 * one, 0 * one, 0),
                ObjectAnimator.ofFloat(animaion_button, "rotation", 0, -5, 3, -3, 2, -1, 0)
        ));


        list.add(new DiyAnimationEvent("Bounce","Bounce(反弹)",
                ObjectAnimator.ofFloat(animaion_button, "translationY", 0, 0,-32,0, -16, 0, -8, 0, 0)
        ));



        list.add(new DiyAnimationEvent("Tada","Tada(Tada)",
                ObjectAnimator.ofFloat(animaion_button, "scaleX", 1, 0.9f, 0.9f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1),
                ObjectAnimator.ofFloat(animaion_button, "scaleY", 1, 0.9f, 0.9f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1),
                ObjectAnimator.ofFloat(animaion_button, "rotation", 0, -3, -3, 3, -3, 3, -3, 3, -3, 0)
        ));

        float x = (animaion_button.getWidth() - animaion_button.getPaddingLeft() - animaion_button.getPaddingRight()) / 2
                + animaion_button.getPaddingLeft();
        float y = animaion_button.getHeight() - animaion_button.getPaddingBottom();
        list.add(new DiyAnimationEvent("StandUp","StandUp(StandUp)",
                ObjectAnimator.ofFloat(animaion_button, "pivotX", x, x, x, x, x),
                ObjectAnimator.ofFloat(animaion_button, "pivotY", y, y, y, y, y),
                ObjectAnimator.ofFloat(animaion_button, "rotationX", 55, -30, 15, -15, 0)
        ));


        list.add(new DiyAnimationEvent("Wave","Wave(波)",
                ObjectAnimator.ofFloat(animaion_button, "rotation", 12, -12, 3, -3, 0),
                ObjectAnimator.ofFloat(animaion_button, "pivotX", x, x, x, x, x),
                ObjectAnimator.ofFloat(animaion_button, "pivotY", y, y, y, y, y)

        ));

        x = animaion_button.getPaddingLeft();
        y = animaion_button.getPaddingTop();
        list.add(new DiyAnimationEvent("Hinge","Hinge(铰链)",
                Glider.glide(Skill.SineEaseInOut, 1300, ObjectAnimator.ofFloat(animaion_button, "rotation", 0, 80, 60, 80, 60, 60)),
                ObjectAnimator.ofFloat(animaion_button, "translationY", 0, 0, 0, 0, 0, 700),
                ObjectAnimator.ofFloat(animaion_button, "alpha", 1, 1, 1, 1, 1, 0),
                ObjectAnimator.ofFloat(animaion_button, "pivotX", x, x, x, x, x, x),
                ObjectAnimator.ofFloat(animaion_button, "pivotY", y, y, y, y, y, y)

        ));


        list.add(new DiyAnimationEvent("RollIn","RollIn(RollIn)",
                ObjectAnimator.ofFloat(animaion_button, "alpha", 0, 1),
                ObjectAnimator.ofFloat(animaion_button, "translationX", -(animaion_button.getWidth() - animaion_button.getPaddingLeft() - animaion_button.getPaddingRight()), 0),
                ObjectAnimator.ofFloat(animaion_button, "rotation", -120, 0)

        ));
        list.add(new DiyAnimationEvent("RollOut","RollOut(RollOut)",
                ObjectAnimator.ofFloat(animaion_button, "alpha", 1, 0),
                ObjectAnimator.ofFloat(animaion_button, "translationX", 0, animaion_button.getWidth()),
                ObjectAnimator.ofFloat(animaion_button, "rotation", 0, 120)

        ));


        list.add(new DiyAnimationEvent("BounceIn","BounceIn(BounceIn)",
                ObjectAnimator.ofFloat(animaion_button, "alpha", 0, 1, 1, 1),
                ObjectAnimator.ofFloat(animaion_button, "scaleX", 0.3f, 1.05f, 0.9f, 1),
                ObjectAnimator.ofFloat(animaion_button, "scaleY", 0.3f, 1.05f, 0.9f, 1)
        ));


        list.add(new DiyAnimationEvent("BounceOut","BounceOut(BounceOut)",
                ObjectAnimator.ofFloat(animaion_button, "alpha", 1, 1, 1, 0),
                ObjectAnimator.ofFloat(animaion_button, "scaleX", 1f, 0.9f, 1.1f, 0.3f),
                ObjectAnimator.ofFloat(animaion_button, "scaleY", 1f, 0.9f, 1.1f, 0.3f)
        ));


        list.add(new DiyAnimationEvent("BounceInDown","BounceInDown(BounceInDown)",
                ObjectAnimator.ofFloat(animaion_button, "alpha", 0, 1, 1, 1),
                ObjectAnimator.ofFloat(animaion_button, "translationY", -animaion_button.getHeight(), 30, -10, 0)

        ));

        list.add(new DiyAnimationEvent("BounceInUp","BounceInUp(BounceInUp)",
                ObjectAnimator.ofFloat(animaion_button, "alpha", 0, 1, 1, 1),
                ObjectAnimator.ofFloat(animaion_button, "translationY", animaion_button.getHeight(), -30, 10, 0)

        ));

        list.add(new DiyAnimationEvent("BounceInLeft","BounceInLeft(BounceInLeft)",
                ObjectAnimator.ofFloat(animaion_button, "translationX", -animaion_button.getWidth(), 30, -10, 0),
                ObjectAnimator.ofFloat(animaion_button, "alpha", 0, 1, 1, 1)

        ));

        list.add(new DiyAnimationEvent("BounceInRight","BounceInRight(BounceInRight)",
                ObjectAnimator.ofFloat(animaion_button, "translationX", animaion_button.getWidth(), -30, 10, 0),
                ObjectAnimator.ofFloat(animaion_button, "alpha", 0, 1, 1, 1)

        ));


        list.add(new DiyAnimationEvent("FadeIn","FadeIn(FadeIn)",
                ObjectAnimator.ofFloat(animaion_button, "alpha", 0, 1)
        ));

        list.add(new DiyAnimationEvent("FadeOut","FadeOut(FadeOut)",
                ObjectAnimator.ofFloat(animaion_button, "alpha", 1, 0)
        ));



        list.add(new DiyAnimationEvent("Swing","Swing(摇摆不定的)",
                ObjectAnimator.ofFloat(animaion_button, "rotation", 0, 10, -10, 6, -6, 3, -3, 0)

        ));
        list.add(new DiyAnimationEvent("Swing","Swing(摇摆不定的)",
                ObjectAnimator.ofFloat(animaion_button, "rotation", 0, 10, -10, 6, -6, 3, -3, 0)

        ));
        list.add(new DiyAnimationEvent("Swing","Swing(摇摆不定的)",
                ObjectAnimator.ofFloat(animaion_button, "rotation", 0, 10, -10, 6, -6, 3, -3, 0)

        ));
        list.add(new DiyAnimationEvent("Swing","Swing(摇摆不定的)",
                ObjectAnimator.ofFloat(animaion_button, "rotation", 0, 10, -10, 6, -6, 3, -3, 0)

        ));
        list.add(new DiyAnimationEvent("Swing","Swing(摇摆不定的)",
                ObjectAnimator.ofFloat(animaion_button, "rotation", 0, 10, -10, 6, -6, 3, -3, 0)

        ));
        list.add(new DiyAnimationEvent("Swing","Swing(摇摆不定的)",
                ObjectAnimator.ofFloat(animaion_button, "rotation", 0, 10, -10, 6, -6, 3, -3, 0)

        ));
        list.add(new DiyAnimationEvent("Swing","Swing(摇摆不定的)",
                ObjectAnimator.ofFloat(animaion_button, "rotation", 0, 10, -10, 6, -6, 3, -3, 0)

        ));
        list.add(new DiyAnimationEvent("Swing","Swing(摇摆不定的)",
                ObjectAnimator.ofFloat(animaion_button, "rotation", 0, 10, -10, 6, -6, 3, -3, 0)

        ));
        list.add(new DiyAnimationEvent("Swing","Swing(摇摆不定的)",
                ObjectAnimator.ofFloat(animaion_button, "rotation", 0, 10, -10, 6, -6, 3, -3, 0)

        ));
        list.add(new DiyAnimationEvent("Swing","Swing(摇摆不定的)",
                ObjectAnimator.ofFloat(animaion_button, "rotation", 0, 10, -10, 6, -6, 3, -3, 0)

        ));
        list.add(new DiyAnimationEvent("Swing","Swing(摇摆不定的)",
                ObjectAnimator.ofFloat(animaion_button, "rotation", 0, 10, -10, 6, -6, 3, -3, 0)

        ));
        list.add(new DiyAnimationEvent("Swing","Swing(摇摆不定的)",
                ObjectAnimator.ofFloat(animaion_button, "rotation", 0, 10, -10, 6, -6, 3, -3, 0)

        ));




        final DiyAnimationAdapter diyAnimationAdapter = new DiyAnimationAdapter(list);
        diyAnimationAdapter.setDiyAnimationDegete(new DiyAnimationAdapter.DiyAnimationDegete() {
            @Override
            public void onClick(final DiyAnimationEvent diyAnimationEvent) {
                //clearShow();
                    show(""+diyAnimationEvent.type);
                    switch (diyAnimationEvent.type) {
                        case MENU:
                            upDateAnimation();
                            break;
                        case PropertyValuesHolder:
                            PropertyValuesHolderTest();
                            break;
                        case AnimatorSet1:
                            AnimatorSetTest1();
                        case AnimatorSet2:
                            AnimatorSetTest2();
                            break;
                        case Listener:
                            ListenerTest();
                            break;
                        case AnimatorListenerAdapter:
                            AnimatorListenerAdapterTest();
                            break;
                        case UpdateListener:
                            UpdateListenerTest();
                            break;
                        case XML:
                            XMLTest();
                            break;
                        case NONE:
                            break;
                        case TEST:
                            test();
                            break;
                        case Animator:

                            AnimatorSet set = new AnimatorSet();
                            set.setDuration(2000);
                            set.playTogether(diyAnimationEvent.animators);
                            set.start();

                            break;
                        case Animation:

                            diyAnimationEvent.animation.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {
                                    show(diyAnimationEvent.intent+" onAnimationStart");
                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    show(diyAnimationEvent.intent+" onAnimationEnd");
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {
                                    show(diyAnimationEvent.intent+" onAnimationRepeat");
                                }
                            });
                            //animaion_button.setAnimation(diyAnimationEvent.animation);
                            animaion_button.startAnimation(diyAnimationEvent.animation);


                            break;
                        default:
                            break;
                    }
                }

        });
        animaion_zhangyu_recycler_view.setAdapter(diyAnimationAdapter);
    }

    private void XMLTest() {
        /**
         * XML 实现组合动画
         * */
        Animator animator = AnimatorInflater.loadAnimator(this,R.animator.test1);
        animator.setTarget(animaion_button);
        animator.start();
    }

    private void test() {

        /**
         *
         * 学习网址:
         * https://www.cnblogs.com/itgungnir/p/6211380.html
         * */
        TypeEvaluator<Float> typeEvaluator = new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                return 8f;
            }
        };


        ValueAnimator animator = ValueAnimator.ofInt(0, 200,0); // 产生一个从0到100变化的整数的动画
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue(); // 动态的获取当前运行到的属性值
                show(value + "");
                animaion_button.setTranslationY(value);

            }
        });
        animator.start(); // 开始播放动画



    }

    private void UpdateListenerTest() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 200,0); // 产生一个从0到100变化的整数的动画
        animator.setDuration(2000);
        animator.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                return null;
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue(); // 动态的获取当前运行到的属性值
                show(value + "");

                animaion_button.setRotation(value);
                animaion_button.setTranslationX(value);
                animaion_button.setTranslationY(value);
                animaion_button.setTranslationZ(value);
            }
        });
        animator.start(); // 开始播放动画
    }

    private void AnimatorListenerAdapterTest(){
        /**实现多种组合*/
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(animaion_button, "alpha", 0f, 1f);
        final ObjectAnimator animator2 = ObjectAnimator.ofFloat(animaion_button, "translationX", 0f, 500f);
        final ObjectAnimator animator3 = ObjectAnimator.ofFloat(animaion_button, "translationY", 0f, 500f);

        animator1.setDuration(3000);
        animator2.setDuration(3000);
        animator3.setDuration(3000);
        // 设置属性动画的监听事件（使用AnimatorListenerAdapter可以选择不监听所有事件）
        animator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animator3.start(); // 在animator1执行完后执行animator3
            }
            @Override
            public void onAnimationStart(Animator animation) {
                animator2.start(); //在animator1执行的同时执行animator2
            }
        });
        animator1.start();

    }
    private void ListenerTest() {

        /**实现多种组合*/
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(animaion_button, "alpha", 0f, 1f);
        final ObjectAnimator animator2 = ObjectAnimator.ofFloat(animaion_button, "translationX", 0f, 500f);
        final ObjectAnimator animator3 = ObjectAnimator.ofFloat(animaion_button, "translationY", 0f, 500f);
        animator1.setDuration(3000);
        animator2.setDuration(3000);
        animator3.setDuration(3000);
        // 设置属性动画的监听事件（使用AnimatorListener必须要监听所有四个事件）
        animator1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                animator2.start();
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                animator3.start();
            }
            @Override
            public void onAnimationCancel(Animator animation) {
            }
            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animator1.start();
    }

    @SuppressLint("WrongConstant")
    @Override
    public void initView() {
        animaion_button = findViewById(R.id.animaion_button);
        animaion_daimajia_recysler_view = findViewById(R.id.animaion_daimajia_recysler_view);
        text_view_values = findViewById(R.id.animaion_text_view_values);
        animaion_zhangyu_recycler_view = findViewById(R.id.animaion_zhangyu_recycler_view);
        animaion_daimajia_recysler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        animaion_zhangyu_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));


        image_vew_menu =  findViewById(R.id.animation_image_vew_menu);
        image_vew_dissatisfied = findViewById(R.id.animation_image_vew_dissatisfied);
        image_vew_neutral = findViewById(R.id.animation_image_vew_neutral);
        image_vew_very_satisfied = findViewById(R.id.animation_image_vew_very_satisfied);
        image_vew_satisfied = findViewById(R.id.animation_image_vew_satisfied);
        image_vew_very_dissatisfied = findViewById(R.id.animation_image_vew_very_dissatisfied);

        initDiy();
        //initAnimation();

        menus = new ArrayList<>();
        menus.add(image_vew_dissatisfied);
        menus.add(image_vew_very_satisfied);
        menus.add(image_vew_neutral);
        menus.add(image_vew_very_dissatisfied);
        menus.add(image_vew_satisfied);
    }


    //菜单动画功能
    private void upDateAnimation() {
        for (int i = 0; i < menus.size(); i++) {
            ImageView menu = menus.get(i);
            double angle = Math.toRadians(i * (90 * 1.0 / (menus.size() - 1))); // 角度
            double radius = 200; // 半径
            float distanceX = (float) (Math.cos(angle) * radius); // X坐标偏移量
            float distanceY = (float) (Math.sin(angle) * radius); // Y坐标偏移量
            ObjectAnimator animatorX;
            ObjectAnimator animatorY;
            if (isOpen) { // 如果菜单是打开的则关闭菜单
                animatorX = ObjectAnimator.ofFloat(menu, "translationX", -distanceX, 0f);
                animatorY = ObjectAnimator.ofFloat(menu, "translationY", -distanceY, 0f);
            } else { // 如果菜单是关闭的则打开菜单
                animatorX = ObjectAnimator.ofFloat(menu, "translationX", 0f, -distanceX);
                animatorY = ObjectAnimator.ofFloat(menu, "translationY", 0f, -distanceY);
            }
            AnimatorSet set = new AnimatorSet(); // X、Y轴同时移动
            if(isOpen){
                set.playTogether(animatorX, animatorY);
            }else {
                set.playSequentially(animatorX, animatorY);
            }
            set.setDuration(1000);
            set.setInterpolator(new BounceInterpolator());
            set.start();
        }
        isOpen = !isOpen;
    }

    private void show(String values){
        text_view_values.append(values+"\t\t");
    }

    private void clearShow(){
        text_view_values.setText("");
    }


    @Override
    public void initDate() {

        List<DaimajiaEvent> list = new ArrayList<>();
        list.add(new DaimajiaEvent("ZoomOutUp",Techniques.ZoomOutUp));
        list.add(new DaimajiaEvent("ZoomOutDown",Techniques.ZoomOutDown));
        list.add(new DaimajiaEvent("ZoomOut",Techniques.ZoomOut));
        list.add(new DaimajiaEvent("ZoomOutLeft",Techniques.ZoomOutLeft));
        list.add(new DaimajiaEvent("ZoomOutRight",Techniques.ZoomOutRight));

        list.add(new DaimajiaEvent("ZoomIn",Techniques.ZoomIn));
        list.add(new DaimajiaEvent("ZoomInDown",Techniques.ZoomInDown));
        list.add(new DaimajiaEvent("ZoomInDown",Techniques.ZoomInDown));
        list.add(new DaimajiaEvent("ZoomInLeft",Techniques.ZoomInLeft));
        list.add(new DaimajiaEvent("ZoomInRight",Techniques.ZoomInRight));
        list.add(new DaimajiaEvent("ZoomInUp",Techniques.ZoomInUp));

        list.add(new DaimajiaEvent("FlipInX",Techniques.FlipInX));
        list.add(new DaimajiaEvent("FlipInY",Techniques.FlipInY));
        list.add(new DaimajiaEvent("FlipOutX",Techniques.FlipOutX));
        list.add(new DaimajiaEvent("FlipOutY",Techniques.FlipOutY));

        list.add(new DaimajiaEvent("SlideInDown",Techniques.SlideInDown));
        list.add(new DaimajiaEvent("SlideInUp",Techniques.SlideInUp));
        list.add(new DaimajiaEvent("SlideInLeft",Techniques.SlideInLeft));
        list.add(new DaimajiaEvent("SlideInRight",Techniques.SlideInRight));
        list.add(new DaimajiaEvent("SlideOutDown",Techniques.SlideOutDown));
        list.add(new DaimajiaEvent("SlideOutUp",Techniques.SlideOutUp));
        list.add(new DaimajiaEvent("SlideOutRight",Techniques.SlideOutRight));



        list.add(new DaimajiaEvent("RotateIn",Techniques.RotateIn));
        list.add(new DaimajiaEvent("RotateInDownLeft",Techniques.RotateInDownLeft));
        list.add(new DaimajiaEvent("RotateInDownRight",Techniques.RotateInDownRight));
        list.add(new DaimajiaEvent("RotateInUpLeft",Techniques.RotateInUpLeft));
        list.add(new DaimajiaEvent("RotateInUpRight",Techniques.RotateInUpRight));
        list.add(new DaimajiaEvent("RotateOut",Techniques.RotateOut));
        list.add(new DaimajiaEvent("RotateOutDownLeft",Techniques.RotateOutDownLeft));
        list.add(new DaimajiaEvent("RotateOutDownRight",Techniques.RotateOutDownRight));
        list.add(new DaimajiaEvent("RotateOutUpLeft",Techniques.RotateOutUpLeft));
        list.add(new DaimajiaEvent("RotateOutUpRight",Techniques.RotateOutUpRight));


        list.add(new DaimajiaEvent("FadeIn",Techniques.FadeIn));
        list.add(new DaimajiaEvent("FadeInDown",Techniques.FadeInDown));
        list.add(new DaimajiaEvent("FadeInUp",Techniques.FadeInUp));
        list.add(new DaimajiaEvent("FadeInLeft",Techniques.FadeInLeft));
        list.add(new DaimajiaEvent("FadeInRight",Techniques.FadeInRight));
        list.add(new DaimajiaEvent("FadeOut",Techniques.FadeOut));
        list.add(new DaimajiaEvent("FadeOutDown",Techniques.FadeOutDown));
        list.add(new DaimajiaEvent("FadeOutUp",Techniques.FadeOutUp));
        list.add(new DaimajiaEvent("FadeOutLeft",Techniques.FadeOutLeft));
        list.add(new DaimajiaEvent("FadeOutRight",Techniques.FadeOutRight));

        list.add(new DaimajiaEvent("ZoomOutDown",Techniques.FadeIn));
        list.add(new DaimajiaEvent("ZoomOutDown",Techniques.FadeIn));
        list.add(new DaimajiaEvent("ZoomOutDown",Techniques.FadeIn));
        list.add(new DaimajiaEvent("ZoomOutDown",Techniques.FadeIn));
        list.add(new DaimajiaEvent("ZoomOutDown",Techniques.FadeIn));
        list.add(new DaimajiaEvent("ZoomOutDown",Techniques.FadeIn));
        list.add(new DaimajiaEvent("ZoomOutDown",Techniques.FadeIn));
        list.add(new DaimajiaEvent("ZoomOutDown",Techniques.FadeIn));


        DaimajiaAdapter daimajiaAdapter = new DaimajiaAdapter(list);
        daimajiaAdapter.setDaimajiaAdapterDegele(new DaimajiaAdapter.DaimajiaAdapterDegele() {
            @Override
            public void onClick(final DaimajiaEvent daimajiaEvent) {
                if (rope != null) {
                    rope.stop(true);
                }

                rope = YoYo.with(daimajiaEvent.techniques)
                        .duration(2000)
                        .repeat(YoYo.INFINITE)
                        .pivot(YoYo.CENTER_PIVOT, YoYo.CENTER_PIVOT)
                        .interpolate(new AccelerateDecelerateInterpolator())
                        .withListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                show(daimajiaEvent.intent+":onAnimationStart");
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                show(daimajiaEvent.intent+":onAnimationEnd");
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {
                                show(daimajiaEvent.intent+":onAnimationCancel");
                                //Toast.makeText(AnimationsActivity.this, "canceled previous animation", Toast.LENGTH_SHORT).show();
                                //ToastUtils.show("canceled previous animation");

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {
                                show(daimajiaEvent.intent+":onAnimationRepeat");

                            }
                        })
                        .playOn(animaion_button);
            }
        });
        animaion_daimajia_recysler_view.setAdapter(daimajiaAdapter);

        animaion_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rope != null) {
                    rope.stop(true);
                }
            }
        });
    }


    /**
     *
     * 用PropertyValuesHolder实现组合动画
     *
     * */
    private void PropertyValuesHolderTest(){
        /**
         * 同时执行
         * */
        //移动
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("translationX", 0f, 500f,0f);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("translationY", 0f, 500f,0f);
        //旋转
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("rotation", 0f, 360f);
        ObjectAnimator.ofPropertyValuesHolder(image_vew_neutral, holder1, holder2, holder3).setDuration(3000).start();

        //keyframe
        Keyframe keyframe1 = Keyframe.ofFloat(0.0f,0);
        Keyframe keyframe2 = Keyframe.ofFloat(0.25f,-90);
        Keyframe keyframe3 = Keyframe.ofFloat(0.5f,0);
        Keyframe keyframe4 = Keyframe.ofFloat(0.75f, 90);
        Keyframe keyframe5 = Keyframe.ofFloat(1.0f,0);
        PropertyValuesHolder rotation = PropertyValuesHolder.ofKeyframe("rotation", keyframe1, keyframe2, keyframe3, keyframe4,keyframe5);

        //alpha
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha",1.0f,0.2f,1.0f);
        //scale
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX",1.0f,2f,1.0f,0.5f,1.0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY",1.0f,2f,1.0f,0.5f,1.0f);
        PropertyValuesHolder color = PropertyValuesHolder.ofInt("BackgroundColor", 0XFFFFFF00, 0XFF0000FF,0XFFFFFF00);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(animaion_button,rotation,alpha,scaleX,scaleY,color,holder1,holder2);
        animator.setInterpolator(new OvershootInterpolator());
        animator.setDuration(5000).start();

    }


    private boolean AnimatorSetTest = false;
    private void AnimatorSetTest1(){
        /**
         *
         * 同时开始
         *
         * 依次执行
         *
         * */
        //移动 translation
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(animaion_button, "translationX", 0f, 500f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(animaion_button, "translationY", 0f, 500f);
        //rotation
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(animaion_button, "rotation", 0f, 360f);

        ObjectAnimator animator4 = ObjectAnimator.ofFloat(animaion_button, "alpha", 1.0f,0.2f,1.0f);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(animaion_button,"scaleX",1.0f,2f,1.0f,0.5f,1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(animaion_button,"scaleY",1.0f,2f,1.0f,0.5f,1.0f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(3000);
        if(AnimatorSetTest){
            set.playTogether(animator1, animator2, animator3,animator4,scaleX,scaleY);
        }else {
            set.playSequentially(animator1, animator2, animator3,animator4,scaleX,scaleY);
        }
        AnimatorSetTest = !AnimatorSetTest;

        set.start();
    }


    private void AnimatorSetTest2(){
        /**
         * play 实现组合动画
         *
         * 实现多种组合
         *
         * */

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(animaion_button, "translationX", 0f, 500f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(animaion_button, "translationY", 0f, 500f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(animaion_button, "rotationX", 0f, 360f);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(animaion_button, "rotationY", 0f, 360f);
        AnimatorSet set = new AnimatorSet();
        set.play(animator3).before(animator2).after(animator1).with(animator4);
        set.setDuration(3000);
        set.start();
    }

}
