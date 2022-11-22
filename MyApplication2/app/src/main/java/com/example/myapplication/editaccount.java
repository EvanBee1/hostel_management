package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class editaccount extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://androiddev-eaabf-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();

    String placeholder, alertplaceholder, account_phone_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editaccount);

        Intent intent = getIntent();
        account_phone_pass = intent.getStringExtra("phonepass3");

        Button passwordBtn = findViewById(R.id.editaccount_password_button);
        Button emailBtn = findViewById(R.id.editaccount_email_button);
        Button phoneBtn = findViewById(R.id.editaccount_phone_button);
        Button institutionBtn = findViewById(R.id.editaccount_institution_button);

        passwordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                placeholder = "password";
                alertshow();

            }
        });

        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                placeholder = "email";
                alertshow();

            }
        });

        phoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                placeholder = "phone";
                alertshow();

            }
        });

        institutionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                placeholder = "institution";
                alertshow();

            }
        });
    }

    public void alertshow(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Change Information");
        alert.setMessage(placeholder);

        // Set an EditText view to get user input
        EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                // Do something with value!

                databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String data = input.getText().toString();
                        String getOriginalData = snapshot.child(account_phone_pass).child(placeholder).getValue().toString();
                        if(data.equals(getOriginalData)){
                            Toast.makeText(editaccount.this, "Same information inputted", Toast.LENGTH_SHORT).show();
                        }
                        if(data.isEmpty()){
                            Toast.makeText(editaccount.this, "You entered nothing", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            databaseReference.child("users").child(account_phone_pass).child(placeholder).setValue(data);
                            Toast.makeText(editaccount.this, "Information Updated", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        alert.show();
    }

}