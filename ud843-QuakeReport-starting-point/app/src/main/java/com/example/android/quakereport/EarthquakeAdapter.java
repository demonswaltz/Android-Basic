package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;


/**
 * Created by knits4 on 1/13/18.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdapter (Activity context, ArrayList<Earthquake> earthquakes){
        super(context, 0, earthquakes);
    }
    private String offsetLocation;
    private String primaryLoaction;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Earthquake currentEarthQuake = getItem(position);


        // Find the TextView with view ID magnitude
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        // Format the magnitude to show 1 decimal place
        String formattedMagnitude = formatMagnitude(currentEarthQuake.getmMagnitude());
        // Display the magnitude of the current earthquake in that TextView
        magnitudeView.setText(formattedMagnitude);

        TextView offsetLocationText = (TextView) listItemView.findViewById(R.id.offsetLocation);
        TextView primaryLocationText = (TextView) listItemView.findViewById(R.id.primaryLocation);
        String locationString = currentEarthQuake.getmLocation();
        int ofWhere = locationString.indexOf("of");
        if (ofWhere == -1){
            offsetLocation = "Near";
            primaryLoaction = locationString;
        } else{
            offsetLocation = locationString.substring(0,ofWhere+2);
            primaryLoaction = locationString.substring(ofWhere +2);


        }

        offsetLocationText.setText(offsetLocation);
        primaryLocationText.setText(primaryLoaction);

        TextView dateText = (TextView) listItemView.findViewById(R.id.date);
        TextView timeText = (TextView) listItemView.findViewById(R.id.time);
        long timeInMilliseconds = currentEarthQuake.getmTime();
        Date dateObject = new Date(timeInMilliseconds);


        String dateToDisplay = dateFormatter(dateObject);
        String timeToDisplay = timeFormatter(dateObject);
        dateText.setText(dateToDisplay);
        timeText.setText(timeToDisplay);

        return listItemView;
    }
    private String dateFormatter(Date dateObject) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        String dateToDisplay = dateFormatter.format(dateObject);
        return dateToDisplay;

    }

    private String timeFormatter(Date dateObject){
        SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
        String timeToDisplay = timeFormatter.format(dateObject);
        return timeToDisplay;

    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }



}
