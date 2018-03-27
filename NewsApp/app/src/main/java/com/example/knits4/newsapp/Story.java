package com.example.knits4.newsapp;

import java.net.URL;
import java.util.Date;

/**
 * Created by knits4 on 2/23/18.
 */

public class Story {
    private String sTitle;
    private String sSection;
    private Date sDate;
    private URL sUrl;
    private String sAuthor;

    public Story (String title, String section,  URL url, Date date, String author){
        sTitle = title;
        sSection = section;
        sUrl = url;
        sDate = date;
        sAuthor = author;
    }
    public Story (String title, String section,URL url, Date date){
        sTitle = title;
        sSection = section;
        sUrl = url;
        sDate = date;
    }
    public Story (String title, String section,URL url, String author){
        sTitle = title;
        sSection = section;
        sUrl = url;
        sAuthor = author;
    }
    public Story (String title, String section,URL url){
        sTitle = title;
        sSection = section;
        sUrl = url;
    }

    public String getsTitle() {return sTitle;}
    public Date getsDate() {return  sDate;}
    public URL getUrl() {return sUrl;}
    public String getAuthor(){return sAuthor;}
    public String getsSection(){return sSection;}

}