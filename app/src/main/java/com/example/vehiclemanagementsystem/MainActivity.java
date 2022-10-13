package com.example.vehiclemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    private Button RegisterButton, LoginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Button   RegisterButton = (Button) findViewById(R.id. RegisterButton);
       // RegisterButton.setOnClickListener(new View.OnClickListener() {
          //  public void onClick(View v) {

            //}
     //   });

        Button   LoginButton = (Button) findViewById(R.id. LoginButton);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });


    }


    public void onRegister(View view) {
        Intent intent = new Intent(MainActivity.this, register_page.class);
        startActivity(intent);
    }
}