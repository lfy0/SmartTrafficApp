package com.example.nappy.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nappy.R;
import com.example.nappy.adapter.LampListItemAdapter;
import com.example.nappy.beans.LampBean;
import com.example.nappy.inter.NetworkOnResult;
import com.example.nappy.network.NetRequest;
import com.example.nappy.util.GsonUtil;
import com.example.nappy.util.SpinnerAdapterUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 红绿灯管理界面
 */
public class LampFragment extends Fragment {

    private static final String TAG = "LampFragment";

    private Spinner mSLampSort;

    private Button mBtnFind;
    private Button mBtnBatchSetting;
    private ListView mLvLampList;

    private String lampSortArr[]={
            "路口升序","路口降序",
            "红灯升序","红灯降序",
            "绿灯升序","绿灯降序",
            "黄灯升序","黄灯降序"
    };


    private List<LampBean> lampBeens=new ArrayList<>();
    private LampListItemAdapter itemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lamp, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initAdapter();
    }

    private void initAdapter() {
        itemAdapter=new LampListItemAdapter(getActivity(),lampBeens);
        mLvLampList.setAdapter(itemAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated: 2");
        initEvent();
        initData();
    }

    /**
     * 初始化列表
     */
    private void initData() {
        new NetRequest("all.light")
                .showDialog(getActivity())
                .setNetworkOnResult(new NetworkOnResult() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        try {
                            Log.i(TAG, "onSuccess: "+jsonObject);
                            if ("成功".equals(jsonObject.getString("response"))){
                                JSONArray jsonArr=jsonObject.getJSONArray("result");
                                Log.i(TAG, "onSuccess: "+jsonArr);
                                lampBeens=GsonUtil.gsonToList(jsonArr.toString(),LampBean.class);
                                itemAdapter.setObjects(lampBeens);
                                itemAdapter.notifyDataSetChanged();//获取数据并更新
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
        mSLampSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //排序对象
                LampBean.select=position;
                Collections.sort(lampBeens);
                itemAdapter.setObjects(lampBeens);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        itemAdapter.setOnSettingLintener(new LampListItemAdapter.OnSettingLintener() {
            @Override
            public void OnSetting(View view, int pos) {
                ArrayList<Integer> arr=new ArrayList<>();
                arr.add((pos+1));
                showSettingLampDialog(arr);
            }
        });
        mBtnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemAdapter.notifyDataSetChanged();
            }
        });
        mBtnBatchSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> arr=new ArrayList<>();
                List<LampBean> list=itemAdapter.getSelectList();
                for (int i = 0; i <list.size(); i++) {
                    LampBean lampBean=list.get(i);
                    arr.add(lampBean.getId());
                }
                showSettingLampDialog(arr);
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void initView() {
        mSLampSort = (Spinner) getView().findViewById(R.id.s_lamp_sort);
        mSLampSort.setAdapter(SpinnerAdapterUtils.getSpinnerAdapter(getContext(),lampSortArr));
        mBtnFind = (Button) getView().findViewById(R.id.btn_find);
        mBtnBatchSetting = (Button) getView().findViewById(R.id.btn_batch_setting);
        mLvLampList = (ListView) getView().findViewById(R.id.lv_lamp_list);
    }


    /**
     * 显示设置时长对话框
     * @param arr 存放要设置的路口
     */

    private void  showSettingLampDialog(final ArrayList<Integer> arr){
        final AlertDialog alertDialog=new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("红绿灯设置");
        View view=LayoutInflater.from(getActivity())
                .inflate(R.layout.lamp_dialog_layout,null);
        final EditText etGreenLamp=view.findViewById(R.id.et_set_green_lamp);
        final EditText etRedLamp=view.findViewById(R.id.et_set_red_lamp);
        final EditText etYelloLamp=view.findViewById(R.id.et_set_yello_lamp);
        Button btnDetermine=view.findViewById(R.id.btn_queding);
        Button btnCancel=view.findViewById(R.id.btn_quxiao);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btnDetermine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String greenLamp=etGreenLamp.getText().toString().trim();
                String redLamp=etRedLamp.getText().toString().trim();
                String yelloLamp=etYelloLamp.getText().toString().trim();
                final JSONObject jsonObj=new JSONObject();
                try {
                    jsonObj.put("red_light",redLamp);
                    jsonObj.put("green_light",greenLamp);
                    jsonObj.put("yellow_light",yelloLamp);
                    for (int i = 0; i <arr.size(); i++) {
                        int lamp=arr.get(i);
                        jsonObj.put("id"+lamp,""+lamp);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "onClick: "+jsonObj);
                new NetRequest("batch.light")
                        .setJsonBody(jsonObj)
                        .setNetworkOnResult(new NetworkOnResult() {
                            @Override
                            public void onSuccess(JSONObject jsonObject) {
                                Log.i(TAG, "onSuccess: "+jsonObject);
                                try {
                                    if ("成功".equals(jsonObject.getString("response"))){
                                        String result=jsonObject.getString("result");
                                        Toast.makeText(getContext(), ""+result, Toast.LENGTH_SHORT).show();
                                        alertDialog.dismiss();
                                        initData();
                                    }else {
                                        Toast.makeText(getContext(), "修改失败", Toast.LENGTH_SHORT).show();
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
        });
        alertDialog.setView(view);
        alertDialog.show();
    }
}
