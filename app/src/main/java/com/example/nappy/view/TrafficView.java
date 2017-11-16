package com.example.nappy.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.nappy.R;
import com.example.nappy.car.Car1;
import com.example.nappy.car.Car2;
import com.example.nappy.car.Car3;
import com.example.nappy.car.Car4;

/**
 * Created by liufangya on 2017/10/23.
 */
public class TrafficView extends SurfaceView implements SurfaceHolder.Callback ,Runnable{

    private static final String TAG = "TrafficView";
    private Thread thread;// SurfaceView通常须要自己单独的线程来播放动画
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Bitmap map;
    private Paint paint;

    private Car1 car;
    private Car2 car2;
    private Car3 car3;
    private Car4 car4;


    public TrafficView(Context context) {
        this(context,null);
    }


    public TrafficView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.surfaceHolder = this.getHolder();
        this.surfaceHolder.addCallback(this);
        this.car =new Car1(context);
        this.car2=new Car2(context);
        this.car3=new Car3(context);
        this.car4=new Car4(context);
        this.map= BitmapFactory.decodeResource(context.getResources(), R.drawable.maps);
        this.paint=new Paint();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    //surfaceView发生变化时调用
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public void run() {
        while (true) {
            //全排列4辆小车当有两辆小车相撞时需要有一辆小车停下
            if (!car.getArea().equals(car2.getArea())
                    &&!car.getArea().equals(car3.getArea())
                    &&!car.getArea().equals(car4.getArea())){
                car.getNextPos();
            }
            if (!car2.getArea().equals(car3.getArea())
                    &&!car2.getArea().equals(car4.getArea())){
                car2.getNextPos();
            }
            if (!car3.getArea().equals(car4.getArea())){
                car3.getNextPos();
            }
            car4.getNextPos();
            canvas = this.surfaceHolder.lockCanvas(); // 通过lockCanvas加锁并得到该SurfaceView的画布
            if (canvas!=null){
                Rect rectSrc=new Rect(0,0,map.getWidth(),map.getHeight());
                Rect rect=new Rect(0,0,this.getRight(),this.getBottom());
                canvas.drawBitmap(map,rectSrc,rect,paint);
                car.drawSelf(canvas); // 把SurfaceView的画布传给物件。物件会用这个画布将自己绘制到上面的某个位置
                car2.drawSelf(canvas);
                car3.drawSelf(canvas);
                car4.drawSelf(canvas);
                this.surfaceHolder.unlockCanvasAndPost(canvas); // 释放锁并提交画布进行重绘
                try {
                    Thread.sleep(5); // 这个就相当于帧频了，数值越小画面就越流畅
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
