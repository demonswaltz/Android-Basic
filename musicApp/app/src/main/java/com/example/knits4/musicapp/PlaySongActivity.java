package com.example.knits4.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by knits4 on 12/21/17.
 */

public class PlaySongActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.now_playing_activity);

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String artist = intent.getStringExtra("artist");
        String album = intent.getStringExtra("album");

        TextView titleText = findViewById(R.id.title_textview);
        TextView artistText = findViewById(R.id.artist_textview);
        TextView albumText = findViewById(R.id.album_textview);

        titleText.setText(title);
        artistText.setText(artist);
        albumText.setText(album);


        final ImageButton theButton = findViewById(R.id.now_playing_button);
        theButton.setOnClickListener(new View.OnClickListener() {
            int isClicked = 0;
            @Override
            public void onClick(View view) {
                if (isClicked == 0) {
                    theButton.setImageResource(R.mipmap.ic_ic_pause_white_48dp);
                    isClicked = 1;
                }else{
                    theButton.setImageResource(R.mipmap.ic_ic_play_arrow_white_48dp);
                    isClicked = 0;
                }
            }


        });
        TextView goBack = findViewById(R.id.back);
        goBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent toTen = new Intent(PlaySongActivity.this, MainActivity.class);
                startActivity(toTen);

            }
                                  }

        );
    }
}
