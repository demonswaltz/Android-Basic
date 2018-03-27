package com.example.knits4.database_store;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.knits4.database_store.data.StoreDBHelper;
import com.example.knits4.database_store.data.StoreContract.StoreEntry;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        queryData();
    }
    private void insertData(){
        StoreDBHelper mDBHelper= new StoreDBHelper(this);

        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(StoreEntry.COLUMN_PRODUCT_NAME, "Test");
        values.put(StoreEntry.COLUMN_PRODUCT_PRICE, 200);
        values.put(StoreEntry.COLUMN_PRODUCT_QUANTITY, 5);
        values.put(StoreEntry.COLUMN_PRODUCT_SUPPLIER_NAME, "BooksRUs");
        values.put(StoreEntry.COLUMN_PRODUCT_SUPPLIER_NUMBER, "8887776666");

        long newRow;
        newRow =  db.insert(StoreEntry.TABLE_NAME, null, values);

    }

    private Cursor queryData(){
        StoreDBHelper mDBHelper= new StoreDBHelper(this);

        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        String[] projection ={
                StoreEntry._ID,
                StoreEntry.COLUMN_PRODUCT_NAME,
                StoreEntry.COLUMN_PRODUCT_QUANTITY
                };
        Cursor cursor = db.query(StoreEntry.TABLE_NAME, projection, null, null, null, null, null);
        TextView displayTable = findViewById(R.id.book_text);
        int idColumnIndex = cursor.getColumnIndex(StoreEntry._ID);
        int productColumnIndex = cursor.getColumnIndex(StoreEntry.COLUMN_PRODUCT_NAME);
        int quantityColumnIndex = cursor.getColumnIndex(StoreEntry.COLUMN_PRODUCT_QUANTITY);

        while (cursor.moveToNext()) {
            int currentID = cursor.getInt(idColumnIndex);
            String currentProduct = cursor.getString(productColumnIndex);
            int currentQuantity = cursor.getInt(quantityColumnIndex);

            displayTable.append("\n" + currentID + " - " + currentProduct + " - " + currentQuantity );
        }
        cursor.close();
        return cursor;

    }
}
