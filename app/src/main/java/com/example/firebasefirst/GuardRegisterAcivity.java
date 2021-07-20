package com.example.firebasefirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class GuardRegisterAcivity extends AppCompatActivity {

    private EditText Gid;
    private EditText Gphone;
    private Button Gregister;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard_register_acivity);

        Gid         = findViewById(R.id.GId);
        Gphone      = findViewById(R.id.GPhone);
        Gregister   = findViewById(R.id.GRegister);

        auth = FirebaseAuth.getInstance();

        Gregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_gid = Gid.getText().toString();
                String txt_gphone = Gphone.getText().toString();

                if (TextUtils.isEmpty(txt_gid) || TextUtils.isEmpty(txt_gphone)) {
                    Toast.makeText(GuardRegisterAcivity.this, "Empty Credentials! Please fill the details", Toast.LENGTH_SHORT).show();
                } else if (txt_gphone.length() != 10) {
                    Toast.makeText(GuardRegisterAcivity.this, "Enter a valid phone number", Toast.LENGTH_SHORT).show();
                } else {
                    register(txt_gid, txt_gphone);
                }
            }
        });

    }

    private void register(String gid, String gphone) {
        String email = gid.toLowerCase() + "@" + "iitg.ac.in";
        auth.createUserWithEmailAndPassword(email, gphone).addOnCompleteListener(GuardRegisterAcivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(GuardRegisterAcivity.this, "Guard Registration Successful!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GuardRegisterAcivity.this, GuardHomePage.class));
                    finish();
                } else {
                    Toast.makeText(GuardRegisterAcivity.this, "Registration Failed! Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}