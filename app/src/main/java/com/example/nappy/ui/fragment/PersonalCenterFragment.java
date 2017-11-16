package com.example.nappy.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nappy.R;
import com.example.nappy.util.DensityUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 个人中心
 */
public class PersonalCenterFragment extends Fragment {

    private static final String TAG = "PersonalCenterFragment";


    private ViewPager mVpContext;
    private List<Fragment> list;
    private TabLayout mTlTabSelect;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal_center, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated: ");
        initViewPager();
        initAddTabs();
    }

    private void initAddTabs() {
        mTlTabSelect.setTabMode(TabLayout.MODE_FIXED);
        mTlTabSelect.setupWithViewPager(mVpContext);
        if (getSet()){
            mVpContext.setCurrentItem(1,false);
        }
    }

    private void initViewPager() {
        list= new ArrayList<>();
        list.add(new PersonInfoFragment());
        list.add(new TopUpLogFragment());
        list.add(new ThresholdSettingFragment());
        mVpContext.setAdapter(
                new FragmentAdapter(getChildFragmentManager()));//Fragment中嵌套Fragment碎片管理器，应使用getChildFragmentManager()
        reflex(mTlTabSelect);
    }

    private boolean getSet(){
        Bundle bundle=getArguments();
        if (bundle==null)
            return false;
        int i=bundle.getInt("key");
        if (i==1){
           return true;
        }else {
            return false;
        }
    }



    private void initView() {
        mVpContext = (ViewPager) getView().findViewById(R.id.vp_context);
        mTlTabSelect = (TabLayout) getView().findViewById(R.id.tl_tab_select);
    }


    private class FragmentAdapter extends FragmentPagerAdapter {

        private String[] titleArr = {"个人信息", "充值记录", "阀值设置"};

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleArr[position];
        }
    }

    public void reflex(final TabLayout tabLayout){
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);
                    int dp10 = DensityUtils.dp2px(tabLayout.getContext(), 20);
                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width ;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
