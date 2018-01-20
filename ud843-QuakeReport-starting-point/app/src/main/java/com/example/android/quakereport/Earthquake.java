package com.example.android.quakereport;

/**
 * Created by knits4 on 1/13/18.
 */

public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private Long mTime;

    public Earthquake(double magnitude, String location, Long time){
        mMagnitude = magnitude;
        mLocation = location;
        mTime = time;
    }
    public  double getmMagnitude(){return mMagnitude;}
    public String getmLocation(){return mLocation;}
    public Long getmTime(){return mTime;}
}