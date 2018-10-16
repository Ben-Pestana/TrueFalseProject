package com.example.truefalseproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity{
    @Override
    public String toString() {
        return "MainActivity{" +
                "trueButton=" + trueButton +
                ", falseButton=" + falseButton +
                ", question=" + question +
                ", questionNum=" + questionNum +
                ", quiz=" + quiz +
                '}';
    }

    private Button trueButton;
    private Button falseButton;
    private TextView question;
    private TextView questionNum;
    private Quiz quiz;
    private TextView score;

    private int stuff = 0;

    public static final String TAG = "MainActivity";

    //Gets questions from the json doc

    //String stream = readTextFile(XmlFileInputStream);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            wireWidgets();
            setOnClickListener();
            InputStream XmlFileInputStream = getResources().openRawResource(R.raw.questions); // getting XML

            String jsonString = readTextFile(XmlFileInputStream);
            Log.d(TAG, "onCreate" + jsonString);

            Gson gson = new Gson();
            Question[] questions = gson.fromJson(jsonString, Question[].class);

            quiz = new Quiz(Arrays.asList(questions));
            updateDisplay();
    }

    private void setOnClickListener() {
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quiz.isThereAnotherQuestion() == true) {
                    if (quiz.getQuestion().getAnswer() == true) {
                        quiz.addOneToScore();
                        Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                        updateDisplay();
                    } else if (quiz.getQuestion().getAnswer() == false) {
                        Toast.makeText(MainActivity.this, "WRONG!", Toast.LENGTH_SHORT).show();
                        updateDisplay();
                    } else {
                        updateDisplay();
                    }
                }
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quiz.isThereAnotherQuestion() == true) {
                    if (quiz.getQuestion().getAnswer() == false) {
                        quiz.addOneToScore();
                        Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                        updateDisplay();
                    }
                    else if (quiz.getQuestion().getAnswer() == true) {
                        Toast.makeText(MainActivity.this, "WRONG!", Toast.LENGTH_SHORT).show();
                        updateDisplay();
                    }
                    else {
                        updateDisplay();
                    }
                }
            }
        });
    }

    private void wireWidgets() {
        trueButton = findViewById(R.id.button_main_true);
        falseButton = findViewById(R.id.button_main_false);
        question = findViewById(R.id.textView_main_questionText);
        questionNum = findViewById(R.id.textView_main_questioNum);
        score = findViewById(R.id.textView_main_score);
    }

    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }

    private void updateDisplay() {
        if (quiz.isThereAnotherQuestion() == true) {
            question.setText(quiz.getNextQuestion().getQuestionQ());
            questionNum.setText(quiz.getQuestionNum() + "");
            score.setText(quiz.getScore() + "");
        }

    }


}
