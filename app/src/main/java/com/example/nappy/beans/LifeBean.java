package com.example.nappy.beans;

/**
 * Created by liufangya on 2017/10/24.
 */

public class LifeBean {

    private int icon;
    private String jibie;
    private String tishiInfo;

    public LifeBean() {

    }

    public LifeBean(int icon, String jibie, String tishiInfo) {
        this.icon = icon;
        this.jibie = jibie;
        this.tishiInfo = tishiInfo;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getJibie() {
        return jibie;
    }

    public void setJibie(String jibie) {
        this.jibie = jibie;
    }

    public String getTishiInfo() {
        return tishiInfo;
    }

    public void setTishiInfo(String tishiInfo) {
        this.tishiInfo = tishiInfo;
    }

    @Override
    public String toString() {
        return "LifeBean{" +
                "icon=" + icon +
                ", jibie='" + jibie + '\'' +
                ", tishiInfo='" + tishiInfo + '\'' +
                '}';
    }
}
