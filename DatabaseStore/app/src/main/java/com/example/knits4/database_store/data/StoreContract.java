package com.example.knits4.database_store.data;

import android.provider.BaseColumns;
import android.telephony.PhoneNumberUtils;

/**
 * Created by knits4 on 3/25/18.
 */

public class StoreContract {
    public static abstract class StoreEntry implements BaseColumns{
        public static final String TABLE_NAME = "books";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME = "productName";
        public static final String COLUMN_PRODUCT_PRICE = "price";
        public static final String COLUMN_PRODUCT_QUANTITY = "quantity";
        public static final String COLUMN_PRODUCT_SUPPLIER_NAME = "supplierName";
        public static final String COLUMN_PRODUCT_SUPPLIER_NUMBER = "supplierPhoneNumber";
    }
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String PRIMARY_KEY = " PRIMARY KEY";
    private static final String COMMA_SEP = ", ";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + StoreEntry.TABLE_NAME + "( " +
                    StoreEntry._ID + INT_TYPE + PRIMARY_KEY + COMMA_SEP +
                    StoreEntry.COLUMN_PRODUCT_NAME + TEXT_TYPE + COMMA_SEP +
                    StoreEntry.COLUMN_PRODUCT_PRICE + INT_TYPE + COMMA_SEP +
                    StoreEntry.COLUMN_PRODUCT_QUANTITY + INT_TYPE + COMMA_SEP +
                    StoreEntry.COLUMN_PRODUCT_SUPPLIER_NAME + INT_TYPE + COMMA_SEP +
                    StoreEntry.COLUMN_PRODUCT_SUPPLIER_NUMBER + TEXT_TYPE +
                    ")";
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + StoreEntry.TABLE_NAME;

}
