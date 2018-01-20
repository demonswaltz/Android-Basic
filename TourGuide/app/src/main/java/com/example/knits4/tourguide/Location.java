package com.example.knits4.tourguide;

/**
 * Created by knits4 on 1/7/18.
 */

public class Location {
    private static final int NO_IMAGE_SET = -1;
    private String mLocationName;
    private String mAddress;
    private int mImageResourceID = NO_IMAGE_SET;

    public Location(String location, String address, int imageResourceID) {
        mLocationName = location;
        mAddress = address;
        mImageResourceID = imageResourceID;
    }

    public Location(String location, int imageResourceID) {
        mLocationName = location;
        mAddress = null;
        mImageResourceID = imageResourceID;
    }

    public String getmLocationName() {
        return mLocationName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public int getmImageResourceID() {
        return mImageResourceID;
    }

    public boolean hasImage() {
        return mImageResourceID != NO_IMAGE_SET;
    }
}
