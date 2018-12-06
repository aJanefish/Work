package zy.walk.com.thewalkers.diy;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.Random;

import androidx.annotation.Nullable;

public class RunBallTwo extends View {
    private ValueAnimator mAnimator;//时间流
    private Ball mBall;//小球对象
    private Paint mPaint;//主画笔
    private Point mCoo;//坐标系

    private float defaultR = 20;//默认小球半径
    private int defaultColor = Color.BLUE;//默认小球颜色
    private float defaultVX = 0;//默认小球x方向速度
    private float defaultVY = 0;//默认小球y方向速度
    private String TAG = "RunBallTwo";

    public RunBallTwo(Context context) {
        this(context, null);
    }

    public RunBallTwo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        //初始化小球
        mBall = new Ball(defaultR);
        mBall.color = randomRGB();
        mBall.x = defaultR;
        mBall.y = defaultR;
        Ball endBall = mBall.clone();//小球的终点
        endBall.x = 1600;
        endBall.y = 400;


        //初始画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //初始化时间流ValueAnimator
        mAnimator = ValueAnimator.ofObject(new SinEvaluator(), mBall, endBall);
        mAnimator.setRepeatCount(-1);
        mAnimator.setDuration(1000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mBall = (Ball) animation.getAnimatedValue();//通过估值器计算，更新小球
                //时间流,必须是线性的

                invalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCoo == null) {
            mCoo = new Point(canvas.getWidth(), canvas.getHeight());
        }
        canvas.save();

        //平移
        canvas.translate(0, mCoo.y/2);
        canvas.drawLine(0,0,canvas.getWidth(),0,mPaint);
        drawBall(canvas, mBall);
        Log.d(TAG,"mBall:"+mBall);
        canvas.restore();
    }

    /**
     * 绘制小球
     *
     * @param canvas
     * @param ball
     */
    private void drawBall(Canvas canvas, Ball ball) {
        mPaint.setColor(ball.color);
        canvas.drawCircle(ball.x, ball.y, ball.r, mPaint);
    }


    public void start() {
        mAnimator.start();//开启时间流

    }

    public void pause() {
        reset();
        mAnimator.pause();//暂停时间流
    }

    private void reset() {
        invalidate();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                start();
                break;
            case MotionEvent.ACTION_UP:
                pause();
                break;
        }
        return true;
    }

    private class SinEvaluator implements TypeEvaluator {
        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {

            //初始点
            Ball startPos = (Ball) startValue;
            //结束点
            Ball endPos = (Ball) endValue;
            //计算每次更新时的x坐标

            Ball clone = startPos.clone();
            clone.x = startPos.x + fraction * (endPos.x - startPos.x);
            //正炫函数
            //将y坐标进行联动
            clone.y = (float) (Math.sin(clone.x * Math.PI / 180) * 100);
            //返回更新后的点
            return clone;
        }
    }
    /**
     * 返回随机颜色
     *
     * @return 随机颜色
     */
    public static int randomRGB() {
        Random random = new Random();
        int r = 30 + random.nextInt(200);
        int g = 30 + random.nextInt(200);
        int b = 30 + random.nextInt(200);
        return Color.rgb( r, g, b);
    }

}
