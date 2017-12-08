package com.demonswaltz.deweyquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by knits4 on 11/25/17.
 */

public class DeweyTwo extends Activity {
    int questionNum = 1;
    int score = 0;
    Boolean buttonStat1;
    Boolean buttonStat2;
    Boolean buttonStat3;
    Boolean buttonStat4;
    String textAnswer;
    boolean answers[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainGameOne();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putInt("QuestionNumber", questionNum);
        savedInstanceState.putInt("score", score);
        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            // Restore UI state from the savedInstanceState.
            questionNum = savedInstanceState.getInt("QuestionNumber");
            score = savedInstanceState.getInt("score");
            MainGameOne();
        }
    }

    public void MainGameOne() {

        if (questionNum == 1) {
            radioQuestion("811.54", getString(R.string.poetry), true, getString(R.string.cats), false, getString(R.string.trains), false, getString(R.string.folkLore), false);
        }
        if (questionNum == 2) {
            textQuestion(getString(R.string.year_published), "1876");
        }
        if (questionNum == 3) {
            checkboxQuestion(getString(R.string.animals590), getString(R.string.folkLore), false, getString(R.string.planets), false, getString(R.string.gators), true, getString(R.string.wolves), true, getString(R.string.spanish), false, getString(R.string.lemurs), true);
        }
        if (questionNum == 4) {
            radioQuestion("398.2", getString(R.string.folkLore), true, getString(R.string.trains), false, getString(R.string.dentist), false, getString(R.string.origami), false);
        }
        if (questionNum == 5) {
            radioQuestion("617.6", getString(R.string.dentist), true, getString(R.string.gators), false, getString(R.string.cats), false, getString(R.string.trains), false);
        }
        if (questionNum == 6) {
            radioQuestion("468", getString(R.string.poetry), false, getString(R.string.spanish), true, getString(R.string.folkLore), false, getString(R.string.origami), false);
        }
        if (questionNum == 7) {
            checkboxQuestion(getString(R.string.sciencebooks), "100's", false, "200s", false, "300s", true, "400s", false, "500s", true, "600s", true);
        }
        if (questionNum == 8) {
            radioQuestion("736.982", getString(R.string.civilWar), false, getString(R.string.poetry), false, getString(R.string.origami), true, getString(R.string.folkLore), false);
        }
        if (questionNum == 9) {
            textQuestion(getString(R.string.first_name), "Melvil");
        }
        if (questionNum == 10) {
            radioQuestion("625.2", getString(R.string.cats), false, getString(R.string.dentist), false, getString(R.string.trains), true, getString(R.string.civilWar), false);
        }
        if (questionNum == 11) {
            allDone();
        }
    }

    public void radioQuestion(String question, String a1, Boolean isright1, String a2, Boolean isright2, String a3, Boolean isright3, String a4, Boolean isright4) {
        setContentView(R.layout.radio_layout);

        TextView questionText = findViewById(R.id.question_text_view);
        questionText.setText(question);
        Button button1 = findViewById(R.id.button1);
        button1.setText(a1);
        Button button2 = findViewById(R.id.button2);
        button2.setText(a2);
        Button button3 = findViewById(R.id.button3);
        button3.setText(a3);
        Button button4 = findViewById(R.id.button4);
        button4.setText(a4);
        buttonStat1 = isright1;
        buttonStat2 = isright2;
        buttonStat3 = isright3;
        buttonStat4 = isright4;
    }

    public void checkRadio(View view) {
        RadioButton button1 = findViewById(R.id.button1);
        boolean checked1 = (button1).isChecked();
        RadioButton button2 = findViewById(R.id.button2);
        boolean checked2 = (button2).isChecked();
        RadioButton button3 = findViewById(R.id.button3);
        boolean checked3 = (button3).isChecked();
        RadioButton button4 = findViewById(R.id.button4);
        boolean checked4 = (button4).isChecked();
        if (checked1 && buttonStat1) {
            rightAnswer();
        } else if (checked2 && buttonStat2) {
            rightAnswer();
        } else if (checked3 && buttonStat3) {
            rightAnswer();
        } else if (checked4 && buttonStat4) {
            rightAnswer();
        } else {
            wrongAnswer();
        }
    }

    private void wrongAnswer() {
        Context context = getApplicationContext();
        CharSequence text = getString(R.string.nope) + " " + getString(R.string.yourScore) + " " + score;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        questionNum++;
        MainGameOne();
    }

    public void rightAnswer() {
        score++;
        Context context = getApplicationContext();
        CharSequence text = getString(R.string.right_answer) + " " + getString(R.string.yourScore) + " " + score;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        questionNum++;
        MainGameOne();
    }

    public void textQuestion(String question, String answer) {
        setContentView(R.layout.text_layout);
        TextView questionText = findViewById(R.id.question_text_view);
        questionText.setText(question);
        questionText.setTextSize(24);
        textAnswer = answer;
    }

    public void checkText(View view) {
        EditText editText = findViewById(R.id.edit_text_view);
        String userAnswer = editText.getText().toString();

        if ((textAnswer).equals(userAnswer)) {
            rightAnswer();
        } else {
            wrongAnswer();
        }
    }

    public void checkboxQuestion(String question, String a1, boolean ischecked1, String a2, boolean ischecked2, String a3, boolean ischecked3, String a4, boolean ischecked4, String a5, boolean ischecked5, String a6, boolean ischecked6) {
        setContentView(R.layout.checkbox_layout);
        TextView questionText = findViewById(R.id.question_text_view);
        questionText.setText(question);
        questionText.setTextSize(24);
        Button button1 = findViewById(R.id.button1);
        button1.setText(a1);
        Button button2 = findViewById(R.id.button2);
        button2.setText(a2);
        Button button3 = findViewById(R.id.button3);
        button3.setText(a3);
        Button button4 = findViewById(R.id.button4);
        button4.setText(a4);
        Button button5 = findViewById(R.id.button5);
        button5.setText(a5);
        Button button6 = findViewById(R.id.button6);
        button6.setText(a6);
        answers = new boolean[6];
        answers[0] = ischecked1;
        answers[1] = ischecked2;
        answers[2] = ischecked3;
        answers[3] = ischecked4;
        answers[4] = ischecked5;
        answers[5] = ischecked6;
    }

    public void checkCheckbox(View view) {
        CheckBox uanswer1 = findViewById(R.id.button1);
        CheckBox uanswer2 = findViewById(R.id.button2);
        CheckBox uanswer3 = findViewById(R.id.button3);
        CheckBox uanswer4 = findViewById(R.id.button4);
        CheckBox uanswer5 = findViewById(R.id.button5);
        CheckBox uanswer6 = findViewById(R.id.button6);
        boolean userAnswers[] = {uanswer1.isChecked(), uanswer2.isChecked(), uanswer3.isChecked(), uanswer4.isChecked(), uanswer5.isChecked(), uanswer6.isChecked()};
        boolean retval = Arrays.equals(userAnswers, answers);
        if (retval == true) {
            rightAnswer();
        } else {
            wrongAnswer();
        }

    }

    private void allDone() {
        setContentView(R.layout.text_layout);
        EditText editView = findViewById(R.id.edit_text_view);
        ViewGroup layout1 = (ViewGroup) editView.getParent();
        if (null != layout1) //for safety only  as you are doing onClick
            layout1.removeView(editView);
        final Button submitButton = findViewById(R.id.submit_button);
        submitButton.setBackgroundColor(Color.parseColor("#551A8B"));
        submitButton.setTextSize(36);
        submitButton.setTextColor(Color.parseColor("#FFFFFF"));
        submitButton.setText(getString(R.string.yourScore) + " " + score);
        TextView questionText = findViewById(R.id.question_text_view);
        questionText.setTextSize(48);
        questionText.setText(R.string.yay);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                questionNum = 1;
                returnToMain();

            }
        });
    }

    private void returnToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}