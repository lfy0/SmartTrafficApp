package com.example.nappy.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.nappy.R;
import com.example.nappy.app.AppClient;

public class IllegalImageActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mIvImage1;
    private ImageView mIvImage2;
    private ImageView mIvImage3;
    private ImageView mIvImage4;
    private Button mBtnBack;

    public interface OnBitmapRequest{
        void  BitmapRequest(Bitmap bitmap);
    }

    public static  OnBitmapRequest onBitmapRequest;

    public static void setOnBitmapRequest(OnBitmapRequest onBitmapRequest) {
        IllegalImageActivity.onBitmapRequest = onBitmapRequest;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_table_layout);
        getSupportActionBar().hide();
        initView();
        initEvent();
    }

    private void initEvent() {
        mIvImage1.setOnClickListener(this);
        mIvImage2.setOnClickListener(this);
        mIvImage3.setOnClickListener(this);
        mIvImage4.setOnClickListener(this);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IllegalImageActivity.this.finish();
                startActivity(new Intent(IllegalImageActivity.this,QueryResultsActivity.class));
            }
        });
    }

    private void initView() {
        mIvImage1 = (ImageView) findViewById(R.id.iv_image1);
        mIvImage2 = (ImageView) findViewById(R.id.iv_image2);
        mIvImage3 = (ImageView) findViewById(R.id.iv_image3);
        mIvImage4 = (ImageView) findViewById(R.id.iv_image4);
        mBtnBack = (Button) findViewById(R.id.btn_back);
    }



    @Override
    public void onClick(View v) {
        Bitmap btimap = null;
        switch (v.getId()){
            case R.id.iv_image1:
                btimap= BitmapFactory.decodeResource(getResources(),R.drawable.weizhang1);
                break;
            case R.id.iv_image2:
                btimap= BitmapFactory.decodeResource(getResources(),R.drawable.weizhang2);
                break;
            case R.id.iv_image3:
                btimap= BitmapFactory.decodeResource(getResources(),R.drawable.weizhang3);
                break;
            case R.id.iv_image4:
                btimap= BitmapFactory.decodeResource(getResources(),R.drawable.weizhang4);
                break;
            default:
                break;
        }
        AppClient.illegaImageBitmap=btimap;
        IllegalImageActivity.this.finish();
        startActivity(new Intent(IllegalImageActivity.this,ImageReadActivity.class));
    }
}
