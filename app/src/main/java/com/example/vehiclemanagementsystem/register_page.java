package com.example.vehiclemanagementsystem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register_page extends AppCompatActivity {
    public static final String TAG = "TAG";

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        final EditText Email_id = findViewById(R.id.Email_id);
        final EditText Fname = findViewById(R.id.FName);
        final EditText Lname = findViewById(R.id.LName);
        final EditText Phone_No = findViewById(R.id.Phone_No);
        final EditText driver_ID = findViewById(R.id.driver_ID);
        final EditText password = findViewById(R.id.password);
        final TextView Password = findViewById(R.id.Password);
        final Button RegisterButton = findViewById(R.id.RegisterButton);
        final TextView login_btn = findViewById(R.id.loginnowbtn);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();



        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String driverTxt = driver_ID.getText().toString();
                final String emailTxt = Email_id.getText().toString();
                final String passwrdTxt = password.getText().toString();
                final String firstnameTxt = Fname.getText().toString();
                final String lastnameTxt = Lname.getText().toString();
                final String PhoneNumberTxt = Phone_No.getText().toString();
                final String confirmpasswordTxt = Password.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (TextUtils.isEmpty(emailTxt)) {
                    Email_id.setError("Email is Required.");
                    return;
                }

                if (TextUtils.isEmpty(passwrdTxt)) {
                    password.setError("Password is Required.");
                    return;
                }

                if (passwrdTxt.length() < 6) {
                    password.setError("Password Must be >=6 Characters");
                    return;
                }
                if (TextUtils.isEmpty(confirmpasswordTxt)) {
                    Password.setError("Password is Required.");
                    return;
                }
                if (TextUtils.isEmpty(driverTxt)) {
                    driver_ID.setError("Driver ID is Required.");
                    return;
                }

                if (TextUtils.isEmpty(firstnameTxt)) {
                    Fname.setError("First name is Required.");
                    return;
                }
                if (TextUtils.isEmpty(lastnameTxt)) {
                    Lname.setError("Last name is Required.");
                    return;
                }
                if (TextUtils.isEmpty(PhoneNumberTxt)) {
                    Phone_No.setError("Phone Number is Required.");
                    return;
                }


                fAuth.createUserWithEmailAndPassword(emailTxt, passwrdTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(register_page.this, "User Created.", Toast.LENGTH_SHORT).show();
                             userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put(" First name", Fname);
                            user.put("Last name", Lname);
                            user.put("Email", Email_id);
                            user.put("phone", Phone_No);
                            user.put("Driver ID", driver_ID);
                            
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for " + userID);
                                    Toast.makeText(register_page.this, "Registered Successfully.", Toast.LENGTH_SHORT).show();
                                }
                            });
                            //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            Intent intent = new Intent(register_page.this, Login.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        } else {
                            Toast.makeText(register_page.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }
}