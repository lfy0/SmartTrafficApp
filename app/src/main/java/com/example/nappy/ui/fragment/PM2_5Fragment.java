package com.example.nappy.ui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nappy.R;
import com.example.nappy.inter.NetworkOnResult;
import com.example.nappy.network.NetRequest;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link PM2_5Fragment} subclass.
 *空气质量表
 */
public class PM2_5Fragment extends Fragment {

    private static final String TAG = "PM2_5Fragment";

    private BarChart mBcPm25Table;
    private ArrayList<String> xValue;
    private ArrayList<BarEntry> yValue;
    private NetRequest pm2Request;
    private ArrayList<Float> values;

    private Handler handler;
    public PM2_5Fragment(Handler handler) {
        this.handler=handler;
        Log.i(TAG, "PM2_5Fragment: "+handler);
    }

    public interface OnTextListener{
        void OnText(String text);
    }

    public static OnTextListener onTextListener;

    public static void setOnTextListener(OnTextListener onTextListener) {
        PM2_5Fragment.onTextListener = onTextListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pm2_5, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initXValue();
        shwoChart(mBcPm25Table,getSetBarData(-1));
    }

    private void initData() {
        pm2Request=new NetRequest("sensor.live")
                .setLoop(true)
                .setLoopTime(3000)
                .setNetworkOnResult(new NetworkOnResult() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        try {
                            if ("成功".equals(jsonObject.getString("response"))){
                                JSONObject jsonObj=jsonObject.getJSONObject("result");
                                int pm2=jsonObj.getInt("pm2");
                                Log.i(TAG, "onSuccess: "+pm2);
                                notifyChart(mBcPm25Table,getSetBarData(pm2));
                                //获取
                                float v=Collections.min(values);
                                Message msg=Message.obtain();
                                msg.what=LifeAssistantFragment.SEND_TEXT;
                                msg.obj="过去一分钟空气质量最差值"+v;
                                handler.sendMessage(msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError() {

                    }
                    
                });
    }


    private void initView() {
        mBcPm25Table = (BarChart) getView().findViewById(R.id.bc_pm2_5_table);
    }

    private void shwoChart(BarChart barChart, BarData barData){
        barChart.setDescription("");
        barChart.getAxisRight().setEnabled(false);
        barChart.getXAxis().setLabelsToSkip(0);//设置间隔
        barChart.getXAxis().setGridColor(Color.TRANSPARENT);
        XAxis xAxis = barChart.getXAxis();//获取x轴
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴标签显示位置
        Legend legend = barChart.getLegend();
        legend.setEnabled(false);
        notifyChart(barChart,barData);
    }

    private void notifyChart(BarChart barChart, BarData barData) {
        barChart.setData(barData);
        barChart.postInvalidate();
    }

    private BarData getSetBarData(float value){
        if (value==-1){
            yValue=new ArrayList<>();
            values=new ArrayList<>();
        }
        if (yValue.size()==20){
            yValue.remove(0);
            values.remove(0);
            for (int i = 0; i <yValue.size(); i++) {
                BarEntry barEntry=yValue.get(i);
                barEntry.setXIndex(i);
            }
        }
        if (value!=-1){
            yValue.add(new BarEntry(value,yValue.size()));
            values.add(value);
        }
        BarDataSet barDataSet=new BarDataSet(yValue,"");
        BarData barData=new BarData(xValue,barDataSet);
        return  barData;
    }

    private void initXValue() {
        xValue=new ArrayList<>();
        for (int i = 1; i <=60; i++) {
            if (i%3==0){
                String str=i+"";
                xValue.add("00".substring(str.length())+str);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        if (pm2Request!=null)
        pm2Request.clean();
    }

    /**
     * 当前界面销毁时调用
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //如果界面被划开
        if (!isVisibleToUser){
            if (pm2Request!=null)
            pm2Request.clean();
        }else {
            initData();
        }
    }
}
