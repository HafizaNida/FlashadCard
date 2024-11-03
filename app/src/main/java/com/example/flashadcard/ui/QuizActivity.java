package com.example.flashadcard.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashadcard.R;
import com.example.flashadcard.data.Flashcard;
import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private TextView questionTextView, scoreTextView;
    private EditText answerInput;
    private Button submitButton, nextQuestionButton;

    private ArrayList<Flashcard> flashcards;
    private int currentCardIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = findViewById(R.id.questionTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        answerInput = findViewById(R.id.answerInput);
        submitButton = findViewById(R.id.submitButton);
        nextQuestionButton = findViewById(R.id.nextQuestionButton);

        // Retrieve the flashcards from the Intent
        flashcards = getIntent().getParcelableArrayListExtra("flashcard");

        if (flashcards != null && !flashcards.isEmpty()) {
            displayCurrentQuestion();
        } else {
            Toast.makeText(this, "No flashcards available!", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if no flashcards are available
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        nextQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });

        updateScore();
    }

    private void displayCurrentQuestion() {
        Flashcard currentCard = flashcards.get(currentCardIndex);
        questionTextView.setText(currentCard.getQuestion());
        answerInput.setText(""); // Clear the input field
    }

    private void checkAnswer() {
        String userAnswer = answerInput.getText().toString().trim();
        Flashcard currentCard = flashcards.get(currentCardIndex);

        if (userAnswer.equalsIgnoreCase(currentCard.getAnswer())) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong answer! The correct answer is: " + currentCard.getAnswer(), Toast.LENGTH_SHORT).show();
        }
        updateScore();
    }

    private void nextQuestion() {
        currentCardIndex = (currentCardIndex + 1) % flashcards.size();
        displayCurrentQuestion();
    }

    private void updateScore() {
        scoreTextView.setText("Score: " + score);
    }
}
