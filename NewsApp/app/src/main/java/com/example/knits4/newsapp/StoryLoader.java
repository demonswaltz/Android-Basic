package com.example.knits4.newsapp;

import android.content.Context;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by knits4 on 2/27/18.
 */

public class StoryLoader extends AsyncTaskLoader<List<Story>> {

    /** Tag for log messages */
    private static final String LOG_TAG = StoryLoader.class.getName();

    /** Query URL */
    private String mUrl;


    public StoryLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Story> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<Story> stories = QueryUtils.fetchStoryData(mUrl);
        return stories;
    }
}
