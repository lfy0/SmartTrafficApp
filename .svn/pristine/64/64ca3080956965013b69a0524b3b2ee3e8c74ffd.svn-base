package com.example.nappy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.nappy.R;
import com.example.nappy.beans.BusBean;

import java.util.List;

/**
 * Created by liufangya on 2017/10/16.
 */

public class BusListAdapter extends BaseExpandableListAdapter {

    List<String> groupList;
    List<List<BusBean>> childList;
    private Context context;

    public BusListAdapter(Context context,List<String> groupList, List<List<BusBean>> childList) {
        this.groupList = groupList;
        this.childList = childList;
        this.context=context;
    }

    public void setChildList(List<List<BusBean>> childList) {
        this.childList = childList;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return childList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolder1 viewHolder1;
        String zhantai=groupList.get(groupPosition);
        if (convertView==null){
            viewHolder1=new ViewHolder1();
            convertView=LayoutInflater.from(context)
                    .inflate(R.layout.bus_item_layout,null);
            viewHolder1.mTvZhanTai=convertView.findViewById(R.id.tv_zhantai);
            convertView.setTag(viewHolder1);
        }else {
            viewHolder1= (ViewHolder1) convertView.getTag();
        }
        viewHolder1.mTvZhanTai.setText(""+zhantai);
        return convertView;
    }

    public class ViewHolder1{
        TextView mTvZhanTai;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder holder;
        BusBean busBean= (BusBean) getChild(groupPosition,childPosition);
        if (convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context)
                    .inflate(R.layout.bus_item_list_layout,null);
            holder.tvCarNum=convertView.findViewById(R.id.tv_car);
            holder.tvJvLiNum=convertView.findViewById(R.id.tv_bus_juli);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tvCarNum.setText(busBean.getPersonNumber()+"");
        holder.tvJvLiNum.setText(busBean.getDistanceNumber()+"");
        return convertView;
    }

    private class ViewHolder{
        TextView tvCarNum;
        TextView tvJvLiNum;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
