package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesFragment extends Fragment {


    public PhrasesFragment() {
        // Required empty public constructor
    }


    private MediaPlayer mMediaPlayer = null;
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mAudioFocusListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if (i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                mMediaPlayer.pause();
            } else if (i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaPlayer.pause();
            } else if (i == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (i == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(getString(R.string.where_are_you_going), "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word(getString(R.string.what_is_your_name), "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        words.add(new Word(getString(R.string.my_name_is), "oyaaset...", R.raw.phrase_my_name_is));
        words.add(new Word(getString(R.string.how_are_you_feeling), "michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word(getString(R.string.im_feeling_good), "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word(getString(R.string.are_you_coming), "әәnәs'aa?", R.raw.phrase_are_you_coming));
        words.add(new Word(getString(R.string.yes_im_coming), "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        words.add(new Word(getString(R.string.im_coming), "әәnәm", R.raw.phrase_im_coming));
        words.add(new Word(getString(R.string.lets_go), "yoowutis", R.raw.phrase_lets_go));
        words.add(new Word(getString(R.string.come_here), "әnni'nem", R.raw.phrase_come_here));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_phrases);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml file.
        final ListView listView = (ListView) rootView.findViewById(R.id.list);



        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);


        //Set onClickListener for audio playback
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //create AudioManager
                mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);



                Word word = words.get(position);
                mAudioManager.requestAudioFocus(mAudioFocusListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceID());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mMediaPlayer) {
                        releaseMediaPlayer();
                    }
                });
                mAudioManager.abandonAudioFocus(mAudioFocusListener);
            }
        });

        return rootView;
    }



}
