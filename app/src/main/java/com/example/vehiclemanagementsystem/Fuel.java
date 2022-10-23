package com.example.vehiclemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


public class Fuel extends AppCompatActivity {

    EditText Fuel_type;
    EditText receiptNumber;
    EditText number_plate;
    EditText amount;
    EditText date;
    Button submitbtn;
    DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel);
        Fuel_type = findViewById(R.id.Fuel_type);
        receiptNumber = findViewById(R.id.receiptNumber);
        number_plate = findViewById(R.id.number_plate);
        amount = findViewById(R.id.amount);
        date = findViewById(R.id.date);
        submitbtn= findViewById(R.id.submitbtn);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        date.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog( Fuel.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    setListener,year,month,day);
            datePickerDialog.getWindow().setBackgroundDrawable((new ColorDrawable(Color.TRANSPARENT)));
            datePickerDialog.show();
        });
        setListener = (view, year1, month1, dayOfMonth) -> {
            month1 = month1 +1;
            String datex = dayOfMonth+"/"+ month1 +"/"+ year1;
            date.setText(datex);
        };


        submitbtn.setOnClickListener(v -> {
            String number_plateTxt = number_plate.getText().toString();
            String amountTxt= amount.getText().toString();
            String dateTxt = date.getText().toString();
            String receiptNumberTxt = receiptNumber.getText().toString();
            String Fuel_typeTxt = Fuel_type.getText().toString();
            if(number_plateTxt.isEmpty() || amountTxt.isEmpty() || dateTxt.isEmpty() || receiptNumberTxt.isEmpty() || Fuel_typeTxt.isEmpty()) {
                Toast.makeText(Fuel.this, "Fill all the input fields", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(Fuel.this, "Saved", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Fuel.this,Homepage.class));
            }

        });


    }
}