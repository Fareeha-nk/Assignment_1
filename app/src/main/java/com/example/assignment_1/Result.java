package com.example.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Result extends AppCompatActivity {


    TextView tvUsername_Result, tvQuestion_Number, tvScore_Separator, tvTotal_Questions;
    Button btnShare_Result;

    private void init3(){
        tvUsername_Result=findViewById(R.id.username_result);
        tvQuestion_Number=findViewById(R.id.question_number);
        tvScore_Separator=findViewById(R.id.score_separator);
        tvTotal_Questions=findViewById(R.id.total_questions);
        btnShare_Result=findViewById(R.id.ShareResult);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init3();

        ActivityResultLauncher<Intent> intent;
        intent=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (result -> {
            if (result.getResultCode() == RESULT_OK) {
                Toast.makeText(this, "Succesful", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Unsuccesful", Toast.LENGTH_SHORT).show();
            }

        }));


        btnShare_Result.setOnClickListener(v ->{
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Your score is: " + tvQuestion_Number.getText().toString().trim());
            intent.launch(shareIntent);
        });


        String username= getIntent().getStringExtra("username");
        tvUsername_Result.setText(username);
        int score= getIntent().getIntExtra("score", 0);
        tvQuestion_Number.setText(String.valueOf(score));


    }
}