package com.example.nappy.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import static android.graphics.Bitmap.createBitmap;

/**
 * Created by liufangya on 2017/10/23.
 */

public class BitmapUtils {

    public static final int BITMAP_TOP=0;
    public static final int BITMAP_RIGHT=90;
    public static final int BITMAP_BOTTOM=180;
    public static final int BITMAP_LEFT=270;

    /**
     * 设置bitmap宽高
     * @param bitMap
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap setSize(Bitmap bitMap, int newWidth, int newHeight){
        int width = bitMap.getWidth();
        int height = bitMap.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        return createBitmap(bitMap, 0, 0, width, height, matrix, true);
    }


    /**
     * bitmap旋转
     * @param bm
     * @param direction
     * @return
     */
    public static Bitmap setRotate(Bitmap bm,int direction){
        Matrix matrix = new Matrix();
        matrix.preRotate(direction);
        return Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),bm.getHeight(), matrix, true);
    }

}
