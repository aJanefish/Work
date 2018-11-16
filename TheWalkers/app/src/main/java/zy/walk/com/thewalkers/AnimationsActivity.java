package zy.walk.com.thewalkers;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import zy.walk.com.thewalkers.activity.BaseActivity;
import zy.walk.com.thewalkers.adapter.DaimajiaAdapter;
import zy.walk.com.thewalkers.adapter.DiyAnimationAdapter;
import zy.walk.com.thewalkers.event.DaimajiaEvent;
import zy.walk.com.thewalkers.event.DiyAnimationEvent;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

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
        list.add(new DiyAnimationEvent("XML rotate","rotate",animator3));
        DiyAnimationAdapter diyAnimationAdapter = new DiyAnimationAdapter(list);
        diyAnimationAdapter.setDiyAnimationDegete(new DiyAnimationAdapter.DiyAnimationDegete() {
            @Override
            public void onClick(final DiyAnimationEvent diyAnimationEvent) {
                clearShow();
                if(diyAnimationEvent.animation != null){

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


                }
            }
        });
        animaion_zhangyu_recycler_view.setAdapter(diyAnimationAdapter);
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

        initDiy();
    }

    private void show(String values){
        text_view_values.append(values+"\n");
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
}
