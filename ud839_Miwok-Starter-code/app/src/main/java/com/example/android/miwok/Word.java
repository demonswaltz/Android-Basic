package com.example.android.miwok;

/**
 * Created by knits4 on 12/10/17.
 */

public class Word {
    // String for miwok translation
    private String mMiwokTranslation;

    //String for default translation;
    private String mDefaultTranslation;

    //int for image file name storage
    private int mImageResourceID = NO_IMGAGE_SET;

    //int for audio file name storage
    private int mAudioResourceID;

    //Constant for checking if imageResourceID is set
    private static final int NO_IMGAGE_SET = -1;

    public Word(String defaultWord, String miwokWord, int AudioResourceID){
        mMiwokTranslation = miwokWord;
        mDefaultTranslation = defaultWord;
        mAudioResourceID = AudioResourceID;
    }

    public Word(String defaultWord, String miwokWord, int imageID, int AudioResourceID){
        mMiwokTranslation = miwokWord;
        mDefaultTranslation = defaultWord;
        mImageResourceID = imageID;
        mAudioResourceID = AudioResourceID;

    }

    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }

    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    public int getImageResourceID() {return mImageResourceID;}

    public int getAudioResourceID() {return mAudioResourceID;}

    public boolean hasImage(){
        return mImageResourceID != NO_IMGAGE_SET;
    }
}
