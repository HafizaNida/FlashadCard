package com.example.flashadcard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashadcard.data.Flashcard;
import com.example.flashadcard.ui.QuizActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText questionInput, answerInput;
    private TextView scoreDisplay;
    private Button addFlashcardButton, showAnswerButton, nextQuestionButton, quizButton;
    private ArrayList<Flashcard> flashcards;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionInput = findViewById(R.id.questionInput);
        answerInput = findViewById(R.id.answerInput);
        scoreDisplay = findViewById(R.id.scoreDisplay);
        addFlashcardButton = findViewById(R.id.addFlashcardButton);


        quizButton = findViewById(R.id.quizButton);

        flashcards = new ArrayList<>();

        addFlashcardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFlashcard();
            }
        });


        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

        updateScore();
    }

    private void addFlashcard() {
        String question = questionInput.getText().toString().trim();
        String answer = answerInput.getText().toString().trim();

        if (!question.isEmpty() && !answer.isEmpty()) {
            Flashcard flashcard = new Flashcard(question, answer);
            flashcards.add(flashcard);
            questionInput.setText("");
            answerInput.setText("");
            Toast.makeText(this, "Flashcard added!", Toast.LENGTH_SHORT).show();
            updateScore(); // Optionally update score or flashcard count here
        } else {
            Toast.makeText(this, "Please enter both question and answer!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showAnswer() {
        // Implement showing the answer logic
    }

    private void nextQuestion() {
        // Implement next question logic if needed
    }

    private void startQuiz() {
        if (!flashcards.isEmpty()) {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putParcelableArrayListExtra("flashcard", flashcards);
            startActivity(intent);
        } else {
            Toast.makeText(this, "No flashcards available!", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateScore() {
        // Display the number of flashcards or any other score logic
        scoreDisplay.setText("Total Flashcards: " + flashcards.size());
    }
}
