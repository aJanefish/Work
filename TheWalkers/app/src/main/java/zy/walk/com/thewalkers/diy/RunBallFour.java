package zy.walk.com.thewalkers.diy;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 *
 * 画笔叠合XOR测试
 *
 *
 *
 * */

import androidx.annotation.Nullable;

public class RunBallFour extends View {
    private ValueAnimator mAnimator;//时间流
    private Paint mPaint;//主画笔
    private Point mCoo;//坐标系

    private float defaultR = 64;//默认小球半径
    private float defaultVX = 10;//默认小球x方向速度
    private float defaultVY = 10;//默认小球y方向速度
    private String TAG = "RunBallTwo";
    List<Ball> list;

    public RunBallFour(Context context) {
        this(context, null);
    }

    public RunBallFour(Context context, @Nullable AttributeSet attrs) {
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
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));//设置对源的叠合模式
    }

    //初始化时准备一个小球数组---参数值随机一些
    private void initBall() {
        list.clear();
        for (int i = 0; i < 10; i++) {
            Ball mBall = new Ball();
            mBall.color = randomRGB();

            mBall.r = rangeInt(80, 120);

            mBall.vX = (float) (Math.pow(-1, Math.ceil(Math.random() * 1000)) * 20 * Math.random());

            mBall.vY = rangeInt(-10, 10);

            mBall.aY = 0.98f;

            mBall.x = mBall.r;
            mBall.y = mBall.r;

            list.add(mBall);
        }
    }



    private float rangeInt(int num1, int num2) {
        return num1+(int)(Math.random()*(num2-num1));
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

        if (ball.x > mCoo.x - ball.r) {
            ball.x = mCoo.x - ball.r;
            ball.vX = -Math.abs(ball.vX);
            ball.aX = -Math.abs(ball.aX);
            //ball.color = randomRGB();//更改颜色
        }

        if (ball.x < ball.r) {
            ball.x = ball.r;
            ball.vX = Math.abs(ball.vX);
            ball.aX = Math.abs(ball.aX);
            //ball.color = randomRGB();//更改颜色

        }

        if (ball.y > mCoo.y - ball.r) {
            ball.y = mCoo.y - ball.r;
            ball.vY = -Math.abs(ball.vY);
            //ball.aY = -Math.abs(ball.aY);
            //ball.color = randomRGB();//更改颜色

        }
        if (ball.y < ball.r) {
            ball.y = ball.r;
            ball.vY = Math.abs(ball.vY);
            //ball.aY = Math.abs(ball.aY);
            //ball.color = randomRGB();//更改颜色

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
            int sc = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                sc = canvas.saveLayer(new RectF(0, 0, mCoo.x, mCoo.y), null);
            }
        canvas.translate(0, 0);
        canvas.drawLine(0, 0, canvas.getWidth(), 0, mPaint);

        drawBall(canvas);

        //canvas.restore();
        canvas.restoreToCount(sc);

        ////创建一个图层，在图层上演示图形混合后的效果
        //    int sc = 0;
        //    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        //        sc = canvas.saveLayer(new RectF(0, 0, 2500, 2500), null);
        //    }
        //    mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));//设置对源的叠合模式
        //    canvas.translate(mCoo.x, mCoo.y);
        //    drawBalls(canvas, mBalls);
        //    canvas.restoreToCount(sc);
        //

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
        //mAnimator.pause();//暂停时间流
        mAnimator.cancel();
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
