package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class account extends AppCompatActivity {
    TextView a1, a3, a4,a5,a6;


    Button btnA, btnB;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://androiddev-eaabf-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accout);

        Intent intent = getIntent();
        String accountphonepass = intent.getStringExtra("phoneplaceholder");

        a1= (TextView) findViewById(R.id.account_email);
        a3= (TextView) findViewById(R.id.account_phone);
        a4= (TextView) findViewById(R.id.account_institution);
        a5= (TextView) findViewById(R.id.account_password);
        a6= (TextView) findViewById(R.id.account_username);

        showAllUserData();

        btnA = (Button) findViewById(R.id.account_button);
        btnB = (Button) findViewById(R.id.account_refresh_button);

        btnA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(account.this, editaccount.class);

                intent.putExtra("phonepass3", accountphonepass);
                startActivity(intent);
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        final String getpassword = snapshot.child(accountphonepass).child("password").getValue().toString();
                        final String getemail = snapshot.child(accountphonepass).child("email").getValue().toString();
                        final String getinstitution = snapshot.child(accountphonepass).child("institution").getValue().toString();
                        final String getusername = snapshot.child(accountphonepass).child("fullname").getValue().toString();
                        a1.setText(getemail);
                        a3.setText(accountphonepass);
                        a4.setText(getinstitution);
                        a5.setText(getpassword);
                        a6.setText(getusername);
                        Toast.makeText(account.this, "Information Updated", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    private void showAllUserData(){
        Intent intent = getIntent();
        String account_phone_pass = intent.getStringExtra("phoneplaceholder");
        String account_email_pass = intent.getStringExtra("emailplaceholder");
        String account_password_pass = intent.getStringExtra("passwordplaceholder");
        String account_institution_pass = intent.getStringExtra("institutionplaceholder");
        String account_username_pass = intent.getStringExtra("usernameplaceholder");
        a1.setText(account_email_pass);
        a3.setText(account_phone_pass);
        a4.setText(account_institution_pass);
        a5.setText(account_password_pass);
        a6.setText(account_username_pass);
    }
}
