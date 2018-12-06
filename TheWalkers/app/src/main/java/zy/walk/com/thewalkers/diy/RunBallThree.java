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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * 分裂
 * */

import androidx.annotation.Nullable;

public class RunBallThree extends View {
    private ValueAnimator mAnimator;//时间流
    private Paint mPaint;//主画笔
    private Point mCoo;//坐标系

    private float defaultR = 128;//默认小球半径
    private float defaultVX = 10;//默认小球x方向速度
    private float defaultVY = 10;//默认小球y方向速度
    private String TAG = "RunBallTwo";
    List<Ball> list;

    public RunBallThree(Context context) {
        this(context, null);
    }

    public RunBallThree(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        list = new ArrayList<>();

        initBall();
        //初始画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //初始化时间流ValueAnimator
        mAnimator = ValueAnimator.ofInt(0, 1);
        mAnimator.setRepeatCount(-1);
        mAnimator.setDuration(1000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                updateBall();//更新小球信息
                invalidate();
            }
        });
    }

    private void initBall() {
        list.clear();
        //初始化小球
        Ball mBall = new Ball(defaultR);
        mBall.color = randomRGB();
        mBall.x = defaultR;
        mBall.y = defaultR;
        mBall.vX = defaultVX;
        mBall.vY = defaultVY;

        list.add(mBall);

    }

    private void updateBall() {
        List<Ball> newList = new ArrayList<>();
        newList.addAll(list);
        for (Ball ball : newList) {
            defaultUpdateBall(ball);
        }
    }

    private int defaleR = 32;

    private void defaultUpdateBall(Ball ball) {

        ball.vX += ball.aX;
        ball.x += ball.vX;

        ball.vY += ball.aY;
        ball.y += ball.vY;

        if (ball.x + ball.r > mCoo.x) {
            ball.vX = -Math.abs(ball.vX);
            ball.aX = -Math.abs(ball.aX);
            ball.color = randomRGB();//更改颜色
            if (ball.r >= defaleR) {
                ball.r = ball.r / 2;
                Ball newBall = ball.clone();
                newBall.vY = -newBall.vY;
                newBall.color = randomRGB();
                list.add(newBall);
            }

        }
        if (ball.x < ball.r) {
            ball.vX = Math.abs(ball.vX);
            ball.aX = Math.abs(ball.aX);
            ball.color = randomRGB();//更改颜色

            if (ball.r >= defaleR) {
                ball.r = ball.r / 2;
                Ball newBall = ball.clone();
                newBall.vY = -newBall.vY;
                newBall.color = randomRGB();
                list.add(newBall);
            }

        }

        if (ball.y + ball.r > mCoo.y) {
            ball.vY = -Math.abs(ball.vY);
            ball.aY = -Math.abs(ball.aY);
            ball.color = randomRGB();//更改颜色

            if (ball.r >= defaleR) {
                ball.r = ball.r / 2;

                Ball newBall = ball.clone();
                newBall.vX = -newBall.vX;
                newBall.color = randomRGB();
                list.add(newBall);
            }

        }
        if (ball.y < ball.r) {

            ball.vY = Math.abs(ball.vY);
            ball.aY = Math.abs(ball.aY);
            ball.color = randomRGB();//更改颜色

            if (ball.r >= defaleR) {
                ball.r = ball.r / 2;

                Ball newBall = ball.clone();
                newBall.vX = -newBall.vX;
                newBall.color = randomRGB();
                list.add(newBall);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCoo == null) {
            mCoo = new Point(canvas.getWidth(), canvas.getHeight());
        }
        canvas.save();

        //平移
        canvas.translate(0, 0);
        canvas.drawLine(0, 0, canvas.getWidth(), 0, mPaint);

        drawBall(canvas);

        canvas.restore();
    }

    /**
     * 绘制小球
     *
     * @param canvas
     */
    private void drawBall(Canvas canvas) {

        for (Ball ball : list) {
            mPaint.setColor(ball.color);
            canvas.drawCircle(ball.x, ball.y, ball.r, mPaint);
        }

    }


    public void start() {
        mAnimator.start();//开启时间流

    }

    public void pause() {
        reset();
        mAnimator.pause();//暂停时间流
    }

    private void reset() {
        initBall();
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
        return Color.rgb(r, g, b);
    }

}
