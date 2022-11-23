package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class HostelInfo extends AppCompatActivity {
    //connects to firebase database
    //create object of database reference class to access firebase realtime database

    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://androiddev-eaabf-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
    TextView a1, a2, a3, a4 ,a5 ,a6 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_info);

        //receive from MainActivity
        Intent intent = getIntent();
        //obtain account from database
        String account_phone = intent.getStringExtra("phoneplaceholder");

        //connect activity with xml design
        a1= (TextView) findViewById(R.id.hostel_hostelunit);
        a2= (TextView) findViewById(R.id.hostel_size_txt);
        a3= (TextView) findViewById(R.id.hostel_due_txt);
        a4= (TextView) findViewById(R.id.hostel_bed_txt);
        a5= (TextView) findViewById(R.id.hostel_kitchen_txt);
        a6= (TextView) findViewById(R.id.hostel_toilet_txt);

        showAllUserData();


//        Button hostelBtn; //to be deleted/reuse DELETE
//        hostelBtn = (Button) findViewById(R.id.hostel_edit);
//
//        hostelBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                Intent i = new Intent(HostelInfo.this,MainActivity.class);
//                startActivity(i);
//            }
//        });
    }

    private void showAllUserData(){
        Intent intent = getIntent();
        //obtain from main activity and put in the androids view
        //get methods to obtain data from the intent
        //hostel name
        String account_hotel = intent.getStringExtra("hotelplaceholder");
        //hotel unit ex: A1-10-02
        String account_hotel_unit = intent.getStringExtra("hotelunitplaceholder");
        //the duration of students stay at hotel
        String account_hotel_due = intent.getStringExtra("hoteldueplaceholder");
        //status of the kitcen ownership, usually shared
        String account_hotel_kitchen = intent.getStringExtra("hotelkitchenplaceholder");
        //status of the bathroom
        String account_hotel_toilet = intent.getStringExtra("hoteltoiletplaceholder");
        //size of the bed rented by student, ex:queen, double, single
        String account_hotel_bedsize = intent.getStringExtra("hotelbedsizeplaceholder");

        //set the information into hostel view
        a1.setText(account_hotel_unit);
        a2.setText(account_hotel);
        a3.setText(account_hotel_due);
        a4.setText(account_hotel_bedsize);
        a5.setText(account_hotel_kitchen);
        a6.setText(account_hotel_toilet);

    }
}
