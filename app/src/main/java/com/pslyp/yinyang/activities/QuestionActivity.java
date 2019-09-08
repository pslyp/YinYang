package com.pslyp.yinyang.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pslyp.yinyang.R;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button backButton, nextButton, submitButton;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private TextView questionText;

    private ArrayList<String> questionArray;
    private int Score[];

    private int indexQuestion = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        initInstance();
        initQuestion();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button :
                backQuestion();
                break;
            case R.id.next_button :
                nextQuestion();
                break;
        }
    }

    private void initInstance() {
        radioGroup = findViewById(R.id.radio_group);
        questionText = findViewById(R.id.question_text_view);
        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(this);
        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(this);
        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(this);
    }

    private void initQuestion() {
        questionArray = new ArrayList<>();

        questionArray.add(null);
        questionArray.add("ข้อนี้ตอบ 1");
        questionArray.add("ข้อนี้ตอบ 3");
        questionArray.add("ข้อนี้ตอบ 5");
        questionArray.add("ข้อนี้ตอบ 2");
        questionArray.add("ข้อนี้ตอบ 4");

        Score = new int[questionArray.size()];

        setQuestion();
    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        nextButton.setEnabled(true);

        Toast.makeText(this, String.valueOf(radioButton.isChecked()), Toast.LENGTH_SHORT).show();
    }

    private void setQuestion() {
        questionText.setText(indexQuestion + ". " + questionArray.get(indexQuestion));
        backButton.setVisibility(View.GONE);
        nextButton.setEnabled(false);
        submitButton.setVisibility(View.GONE);

        setButton(0);
    }

    private void setAnswer(int score) {
        Log.e("Set Answer Score", String.valueOf(score));
        switch (score) {
            case 1 :
                radioButton = findViewById(R.id.one_radio_button);
                radioButton.setChecked(true);
                break;
            case 2 :
                radioButton = findViewById(R.id.two_radio_button);
                radioButton.setChecked(true);
                break;
            case 3 :
                radioButton = findViewById(R.id.three_radio_button);
                radioButton.setChecked(true);
                break;
            case 4 :
                radioButton = findViewById(R.id.four_radio_button);
                radioButton.setChecked(true);
                break;
            case 5 :
                radioButton = findViewById(R.id.five_radio_button);
                radioButton.setChecked(true);
                break;
        }
    }

    private void setButton(int number) {
        //0 >> first page
        //1 >> back&next page
        //2 >> back&next page have answer
        //3 >> last page
        //4 >> last page have answer
        switch (number) {
            case 0 :
                backButton.setVisibility(View.GONE);
                nextButton.setEnabled(false);
                break;
            case 1 :
                backButton.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.VISIBLE);
                nextButton.setEnabled(false);
                submitButton.setVisibility(View.GONE);
                break;
            case 2 :
                nextButton.setEnabled(true);
                submitButton.setVisibility(View.GONE);
                break;
            case 3 :
                backButton.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.GONE);
                submitButton.setVisibility(View.VISIBLE);
                submitButton.setEnabled(false);
                break;
            case 4 :
                submitButton.setEnabled(true);
                break;
        }
    }

    private void setScore() {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        Score[indexQuestion] = Integer.parseInt(radioButton.getText().toString());

        Log.e("Set score", String.valueOf(indexQuestion) + " " + String.valueOf(Score[indexQuestion]));
    }

    private int getScore(int index) {
        return Score[index];
    }

    private void backQuestion() {
        if(indexQuestion != 1)
            indexQuestion--;
        if(indexQuestion == 1)
            setButton(0);
        else
            setButton(1);

        questionText.setText(indexQuestion + ". " + questionArray.get(indexQuestion));

        int score = getScore(indexQuestion);
        if(score > 0) {
            setAnswer(score);
            setButton(2);
        }

//        Log.e("back point answer: ", String.valueOf(Score[indexQuestion]));
    }

    private void nextQuestion() {
        setScore();

        if(indexQuestion < questionArray.size()-1)
            indexQuestion++;

        if(indexQuestion == questionArray.size()-1)
            setButton(3);
        else
            setButton(1);

        questionText.setText(indexQuestion + ". " + questionArray.get(indexQuestion));

        int score = getScore(indexQuestion);
        if(score > 0) {
            setAnswer(score);
            setButton(2);
        } else
            radioGroup.clearCheck();
    }

    private int checkPoint(RadioButton radioId) {
        switch (radioId.getId()) {
            case R.id.one_radio_button :
                return 1;
//            case
//            case
//            case
//            case
        }
        return 0;
    }

}
