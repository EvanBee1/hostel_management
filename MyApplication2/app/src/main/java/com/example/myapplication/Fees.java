package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
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

public class Fees extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://androiddev-eaabf-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees);

        Intent intent = getIntent();
        String account_phone = intent.getStringExtra("phoneplaceholder");
        String account_payable = intent.getStringExtra("payableplaceholder");
        String account_balance = intent.getStringExtra("balanceplaceholder");

        final Button a1 = findViewById(R.id.fee_check);
        final Button a2 = findViewById(R.id.fee_topup);
        final Button a3 = findViewById(R.id.fee_pay);
        final TextView a4 = findViewById(R.id.fees_fee);
        final TextView a5 = findViewById(R.id.fees_fee2);

        a4.setText(account_balance);
        a5.setText(account_payable);

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("users").child(account_phone).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String payable = snapshot.child("payable").getValue().toString();
                        String balance = snapshot.child("balance").getValue().toString();
                        a4.setText(payable);
                        a5.setText(balance);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });

        Dialog mDialog = new Dialog(this);

        a2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                databaseReference.child("users").child(account_phone).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Intent i = new Intent(Fees.this,topup.class);
                        i.putExtra("phonepass", account_phone);

                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("users").child(account_phone).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Intent i = new Intent(Fees.this,Pay.class);
                        i.putExtra("phonepass", account_phone);
                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}