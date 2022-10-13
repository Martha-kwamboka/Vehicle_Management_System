package com.example.vehiclemanagementsystem;


import static android.content.ContentValues.TAG;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class register_page extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        final EditText Email_id =  findViewById(R.id.Email_id);
        final EditText Fname = findViewById(R.id.FName);
        final EditText Lname = findViewById(R.id.LName);
        final EditText Phone_No = findViewById(R.id.Phone_No);
        final EditText driver_ID =  findViewById(R.id.driver_ID);
        final EditText   password =  findViewById(R.id.password);
        final TextView Password =  findViewById(R.id.Password);
        final Button RegisterButton = findViewById(R.id.RegisterButton);
        final TextView login_btn = findViewById(R.id.loginnowbtn);




        RegisterButton.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  checkDataEntered();
                                              }


                                              private void checkDataEntered() {

                                                  final String driverTxt = driver_ID.getText().toString();
                                                  final String emailTxt = Email_id.getText().toString();
                                                  final String passwrdTxt = password.getText().toString();
                                                  final String firstnameTxt = Fname.getText().toString();
                                                  final String lastnameTxt = Lname.getText().toString();
                                                  final String PhoneNumberTxt = Phone_No.getText().toString();
                                                  final String confirmpasswordTxt = Password.getText().toString();
                                                  String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                                                  if (driverTxt.isEmpty() || passwrdTxt.isEmpty()
                                                          || firstnameTxt.isEmpty() || lastnameTxt.isEmpty() || PhoneNumberTxt.isEmpty() || confirmpasswordTxt.isEmpty()) {
                                                      Toast.makeText(register_page.this, "fill in all the spaces", Toast.LENGTH_SHORT).show();

                                                  } else if (passwrdTxt.equals(confirmpasswordTxt)) {
                                                      Toast.makeText(register_page.this, "password does not match", Toast.LENGTH_SHORT).show();
                                                  } else if (emailTxt.matches(emailPattern)) {
                                                      Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
                                                  } else {
                                                      Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                                                  }

                                              }

                                          });





        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

            }
}