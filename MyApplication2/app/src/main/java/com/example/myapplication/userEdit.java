package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class userEdit extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://androiddev-eaabf-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();

    String account_phone_pass,account_username_pass, placeholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);

        Intent intent = getIntent();
        account_phone_pass = intent.getStringExtra("phonepass");
        account_username_pass = intent.getStringExtra("usernamepass");
        TextView usernametxt = findViewById(R.id.edituser_username_label);
        usernametxt.setText(account_username_pass);

        Button hotelBtn = findViewById(R.id.edituser_hostel_button);
        Button hoteldueBtn = findViewById(R.id.edituser_hosteldue_button);
        Button hotelunitBtn = findViewById(R.id.edituser_hostelunit_button);
        Button passwordBtn = findViewById(R.id.edituser_password_button);
        Button balanceBtn = findViewById(R.id.edituser_balance_button);
        Button payableBtn = findViewById(R.id.edituser_payable_button);
        Button emailBtn = findViewById(R.id.edituser_email_button);
        Button fullnameBtn = findViewById(R.id.edituser_fullname_button);
        Button institutionBtn = findViewById(R.id.edituser_institution_button);
        Button roleBtn = findViewById(R.id.edituser_role_button);

        hotelBtn.setOnClickListener(new View.OnClickListener() {//gave value to placeholder so the popup can open with the placeholder value set
            @Override
            public void onClick(View v) {
                placeholder = "Hostel";
                alertshow();
            }
        });
        hoteldueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeholder = "HostelDue";
                alertshow();
            }
        });
        hotelunitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeholder = "HostelUnit";
                alertshow();
            }
        });
        passwordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeholder = "password";
                alertshow();
            }
        });
        balanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeholder = "balance";
                alertshow();
            }
        });
        payableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeholder = "payable";
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
        fullnameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeholder = "fullname";
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
        roleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeholder = "role";
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
                            Toast.makeText(userEdit.this, "Same information inputted", Toast.LENGTH_SHORT).show();
                        }
                        if(data.isEmpty()){
                            Toast.makeText(userEdit.this, "You entered nothing", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            databaseReference.child("users").child(account_phone_pass).child(placeholder).setValue(data);
                            Toast.makeText(userEdit.this, "Information Updated", Toast.LENGTH_SHORT).show();
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
