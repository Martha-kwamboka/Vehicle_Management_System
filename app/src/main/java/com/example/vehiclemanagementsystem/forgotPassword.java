package com.example.vehiclemanagementsystem;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        final EditText Email_ID= findViewById(R.id.Email_id);
        final Button PasswordResetbutton= findViewById(R.id.PasswordResetbutton);
        PasswordResetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email= Email_ID.getText().toString();
                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete( Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(forgotPassword.this, "Email sent", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(forgotPassword.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(forgotPassword.this, "Fill in the required details", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(forgotPassword.this, "Email sent", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}