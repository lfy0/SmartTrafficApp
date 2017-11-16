package com.example.nappy.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.nappy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataAnalysisFragment extends Fragment implements ViewPager.OnPageChangeListener {


    private ViewPager mVpChartView;
    private LinearLayout mLlIndicator;

    private Fragment fragments[]= {
            new Chart1Fragment(),
            new Chart2Fragment(),
            new Chart3Fragment(),
            new Chart4Fragment(),
            new Chart5Fragment(),
            new Chart6Fragment(),
            new Chart7Fragment()
    };

    private MyPagerAdapter myPagerAdapter;

    public DataAnalysisFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_data_analysis, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initAdapter();
        initEvent();
        selectView(0);
    }

    private void initEvent() {
        mVpChartView.addOnPageChangeListener(this);
    }

    private void initAdapter() {
        myPagerAdapter=new MyPagerAdapter(getChildFragmentManager());
        mVpChartView.setAdapter(myPagerAdapter);
    }

    private void initView() {
        mVpChartView = (ViewPager) getView().findViewById(R.id.vp_chart_view);
        mLlIndicator = (LinearLayout) getView().findViewById(R.id.ll_indicator);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        selectView(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    private class  MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments[i];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }

    private void selectView(int index){
        for (int i = 0; i <mLlIndicator.getChildCount(); i++) {
            if (index==i)
                mLlIndicator.getChildAt(i)
                .setBackgroundResource(R.drawable.indicator_select);
            else
                mLlIndicator.getChildAt(i)
                .setBackgroundResource(R.drawable.indicator_back);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mVpChartView.removeOnPageChangeListener(this);
    }
}
