package com.example.vehiclemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Maintenance extends AppCompatActivity {
    private static final String TAG = "";
            EditText number_plate;
            EditText nextmaintenance;
            EditText lastmaintenance;
            EditText description;
            EditText amount;
            Button submitbtn;
            String UserID;
            DatePickerDialog.OnDateSetListener setListener;
            DatePickerDialog.OnDateSetListener setListen;
    FirebaseDatabase firebaseDatabase;
    private FirebaseFirestore fstore;
    private FirebaseAuth fAuth;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);
        number_plate= findViewById(R.id.number_plate);
        nextmaintenance =findViewById(R.id.nextMaintenance);
        lastmaintenance =findViewById(R.id.lastMaintenance);
        description = findViewById(R.id.description);
        amount = findViewById(R.id.amount);
        submitbtn = findViewById(R.id.submitbtn);
        firebaseDatabase = FirebaseDatabase.getInstance();
        fstore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();


        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("MaintenanceInfo");

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        nextmaintenance.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog( Maintenance.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    setListen,year,month,day);
            datePickerDialog.getWindow().setBackgroundDrawable((new ColorDrawable(Color.TRANSPARENT)));
            datePickerDialog.show();
        });
        setListen = (view, year12, month12, dayOfMonth) -> {
           month12 = month12 +1;
           month12 = month12 -1;
           String date = dayOfMonth+"/"+ month12 +"/"+ year12;
           nextmaintenance.setText(date);
        };

        lastmaintenance.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog( Maintenance.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    setListener,year,month,day);
            datePickerDialog.getWindow().setBackgroundDrawable((new ColorDrawable(Color.TRANSPARENT)));
            datePickerDialog.show();
        });
        setListener = (view, year1, month1, dayOfMonth) -> {
            month1 = month1 +1;
            String date = dayOfMonth+"/"+ month1 +"/"+ year1;
            lastmaintenance.setText(date);
        };

        submitbtn.setOnClickListener(v -> {
            String number_plateTxt = number_plate.getText().toString();
            String lastmaintenanceTxt =lastmaintenance.getText().toString();
            String nextmaintenanceTxt = nextmaintenance.getText().toString();
            String amountTxt= amount.getText().toString();
            String descriptionTxt = description.getText().toString();

            if (number_plateTxt.isEmpty()|| lastmaintenanceTxt.isEmpty()|| nextmaintenanceTxt.isEmpty()||amountTxt.isEmpty()|| descriptionTxt.isEmpty()){
                Toast.makeText(Maintenance.this, "fill in all the spaces", Toast.LENGTH_SHORT).show();
            }
            Map<String, Object> note = new HashMap<>();
            note.put("number Plate",number_plateTxt);
            note.put("Total Maintenance amount",amountTxt);
            note.put("last Maintenance",lastmaintenanceTxt);
            note.put("Next Maintenance", nextmaintenanceTxt);
            note.put("Description", descriptionTxt);



            //send to firebase
            fstore.collection("Maintenance").document().set(note).addOnSuccessListener(aVoid -> {
                Log.d(TAG, "onSuccess:Data saved for " + UserID);
                Intent intent = new Intent(Maintenance.this, Homepage.class);
                startActivity(intent);
                Toast.makeText(Maintenance.this, "Data Saved" ,
                        Toast.LENGTH_SHORT).show();

            });



        });
    }
}