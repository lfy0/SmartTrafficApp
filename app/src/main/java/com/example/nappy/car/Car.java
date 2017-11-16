package com.example.nappy.car;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.nappy.util.BitmapUtils;

/**
 * Created by liufangya on 2017/10/23.
 */
public abstract class Car {


    private static final String TAG = "Car";
    protected float x;
    protected float y;

    protected Bitmap carImg;
    protected Bitmap carOriginal;
    protected Paint paint;

    public Car(Context context){
        carOriginal= BitmapFactory.decodeResource(context.getResources(), getCarRes());
        carOriginal= BitmapUtils.setSize(carOriginal,40,90);
        carImg=getRotate(carOriginal);
        this.x=getX();
        this.y=getY();
        this.paint=new Paint();
    }

    protected abstract   int  getCarRes();

    protected abstract int getX();
    protected abstract int getY();

    protected abstract Bitmap getRotate(Bitmap carOriginal);

    // 在SurfaceView加锁同步后传给自己的Canvas上绘制自己
    public void drawSelf(Canvas canvas) {
        canvas.drawBitmap(carImg, x, y, paint);
    }

    protected int mark=0;
    // 获取物件下一次要绘制的位置(这里是沿着一个边长为400的正方形不断运动的)
    public abstract void getNextPos();

    public Area getArea(){
        return new Area(x,y);
    }

    /**
     * 一块小车的区域
     */
    public class Area{

        float x1;
        float x2;
        float y1;
        float y2;
        float x3;
        float y3;
        float x4;
        float y4;

        public  Area(float x1,float y1){
            this.x1=x1-50;
            this.y1=y1-50;
            this.x2=this.x1+150;
            this.y2=this.y1+150;
            this.x3=this.x1;
            this.y3=this.y1+150;
            this.x4=this.x1+150;
            this.y4=this.y1;
        }

        public float getX1() {
            return x1;
        }

        public void setX1(float x1) {
            this.x1 = x1;
        }

        public float getX2() {
            return x2;
        }

        public float getY1() {
            return y1;
        }

        public void setY1(float y1) {
            this.y1 = y1;
        }

        public float getY2() {
            return y2;
        }

        public float getX3() {
            return x3;
        }

        public void setX3(float x3) {
            this.x3 = x3;
        }

        public float getY3() {
            return y3;
        }

        public void setY3(float y3) {
            this.y3 = y3;
        }

        public float getX4() {
            return x4;
        }

        public void setX4(float x4) {
            this.x4 = x4;
        }

        public float getY4() {
            return y4;
        }

        public void setY4(float y4) {
            this.y4 = y4;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Area)){
                return false;
            }
            Area orea= (Area) obj;
            if ((this.getX1()<orea.getX1()&&orea.getX1()<this.getX2())&&
                    (this.getY1()<orea.getY1()&&orea.getY1()<this.getY2())){
                return true;
            }
            if ((this.getX1()<orea.getX2()&&orea.getX2()<this.getX2())&&
                    (this.getY1()<orea.getY2()&&orea.getY2()<this.getY2())){
                return true;
            }
            if ((this.getX1()<orea.getX3()&&orea.getX3()<this.getX2())&&
                    (this.getY1()<orea.getY3()&&orea.getY3()<this.getY2())){
                return true;
            }
            if ((this.getX1()<orea.getX4()&&orea.getX4()<this.getX2())&&
                    (this.getY1()<orea.getY4()&&orea.getY4()<this.getY2())){
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Area{" +
                    "x1=" + x1 +
                    ", x2=" + x2 +
                    ", y1=" + y1 +
                    ", y2=" + y2 +
                    '}';
        }

    }
}
