package com.example.nappy.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.nappy.app.AppClient;
import com.example.nappy.app.AppConfig;
import com.example.nappy.inter.NetworkOnResult;

import org.json.JSONObject;


/**
 * Created by liufangya on 2017/10/11.
 *
 * 进行网络请求的基类
 */
public class  NetRequest extends Thread {

    private static final String TAG = "NetRequest";
    private JSONObject jsonBody;//向服务器发送的数据
    private String action;//地址后缀
    private NetworkOnResult networkOnResult;//成功或失败的监听
    private boolean isLoop=false;//是否循环请求
    private long loopTime=1000;//循环请求的间隔时间


    private ProgressDialog mProDialog;


    /**
     * 是否显示网络请求对话框
     * @param context
     * @return
     */
    public NetRequest showDialog(Context context){
        mProDialog=new ProgressDialog(context);
        mProDialog.show();
        return this;
    }


    /**
     * 关闭请求进度对话框
     */
    public void cancelDialog(){
        if (mProDialog!=null){
            mProDialog.cancel();
        }
    }

    public NetRequest(String action){
       this.action=action;
    }


    public NetRequest setJsonBody(JSONObject jsonBody) {
        this.jsonBody = jsonBody;
        return this;
    }

    /**
     * 设置关于请求成功或失败的监听
     * @param networkOnResult
     * @return
     */
    public NetRequest setNetworkOnResult(NetworkOnResult networkOnResult) {
        this.networkOnResult = networkOnResult;
        start();
        return this;
    }

    public NetRequest setLoop(boolean loop) {
        isLoop = loop;
        return this;
    }

    public NetRequest setLoopTime(long loopTime) {
        this.loopTime = loopTime;
        return this;
    }

    /**
     * 请求服务器数据
     */
    public void request(){
        Log.i(TAG, "request: "+AppConfig.URL+action);
        JsonObjectRequest jsonRequest=new JsonObjectRequest(Request.Method.POST,
                AppConfig.URL+action, jsonBody,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject){
                networkOnResult.onSuccess(jsonObject);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i(TAG, "onErrorResponse: ",volleyError);
                networkOnResult.onError();
            }
        });
        AppClient.getRequestQueue().add(jsonRequest);
    }

    /**
     * 子线程内进行请求
     */
    @Override
    public void run() {
        super.run();
        do {
            request();
            try {
                Thread.sleep(loopTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (isLoop);
        cancelDialog();
    }

    /**
     * 关闭循环获取数据的线程释放资源
     */
    public void clean(){
        isLoop=false;
    }
}
