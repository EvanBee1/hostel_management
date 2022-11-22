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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Pay extends AppCompatActivity {


    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://androiddev-eaabf-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        Intent i = getIntent();
        String account = i.getStringExtra("phonepass");

        EditText paytxt = findViewById(R.id.pay_amount);
        Button payBtn = findViewById(R.id.pay_pay);

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.child("users").child(account).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String payable = snapshot.child("payable").getValue().toString();
                        String balance = snapshot.child("balance").getValue().toString();

                        String pay = paytxt.getText().toString();
                        float getpay = Float.parseFloat(pay);

                        float currpayable = Float.parseFloat(payable);
                        float actualbalance = Float.parseFloat(balance);

                        if(getpay==0){
                            Toast.makeText(Pay.this, "you entered nothing", Toast.LENGTH_SHORT).show();

                        }

                        if (currpayable==0) {
                            Toast.makeText(Pay.this, "There is no fees to settle", Toast.LENGTH_SHORT).show();
                            paytxt.setText("0");

                        }
                        else{
                            float total = actualbalance - getpay ;
                            float afterpay = currpayable - getpay;
                            if(afterpay<0){
                                Toast.makeText(Pay.this, "You are paying too much", Toast.LENGTH_SHORT).show();
                                paytxt.setText("0");
                            }
                            else{
                                databaseReference.child("users").child(account).child("balance").setValue(total);
                                databaseReference.child("users").child(account).child("payable").setValue(afterpay);
                                Toast.makeText(Pay.this, "Payment Successful", Toast.LENGTH_SHORT).show();
                                paytxt.setText("0");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

    }

}

//    String payable = snapshot.child("payable").getValue().toString();
//    String balance = snapshot.child("balance").getValue().toString();
//
//    float currpayable = Float.parseFloat(payable);
//    float actualbalance = Float.parseFloat(balance);
//
//    float total = actualbalance - currpayable ;
//    float afterpay = currpayable - total ;
//
//                        databaseReference.child("users").child(account_phone).child("balance").setValue(total);
//                                databaseReference.child("users").child(account_phone).child("payable").setValue(afterpay);
//                                Toast.makeText(Fees.this, "Payment Successful", Toast.LENGTH_SHORT).show();