package com.example.knits4.musicapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by knits4 on 12/15/17.
 */

public class SongAdapter extends ArrayAdapter<Song> {
    private Context context;

    public SongAdapter(Activity context, ArrayList<Song> songs) {
        super(context, 0, songs);
        this.context = context;

    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.song_list_item, parent, false);
        }
            ImageButton playButton = (ImageButton) listItemView.findViewById(R.id.play_button);
            playButton.setTag(position);
            playButton.setOnClickListener(new View.OnClickListener() {
                @Override
              public void onClick(View view) {
                int itemposition = (Integer) view.getTag();
                Song song = getItem(itemposition);
                Intent playIntent = new Intent(context, PlaySongActivity.class);
                   String title = song.getTitle();
                   String artist = song.getArtist();
                   String album = song.getAlbum();
                   playIntent.putExtra("title", title);
                   playIntent.putExtra("artist", artist);
                   playIntent.putExtra("album", album);
                   context.startActivity(playIntent);
            }
            });


            Song currentSong = getItem(position);
            TextView titleTextView = (TextView) listItemView.findViewById(R.id.song_title);
            titleTextView.setText(currentSong.getTitle());

            TextView artistTextView = (TextView) listItemView.findViewById(R.id.song_artist);
            artistTextView.setText(currentSong.getArtist());

            TextView albumTextView = (TextView) listItemView.findViewById(R.id.song_album);
            albumTextView.setText(currentSong.getAlbum());




        return listItemView;

    }
}