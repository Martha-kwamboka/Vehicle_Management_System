package com.example.vehiclemanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class Logout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);


        AlertDialog.Builder builder=new AlertDialog.Builder(Logout.this); //Home is name of the activity
        builder.setMessage("Do you want to exit?");
        builder.setPositiveButton("OK", (dialog, id) -> {

            finish();
            Intent i=new Intent();
            i.putExtra("finish", true);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
            //startActivity(i);
            finish();
            startActivity(new Intent(Logout.this, MainActivity.class));

        });

        builder.setNegativeButton("CANCEL", (dialog, id) -> {
            dialog.cancel();
            startActivity(new Intent(Logout.this, Homepage.class));
        });

        AlertDialog alert=builder.create();
        alert.show();



    }
}

