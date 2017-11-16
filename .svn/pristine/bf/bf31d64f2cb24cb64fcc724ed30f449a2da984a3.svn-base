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
import com.github.mikephil.charting.charts.BarChart;
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


public class Chart6Fragment extends Fragment {

    private static final String TAG = "Chart6Fragment";

    private BarChart mBcChart6;

    private List<String> xValue;
    private List<BarEntry> yValue;

    private List<Float> values;

    public Chart6Fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chart6, container, false);
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
        values.add(-1.0f);
        setChart(getData(values));
    }

    private void initXValue() {
        values=new ArrayList<>();
        xValue=new ArrayList<>();
        String statrTime=":00:01";
        String endTime=":00:00";
        for (int i = 0; i <=22; i++) {
            if (i%2==0){
                xValue.add(i+statrTime+"-"+(i+2)+(endTime));
            }
        }
    }

    private void initView() {
        mBcChart6 = (BarChart)getView().findViewById(R.id.bc_chart6);
    }

    private void setChart(BarData barData){
        mBcChart6.setDescription("");
        mBcChart6.getAxisRight().setEnabled(false);
        mBcChart6.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mBcChart6.getXAxis().setLabelsToSkip(0);
        mBcChart6.getXAxis().setGridColor(Color.TRANSPARENT);
        mBcChart6.getXAxis().setTextSize(5);
        mBcChart6.getLegend().setEnabled(false);
        mBcChart6.getAxisLeft().setValueFormatter(new MyValueFomor());
        notifyChart(barData);
    }

    private void notifyChart(BarData barData) {
        mBcChart6.setData(barData);
        mBcChart6.postInvalidate();
    }

    private BarData getData(List<Float> values){
        yValue=new ArrayList<>();
        if (values.get(0)!=-1){
            for (int i = 0; i <values.size(); i++) {
                yValue.add(new BarEntry(values.get(i),i));
            }
        }
        BarDataSet barDataSet=new BarDataSet(yValue,"");
        barDataSet.setBarSpacePercent(30f);
        barDataSet.setValueFormatter(new MyValueFomor());
        List<Integer> colors=new ArrayList<>();
        colors.add(Color.parseColor("#5577be"));
        colors.add(Color.parseColor("#403134"));
        colors.add(Color.parseColor("#060467"));
        colors.add(Color.parseColor("#4f612f"));
        colors.add(Color.parseColor("#b8afdc"));

        colors.add(Color.parseColor("#7b933d"));
        colors.add(Color.parseColor("#963638"));
        colors.add(Color.parseColor("#fbc200"));
        colors.add(Color.parseColor("#4d80bf"));
        colors.add(Color.parseColor("#e9680d"));

        colors.add(Color.parseColor("#983578"));
        colors.add(Color.parseColor("#621c26"));
        barDataSet.setColors(colors);
        Log.i(TAG, "getData: "+values.size()+"----"+yValue.size()+"/"+xValue.size());
        BarData barData=new BarData(xValue,barDataSet);
        return barData;
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
                        Log.i(TAG, "onSuccess: "+jsonObject);
                        try {
                            if ("成功".equals(jsonObject.getString("response"))){
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

                                values.add((float) dataBean.getOne());
                                values.add((float) dataBean.getTwo());
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

    private class MyValueFomor implements ValueFormatter,YAxisValueFormatter{

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
