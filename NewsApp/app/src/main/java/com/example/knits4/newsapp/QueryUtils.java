package com.example.knits4.newsapp;

import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by knits4 on 2/23/18.
 */

public final class QueryUtils {
    private static final String SAMPLE_JSON_RESPONSE = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":11979,\"startIndex\":1,\"pageSize\":3,\"currentPage\":1,\"pages\":3993,\"orderBy\":\"relevance\",\"results\":[{\"id\":\"science/2018/feb/18/china-great-leap-forward-science-research-innovation-investment-5g-genetics-quantum-internet\",\"type\":\"article\",\"sectionId\":\"science\",\"sectionName\":\"Science\",\"webPublicationDate\":\"2018-02-18T09:00:40Z\",\"webTitle\":\"China’s great leap forward in science\",\"webUrl\":\"https://www.theguardian.com/science/2018/feb/18/china-great-leap-forward-science-research-innovation-investment-5g-genetics-quantum-internet\",\"apiUrl\":\"https://content.guardianapis.com/science/2018/feb/18/china-great-leap-forward-science-research-innovation-investment-5g-genetics-quantum-internet\",\"tags\":[{\"id\":\"profile/philip-ball\",\"type\":\"contributor\",\"webTitle\":\"Philip Ball\",\"webUrl\":\"https://www.theguardian.com/profile/philip-ball\",\"apiUrl\":\"https://content.guardianapis.com/profile/philip-ball\",\"references\":[],\"bio\":\"<p>Philip Ball is a science writer. His latest book is The Water Kingdom: A Secret History of China</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2014/6/23/1403543415535/Philip-Ball.jpg\",\"firstName\":\"philip\",\"lastName\":\"ball\"}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"science/political-science/2017/nov/16/the-deferred-promise-of-islamic-world-science\",\"type\":\"article\",\"sectionId\":\"science\",\"sectionName\":\"Science\",\"webPublicationDate\":\"2017-11-16T07:00:21Z\",\"webTitle\":\"The deferred promise of Islamic-world science\",\"webUrl\":\"https://www.theguardian.com/science/political-science/2017/nov/16/the-deferred-promise-of-islamic-world-science\",\"apiUrl\":\"https://content.guardianapis.com/science/political-science/2017/nov/16/the-deferred-promise-of-islamic-world-science\",\"tags\":[{\"id\":\"profile/ehsan-masood\",\"type\":\"contributor\",\"webTitle\":\"Ehsan Masood\",\"webUrl\":\"https://www.theguardian.com/profile/ehsan-masood\",\"apiUrl\":\"https://content.guardianapis.com/profile/ehsan-masood\",\"references\":[],\"bio\":\"<p>Ehsan Masood is editor of Research Fortnight and presented Science: Right or Left? for BBC Radio 4</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2009/2/16/1234778843045/ehsan.jpg\",\"firstName\":\"ehsan\",\"lastName\":\"masood\"},{\"id\":\"profile/james-wilsdon\",\"type\":\"contributor\",\"webTitle\":\"James Wilsdon\",\"webUrl\":\"https://www.theguardian.com/profile/james-wilsdon\",\"apiUrl\":\"https://content.guardianapis.com/profile/james-wilsdon\",\"references\":[],\"bio\":\"<p>James Wilsdon is professor of research policy and director of impact and engagement in the Faculty of Social Sciences at the <a href-\\\"https://www.sheffield.ac.uk/faculty/social-sciences\\\">University of Sheffield</a> He is also chair of the <a href-\\\"https://campaignforsocialscience.org.uk/\\\">Campaign for Social Science</a>. On Twitter he is <a href=\\\"http://twitter.com/jameswilsdon\\\" title=\\\"\\\">@jameswilsdon</a></p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2013/1/22/1358866514220/Guardian-science-blogger--006.jpg\",\"firstName\":\"james\",\"lastName\":\"wilsdon\",\"twitterHandle\":\"jameswilsdon\"}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"science/2018/jan/26/lab-notes-bones-and-clones-made-this-weeks-science-headlines\",\"type\":\"article\",\"sectionId\":\"science\",\"sectionName\":\"Science\",\"webPublicationDate\":\"2018-01-26T18:49:42Z\",\"webTitle\":\"Lab notes: Bones and clones made this week's science headlines\",\"webUrl\":\"https://www.theguardian.com/science/2018/jan/26/lab-notes-bones-and-clones-made-this-weeks-science-headlines\",\"apiUrl\":\"https://content.guardianapis.com/science/2018/jan/26/lab-notes-bones-and-clones-made-this-weeks-science-headlines\",\"tags\":[{\"id\":\"profile/tash-reith-banks\",\"type\":\"contributor\",\"webTitle\":\"Tash Reith-Banks\",\"webUrl\":\"https://www.theguardian.com/profile/tash-reith-banks\",\"apiUrl\":\"https://content.guardianapis.com/profile/tash-reith-banks\",\"references\":[],\"bio\":\"<p>Tash Reith-Banks is production editor for science. She also writes about theatre, books and music and can be found on Twitter <a href=\\\"http://twitter.com/TashReithBanks\\\">@TashReithBanks</a></p>\",\"firstName\":\"tash\",\"lastName\":\"reith-banks\",\"twitterHandle\":\"TashReithBanks\"}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}]}}";

