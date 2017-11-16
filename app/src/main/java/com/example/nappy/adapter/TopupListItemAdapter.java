package com.example.nappy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nappy.R;
import com.example.nappy.beans.AccountTable;

import java.util.ArrayList;
import java.util.List;

public class TopupListItemAdapter extends BaseAdapter {

    private List<AccountTable> objects = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;

    public TopupListItemAdapter(Context context,List<AccountTable> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects=objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public AccountTable getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.topup_list_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((AccountTable)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(AccountTable object, ViewHolder holder) {
        holder.tvListTime.setText(object.getAccountTime().substring(0,10)+"\n"+object.getAccountWeek());
        holder.tvTopupPerson.setText("充值人:"+object.getAccountPerson());
        holder.tvTopupCarNum.setText("车牌号:"+object.getCarId());
        holder.tvTopupAdd.setText("充值:"+object.getCarMoney());
        holder.tvTopupYue.setText("余额:"+object.getCarBalance());
        holder.tvTopupAddTime.setText(object.getAccountTime());
    }

    protected class ViewHolder {
        private TextView tvListTime;
        private TextView tvTopupPerson;
        private TextView tvTopupCarNum;
        private TextView tvTopupAdd;
        private TextView tvTopupYue;
        private TextView tvTopupAddTime;

        public ViewHolder(View view) {
            tvListTime = (TextView) view.findViewById(R.id.tv_list_time);
            tvTopupPerson = (TextView) view.findViewById(R.id.tv_topup_person);
            tvTopupCarNum = (TextView) view.findViewById(R.id.tv_topup_car_num);
            tvTopupAdd = (TextView) view.findViewById(R.id.tv_topup_add);
            tvTopupYue = (TextView) view.findViewById(R.id.tv_topup_yue);
            tvTopupAddTime = (TextView) view.findViewById(R.id.tv_topup_add_time);
        }
    }
}
