package com.example.nappy.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nappy.R;
import com.example.nappy.adapter.TopupListItemAdapter;
import com.example.nappy.beans.AccountTable;
import com.example.nappy.dao.AccountDao;
import com.example.nappy.util.DataBaseHolder;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * TabHost的充值记录
 */
public class TopUpLogFragment extends Fragment {


    private static final String TAG = "TopUpLogFragment";
    private ListView mLvTopup;

    private TopupListItemAdapter adapter;
    private AccountDao accountDao;
    private TextView mTvMoneySum;

    public TopUpLogFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_up_log, container, false);
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
    }

    private void initAdapter() {
        Context context = getContext();
        if (DataBaseHolder.getHelper(context).isClose()) {
            accountDao = new AccountDao(context);
            List<AccountTable> tables = accountDao.getAccountTableAll();
            //处理总金额字符串
            String sumText="";
            int sum = 0;
            for (AccountTable accountTable : tables) {
                sum += accountTable.getCarMoney();
            }
            sumText=sum+sumText;
            String sumTextBuf="";
            for (int i = 0; i <sumText.length(); i++) {
                sumTextBuf+=sumText.charAt(i);
                if ((i+1)%3==0&&i!=sumText.length()-1){
                    sumTextBuf+=",";
                }
            }
            sumTextBuf+=".00";
            mTvMoneySum.setText(sumTextBuf);
            adapter = new TopupListItemAdapter(getActivity(), tables);
            mLvTopup.setAdapter(adapter);
        } else {
            Toast.makeText(getActivity(), "请重新进入更新程序", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        mLvTopup = (ListView) getView().findViewById(android.R.id.list);
        mLvTopup.setEmptyView(getView().findViewById(android.R.id.empty));
        mTvMoneySum = (TextView) getView().findViewById(R.id.tv_money_sum);
    }


}
