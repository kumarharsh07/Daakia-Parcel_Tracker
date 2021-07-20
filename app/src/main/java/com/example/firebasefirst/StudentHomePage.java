package com.example.firebasefirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentHomePage extends AppCompatActivity {

    private EditText hostel;
    private EditText room;

    private Button check;
    private Button collected;
    private Button get_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);

        hostel      = findViewById(R.id.s_hostel_name);
        room        = findViewById(R.id.s_room_no);
        check       = findViewById(R.id.s_check);
        collected   = findViewById(R.id.s_collected);
        get_details = findViewById(R.id.s_get_details);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hostel_ = hostel.getText().toString().toLowerCase();
                String room_ = room.getText().toString().toLowerCase();
                get_status(hostel_, room_);
            }
        });

        collected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hostel_ = hostel.getText().toString().toLowerCase();
                String room_ = room.getText().toString().toLowerCase();
                change_status(hostel_, room_);
            }
        });
    }

    private void change_status(String hostel, String room) {
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(hostel).child(room).child("Status");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Object status = dataSnapshot.getValue();
                Object present = true;
                if(status == present) {
                    ref.setValue(false);
                    Toast.makeText(StudentHomePage.this, "Parcel Status Changed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(StudentHomePage.this, "No pending parcels to collect!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StudentHomePage.this, "No pending parcels to collect!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void get_status(String hostel, String room) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(hostel).child(room).child("Status");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Object status = dataSnapshot.getValue();
                Object present = true;
                if(status == present) {
                    Toast.makeText(StudentHomePage.this, "Your parcel has arrived, go and collect it!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(StudentHomePage.this, "No pending parcels to collect!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StudentHomePage.this, "Oops! there was an error, please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}