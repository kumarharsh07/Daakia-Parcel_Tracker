package com.example.firebasefirst;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;

public class GuardHomePage extends AppCompatActivity {

    private Button logout;
    private Button add_parcel;
    private Button exit;

    private EditText room_no;
    private EditText hostel;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard_home_page);

        logout      = findViewById(R.id.g_logout);
        add_parcel  = findViewById(R.id.g_add_parcel);
        exit        = findViewById(R.id.g_exit);
        room_no     = findViewById(R.id.g_parcel_room);
        hostel      = findViewById(R.id.g_hostel_name);

        auth = FirebaseAuth.getInstance();
        final String email = auth.getCurrentUser().getEmail();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(GuardHomePage.this, "You have been successfully logged out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(GuardHomePage.this, GuardLoginActivity.class));
                finish();
            }
        });

        add_parcel.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(final View view) {
                String hostel_name = hostel.getText().toString().toLowerCase();
                String room = room_no.getText().toString().toLowerCase();

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();

                HashMap<String, Object> obj = new HashMap<>();
                obj.put("Status", true);
                obj.put("Guard ID", email);
                obj.put("Time", formatter.format(date));

                FirebaseDatabase.getInstance().getReference().child(hostel_name).child(room).setValue(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(GuardHomePage.this, "Parcel added successfully!", Toast.LENGTH_SHORT).show();
                        hostel.setText("");
                        room_no.setText("");
                    }
                });
            }
        });

    }
}