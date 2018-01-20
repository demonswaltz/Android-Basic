package com.example.knits4.musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a list of words
        ArrayList<Song> songs = new ArrayList<Song>();
        songs.add(new Song("Legends", "Kelsea Ballerini", "Unapologetically"));
        songs.add(new Song("Story of O.J.", "JAY-Z", "4:44"));
        songs.add(new Song("Bad Liar", "Selena Gomez", " "));
        songs.add(new Song("Bodak Yellow", "Cardi B", " "));
        songs.add(new Song("Feel It Still", "Portugal, The Man", "Woodstock"));
        songs.add(new Song("Biscuit Town", "King Krüle", "The OOZ"));
        songs.add(new Song("Chanel", "Frank Ocean", " "));
        songs.add(new Song("Despacito", "Luis Fonsi & Daddy Yankee", " "));
        songs.add(new Song("New York", "St. Vincent", "Masseduction"));
        songs.add(new Song("New Rules", "Dua Lipa", "Dua Lipa"));


        SongAdapter adapter = new SongAdapter(this, songs);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);



    }

}
