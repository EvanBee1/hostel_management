package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class topup extends AppCompatActivity {


    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://androiddev-eaabf-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);

        Intent i = getIntent();
        String account = i.getStringExtra("phonepass");

        EditText bal= findViewById(R.id.topup_amount);
        Button calculate = findViewById(R.id.topup_topup);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    databaseReference.child("users").child(account).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String currBalance = snapshot.child("balance").getValue().toString();
                            String balancetxt = bal.getText().toString();
                            Log.d("test",balancetxt);
                            float currBalanceValue = Float.parseFloat(currBalance);
                            float getBalance = Float.parseFloat(balancetxt);

                            float total = currBalanceValue + getBalance;

                            if(getBalance==0){
                                Toast.makeText(topup.this, "You entered nothing", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                databaseReference.child("users").child(account).child("balance").setValue(total);
                                Toast.makeText(topup.this, "Topup Successful", Toast.LENGTH_SHORT).show();
                                bal.setText("0");
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