package com.example.nappy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nappy.R;
import com.example.nappy.beans.FaKuanInfoBean;

import java.util.ArrayList;
import java.util.List;

public class FindSelectInfoInfosAdapter extends BaseAdapter {

    private List<FaKuanInfoBean> objects = new ArrayList<>();

    private Context context;

    private LayoutInflater layoutInflater;

    public FindSelectInfoInfosAdapter(Context context,List<FaKuanInfoBean> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects=objects;
    }

    public void setObjects(List<FaKuanInfoBean> objects) {
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public FaKuanInfoBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.find_select_info_infos, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((FaKuanInfoBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(FaKuanInfoBean object, ViewHolder holder) {
        holder.tvTime.setText(object.getDate()+"");
        holder.tvTrafficlu.setText(object.getAddress()+"");
        holder.tvTvInfo.setText(object.getInfo()+"");
        holder.tvFakuan.setText("罚款:"+object.getAmerce());
        holder.tvKoufen.setText("扣分："+object.getDeduction());
    }

    protected class ViewHolder {

        private TextView tvTime;
        private TextView tvTrafficlu;
        private TextView tvTvInfo;
        private TextView tvKoufen;
        private TextView tvFakuan;

        public ViewHolder(View view) {
            tvTime = (TextView) view.findViewById(R.id.tv_time);
            tvTrafficlu = (TextView) view.findViewById(R.id.tv_trafficlu);
            tvTvInfo = (TextView) view.findViewById(R.id.tv_tv_info);
            tvKoufen = (TextView) view.findViewById(R.id.tv_koufen);
            tvFakuan = (TextView) view.findViewById(R.id.tv_fakuan);
        }
    }
}
