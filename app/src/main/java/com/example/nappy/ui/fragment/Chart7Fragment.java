package com.example.nappy.ui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nappy.R;
import com.example.nappy.beans.ChartDataBean;
import com.example.nappy.inter.NetworkOnResult;
import com.example.nappy.network.NetRequest;
import com.github.mikephil.charting.charts.HorizontalBarChart;
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
 *
 */
public class Chart7Fragment extends Fragment {


    private HorizontalBarChart mHbcChart2;

    private List<String> XValue;

    private List<BarEntry> YValue;

    private List<Float> values;
    private static final String TAG = "Chart7Fragment";

    public Chart7Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chart7, container, false);
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
        values.add(-1f);
        setChart(getData(values));
    }

    private void initXValue() {
        XValue=new ArrayList<>();
        values=new ArrayList<>();
        XValue.add("违规驶入导向车道");
        XValue.add("违规停车拒绝驶出");
        XValue.add("违反禁止标线指示");
        XValue.add("违反信号灯指示");
        XValue.add("机动车不走机动车道");
        XValue.add("不按规定系安全带");
        XValue.add("违反禁令标志指示");
        XValue.add("违规使用专用车道");
        XValue.add("机动车逆向行驶");
        XValue.add("超速行驶");
    }

    private void initView() {
        mHbcChart2 =getView().findViewById(R.id.hbc_chart2);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            initData();
        }
    }

    private void initData() {
        new NetRequest("date.live")
                .setNetworkOnResult(new NetworkOnResult() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        try {
                            Log.i(TAG, "onSuccess: "+jsonObject);
                            if ("成功".equals(jsonObject.getString("response"))){
                                Log.i(TAG, "onSuccess: "+jsonObject);
                                JSONObject jsonObj=jsonObject.getJSONObject("result");
                                ChartDataBean dataBean=new Gson().fromJson(jsonObj.toString(),ChartDataBean.class);
                                values.clear();
                                values.add((float) dataBean.getOne());
                                values.add((float) dataBean.getTwo());
                                values.add((float) dataBean.getThree());
                                values.add((float) dataBean.getFour());
                                values.add((float) dataBean.getFive());

                                values.add((float) dataBean.getSix());
                                values.add((float) dataBean.getSeven());
                                values.add((float) dataBean.getEight());
                                values.add((float) dataBean.getNine());
                                values.add((float) dataBean.getTen());
                                notifyChart(getData(values));
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

    private void setChart(BarData barData){
        mHbcChart2.setDescription("");
        mHbcChart2.getAxisRight().setEnabled(false);
        mHbcChart2.getLegend().setEnabled(false);
        mHbcChart2.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mHbcChart2.getXAxis().setGridColor(Color.TRANSPARENT);
        notifyChart(barData);
    }

    private void notifyChart(BarData barData) {
        mHbcChart2.setData(barData);
        mHbcChart2.postInvalidate();
    }


    private BarData getData(List<Float> floats){
        YValue=new ArrayList<>();
        if (floats.get(0)!=-1){
            for (int i = 0; i < floats.size(); i++) {
                YValue.add(new BarEntry(floats.get(i),i));
            }
        }
        BarDataSet barDataSet=new BarDataSet(YValue,"");
        barDataSet.setValueFormatter(new MyValueFomor());
        List<Integer> colors=new ArrayList<>();
        colors.add(Color.parseColor("#527ebb"));
        colors.add(Color.parseColor("#7b9041"));
        colors.add(Color.parseColor("#e26c0a"));
        colors.add(Color.parseColor("#604a78"));
        colors.add(Color.parseColor("#fcc200"));
        colors.add(Color.parseColor("#b8cd80"));
        colors.add(Color.parseColor("#f6ab67"));
        colors.add(Color.parseColor("#b0a2c3"));
        colors.add(Color.parseColor("#92b1d0"));
        colors.add(Color.parseColor("#c30101"));
        barDataSet.setColors(colors);
        BarData barData=new BarData(XValue,barDataSet);
        return barData;
    }

    private class MyValueFomor implements ValueFormatter,YAxisValueFormatter {

        private DecimalFormat format;

        public MyValueFomor(){
            format=new DecimalFormat("##0.00");
        }

        @Override
        public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
            return format.format(v)+"%";
        }

        @Override
        public String getFormattedValue(float v, YAxis yAxis) {
            return format.format(v)+"%";
        }
    }

}
