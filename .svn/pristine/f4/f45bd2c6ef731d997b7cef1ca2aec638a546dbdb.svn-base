package com.example.nappy.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nappy.R;
/**
 * Created by liufangya on 2017/10/11.
 * 欢迎访问
 */
public class WelcomeActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();
        mPreferences=PreferenceManager.getDefaultSharedPreferences(this);
        //判断是否是第一次登录
        if (mPreferences.getBoolean("isOneRun",true)){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                    mPreferences.edit().putBoolean("isOneRun",false).commit();
                    WelcomeActivity.this.finish();
                }
            },3000);
        }else {
            startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
            WelcomeActivity.this.finish();
        }
    }
}
