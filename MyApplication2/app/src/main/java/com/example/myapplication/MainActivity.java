package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView D1,D2,D3,D4;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://androiddev-eaabf-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
    String phonepass2, emailpass2,passwordpass2,usernamepass2,institutionpass2, payablepass2, balancepass2, hotelpass2, hotelunitpass2, hotelduepass2, hotelkitchenpass2, hoteltoiletpass2, hotelbedsizepass2;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        D1 = (CardView) findViewById(R.id.account);
        D2 = (CardView) findViewById(R.id.Fees);
        D3 = (CardView) findViewById(R.id.HostelInfo);
        D4 = (CardView) findViewById(R.id.Complaint);

        Intent intent = getIntent();
        String complaintstatuspass2 = intent.getStringExtra("complaintstatuspass");

        if(complaintstatuspass2.equals("2")){
            alertshow();
        }

        D1.setOnClickListener((View.OnClickListener) this);
        D2.setOnClickListener((View.OnClickListener) this);
        D3.setOnClickListener((View.OnClickListener) this);
        D4.setOnClickListener((View.OnClickListener) this);

        Button lgBtn = (Button) findViewById(R.id.logoutBtn);

        lgBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v){
        Intent i ;
        Intent intent;
        switch(v.getId()){
            case R.id.account :
                i = new Intent(this,account.class);
                intent = getIntent();
                phonepass2 = intent.getStringExtra("phonepass");
                emailpass2 = intent.getStringExtra("emailpass");
                passwordpass2 = intent.getStringExtra("passwordpass");
                institutionpass2 = intent.getStringExtra("institutionpass");
                usernamepass2 = intent.getStringExtra("usernamepass");
                i.putExtra("phoneplaceholder", phonepass2);
                i.putExtra("emailplaceholder", emailpass2);
                i.putExtra("passwordplaceholder", passwordpass2);
                i.putExtra("institutionplaceholder", institutionpass2);
                i.putExtra("usernameplaceholder", usernamepass2);
                startActivity(i);
                break;
            case R.id.Fees :
                i = new Intent(this,Fees.class);
                intent = getIntent();
                phonepass2 = intent.getStringExtra("phonepass");
                payablepass2 = intent.getStringExtra("balancepass");
                balancepass2 = intent.getStringExtra("payablepass");
                i.putExtra("phoneplaceholder", phonepass2);
                i.putExtra("payableplaceholder", payablepass2);
                i.putExtra("balanceplaceholder", balancepass2);
                startActivity(i);
                break;
            case R.id.HostelInfo:
                i = new Intent(this,HostelInfo.class);
                intent = getIntent();
                phonepass2 = intent.getStringExtra("phonepass");
                hotelpass2 = intent.getStringExtra("hotelpass");
                hotelunitpass2 = intent.getStringExtra("hotelunitpass");
                hotelduepass2 = intent.getStringExtra("hotelduepass");
                hotelkitchenpass2 = intent.getStringExtra("hotelkitchenpass");
                hoteltoiletpass2 = intent.getStringExtra("hoteltoiletpass");
                hotelbedsizepass2 = intent.getStringExtra("hotelbedsizepass");
                i.putExtra("phoneplaceholder", phonepass2);
                i.putExtra("hotelplaceholder", hotelpass2);
                i.putExtra("hotelunitplaceholder", hotelunitpass2);
                i.putExtra("hoteldueplaceholder", hotelduepass2);
                i.putExtra("hotelkitchenplaceholder", hotelkitchenpass2);
                i.putExtra("hoteltoiletplaceholder", hoteltoiletpass2);
                i.putExtra("hotelbedsizeplaceholder", hotelbedsizepass2);
                startActivity(i);
                break;
            case R.id.Complaint:
                i = new Intent(this,Complaint.class);
                intent = getIntent();
                phonepass2 = intent.getStringExtra("phonepass");
                i.putExtra("phoneplaceholder", phonepass2);
                startActivity(i);
                break;
        }
    }

    public void alertshow(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("HI ! ");
        alert.setMessage("Admin has read your complaint, thanks for sending your feedback");

        alert.setPositiveButton("OK !", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                Intent intent = getIntent();
                phonepass2 = intent.getStringExtra("phonepass");
                databaseReference.child("users").child(phonepass2).child("complaintstatus").setValue("0");
            }
        });

        alert.show();

    }

}
