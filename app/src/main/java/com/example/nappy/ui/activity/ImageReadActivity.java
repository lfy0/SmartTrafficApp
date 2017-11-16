package com.example.nappy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.nappy.R;
import com.example.nappy.app.AppClient;
import com.example.nappy.view.ZoomImageView;

public class ImageReadActivity extends AppCompatActivity {

    private Button mButton;
    private static ZoomImageView mZoomImage;

    private static final String TAG = "ImageReadActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_read);
        getSupportActionBar().hide();
        initView();
        initEvent();
    }
    
    private void initEvent() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageReadActivity.this.finish();
                startActivity(new Intent(ImageReadActivity.this,QueryResultsActivity.class));
            }
        });

    }

    private void initView() {
        mButton = (Button) findViewById(R.id.btn_back);
        mZoomImage = (ZoomImageView) findViewById(R.id.zoom_image);
        mZoomImage.setImage(AppClient.illegaImageBitmap);
    }
}
