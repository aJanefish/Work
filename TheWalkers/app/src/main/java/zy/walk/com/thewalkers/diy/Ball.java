package zy.walk.com.thewalkers.diy;

public class Ball implements Cloneable {
    public float aX;//加速度
    public float aY;//加速度Y
    public float vX;//速度X
    public float vY;//速度Y
    public float x;//点位X
    public float y;//点位Y
    public int color;//颜色
    public float r;//半径
    public long born;


    public Ball(float r) {
        this.r = r;
        x = r;
        y = r;
    }

    public Ball() {

    }

    public Ball clone() {
        Ball clone = null;
        try {
            clone = (Ball) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    @Override
    public String toString() {
        return "Ball{" +
                ", x=" + x +
                ", y=" + y +
                ", color=" + color +
                ", r=" + r +
                '}';
    }
}