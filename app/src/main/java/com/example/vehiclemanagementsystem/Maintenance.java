package com.example.vehiclemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Maintenance extends AppCompatActivity {

            EditText number_plate;
            EditText nextmaintenance;
            EditText lastmaintenance;
            EditText description;
            EditText amount;
            Button submitbtn;
            DatePickerDialog.OnDateSetListener setListener;
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

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        nextmaintenance.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog( Maintenance.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    setListener,year,month,day);
            datePickerDialog.getWindow().setBackgroundDrawable((new ColorDrawable(Color.TRANSPARENT)));
            datePickerDialog.show();
        });
        setListener = (view, year12, month12, dayOfMonth) -> {
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
            else{

                Toast.makeText(Maintenance.this, "Saved", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Maintenance.this, Homepage.class));

            }

        });
    }
}