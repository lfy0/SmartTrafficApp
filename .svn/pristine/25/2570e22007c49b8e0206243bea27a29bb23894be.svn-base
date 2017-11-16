package com.example.nappy.test;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TabHost;

import com.example.nappy.R;
import com.example.nappy.ui.fragment.PersonInfoFragment;
import com.example.nappy.ui.fragment.ThresholdSettingFragment;
import com.example.nappy.ui.fragment.TopUpLogFragment;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "TestActivity";

    private FragmentTabHost mTabhost;

    private LinearLayout mTabcontext;

    private String[] titleArr={"个人信息","充值记录","阀值设置"};

    private Class<?> fragmnets[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
        fragmnets=new Class[]{PersonInfoFragment.class,
                TopUpLogFragment.class
                ,ThresholdSettingFragment.class};
        initTable();
    }

    private void initTable() {
        mTabhost.setup(this,
                getSupportFragmentManager(),
                R.id.tabcontext);
        for (int i = 0; i <titleArr.length; i++) {
            TabHost.TabSpec tabSpec=mTabhost.newTabSpec(titleArr[i])
                    .setIndicator(titleArr[i]);
            mTabhost.addTab(tabSpec,fragmnets[i],null);
        }
    }

    private void initView() {
        mTabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabcontext = (LinearLayout) findViewById(R.id.tabcontext);
    }
}
