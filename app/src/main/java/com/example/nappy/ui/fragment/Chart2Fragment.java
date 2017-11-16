package com.example.nappy.ui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nappy.R;
import com.example.nappy.beans.ChartDataBean;
import com.example.nappy.inter.NetworkOnResult;
import com.example.nappy.network.NetRequest;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Chart2Fragment extends Fragment {


    private static final String TAG = "Chart2Fragment";
    private PieChart mPcChart1;

    private ArrayList<String> xValue;
    private ArrayList<Entry> yValue;

    public Chart2Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chart2, container, false);

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
        setChart(getData(-1,-1));
    }

    private void initData() {
        new NetRequest("repetition.live")
                .setNetworkOnResult(new NetworkOnResult() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        try {
                            if ("成功".equals(jsonObject.getString("response"))){
                                JSONObject jsonObj=jsonObject.getJSONObject("result");
                                ChartDataBean dataBean=new Gson().fromJson(jsonObj.toString(),ChartDataBean.class);
                                notifyChart(mPcChart1,getData((float)dataBean.getTwo(),(float) dataBean.getOne()));
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

    private void initXValue() {

        xValue=new ArrayList<>();
        xValue.add("无重复违章");
        xValue.add("有重复违章");
    }

    private void initView() {
        mPcChart1 = (PieChart) getView().findViewById(R.id.pc_chart1);
    }

    private PieData getData(float one,float two){
        if (one!=-1){
            yValue=new ArrayList<>();
            yValue.add(new Entry(one,0));
            yValue.add(new Entry(two,1));
        }
        PieDataSet pieDataSet=new PieDataSet(yValue,"");
        pieDataSet.setSelectionShift(20f);
        // 设置饼图各个区域颜色
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#4573a7"));
        colors.add(Color.parseColor("#aa4644"));
        pieDataSet.setColors(colors);
        PieData pieData=new PieData(xValue,pieDataSet);
        //设置以百分比显示
        pieData.setValueFormatter(new PercentFormatter());
//        //区域文字的大小
//        pieData.setValueTextSize(11f);
//        //设置区域文字的颜色
//        pieData.setValueTextColor(Color.WHITE);
//        //设置区域文字的字体
//        pieData.setValueTypeface(Typeface.DEFAULT);
        return pieData;
    }

    private void setChart(PieData pieData){
        mPcChart1.setUsePercentValues(true);////设置饼图是否使用百分比
        mPcChart1.setDescription("");
        //设置饼图右下角的文字大小
        mPcChart1.setDescriptionTextSize(16);
        //是否显示圆盘中间文字，默认显示
        mPcChart1.setDrawCenterText(false);
        //设置中间圆盘的颜色
        mPcChart1.setHoleColor(Color.GREEN);
        //设置中间圆盘的半径,值为所占饼图的百分比
        mPcChart1.setHoleRadius(0);
        //设置中间透明圈的半径,值为所占饼图的百分比
        mPcChart1.setTransparentCircleRadius(0);
        //是否显示饼图中间空白区域，默认显示
        mPcChart1.setDrawHoleEnabled(false);
        //设置圆盘是否转动，默认转动
        mPcChart1.setRotationEnabled(false);
        mPcChart1.setExtraOffsets(0.5f,20f,0.5f,0f);

        Legend legend=mPcChart1.getLegend();
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        legend.setTextColor(Color.parseColor("#4151b1"));
        legend.setFormSize(8f);
        legend.setFormToTextSpace(4f);
        legend.setXEntrySpace(6f);
        notifyChart(mPcChart1,pieData);
    }

    /**
     * 刷新
     * @param mPcChart1
     * @param pieData
     */
    private void notifyChart(PieChart mPcChart1, PieData pieData) {
        mPcChart1.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        mPcChart1.setData(pieData);
        mPcChart1.postInvalidate();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            initData();
        }
    }
}
