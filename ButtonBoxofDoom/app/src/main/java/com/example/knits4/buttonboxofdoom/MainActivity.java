package com.example.knits4.buttonboxofdoom;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.lang.reflect.Array;
import java.net.Socket;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final boolean[] puzzleAnswer = {false, false, true, true, false, false, true, true, true, true, true, false};
    boolean runPlease = true;
    String editTextAddress = "192.168.4.20";
    int editTextPort = 8888;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToggleButton button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);
        ToggleButton button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);
        ToggleButton button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);
        ToggleButton button4 = findViewById(R.id.button4);
        button4.setOnClickListener(this);
        ToggleButton button5 = findViewById(R.id.button5);
        button5.setOnClickListener(this);
        ToggleButton button6 = findViewById(R.id.button6);
        button6.setOnClickListener(this);
        ToggleButton button7 = findViewById(R.id.button7);
        button7.setOnClickListener(this);
        ToggleButton button8 = findViewById(R.id.button8);
        button8.setOnClickListener(this);
        ToggleButton button9 = findViewById(R.id.button9);
        button9.setOnClickListener(this);
        ToggleButton button10 = findViewById(R.id.button10);
        button10.setOnClickListener(this);
        ToggleButton button11 = findViewById(R.id.button11);
        button11.setOnClickListener(this);
        ToggleButton button12 = findViewById(R.id.button12);
        button12.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        demButtons();
    }

    public void demButtons() {
        ToggleButton button1 = findViewById(R.id.button1);
        ToggleButton button2 = findViewById(R.id.button2);
        ToggleButton button3 = findViewById(R.id.button3);
        ToggleButton button4 = findViewById(R.id.button4);
        ToggleButton button5 = findViewById(R.id.button5);
        ToggleButton button6 = findViewById(R.id.button6);
        ToggleButton button7 = findViewById(R.id.button7);
        ToggleButton button8 = findViewById(R.id.button8);
        ToggleButton button9 = findViewById(R.id.button9);
        ToggleButton button10 = findViewById(R.id.button10);
        ToggleButton button11 = findViewById(R.id.button11);
        ToggleButton button12 = findViewById(R.id.button12);
        boolean[] buttonstate = {button1.isChecked(), button2.isChecked(), button3.isChecked(), button4.isChecked(), button5.isChecked(), button6.isChecked(), button7.isChecked(), button8.isChecked(), button9.isChecked(), button10.isChecked(), button11.isChecked(), button12.isChecked()};
        boolean retval = Arrays.equals(buttonstate, puzzleAnswer);
        if (retval == true) {
            Context context = getApplicationContext();
            CharSequence text = "Hello toast!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Client myClient = new Client(editTextAddress.toString(), editTextPort);
            myClient.execute();

            }


        }
    }


