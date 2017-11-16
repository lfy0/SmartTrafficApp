package com.example.nappy.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nappy.R;
import com.example.nappy.app.AppClient;
import com.example.nappy.beans.AccountBean;

import java.util.ArrayList;
import java.util.List;

public class AccountMessageItemLayoutAdapter extends BaseAdapter {

    private static final String TAG = "AccountMessageItemLayou";
    private List<AccountBean> objects = new ArrayList<>();

    private Context context;

    private LayoutInflater layoutInflater;

    public List<AccountBean> getObjects() {
        return objects;
    }

    public void setObjects(List<AccountBean> objects) {
        this.objects = objects;
    }

    public interface  OnAccountCheckListener{
        void OnBtnCheck(View view,int pos);
    }

    private OnAccountCheckListener onAccountCheckListener;

    public void setOnAccountCheckListener(OnAccountCheckListener onAccountCheckListener) {
        this.onAccountCheckListener = onAccountCheckListener;
    }

    public AccountMessageItemLayoutAdapter(Context context, List<AccountBean> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects=objects;
        chongZhiList=new ArrayList<>();
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public AccountBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.account_message_item_layout, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((AccountBean)getItem(position), (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    List<AccountBean> chongZhiList;

    public List<AccountBean> getChongZhiList() {
        return chongZhiList;
    }

    private void initializeViews(final AccountBean object, ViewHolder holder, final int pos) {
        holder.tvAccountNum.setText(object.getId()+",");
        holder.tvAccountCarNum.setText("辽"+object.getCar_number());
        holder.tvAccountCarName.setText("车主:"+object.getCar_owner());
        holder.tvAccountMoney.setText("余额:"+object.getCar_money());
        holder.ivAccountCarImg.setBackgroundResource(object.getBitmapRes());
        holder.tvAccountSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chongZhiList.add(object);
                }else {
                    chongZhiList.remove(object);
                }
            }
        });
        holder.btnAddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: ");
                onAccountCheckListener.OnBtnCheck(v,pos);
            }
        });
        if (object.getCar_money()< AppClient.getInt("money")){
            holder.llItem.setBackgroundColor(Color.parseColor("#ffcc00"));
        }else {
            holder.llItem.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    protected class ViewHolder {
        private TextView tvAccountNum;
        private ImageView ivAccountCarImg;
        private TextView tvAccountCarNum;
        private TextView tvAccountCarName;
        private TextView tvAccountMoney;
        private CheckBox tvAccountSelect;
        private Button btnAddAccount;
        private LinearLayout llItem;

        public ViewHolder(View view) {
            tvAccountNum = (TextView) view.findViewById(R.id.tv_account_num);
            ivAccountCarImg = (ImageView) view.findViewById(R.id.iv_account_car_img);
            tvAccountCarNum = (TextView) view.findViewById(R.id.tv_account_car_num);
            tvAccountCarName = (TextView) view.findViewById(R.id.tv_account_car_name);
            tvAccountMoney = (TextView) view.findViewById(R.id.tv_account_money);
            tvAccountSelect = (CheckBox) view.findViewById(R.id.tv_account_select);
            btnAddAccount = (Button) view.findViewById(R.id.btn_add_account);
            llItem=view.findViewById(R.id.ll_item);
        }
    }
}
