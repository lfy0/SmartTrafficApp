package com.example.nappy.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nappy.R;

import java.util.ArrayList;
import java.util.List;


public class BusDialogItemAdapter extends BaseAdapter {
    private static final String TAG = "BusDialogItemAdapter";

    private List<String> objects = new ArrayList<String>();

    private Context context;
    private LayoutInflater layoutInflater;

    public BusDialogItemAdapter(Context context,List<String> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        Log.i(TAG, "BusDialogItemAdapter: "+objects.get(0));
        this.objects=objects;
    }

    @Override
    public int getCount() {
        Log.i(TAG, "getCount: "+objects.size());
        return objects.size();
    }

    @Override
    public String getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.bus_dialog_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((String)getItem(position), (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private void initializeViews(String object, ViewHolder holder,int pos) {
        holder.tvNum.setText((pos+1)+"");
        holder.tvBusNum.setText((pos+1)+"");
        holder.tvPersonNum.setText(object);
        Log.i(TAG, "initializeViews: "+object);
    }

    protected class ViewHolder {

        private TextView tvNum;
        private TextView tvBusNum;
        private TextView tvPersonNum;

        public ViewHolder(View view) {
            tvNum = (TextView) view.findViewById(R.id.tv_num);
            tvBusNum = (TextView) view.findViewById(R.id.tv_busNum);
            tvPersonNum = (TextView) view.findViewById(R.id.tv_personNum);
        }
    }
}
