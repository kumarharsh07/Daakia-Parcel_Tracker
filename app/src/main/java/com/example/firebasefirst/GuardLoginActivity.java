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

public class GuardLoginActivity extends AppCompatActivity {

    private EditText gid;
    private EditText gphone;
    private Button glogin;

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard_login);

        gid     = findViewById(R.id.gid_login);
        gphone  = findViewById(R.id.gphone_login);
        glogin  = findViewById(R.id.g_login);

        auth    = FirebaseAuth.getInstance();

        glogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_gid = gid.getText().toString();
                String txt_phone = gphone.getText().toString();
                String email = txt_gid.toLowerCase() + "@" + "iitg.ac.in";
                guard_login(email, txt_phone);
            }
        });
    }

    private void guard_login(String email, String pass) {
        auth.signInWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(GuardLoginActivity.this, "Guard Login Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(GuardLoginActivity.this, GuardHomePage.class));
                finish();
            }
        });
    }
}