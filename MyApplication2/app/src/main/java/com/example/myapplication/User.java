package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class User extends AppCompatActivity {

    String fullname, phone, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);
    }

    public String getUsername () {
        return fullname;
    }

    public String getUserphone () {
        return phone;
    }

    public String getUseremail () {
        return email;
    }
}