    private QueryUtils(){}

    public static List<Story> fetchStoryData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("QueryUtils ", "Problem making the HTTP request.", e);
        }

        List<Story> stories = extractNewsFromJson(jsonResponse);


        return stories;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("QueryUtils","Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
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
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("QueryUtils", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("QueryUtils", "Problem retrieving story JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }


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

    private static List<Story> extractNewsFromJson(String storyJSON) {
        if (TextUtils.isEmpty(storyJSON)) {
            return null;
        }


        ArrayList<Story> stories = new ArrayList<>();

            try {
                JSONObject baseJsonResponse = new JSONObject(storyJSON);
                JSONObject obj = new JSONObject(baseJsonResponse.getString("response"));
                JSONArray storyArray = obj.getJSONArray("results");
                // for each object in responses create a new news story
                for (int i = 0; i < storyArray.length(); i++) {
                    JSONObject currentstory = storyArray.getJSONObject(i);


                    //extract the title
                    String title = currentstory.getString("webTitle");

                    //extract the section
                    String section = currentstory.getString("sectionName");

                    //extract the url

                    URL url = new URL(currentstory.getString("webUrl"));

                    //try to extract the date, if there isn't one the date is set to null
                    Date formattedDate = null;
                    if (currentstory.getString("webPublicationDate").length() > 0) {
                        String date = currentstory.getString("webPublicationDate");
                        String day = date.split("T")[0];
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        formattedDate = df.parse(day);
                    }
                    // extract the array that contains the contributor information
                    JSONArray tags = currentstory.getJSONArray("tags");
                    String author = null;
                    int tagLength = tags.length();
                    Log.v("tag length: ", String.valueOf(tagLength));
                        if (tags.length() > 0) {
                            JSONObject contributors = tags.getJSONObject(0);
                            Log.v("contributors: ", contributors.toString());

                            //check for author information, if none author is set to null

                            if (contributors.getString("webTitle").length() > 0) {
                                author = contributors.getString("webTitle");
                            }
                        }
                    Story story;
                    if (author != null && formattedDate != null) {
                        story = new Story(title, section, url, formattedDate, author);
                    } else if (author != null) {
                        story = new Story(title, section, url, author);
                    } else if (formattedDate != null) {
                        story = new Story(title, section, url, formattedDate);
                    } else {
                        story = new Story(title, section, url);
                    }

                    stories.add(story);

                }
            } catch (JSONException e) {
                // If an error is thrown when executing any of the above statements in the "try" block,
                // catch the exception here, so the app doesn't crash. Print a log message
                // with the message from the exception.
                Log.e("QueryUtils", "Problem parsing the story JSON results", e);
            } catch (ParseException e) {
                Log.e("QueryUtils", "Problem parsing Simple Date", e);
            } catch (MalformedURLException e) {
                Log.e("QueryUtils", "Problem parsing URL", e);

            }


            return stories;
        }
    }


