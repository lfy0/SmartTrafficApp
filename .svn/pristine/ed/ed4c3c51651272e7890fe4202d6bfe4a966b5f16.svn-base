package com.example.nappy.beans;

import android.support.annotation.NonNull;

/**
 * Created by liufangya on 2017/10/16.
 */
public class BusBean implements Comparable<BusBean>{

    private String personNumber;
    private String distanceNumber;

    public BusBean(String personNumber, String distanceNumber) {
        this.personNumber = personNumber;
        this.distanceNumber = distanceNumber;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public String getDistanceNumber() {
        return distanceNumber;
    }

    public void setDistanceNumber(String distanceNumber) {
        this.distanceNumber = distanceNumber;
    }

    @Override
    public String toString() {
        return "BusBean{" +
                "personNumber='" + personNumber + '\'' +
                ", distanceNumber='" + distanceNumber + '\'' +
                '}';
    }


    @Override
    public int compareTo(@NonNull BusBean o) {
        String thisDis=distanceNumber.substring(this.distanceNumber.indexOf("台")+1,this.distanceNumber.indexOf("米"));
        String oDis=o.distanceNumber.substring(o.distanceNumber.indexOf("台")+1,o.distanceNumber.indexOf("米"));
        return Integer.parseInt(thisDis)-Integer.parseInt(oDis);
    }
}
