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

public class Login extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://androiddev-eaabf-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
    String gethotelkitchen,gethoteltoilet, gethotelbedsize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText phone = findViewById(R.id.phone);
        final EditText password = findViewById(R.id.password);
        final Button loginBtn = findViewById(R.id.loginBtn);
        final TextView registerNowBtn = findViewById(R.id.registerNowBtn);

        loginBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                final String phonetxt = phone.getText().toString();
                final String passwordTxt = password.getText().toString();

                if(phonetxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(Login.this,"Please enter your mobile or password", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //check if mobile/phone exist in database
                            if(snapshot.hasChild(phonetxt)){
                                //mobile exist in database
                                // then get password and match with input
                                final String getPassword = snapshot.child(phonetxt).child("password").getValue().toString();
                                final String getRole = snapshot.child(phonetxt).child("role").getValue().toString();
                                final String getemail = snapshot.child(phonetxt).child("email").getValue().toString();
                                final String getpassword = snapshot.child(phonetxt).child("password").getValue().toString();
                                final String getinstitution = snapshot.child(phonetxt).child("institution").getValue().toString();
                                final String getusername = snapshot.child(phonetxt).child("fullname").getValue().toString();
                                final String getbalance = snapshot.child(phonetxt).child("balance").getValue().toString();
                                final String getpayable = snapshot.child(phonetxt).child("payable").getValue().toString();
                                final String gethotel = snapshot.child(phonetxt).child("Hostel").getValue().toString();
                                final String gethotelunit = snapshot.child(phonetxt).child("HostelUnit").getValue().toString();
                                final String gethoteldue = snapshot.child(phonetxt).child("HostelDue").getValue().toString();
                                if(!gethotel.isEmpty()){
                                    gethotelkitchen= snapshot.child(gethotel).child("kitchen").getValue().toString();
                                    gethoteltoilet = snapshot.child(gethotel).child("toilet").getValue().toString();
                                    gethotelbedsize = snapshot.child(gethotel).child("BedSize").getValue().toString();
                                }
                                if(getPassword.equals(passwordTxt)){
                                    if(getRole.equals("admin")){
                                        Toast.makeText(Login.this, "logged in", Toast.LENGTH_SHORT).show();
                                        //open main activity after launch
                                        Intent intent = new Intent(Login.this, userlist.class);
                                        intent.putExtra("phonepass", phonetxt);
                                        startActivity(intent);
                                        finish();
                                    }

                                    else{
                                        Toast.makeText(Login.this, "logged in", Toast.LENGTH_SHORT).show();
                                        //open main activity after launch
                                        Intent intent = new Intent(Login.this, MainActivity.class);
                                        intent.putExtra("phonepass", phonetxt);
                                        intent.putExtra("emailpass", getemail);
                                        intent.putExtra("passwordpass", getpassword);
                                        intent.putExtra("institutionpass", getinstitution);
                                        intent.putExtra("usernamepass", getusername);
                                        intent.putExtra("balancepass", getbalance);
                                        intent.putExtra("payablepass", getpayable);
                                        intent.putExtra("hotelpass", gethotel);
                                        intent.putExtra("hotelunitpass", gethotelunit);
                                        intent.putExtra("hotelduepass", gethoteldue);
                                        intent.putExtra("hotelbedsizepass", gethotelbedsize);
                                        intent.putExtra("hotelkitchenpass", gethotelkitchen);
                                        intent.putExtra("hoteltoiletpass", gethoteltoilet);

                                        startActivity(intent);
                                        finish();
                                    }
                                }
                                else{
                                    Toast.makeText(Login.this, "Phone or password wrong", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        registerNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Open register activity
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }
}
