package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R.id;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
    //create object of database reference class to access firebase realtime database

    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://androiddev-eaabf-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText fullname = findViewById(R.id.fullname);
        final EditText email = findViewById(R.id.email);
        final EditText phone = findViewById(R.id.phone);
        final EditText institution = findViewById(R.id.institution);
        final EditText password = findViewById(R.id.password);
        final EditText conPassword = findViewById(R.id.conPassword);

        final Button registerBtn = findViewById(id.registerBtn);
        final TextView loginNowBtn = findViewById(id.loginNow);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullnametxt = fullname.getText().toString();
                final String emailtxt = email.getText().toString();
                final String phonetxt = phone.getText().toString();
                final String passwordtxt = password.getText().toString();
                final String institutiontxt = institution.getText().toString();
                final String conPasswordtxt = conPassword.getText().toString();

                if(fullnametxt.isEmpty() || emailtxt.isEmpty() || phonetxt.isEmpty() || passwordtxt.isEmpty() || conPasswordtxt.isEmpty()){
                    Toast.makeText(Register.this, "please fill all fields", Toast.LENGTH_SHORT).show();
                }

                else if(!passwordtxt.equals(conPasswordtxt)) {
                    Toast.makeText(Register.this, "Passwords are not match", Toast.LENGTH_SHORT).show();
                }

                else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(phonetxt)){
                                Toast.makeText(Register.this, "Phone already registered", Toast.LENGTH_SHORT).show();
                            }

                            else{
                                //send data to firebase
                                databaseReference.child("users").child(phonetxt).child("fullname").setValue(fullnametxt);
                                databaseReference.child("users").child(phonetxt).child("email").setValue(emailtxt);
                                databaseReference.child("users").child(phonetxt).child("password").setValue(passwordtxt);
                                databaseReference.child("users").child(phonetxt).child("institution").setValue(institutiontxt);
                                databaseReference.child("users").child(phonetxt).child("role").setValue("user");
                                databaseReference.child("users").child(phonetxt).child("balance").setValue(0);
                                databaseReference.child("users").child(phonetxt).child("payable").setValue(0);
                                databaseReference.child("users").child(phonetxt).child("Hostel").setValue("");
                                databaseReference.child("users").child(phonetxt).child("HostelUnit").setValue("");
                                databaseReference.child("users").child(phonetxt).child("HostelDue").setValue("");
                                databaseReference.child("users").child(phonetxt).child("complaint").setValue("");
                                databaseReference.child("users").child(phonetxt).child("phone").setValue(phonetxt);
                                Toast.makeText(Register.this, "Phone already registered", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
        loginNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
