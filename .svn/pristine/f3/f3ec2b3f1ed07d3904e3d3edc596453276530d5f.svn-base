package com.example.nappy.app;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.nappy.beans.FaKuanInfoBean;
import com.example.nappy.beans.FindInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liufangya on 2017/10/12.
 */

public  class AppClient extends Application {

    private static final String TAG = "AppClient";

    private static RequestQueue mQueue;

    private static SharedPreferences mSharedPreferences;

    public static List<FindInfoBean> lists;
    public static List<List<FaKuanInfoBean>> listInfos;
    public static Bitmap illegaImageBitmap;

    @Override
    public void onCreate() {
        super.onCreate();
        mQueue= Volley.newRequestQueue(this);
        mSharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        AppClient.lists=new ArrayList<>();
        AppClient.listInfos=new ArrayList<>();
    }

    public static  RequestQueue getRequestQueue(){
        return mQueue;
    }

    public static void setString(String key,String value){
        mSharedPreferences.edit().putString(key,value).commit();
    }

    public static String getString(String key){
        return mSharedPreferences.getString(key,"");
    }

    public static void setBoolean(String key,boolean value){
        mSharedPreferences.edit().putBoolean(key,value).commit();
    }

    public static boolean getBoolean(String key){
        return  mSharedPreferences.getBoolean(key,false);
    }

    public static void setInt(String key,int value){
        mSharedPreferences.edit().putInt(key,value).commit();
    }

    public static int getInt(String key){
        return  mSharedPreferences.getInt(key,0);
    }

//    public static Context getContext(){
//        Context context=this.getBaseContext();
//    }
}
