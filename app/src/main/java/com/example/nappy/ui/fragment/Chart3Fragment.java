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
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Chart3Fragment extends Fragment {


    private HorizontalBarChart mHbcChart1;
    private List<String> xValue;
    private List<BarEntry> yValue;


    public Chart3Fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chart3, container, false);
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
        setChart(getData(new float[]{-1,20,30}));
    }

    private void initXValue() {
        xValue=new ArrayList<>();
        xValue.add("1-2条违章");
        xValue.add("3-5条违章");
        xValue.add("5条以上违章");
    }

    private void initView() {
        mHbcChart1 = (HorizontalBarChart) getView().findViewById(R.id.hbc_chart1);
    }


    private void setChart(BarData barData){
        mHbcChart1.setDrawBarShadow(false);
        mHbcChart1.setDrawValueAboveBar(true);
        mHbcChart1.setDescription("");
        mHbcChart1.setMaxVisibleValueCount(60);
        mHbcChart1.setPinchZoom(false);
        mHbcChart1.setDrawGridBackground(false);
        mHbcChart1.setExtraOffsets(100.0f,20.0f,100.0f,20.0f);
        mHbcChart1.getXAxis().setLabelsToSkip(0);
        mHbcChart1.getAxisLeft().setEnabled(false);
        YAxis yAxis=mHbcChart1.getAxisRight();
        yAxis.setValueFormatter(new MyValueFormatter());
        XAxis xl = mHbcChart1.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);

        Legend l = mHbcChart1.getLegend();
        l.setEnabled(false);
        notifyChart(barData);
    }

    private BarData getData(float value[]){
        yValue=new ArrayList<>();
        if (value[0]!=-1){
            for (int i = 0; i <value.length; i++) {
                yValue.add(new BarEntry(value[i],i));
            }
        }
        BarDataSet barDataSet=new BarDataSet(yValue,"");
        barDataSet.setValueFormatter(new MyValueFormatter());
        barDataSet.setValueTextColor(Color.parseColor("#9a212a"));
        barDataSet.setValueTextSize(20f);
        barDataSet.setBarSpacePercent(60f);
        List<Integer> colors=new ArrayList<>();
        colors.add(Color.parseColor("#93d24d"));
        colors.add(Color.parseColor("#5081c6"));
        colors.add(Color.parseColor("#c10100"));
        barDataSet.setColors(colors);
        BarData barData=new BarData(xValue,barDataSet);
        return barData;
    }


    private void notifyChart(BarData barData){
        mHbcChart1.setData(barData);
        mHbcChart1.postInvalidate();
    }

    private class MyValueFormatter implements YAxisValueFormatter,ValueFormatter {

        private DecimalFormat mFormat;

        public MyValueFormatter() {
            mFormat = new DecimalFormat("######0.00");
        }

        @Override
        public String getFormattedValue(float v, YAxis yAxis) {
            return mFormat.format(v) + " %";
        }

        @Override
        public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
            return mFormat.format(v) + " %";
        }


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            initData();
        }
    }

    private void initData() {
        new NetRequest("count.live")
                .setNetworkOnResult(new NetworkOnResult() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        try {
                            if ("成功".equals(jsonObject.getString("response"))){
                                JSONObject jsonObj=jsonObject.getJSONObject("result");
                                ChartDataBean dataBean=new Gson().fromJson(jsonObj.toString(),ChartDataBean.class);
                                BarData barData=getData(new float[]{(float) dataBean.getThree(),(float) dataBean.getTwo(),(float) dataBean.getOne()});
                                notifyChart(barData);
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
}
