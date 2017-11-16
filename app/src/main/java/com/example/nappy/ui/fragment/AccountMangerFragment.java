package com.example.nappy.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nappy.R;
import com.example.nappy.adapter.AccountMessageItemLayoutAdapter;
import com.example.nappy.app.AppClient;
import com.example.nappy.beans.AccountBean;
import com.example.nappy.beans.AccountTable;
import com.example.nappy.dao.AccountDao;
import com.example.nappy.inter.NetworkOnResult;
import com.example.nappy.network.NetRequest;
import com.example.nappy.util.GsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AccountMangerFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "AccountMangerFragment";


    private OnFragmentInteractionListener mListener;

    private ImageView mIvMenu;
    private TextView mTvAccountBatch;
    private TextView mTvAccountLog;
    private ListView mLvAccountList;

    private List<AccountBean> accountBeans;
    private AccountMessageItemLayoutAdapter accountMessageItemLayoutAdapter;

    private int carIcons[]={R.drawable.bmw,R.drawable.zhonghua,
    R.drawable.benci,R.drawable.mazida};
    private AccountDao accountDao;
    public AccountMangerFragment() {

    }

    public static AccountMangerFragment newInstance(String param1, String param2) {
        AccountMangerFragment fragment = new AccountMangerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account_manger, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initAdapter();
        initData();
        initEvent();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        accountDao=new AccountDao(getContext());
    }

    private void initAdapter() {
        accountMessageItemLayoutAdapter=
                new AccountMessageItemLayoutAdapter(getActivity(),accountBeans);
        mLvAccountList.setAdapter(accountMessageItemLayoutAdapter);
    }

    private void initData() {
        new NetRequest("all.car")
                .showDialog(getActivity())
                .setNetworkOnResult(new NetworkOnResult() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        Log.i(TAG, "onSuccess: "+jsonObject);
                        try {
                            if ("成功".equals(jsonObject.getString("response"))){
                                JSONArray josnArr=jsonObject.getJSONArray("result");
                                accountBeans= GsonUtil.gsonToList(josnArr.toString(),AccountBean.class);
                                for (int i = 0; i <accountBeans.size(); i++) {
                                    AccountBean accountBean=accountBeans.get(i);
                                    accountBean.setBitmapRes(carIcons[i]);
                                    accountBeans.set(i,accountBean);
                                }
                                accountMessageItemLayoutAdapter.setObjects(accountBeans);
                                accountMessageItemLayoutAdapter.notifyDataSetChanged();
                                Log.i(TAG, "onSuccess: "+accountBeans);
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

    private void initEvent() {
        mIvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg=new Message();
                msg.what=1;
                onButtonPressed(msg);
            }
        });
        //监听条目内的按钮和
        accountMessageItemLayoutAdapter.setOnAccountCheckListener(new AccountMessageItemLayoutAdapter.OnAccountCheckListener() {
            @Override
            public void OnBtnCheck(View view, int pos) {
                Log.i(TAG, "OnBtnCheck: "+pos);
                showDialog(pos);
            }
        });
        mTvAccountBatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(-1);
            }
        });
        mTvAccountLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putInt("key",1);
                PersonalCenterFragment fragment=new PersonalCenterFragment();
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl_mian,fragment)
                        .commit();
                Message msg=new Message();
                msg.what=2;
                onButtonPressed(msg);
            }
        });
    }

    public void onButtonPressed(Message object) {
        if (mListener != null) {
            mListener.onFragmentInteraction(object);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void initView() {
        accountBeans=new ArrayList<>();
        mIvMenu = getView().findViewById(R.id.iv_menu);
        mTvAccountBatch = getView().findViewById(R.id.tv_account_batch);
        mTvAccountLog = getView().findViewById(R.id.tv_account_log);
        mLvAccountList = getView().findViewById(R.id.lv_account_list);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Message object);
    }

    /**
     * 实现批量充值和单条充值的逻辑
     * @param carId 车的id
     */
    public void showDialog(final int carId){
        final AlertDialog mDialog=new AlertDialog.Builder(getActivity()).create();
        mDialog.setTitle("车辆账户充值");
        View view=LayoutInflater.from(getActivity())
                .inflate(R.layout.accout_add_money_dialog_layout,null);
        final EditText etMoney=view.findViewById(R.id.et_money_input);
        Button btnChongZhi=view.findViewById(R.id.btn_chongzhi);
        Button btnQuXiao=view.findViewById(R.id.btn_quxiao);
        TextView tvCarId=view.findViewById(R.id.tv_car_id);
        String person="车牌号：";
        if (carId!=-1){
            List<AccountBean> accountBeans=accountMessageItemLayoutAdapter.getObjects();
            AccountBean bean=accountBeans.get(carId);
            person+=bean.getCar_number()+" ";
            tvCarId.setText(person);
        }else {
            List<AccountBean> accountList=accountMessageItemLayoutAdapter.getChongZhiList();
            for (int i = 0; i <accountList.size(); i++) {
                AccountBean bean=accountList.get(i);
                person+=bean.getCar_number()+" ";
            }
            tvCarId.setText(person);
        }
        etMoney.addTextChangedListener(new TextAdd());
        btnQuXiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        btnChongZhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String moneyText=etMoney.getText().toString().trim();
                if (carId!=-1){
                    final JSONObject jsonObj=new JSONObject();
                    try {
                        jsonObj.put("car_number",
                                accountMessageItemLayoutAdapter.getObjects().get(carId)
                        .getCar_number());
                        jsonObj.put("car_money",moneyText);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    new NetRequest("single.car")
                            .setJsonBody(jsonObj)
                            .setNetworkOnResult(new NetworkOnResult() {
                                @Override
                                public void onSuccess(JSONObject jsonObject) {
                                    Log.i(TAG, "onSuccess: "+jsonObject.toString());
                                    try {
                                        if ("充值成功".equals(jsonObject.getString("response"))){
                                            int money=jsonObject.getInt("result");
                                            List<AccountBean> acounts=accountMessageItemLayoutAdapter.getObjects();
                                            AccountBean accountBean=acounts.get(carId);
                                            accountBean.setCar_money(money);
                                            acounts.set(carId,accountBean);
                                            accountMessageItemLayoutAdapter.setObjects(acounts);
                                            accountMessageItemLayoutAdapter.notifyDataSetChanged();
                                            Toast.makeText(getActivity(), "充值成功", Toast.LENGTH_SHORT).show();
                                            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                            AccountTable accountTable=new AccountTable(accountBean.getCar_number(),Integer.parseInt(moneyText),money,
                                                    AppClient.getString("username"),sdf.format(new Date()),getWeekOfDate(new Date()));
                                            Log.i(TAG, "onSuccess: "+accountTable);
                                            accountDao.addAccountTable(accountTable);
                                            mDialog.dismiss();
                                        }else {
                                            Toast.makeText(getActivity(), "充值失败", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError() {

                                }
                            });
                }else {
                    List<AccountBean> accounts=accountMessageItemLayoutAdapter.getChongZhiList();
                    final JSONObject jsonObj=new JSONObject();
                    try {
                        jsonObj.put("car_money",moneyText);
                        for (int i = 0; i < accounts.size(); i++) {
                            AccountBean bean = accounts.get(i);
                            jsonObj.put("car_number" + (i + 1), bean.getCar_number());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, "onClick: "+jsonObj.toString());
                    new NetRequest("batch.car")
                            .setJsonBody(jsonObj)
                            .setNetworkOnResult(new NetworkOnResult() {
                                @Override
                                public void onSuccess(JSONObject jsonObject) {
                                    Log.i(TAG, "onSuccess: "+jsonObject);
                                    try {
                                        if ("充值成功".equals(jsonObject.getString("response"))) {
                                            initData();
                                            Toast.makeText(getActivity(), "充值成功", Toast.LENGTH_SHORT).show();
                                            List<AccountBean> beans=accountMessageItemLayoutAdapter.getChongZhiList();
                                            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                            for (int i = 0; i <beans.size(); i++) {
                                                AccountTable accountTable = new AccountTable(beans.get(i).getCar_number() + "", Integer.parseInt(moneyText), beans.get(i).getCar_money(),
                                                        AppClient.getString("username"),sdf.format(new Date()),getWeekOfDate(new Date()));
                                                Log.i(TAG, "onSuccess: " + accountTable);
                                                accountDao.addAccountTable(accountTable);
                                            }
                                            mDialog.dismiss();
                                        }else {
                                            Toast.makeText(getActivity(), "充值失败", Toast.LENGTH_SHORT).show();
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
        });
        mDialog.setView(view);
        mDialog.show();
    }

    private class TextAdd implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.i(TAG, "onTextChanged: "+s);
        }

        @Override
        public void afterTextChanged(Editable s) {
            String ss=s.toString();
            Log.i(TAG, "afterTextChanged: "+ss);
            if (ss.equals("0")){
                s.delete(0,1);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    /**
     * 获取当前日期是星期几<br>
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
}
