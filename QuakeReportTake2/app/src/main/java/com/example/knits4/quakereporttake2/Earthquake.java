package com.example.knits4.quakereporttake2;

/**
 * Created by knits4 on 1/22/18.
 */
public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private Long mTime;
    private String mUrl;

    public Earthquake(double magnitude, String location, Long time, String url){
        mMagnitude = magnitude;
        mLocation = location;
        mTime = time;
        mUrl = url;
    }
    public  double getmMagnitude(){return mMagnitude;}
    public String getmLocation(){return mLocation;}
    public Long getmTime(){return mTime;}
    public String getmUrl(){return mUrl;}

}
