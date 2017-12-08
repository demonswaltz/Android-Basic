package com.demonswaltz.deweyquiz;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by knits4 on 11/25/17.
 */

public class DeweyOne extends Activity{

    int score = 0;
    int attempts = 0;
    int questionNum = 1;
    String colorButt1 = "#D3D3D3";
    String colorButt2 = "#D3D3D3";
    String colorButt3 = "#D3D3D3";
    String colorButt4 = "#D3D3D3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                setContentView(R.layout.deweyone);
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                setContentView(R.layout.dewyonelandscape);
                break;
        }
        MainGameOne();

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("Score", score);
        savedInstanceState.putInt("QuestionNumber", questionNum);
        savedInstanceState.putInt("Attempts", attempts);
        savedInstanceState.putString("ButtonColor1", colorButt1);
        savedInstanceState.putString("ButtonColor2", colorButt2);
        savedInstanceState.putString("ButtonColor3", colorButt3);
        savedInstanceState.putString("ButtonColor4", colorButt4);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        switch (getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                setContentView(R.layout.deweyone);
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                setContentView(R.layout.dewyonelandscape);
                break;
        }
        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("Score");
            questionNum = savedInstanceState.getInt("QuestionNumber");
            attempts = savedInstanceState.getInt("Attempts");
            colorButt1 = savedInstanceState.getString("ButtonColor1");
            colorButt2 = savedInstanceState.getString("ButtonColor2");
            colorButt3 = savedInstanceState.getString("ButtonColor3");
            colorButt4 = savedInstanceState.getString("ButtonColor4");
            super.onRestoreInstanceState(savedInstanceState);
        } else{
            score = 0;
            questionNum = 1;
            attempts = 0;
        }
        MainGameOne();
        displayScore();
    }
    /**
     * Contains all the questions for DeweyOne Quiz Game
     */
    public void MainGameOne() {

        if (questionNum == 1) {
            questionSetup("811.54", getString(R.string.poetry), true, getString(R.string.cats), false, getString(R.string.trains), false, getString(R.string.folkLore), false);
        }
        if (questionNum == 2) {
            questionSetup("636", getString(R.string.civilWar), false, getString(R.string.trains), false, getString(R.string.cats), true, getString(R.string.gators), false);
        }
        if (questionNum == 3) {
            questionSetup("523", getString(R.string.folkLore), false, getString(R.string.planets), true, getString(R.string.gators), false, getString(R.string.dentist), false);
        }
        if (questionNum == 4) {
            questionSetup("398.2", getString(R.string.folkLore), true, getString(R.string.trains), false, getString(R.string.dentist), false, getString(R.string.origami), false);
        }
        if (questionNum == 5) {
            questionSetup("625.2", getString(R.string.cats), false, getString(R.string.dentist), false, getString(R.string.trains), true, getString(R.string.civilWar), false);
        }
        if (questionNum == 6) {
            questionSetup("468", getString(R.string.poetry), false, getString(R.string.spanish), true, getString(R.string.folkLore), false, getString(R.string.origami), false);
        }
        if (questionNum == 7) {
            questionSetup("617.6", getString(R.string.dentist), true, getString(R.string.gators), false, getString(R.string.cats), false, getString(R.string.trains), false);
        }
        if (questionNum == 8) {
            questionSetup("736.982", getString(R.string.civilWar), false, getString(R.string.poetry), false, getString(R.string.origami), true, getString(R.string.folkLore), false);
        }
        if (questionNum == 9) {
            questionSetup("973.7", getString(R.string.gators), false, getString(R.string.cats), false, getString(R.string.trains), false, getString(R.string.civilWar), true);
        }
        if (questionNum == 10) {
            questionSetup("597.98", getString(R.string.planets), false, getString(R.string.gators), true, getString(R.string.spanish), false, getString(R.string.dentist), false);
        }
        if (questionNum == 11) {
            allDone();
        }
    }

    /**
     * Takes the question data and lays out the screen
     * @param question
     * @param a1
     * @param isright1
     * @param a2
     * @param isright2
     * @param a3
     * @param isright3
     * @param a4
     * @param isright4
     */
    public void questionSetup(String question, String a1, Boolean isright1, String a2, Boolean isright2, String a3, Boolean isright3, String a4, Boolean isright4) {
        TextView questionText = findViewById(R.id.question_text_view);
        questionText.setText(question);
        Button button1 = findViewById(R.id.button1);
        button1.setBackgroundColor(Color.parseColor(colorButt1));
        button1.setText(a1);
        Button button2 = findViewById(R.id.button2);
        button2.setBackgroundColor(Color.parseColor(colorButt2));
        button2.setText(a2);
        Button button3 = findViewById(R.id.button3);
        button3.setBackgroundColor(Color.parseColor(colorButt3));
        button3.setText(a3);
        Button button4 = findViewById(R.id.button4);
        button4.setBackgroundColor(Color.parseColor(colorButt4));
        button4.setText(a4);
        buttonSetup(isright1, isright2, isright3, isright4);
    }

    /**
     * Sets listeners based on boolean input for answers (tells the buttons if they are right or wrong)
     */
    public void buttonSetup(Boolean isright1, Boolean isright2, Boolean isright3, Boolean isright4) {
        final Button button1 = findViewById(R.id.button1);
        final Button button2 = findViewById(R.id.button2);
        final Button button3 = findViewById(R.id.button3);
        final Button button4 = findViewById(R.id.button4);
        if (isright1 == false) {
            button1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrongAnswer(button1, 1);
                }
            });

        } else if (isright1 == true) {
            button1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    updateScore();
                }
            });
        }

        if (isright2 == false) {
            button2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrongAnswer(button2, 2);
                }
            });

        } else if (isright2 == true) {
            button2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    updateScore();
                }
            });
        }

        if (isright3 == false) {
            button3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrongAnswer(button3, 3);
                }
            });

        } else if (isright3 == true) {
            button3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    updateScore();
                }
            });
        }
        if (isright4 == false) {
            button4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrongAnswer(button4, 4);
                }
            });

        } else if (isright4 == true) {
            button4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    updateScore();
                }
            });
        }
    }

    /**
     * Provides the functionality needed for buttons that are wrong answers
     * @param view
     */
    private void wrongAnswer(View view, int buttonNum) {
        attempts += 1;
        view.setBackgroundColor(Color.parseColor("#FF0000"));
        switch (buttonNum){
            case 1: colorButt1 = "#FF0000";
                break;
            case 2: colorButt2 = "#FF0000";
                break;
            case 3: colorButt3 = "#FF0000";
                break;
            case 4: colorButt4 = "#FF0000";
        }
    }

    /**
     * This method checks how many attempts it took to answer the question and updates the score appropriately
     * 10 points are awarded for 1 attempt
     * 5 points are awarded for 2 attempts
     * and 1 point for 3 attempts (because you're really just guessing, aren't you?)
     */
    public void updateScore() {
        if (attempts == 0) {
            score += 10;
        } else if (attempts == 1) {
            score += 5;
        } else if (attempts == 2) {
            score += 1;
        }
        displayScore();
        nextQuestion();

    }

    /**
     * updates the score display and returns to MainGameOne to load the next question
     */
    private void displayScore() {
        String scoreString = Integer.toString(score);
        TextView displayScore = findViewById(R.id.score_text_view);
        displayScore.setText(scoreString);
    }

    private void nextQuestion(){
        questionNum++;
        attempts = 0;
        colorButt1 = "#D3D3D3";
        colorButt2 = "#D3D3D3";
        colorButt3 = "#D3D3D3";
        colorButt4 = "#D3D3D3";
        MainGameOne();
    }
    /**
     * Removes buttons to show final score and returns to main menu to pick another game
     */
    private void allDone() {
        Button button1 = findViewById(R.id.button1);
        ViewGroup layout1 = (ViewGroup) button1.getParent();
        if (null != layout1) //for safety only  as you are doing onClick
            layout1.removeView(button1);
        Button button2 = findViewById(R.id.button2);
        ViewGroup layout2 = (ViewGroup) button2.getParent();
        if (null != layout2) //for safety only  as you are doing onClick
            layout2.removeView(button2);
        Button button3 = findViewById(R.id.button3);
        ViewGroup layout3 = (ViewGroup) button3.getParent();
        if (null != layout3) //for safety only  as you are doing onClick
            layout3.removeView(button3);
        final Button button4 = findViewById(R.id.button4);
        button4.setBackgroundColor(Color.parseColor("#551A8B"));
        button4.setTextColor(Color.parseColor("#FFFFFF"));
        button4.setText(getString(R.string.yourScore) + " " + score + "/100");
        TextView questionText = findViewById(R.id.question_text_view);
        questionText.setText(R.string.yay);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                questionNum = 1;
                score = 0;
                colorButt1 = "#D3D3D3";
                colorButt2 = "#D3D3D3";
                colorButt3 = "#D3D3D3";
                colorButt4 = "#D3D3D3";
                returnToMain();
            }
        });
    }
    private void returnToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}




