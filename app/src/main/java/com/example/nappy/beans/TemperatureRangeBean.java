package com.example.nappy.beans;

/**
 * Created by liufangya on 2017/10/24.
 */

public class TemperatureRangeBean {

    /**
     * oneday_max : 27
     * oneday_min : 11
     * threeday_max : 20
     * threeday_min : 16
     * today_max : 20
     * today_min : 16
     * tomorrow_max : 26
     * tomorrow_min : 15
     * twoday_max : 20
     * twoday_min : 18
     * yesterday_max : 25
     * yesterday_min : 10
     */

    private int oneday_max;
    private int oneday_min;
    private int threeday_max;
    private int threeday_min;
    private int today_max;
    private int today_min;
    private int tomorrow_max;
    private int tomorrow_min;
    private int twoday_max;
    private int twoday_min;
    private int yesterday_max;
    private int yesterday_min;

    public TemperatureRangeBean() {
    }

    public TemperatureRangeBean(int oneday_max, int oneday_min, int threeday_max, int threeday_min, int today_max, int today_min, int tomorrow_max, int tomorrow_min, int twoday_max, int twoday_min, int yesterday_max, int yesterday_min) {
        this.oneday_max = oneday_max;
        this.oneday_min = oneday_min;
        this.threeday_max = threeday_max;
        this.threeday_min = threeday_min;
        this.today_max = today_max;
        this.today_min = today_min;
        this.tomorrow_max = tomorrow_max;
        this.tomorrow_min = tomorrow_min;
        this.twoday_max = twoday_max;
        this.twoday_min = twoday_min;
        this.yesterday_max = yesterday_max;
        this.yesterday_min = yesterday_min;
    }

    public float getOneday_max() {
        return oneday_max;
    }

    public void setOneday_max(int oneday_max) {
        this.oneday_max = oneday_max;
    }

    public float getOneday_min() {
        return oneday_min;
    }

    public void setOneday_min(int oneday_min) {
        this.oneday_min = oneday_min;
    }

    public float getThreeday_max() {
        return threeday_max;
    }

    public void setThreeday_max(int threeday_max) {
        this.threeday_max = threeday_max;
    }

    public float getThreeday_min() {
        return threeday_min;
    }

    public void setThreeday_min(int threeday_min) {
        this.threeday_min = threeday_min;
    }

    public float getToday_max() {
        return today_max;
    }

    public void setToday_max(int today_max) {
        this.today_max = today_max;
    }

    public float getToday_min() {
        return today_min;
    }

    public void setToday_min(int today_min) {
        this.today_min = today_min;
    }

    public float getTomorrow_max() {
        return tomorrow_max;
    }

    public void setTomorrow_max(int tomorrow_max) {
        this.tomorrow_max = tomorrow_max;
    }

    public float getTomorrow_min() {
        return tomorrow_min;
    }

    public void setTomorrow_min(int tomorrow_min) {
        this.tomorrow_min = tomorrow_min;
    }

    public float getTwoday_max() {
        return twoday_max;
    }

    public void setTwoday_max(int twoday_max) {
        this.twoday_max = twoday_max;
    }

    public float getTwoday_min() {
        return twoday_min;
    }

    public void setTwoday_min(int twoday_min) {
        this.twoday_min = twoday_min;
    }

    public float getYesterday_max() {
        return yesterday_max;
    }

    public void setYesterday_max(int yesterday_max) {
        this.yesterday_max = yesterday_max;
    }

    public float getYesterday_min() {
        return yesterday_min;
    }

    public void setYesterday_min(int yesterday_min) {
        this.yesterday_min = yesterday_min;
    }

    @Override
    public String toString() {
        return "TemperatureRangeBean{" +
                "oneday_max=" + oneday_max +
                ", oneday_min=" + oneday_min +
                ", threeday_max=" + threeday_max +
                ", threeday_min=" + threeday_min +
                ", today_max=" + today_max +
                ", today_min=" + today_min +
                ", tomorrow_max=" + tomorrow_max +
                ", tomorrow_min=" + tomorrow_min +
                ", twoday_max=" + twoday_max +
                ", twoday_min=" + twoday_min +
                ", yesterday_max=" + yesterday_max +
                ", yesterday_min=" + yesterday_min +
                '}';
    }
}
