package com.example.android.pets.data;

import android.provider.BaseColumns;

/**
 * Created by knits4 on 3/11/18.
 */

public final class PetContract {
    public static abstract class PetsEntry implements BaseColumns{
        public static final String TABLE_NAME = "pets";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PET_NAME ="name";
        public static final String COLUMN_PET_BREED = "breed";
        public static final String COLUMN_PET_GENDER = "gender";
        public static final String COLUMN_PET_WEIGHT= "weight";

        /**
         * Values for Gender Spinner
         */
        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE= 2;
    }
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String PRIMARY_KEY = " PRIMARY KEY";
    private static final String NOT_NULL = " NOT NULL";
    private static final String DEFAULT = " DEFAULT ";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PetsEntry.TABLE_NAME + " (" +
                    PetsEntry._ID + INT_TYPE + PRIMARY_KEY +
                    PetsEntry.COLUMN_PET_NAME + TEXT_TYPE + NOT_NULL+ COMMA_SEP +
                    PetsEntry.COLUMN_PET_BREED + TEXT_TYPE + COMMA_SEP +
                    PetsEntry.COLUMN_PET_GENDER + INT_TYPE + NOT_NULL + COMMA_SEP +
                    PetsEntry.COLUMN_PET_WEIGHT + INT_TYPE +NOT_NULL + DEFAULT + 0 +
                    " )";
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PetsEntry.TABLE_NAME;

}
