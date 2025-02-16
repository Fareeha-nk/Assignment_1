package com.example.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    Button start_button;
    EditText login_placeholder;

    private void init2(){
        start_button=findViewById(R.id.btnStart);
        login_placeholder=findViewById(R.id.login_name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        init2();

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username= login_placeholder.getText().toString().trim();

                if (username.length() < 3 || username.length() > 20 ){
                    Toast.makeText(Login.this, "Username must be between 3 and 20 length", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent i= new Intent(Login.this, Quiz.class);
                    i.putExtra("username", username);
                    startActivity(i);
            }
        });

    }
}