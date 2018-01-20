package com.example.knits4.tourguide;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by knits4 on 1/7/18.
 */

public class LocationAdapter extends ArrayAdapter<Location> {
    public int mColorResourceID;

    public String currentLocationAddress;

    public LocationAdapter (Activity context, ArrayList<Location> locations, int colorResourceID){
        super(context, 0, locations);
        mColorResourceID = colorResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        final Location currentLocation = getItem(position);
        LinearLayout textBackground = listItemView.findViewById(R.id.text_background);
        int color = ContextCompat.getColor(getContext(), mColorResourceID);
        textBackground.setBackgroundColor(color);

        TextView locationText = listItemView.findViewById(R.id.location);
        locationText.setText(currentLocation.getmLocationName());

        TextView addressText = listItemView.findViewById(R.id.address);
        addressText.setText(currentLocation.getmAddress());
        currentLocationAddress = currentLocation.getmAddress();

        ImageView iconView = (ImageView) listItemView.findViewById(R.id.icon);
        if (currentLocation.hasImage()) {
            iconView.setImageResource(currentLocation.getmImageResourceID());
            iconView.setVisibility(View.VISIBLE);
        } else {
            iconView.setVisibility(View.GONE);
        }
        return listItemView;
    }
}
