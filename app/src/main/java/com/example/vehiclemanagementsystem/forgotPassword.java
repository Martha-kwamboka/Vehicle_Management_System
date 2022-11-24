package com.example.vehiclemanagementsystem;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import com.google.firebase.auth.FirebaseAuth;

public class forgotPassword extends AppCompatActivity {
   EditText Email_ID;
   Button PasswordResetbutton;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Email_ID= findViewById(R.id.Email_id);
        PasswordResetbutton= findViewById(R.id.PasswordResetbutton);
        mAuth= FirebaseAuth.getInstance();



        mAuth.sendPasswordResetEmail(String.valueOf(Email_ID)).addOnCompleteListener(task -> {

            if(task.isSuccessful())
            {
                // if isSuccessful then done message will be shown
                // and you can change the password
                Toast.makeText(forgotPassword.this," sent",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(forgotPassword.this,"Error Occurred",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(e -> Toast.makeText(forgotPassword.this,"Error Failed",Toast.LENGTH_LONG).show());
    }



}