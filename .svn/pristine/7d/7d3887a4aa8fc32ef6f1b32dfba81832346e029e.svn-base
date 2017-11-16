package com.example.nappy.car;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.nappy.R;
import com.example.nappy.util.BitmapUtils;

/**
 * Created by liufangya on 2017/10/23.
 */

public class Car4 extends Car {

    public Car4(Context context) {
        super(context);
    }

    @Override
    protected int getCarRes() {
        return R.drawable.car4;
    }

    @Override
    protected int getX() {
        return 90;
    }

    @Override
    protected int getY() {
        return 770;
    }

    @Override
    protected Bitmap getRotate(Bitmap carOriginal) {
        return BitmapUtils.setRotate(carOriginal,BitmapUtils.BITMAP_RIGHT);
    }

    @Override
    public void getNextPos() {
        switch (mark){
            case 0:
                if (x<1640){
                    x+=7;
                }else {
                    carImg=BitmapUtils.setRotate(carOriginal,BitmapUtils.BITMAP_TOP);
                    mark+=5;
                }
                break;
            case 5:
                if (y>60){
                    y-=7;
                }else {
                    carImg=BitmapUtils.setRotate(carOriginal,BitmapUtils.BITMAP_LEFT);
                    mark+=5;
                }
                break;
            case 10:
                if (x>90){
                    x-=7;
                }else {
                    carImg=BitmapUtils.setRotate(carOriginal,BitmapUtils.BITMAP_BOTTOM);
                    mark+=5;
                }
                break;
            case 15:
                if (y<770){
                    y+=7;
                }else {
                    carImg=BitmapUtils.setRotate(carOriginal,BitmapUtils.BITMAP_RIGHT);
                    mark=0;
                }
                break;
        }
    }
}
