package com.example.nappy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nappy.R;
import com.example.nappy.beans.LifeBean;

import java.util.ArrayList;
import java.util.List;

public class LifeItemAdapter extends BaseAdapter {

    private List<LifeBean> objects = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;
    private int icons[] ={R.drawable.zhiwaixianzhishu,R.drawable.ganmaozhisu,R.drawable.chuanyizhisu,
            R.drawable.yundongzhisu,R.drawable.kongqiwurankuoanzhisu};

    public LifeItemAdapter(Context context,List<LifeBean> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects=objects;
    }

    public void setObjects(List<LifeBean> objects) {
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public LifeBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.life_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((LifeBean)getItem(position), (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private void initializeViews(LifeBean object, ViewHolder holder,int pos) {
        holder.ivIndex.setBackgroundResource(icons[pos]);
        holder.tvLevel.setText(object.getJibie());
        holder.tvWarmPrompt.setText(object.getTishiInfo());
    }

    protected class ViewHolder {
        private ImageView ivIndex;
        private TextView tvLevel;
        private TextView tvWarmPrompt;

        public ViewHolder(View view) {
            ivIndex = (ImageView) view.findViewById(R.id.iv_index);
            tvLevel = (TextView) view.findViewById(R.id.tv_level);
            tvWarmPrompt = (TextView) view.findViewById(R.id.tv_warm_prompt);
        }
    }
}
