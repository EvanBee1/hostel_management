package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R.id;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Complaint extends AppCompatActivity {
    //connect to database
    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://androiddev-eaabf-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);

        //receive from MainActivity
        Intent intent = getIntent();
        String account_phone = intent.getStringExtra("phoneplaceholder");
        //declare
        final EditText complaint = findViewById(R.id.complaint_complaint);
        final Button complaintBtn = findViewById(id.complaintBtn);

        complaintBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //redeclare
                final String complainttxt = complaint.getText().toString();

                //if text box is empty
                if(complainttxt.isEmpty()){
                    Toast.makeText(Complaint.this, "The field is empty", Toast.LENGTH_SHORT).show();
                }

                else{
                    //send to database
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //connect to database
                            final String getComplaint = snapshot.child(account_phone).child("complaint").getValue().toString();
                            //if database not empty
                            if(!getComplaint.isEmpty()){
                                Toast.makeText(Complaint.this, "Complaint Record Found", Toast.LENGTH_SHORT).show();
                                complaint.setText("");
                            }

                            else{
                                //connect to database
                                databaseReference.child("users").child(account_phone).child("complaint").setValue(complainttxt);
                                //if database empty then record into databse
                                Toast.makeText(Complaint.this, "Recorded", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });

    }
}
