package com.demonswaltz.deweyquiz;

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
    int score = 0;
    int attempts = 0;
    int questionNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainGame();
    }

    public void mainGame() {
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

    public void questionSetup(String question, String a1, Boolean isright1, String a2, Boolean isright2, String a3, Boolean isright3, String a4, Boolean isright4) {
        TextView questionText = findViewById(R.id.question_text_view);
        questionText.setText(question);
        Button button1 = findViewById(R.id.button1);
        button1.setBackgroundColor(Color.parseColor("#D3D3D3"));
        button1.setText(a1);
        Button button2 = findViewById(R.id.button2);
        button2.setBackgroundColor(Color.parseColor("#D3D3D3"));
        button2.setText(a2);
        Button button3 = findViewById(R.id.button3);
        button3.setBackgroundColor(Color.parseColor("#D3D3D3"));
        button3.setText(a3);
        Button button4 = findViewById(R.id.button4);
        button4.setBackgroundColor(Color.parseColor("#D3D3D3"));
        button4.setText(a4);
        buttonSetup(isright1, isright2, isright3, isright4);
    }

    /**
     * Sets listeners based on boolean input for method
     */
    public void buttonSetup(Boolean isright1, Boolean isright2, Boolean isright3, Boolean isright4) {
        final Button button1 = findViewById(R.id.button1);
        final Button button2 = findViewById(R.id.button2);
        final Button button3 = findViewById(R.id.button3);
        final Button button4 = findViewById(R.id.button4);
        if (isright1 == false) {
            button1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    wrongAnswer(button1);
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
                    wrongAnswer(button2);
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
                    wrongAnswer(button3);
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
                    wrongAnswer(button4);
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


    private void wrongAnswer(View view) {
        attempts += 1;
        view.setBackgroundColor(Color.parseColor("#FF0000"));
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

    }

    private void displayScore() {
        String scoreString = Integer.toString(score);
        TextView displayScore = findViewById(R.id.score_text_view);
        displayScore.setText(scoreString);
        questionNum++;
        attempts = 0;
        Log.v("displayScore", "this is the question number: " + questionNum);
        mainGame();
    }

    private void allDone(){
        Button button1 = findViewById(R.id.button1);
        ViewGroup layout1 = (ViewGroup) button1.getParent();
        if(null!=layout1) //for safety only  as you are doing onClick
            layout1.removeView(button1);
        Button button2 = findViewById(R.id.button2);
        ViewGroup layout2 = (ViewGroup) button2.getParent();
        if(null!=layout2) //for safety only  as you are doing onClick
            layout2.removeView(button2);
        Button button3 = findViewById(R.id.button3);
        ViewGroup layout3 = (ViewGroup) button3.getParent();
        if(null!=layout3) //for safety only  as you are doing onClick
            layout3.removeView(button3);
        Button button4 = findViewById(R.id.button4);
        button4.setBackgroundColor(Color.parseColor("#551A8B"));
        button4.setTextColor(Color.parseColor("#FFFFFF"));
        button4.setText(getString(R.string.yourScore)+ " " + score+"/100");
        TextView questionText = findViewById(R.id.question_text_view);
        questionText.setText(R.string.yay);
    }
}
