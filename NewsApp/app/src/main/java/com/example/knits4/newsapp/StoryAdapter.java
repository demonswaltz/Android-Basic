package com.example.knits4.newsapp;

import android.app.Activity;
import android.location.Location;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by knits4 on 2/23/18.
 */

public class StoryAdapter extends ArrayAdapter<Story> {
    public int mColorResourceID;


    public StoryAdapter (Activity context, ArrayList<Story> stories){
        super(context, 0, stories);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        final Story currentStory = getItem(position);

        //Set textview for Story Title
        TextView titleText = listItemView.findViewById(R.id.title);
        titleText.setText(currentStory.getsTitle());

        //Get date object for current story
        Date dateObject = currentStory.getsDate();

        //Convert date to string and set it to the date textview
        TextView dateText = listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        dateText.setText(formattedDate);

        TextView authorText = listItemView.findViewById(R.id.author);
        authorText.setText(currentStory.getAuthor());

        TextView sectionText = listItemView.findViewById(R.id.section);
        sectionText.setText(currentStory.getsSection());



        return listItemView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
}
