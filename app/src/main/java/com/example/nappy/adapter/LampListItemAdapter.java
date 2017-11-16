package com.example.nappy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.nappy.R;
import com.example.nappy.beans.LampBean;

import java.util.ArrayList;
import java.util.List;

public class LampListItemAdapter extends BaseAdapter {

    private List<LampBean> objects = new ArrayList<>();

    private Context context;

    private LayoutInflater layoutInflater;

    public interface  OnSettingLintener{
        void OnSetting(View view,int pos);
    }

    public void setObjects(List<LampBean> objects) {
        this.objects = objects;
    }

    private OnSettingLintener onSettingLintener;

    public void setOnSettingLintener(OnSettingLintener onSettingLintener) {
        this.onSettingLintener = onSettingLintener;
    }

    public LampListItemAdapter(Context context,List<LampBean> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects=objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public LampBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.lamp_list_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((LampBean)getItem(position), (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private List<LampBean> selectList=new ArrayList<>();

    public List<LampBean> getSelectList() {
        return selectList;
    }

    private void initializeViews(final LampBean object, ViewHolder holder, final int pos) {
        holder.tvLampLukou.setText(object.getId()+"");
        holder.tvLampHongdeng.setText(object.getRed_light()+"");
        holder.tvLampHuangdeng.setText(object.getYellow_light()+"");
        holder.tvLampLvdeng.setText(object.getGreen_light()+"");
        holder.cbControlChaozuo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    selectList.add(object);
                }else {
                    selectList.remove(object);
                }
            }
        });
        holder.btnLampSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSettingLintener.OnSetting(v,pos);
            }
        });
    }

    protected class ViewHolder {
        private TextView tvLampLukou;
        private TextView tvLampHongdeng;
        private TextView tvLampHuangdeng;
        private TextView tvLampLvdeng;
        private CheckBox cbControlChaozuo;
        private Button btnLampSetting;

        public ViewHolder(View view) {
            tvLampLukou = (TextView) view.findViewById(R.id.tv_lamp_lukou);
            tvLampHongdeng = (TextView) view.findViewById(R.id.tv_lamp_hongdeng);
            tvLampHuangdeng = (TextView) view.findViewById(R.id.tv_lamp_huangdeng);
            tvLampLvdeng = (TextView) view.findViewById(R.id.tv_lamp_lvdeng);
            cbControlChaozuo = (CheckBox) view.findViewById(R.id.cb_control_chaozuo);
            btnLampSetting = (Button) view.findViewById(R.id.btn_lamp_setting);
        }
    }
}
