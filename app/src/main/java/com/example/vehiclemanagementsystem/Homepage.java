package com.example.vehiclemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class Homepage extends AppCompatActivity {

CardView cardLogout;
CardView cardLocation;
CardView cardMaintenance;
CardView cardFuel;


@Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_homepage);

    cardFuel= findViewById(R.id.cardFuel);
    cardLocation= findViewById(R.id.cardLocation);
    cardMaintenance=findViewById(R.id.cardMaintenance);
    cardLogout=findViewById(R.id.cardLogout);

    cardFuel.setOnClickListener(v -> {
        showToast("Fuel is clicked");
        startActivity(new Intent(Homepage.this, Fuel.class));

    });

cardMaintenance.setOnClickListener(v -> {
    showToast("Maintenance is clicked");
    startActivity(new Intent(Homepage.this, Maintenance.class));
});


cardLocation.setOnClickListener(v -> {
    showToast("Location is clicked");
    startActivity(new Intent(Homepage.this, Location.class));
});


cardLogout.setOnClickListener(v -> {
    showToast("Logging out");
    startActivity(new Intent(Homepage.this, Logout.class));
});
}
private void  showToast(String message){
    Toast.makeText(this,message, Toast.LENGTH_SHORT).show();

}

}