package com.example.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Quiz extends AppCompatActivity {
    ImageView prevIcon;
    TextView questionText, questionNumber, totalQuestions;
    RadioGroup optionsGroup;
    RadioButton option1, option2, option3, option4;
    Button btnNext;

    private List<Question> questions;
    private int currentQuestionIndex=0;
    private int correctAnswers=0;
    private String username;
    private final int totalCount=10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
            });

        username= getIntent().getStringExtra("username");
        prevIcon=findViewById(R.id.prev_icon);
        questionText=findViewById(R.id.question_info);
        questionNumber=findViewById(R.id.question_number);
        totalQuestions=findViewById(R.id.total_questions);
        optionsGroup=findViewById(R.id.radioGroup);
        option1=findViewById(R.id.option1);
        option2=findViewById(R.id.option2);
        option3=findViewById(R.id.option3);
        option4=findViewById(R.id.option4);
        btnNext=findViewById(R.id.btnNext);


        loadQuestions();
        displayQuestion();

        prevIcon.setOnClickListener(V -> {
            if (currentQuestionIndex > 1) {
                currentQuestionIndex--;
                displayQuestion();
            }
    });

        btnNext.setOnClickListener(v -> {
            if (checkAnswer()) {  // Only proceed if an option is selected
                currentQuestionIndex++; // Update the question index first

                if (currentQuestionIndex < questions.size()) {
                    displayQuestion(); // Then display the next question
                } else {
                    Intent intent = new Intent(Quiz.this, Result.class);
                    intent.putExtra("username", username);
                    intent.putExtra("score", correctAnswers);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private void loadQuestions(){
        questions = new ArrayList<>();
        questions.add(new Question(
                "In what year did the United States host the FIFA world Cup for the first time?",
                "1986", "1994", "2002", "2010", "1994"));
        questions.add(new Question(
                "What is the capital of Pakistan?",
                "Lahore", "Karachi", "Islamabad", "Peshawar", "Islamabad"));
        questions.add(new Question(
                "Which planet is known as the Red Planet?",
                "Earth", "Mars", "Venus", "Uranus", "Mars"));
        questions.add(new Question(
                "Who wrote 'To Kill a Mockingbird'?",
                "Michael Jordan", "Harper Lee", "J.K. Rowling", "Mark Twin", "Harper Lee"));
        questions.add(new Question(
                "Which is the largest ocean on Earth?",
                "Atlantic", "Indian", "Arctic", "Pacific", "Pacific"));
        questions.add(new Question(
                "What is the chemical symbol for gold?",
                "Ag", "Pb", "Au", "Fe", "Au"));
        questions.add(new Question(
                "How many continents are there on Earth?",
                "5", "6", "7", "8", "7"));
        questions.add(new Question(
                "Who painted the Mona Lisa?",
                "Vincent van Gogh", "Leonardo da Vinci", "Pablo Picasso", "Claude Monet", "Leonardo da Vinci"));
        questions.add(new Question(
                "What is the square root of 64?",
                "7", "8", "6", "9", "8"));
        questions.add(new Question(
                "What is the capital of Japan?",
                "Bangkok", "Seoul", "Beijing", "Tokyo", "Tokyo"));
    }

    private void displayQuestion() {
        Question q= questions.get(currentQuestionIndex);
        questionText.setText(q.getQuestion());
        option1.setText(q.getOption1());
        option2.setText(q.getOption2());
        option3.setText(q.getOption3());
        option4.setText(q.getOption4());


        questionNumber.setText(String.valueOf(currentQuestionIndex + 1));
        prevIcon.setVisibility(currentQuestionIndex > 0 ? View.VISIBLE : View.INVISIBLE);
        btnNext.setText(currentQuestionIndex == questions.size() - 1? "Finish" : "Next");
        optionsGroup.clearCheck();
    }

    private boolean checkAnswer() {
        int selectedId = optionsGroup.getCheckedRadioButtonId();

        if (selectedId == -1) {
            return false; // No option selected, stay on the same question
        }

        RadioButton selectedRadio = findViewById(selectedId);
        String selectedAnswer = selectedRadio.getText().toString();

        if (selectedAnswer.equals(questions.get(currentQuestionIndex).getCorrectAnswer())) {
            correctAnswers++; // Update score if correct
        }


        return true; // Proceed to the next question
    }

    class Question {
        private final String question;
        private final String option1, option2, option3, option4;
        private final String correctAnswer;

        public Question(String question, String option1, String option2, String option3, String option4, String correctAnswer){
            this.question=question;
            this.option1=option1;
            this.option2=option2;
            this.option3=option3;
            this.option4=option4;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestion() {return question;}
        public String getOption1() {return option1;}
        public String getOption2() {return option2;}
        public String getOption3() {return option3;}
        public String getOption4() {return option4;}
        public String getCorrectAnswer() {return correctAnswer;}

    }



    }
