package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://androiddev-eaabf-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        EditText searchUser = findViewById(R.id.admin_search);
        Button searchBtn = findViewById(R.id.admin_button);
        Button logoutBtn = findViewById(R.id.lgBtn);



        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user = searchUser.getText().toString();
                if(user.isEmpty()){
                    Toast.makeText(Admin.this, "You entered nothing", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(user)){
                                final String getusername = snapshot.child(user).child("fullname").getValue().toString();
                                Toast.makeText(Admin.this, "User found", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Admin.this, userEdit.class);
                                intent.putExtra("phonepass",user);
                                intent.putExtra("usernamepass",getusername);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(Admin.this, "User not found", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this , Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
