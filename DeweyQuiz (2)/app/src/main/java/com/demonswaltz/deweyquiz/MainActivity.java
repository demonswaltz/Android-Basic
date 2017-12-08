package com.demonswaltz.deweyquiz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Starts the DeweyOne Quiz Game
     *
     * @param view
     */
    public void DeweyOne(View view) {
        Intent intent = new Intent(this, DeweyOne.class);
        startActivity(intent);
    }
    public void DeweyTwo(View view) {
        Intent intent = new Intent(this, DeweyTwo.class);
        startActivity(intent);
    }
}

