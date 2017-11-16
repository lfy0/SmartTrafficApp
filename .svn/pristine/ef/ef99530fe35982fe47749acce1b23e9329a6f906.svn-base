package com.example.nappy.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nappy.R;
import com.example.nappy.app.AppClient;
import com.example.nappy.inter.NetworkOnResult;
import com.example.nappy.network.NetRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * TabHost阀值设置
 */
public class ThresholdSettingFragment extends Fragment {

    private TextView mTvGetYuzi;
    private EditText mEtSetYuzi;
    private Button mBtnSetting;

    private static final String TAG = "ThresholdSettingFragmen";
    public ThresholdSettingFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_threshold_setting, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initEvent();
    }

    private void initEvent() {
        mBtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String money=mEtSetYuzi.getText().toString().trim();
                if (!TextUtils.isEmpty(money)){
                    JSONObject jsonObj=new JSONObject();
                    try {
                        jsonObj.put("CarValue",Integer.parseInt(money));
                    } catch (final JSONException e) {
                        e.printStackTrace();
                    }
                    new NetRequest("setCarValue.in")
                            .setJsonBody(jsonObj)
                            .setNetworkOnResult(new NetworkOnResult() {
                                @Override
                                public void onSuccess(JSONObject jsonObject) {
                                    Log.i(TAG, "onSuccess: "+jsonObject);
                                    try {
                                        if ("成功".equals(jsonObject.getString("response"))){
                                            AppClient.setInt("money",Integer.parseInt(money));
                                            initData();
                                            Toast.makeText(getActivity(), "设置成功", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(getActivity(), "设置失败", Toast.LENGTH_SHORT).show();
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
    }

    private void initData() {
       // int money= AppClient.getInt("money");
        new NetRequest("getCarValue.in")
                .setNetworkOnResult(new NetworkOnResult() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        Log.i(TAG, "onSuccess: "+jsonObject);
                        try {
                            if ("成功".equals(jsonObject.getString("response"))){
                                int result=jsonObject.getInt("result");
                                AppClient.setInt("money",result);
                                if (result==0){
                                    mTvGetYuzi.setText("当前 1-4 号小车账户余额告警阈值未设置!");
                                }else {
                                    mTvGetYuzi.setText("当前 1-4 号小车账户余额告警阈值为"+result+"元");
                                }
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
        mTvGetYuzi = (TextView) getView().findViewById(R.id.tv_get_yuzi);
        mEtSetYuzi = (EditText) getView().findViewById(R.id.et_set_yuzi);
        mBtnSetting = (Button) getView().findViewById(R.id.btn_setting);
    }
}
