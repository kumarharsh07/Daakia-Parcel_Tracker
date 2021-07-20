package com.example.firebasefirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StudentLoginActivity extends AppCompatActivity {

    private EditText webmail;
    private EditText roll;

    private Button login;

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        webmail     = findViewById(R.id.s_login_email);
        roll        = findViewById(R.id.s_login_roll);
        login       = findViewById(R.id.s_login);

        auth        = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email    = webmail.getText().toString().toLowerCase();
                String pass     = roll.getText().toString();

                login_user(email, pass);
            }
        });
    }

    private void login_user(String webmail, String roll) {
        auth.signInWithEmailAndPassword(webmail, roll).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(StudentLoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(StudentLoginActivity.this, StudentHomePage.class));
                finish();
            }
        });
    }
}