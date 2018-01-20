package com.example.knits4.musicapp;

/**
 * Created by knits4 on 12/15/17.
 */

public class Song {
    private String title;
    private String artist;
    private String album;


    public Song(String songtitle, String songartist, String songalbum){
        title =  songtitle;
        artist = songartist;
        album = songalbum;

    }
    public String getTitle(){return title;}
    public String getArtist(){return artist;}
    public String getAlbum(){return album;}
}
