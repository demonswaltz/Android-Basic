package com.demonswaltz.userprofile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView nameTextView = findViewById(R.id.name);
        TextView birthdayTextView = findViewById(R.id.birthday);
        TextView countryTextView = findViewById(R.id.country);
        ImageView profilePic = findViewById(R.id.profile_picture);

        nameTextView.setText("Jess");
        nameTextView.setTextSize(28);
        birthdayTextView.setText("September 27th");
        countryTextView.setText("Jeg er fra USA");
        profilePic.setImageResource(R.drawable.profilepic);
    }
}
