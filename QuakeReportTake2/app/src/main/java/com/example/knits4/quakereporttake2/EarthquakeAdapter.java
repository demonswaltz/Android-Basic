package com.example.knits4.quakereporttake2;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by knits4 on 1/22/18.
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
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthQuake.getmMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        TextView offsetLocationText = (TextView) listItemView.findViewById(R.id.location_offset);
        TextView primaryLocationText = (TextView) listItemView.findViewById(R.id.primary_location);
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

    private int getMagnitudeColor (double magnitude){
        int magint = (int) magnitude;
        int colorValue;
        switch (magint){
            case 0:
            case 1:
                colorValue = R.color.magnitude1;
                break;
            case 2:
                colorValue = R.color.magnitude2;
                break;
            case 3:
                colorValue = R.color.magnitude3;
                break;
            case 4:
                colorValue = R.color.magnitude4;
                break;
            case 5:
                colorValue = R.color.magnitude5;
                break;
            case 6:
                colorValue = R.color.magnitude6;
                break;
            case 7:
                colorValue = R.color.magnitude7;
                break;
            case 8:
                colorValue = R.color.magnitude8;
                break;
            case 9:
                colorValue = R.color.magnitude9;
                break;
            default:
                colorValue = R.color.magnitude10plus;
                break;
        }
        return ResourcesCompat.getColor(getContext(), colorValue, null);
    }



}
