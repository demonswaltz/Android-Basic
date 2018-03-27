package com.example.android.quakereport;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by knits4 on 1/22/18.
 */

public final class QueryUtils {

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Convert String to URL and make HTTPS request
     */
    public static List<Earthquake> fetchEarthquakeData(String requestUrl) {
        //create URL from String
        URL url = createUrl(requestUrl);
        // Perform HTTP request to the URL and receive a JSON response back
        String earthquakeJSON = null;
        List<Earthquake> earthquakeArray = null;
        try {
            Log.e("FetchEarthQuakeData", "start http request");
            earthquakeJSON = makeHttpRequest(url);
            Log.e("QueryUtils", earthquakeJSON);
            earthquakeArray = extractEarthquakes(earthquakeJSON);
        } catch (IOException e) {
            Log.e("QueryUtils", "Error closing input stream", e);
        }

        return earthquakeArray;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("QueryUtils", "Error with creating URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String earthquakeJSON = "";

        // If the URL is null, then return early.
        if (url == null) {
            Log.e("makeHttpRequest", "no url");
            return earthquakeJSON;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                earthquakeJSON = readFromStream(inputStream);
            } else {
                Log.e("QueryUtils", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("QueryUtils", "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return earthquakeJSON;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Earthquake> extractEarthquakes(String earthquakeJSON) {


        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Earthquake> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // build up a list of Earthquake objects with the corresponding data.
            // convert json string to json object
            JSONObject root = new JSONObject(earthquakeJSON);

            //find array containing features in json
            JSONArray featuresArray = root.getJSONArray("features");

            for (int i = 0; i < featuresArray.length(); i++) {
                // get object at index
                JSONObject thisEarthquake = featuresArray.getJSONObject(i);
                //get properties object of current earthquake
                JSONObject properties = thisEarthquake.getJSONObject("properties");
                //extract the magnitude, place and time strings
                double mag = properties.getDouble("mag");
                String place = properties.getString("place");
                Long time = properties.getLong("time");
                String url = properties.getString("url");
                //create earthquake object and add it to the earthquake array
                earthquakes.add(new Earthquake(mag, place, time, url));


            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

}

