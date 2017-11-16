package com.example.nappy.beans;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by liufangya on 2017/10/19.
 */

public class LampBean implements Comparable<LampBean>{


    public static int select=0;

    /**
     * green_light : 150
     * id : 1
     * ids : []
     * red_light : 350
     * yellow_light : 250
     */

    private int green_light;
    private int id;
    private int red_light;
    private int yellow_light;
    private List<?> ids;

    public int getGreen_light() {
        return green_light;
    }

    public void setGreen_light(int green_light) {
        this.green_light = green_light;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRed_light() {
        return red_light;
    }

    public void setRed_light(int red_light) {
        this.red_light = red_light;
    }

    public int getYellow_light() {
        return yellow_light;
    }

    public void setYellow_light(int yellow_light) {
        this.yellow_light = yellow_light;
    }

    public List<?> getIds() {
        return ids;
    }

    public void setIds(List<?> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "LampBean{" +
                "green_light=" + green_light +
                ", id=" + id +
                ", red_light=" + red_light +
                ", yellow_light=" + yellow_light +
                ", ids=" + ids +
                '}';
    }

    /**
     * 实现八种排序规则
     * @param o
     * @return
     */
    @Override
    public int compareTo(@NonNull LampBean o) {
        switch (select){
            case 0:
                return this.id-o.id;
            case 1:
                return o.id-this.id;
            case 2:
                return this.red_light-o.red_light;
            case 3:
                return o.red_light-this.red_light;
            case 4:
                return this.green_light-o.green_light;
            case 5:
                return o.green_light-this.green_light;
            case 6:
                return this.yellow_light-o.yellow_light;
            case 7:
                return o.yellow_light-this.yellow_light;
        }
        return 0;
    }
}
