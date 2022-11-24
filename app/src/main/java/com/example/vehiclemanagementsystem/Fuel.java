package com.example.vehiclemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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



public class Fuel extends AppCompatActivity {

    private static final String TAG = "";
    EditText Fuel_type;
    EditText receiptNumber;
    EditText number_plate;
    EditText amount;
    String UserID;
    EditText date;
    Button submitbtn;
    DatePickerDialog.OnDateSetListener setListener;

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;
    private FirebaseFirestore fstore;
    private FirebaseAuth fAuth;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel);
        Fuel_type = findViewById(R.id.Fuel_type);
        receiptNumber = findViewById(R.id.receiptNumber);
        number_plate = findViewById(R.id.number_plate);
        amount = findViewById(R.id.amount);
        date = findViewById(R.id.date);
        submitbtn = findViewById(R.id.submitbtn);
        firebaseDatabase = FirebaseDatabase.getInstance();
        fstore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();


        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("MaintenanceInfo");

        // initializing our object
        // class variable.


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        date.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(Fuel.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    setListener, year, month, day);
            datePickerDialog.getWindow().setBackgroundDrawable((new ColorDrawable(Color.TRANSPARENT)));
            datePickerDialog.show();
        });
        setListener = (view, year1, month1, dayOfMonth) -> {
            month1 = month1 + 1;
            String datex = dayOfMonth + "/" + month1 + "/" + year1;
            date.setText(datex);
        };

        submitbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Create String Variables to store the values we need to send to firebase
                        String number_plateTxt = number_plate.getText().toString();
                        String amountTxt = amount.getText().toString();
                        String dateTxt = date.getText().toString();
                        String receiptNumberTxt = receiptNumber.getText().toString();
                        String Fuel_typeTxt = Fuel_type.getText().toString();
                        if (number_plateTxt.isEmpty() || amountTxt.isEmpty() || dateTxt.isEmpty() || receiptNumberTxt.isEmpty() || Fuel_typeTxt.isEmpty()) {
                            Toast.makeText(Fuel.this, "Fill all the input fields", Toast.LENGTH_SHORT).show();
                        }
                            //Create map to store the data as a list
                            Map<String, Object> note = new HashMap<>();
                            note.put("numberPlate",number_plateTxt);
                            note.put("fuel amount",amountTxt);
                            note.put("date",dateTxt);
                            note.put("receipt number",receiptNumberTxt);
                            note.put("fuel_type",Fuel_typeTxt);



                            //send to firebase
                            fstore.collection("Fuel").document().set(note).addOnSuccessListener(aVoid -> {
                                Log.d(TAG, "onSuccess:Data saved for " + UserID);
                                Intent intent = new Intent(Fuel.this, Homepage.class);
                                startActivity(intent);
                                Toast.makeText(Fuel.this, "Data Saved" ,
                                        Toast.LENGTH_SHORT).show();

                            });



                    }
                }
        );

    }


    public void Submit_Receipt(View view) {
           }

}



