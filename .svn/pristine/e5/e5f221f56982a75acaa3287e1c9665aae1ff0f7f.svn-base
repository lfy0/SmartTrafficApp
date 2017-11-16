package com.example.nappy.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nappy.R;
import com.example.nappy.beans.FindInfoBean;

import java.util.ArrayList;
import java.util.List;

public class FindInfoItem1Adapter extends BaseAdapter {

    private List<FindInfoBean> objects = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;
    private static final String TAG = "FindInfoItem1Adapter";

    public interface OnRemoveClickListerer{
        void OnRemoveClick(int pos);
    }

    private OnRemoveClickListerer onRemoveClickListerer;

    public void setOnRemoveClickListerer(OnRemoveClickListerer onRemoveClickListerer) {
        this.onRemoveClickListerer = onRemoveClickListerer;
    }

    public FindInfoItem1Adapter(Context context, List<FindInfoBean> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects=objects;
    }

    public void setObjects(List<FindInfoBean> objects) {
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public FindInfoBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.find_layout_item, parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((FindInfoBean)getItem(position), (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private void initializeViews(FindInfoBean object, ViewHolder holder, final int pos) {
        Log.i(TAG, "initializeViews: "+object.toString());
        holder.tvCarId.setText(object.getCar_number()+"");
        holder.tvFakuan.setText("未处理违章"+object.getDispose_number()+"次");
        holder.tvWeichuliWeizhang.setText("扣"+object.getDeduction()+"分    罚款"+object.getAmerce()+"元");
        holder.ivRemoveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRemoveClickListerer.OnRemoveClick(pos);
            }
        });
    }

    protected class ViewHolder {

        private TextView tvCarId;
        private TextView tvWeichuliWeizhang;
        private TextView tvFakuan;
        private ImageView ivRemoveItem;

        public ViewHolder(View view) {
            tvCarId = (TextView) view.findViewById(R.id.tv_car_id);
            tvWeichuliWeizhang = (TextView) view.findViewById(R.id.tv_weichuli_weizhang);
            tvFakuan = (TextView) view.findViewById(R.id.tv_fakuan);
            ivRemoveItem = (ImageView) view.findViewById(R.id.iv_remove_item);
        }
    }


}
