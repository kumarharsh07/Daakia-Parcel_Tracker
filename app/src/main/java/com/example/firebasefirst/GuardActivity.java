package com.example.firebasefirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GuardActivity extends AppCompatActivity {

    private Button gregister;
    private Button glogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard);

        gregister    = findViewById(R.id.Gregister);
        glogin       = findViewById(R.id.Glogin);

        gregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuardActivity.this, GuardRegisterAcivity.class));
            }
        });

        glogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuardActivity.this, GuardLoginActivity.class));
            }
        });

    }
}