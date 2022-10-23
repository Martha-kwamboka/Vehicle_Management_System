package com.example.vehiclemanagementsystem;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class Login extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText Email_id = findViewById(R.id.Email_id);
        final EditText driver_ID = findViewById(R.id.driver_ID);
        final EditText Password = findViewById(R.id.Password);
        final TextView forgotPassword = findViewById(R.id.forgotPassword);
        final Button LoginButton = findViewById(R.id.LoginButton);
        final TextView registerNewAccount = findViewById(R.id.registernowbtn);
        final ImageView google = findViewById(R.id.google);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);

        LoginButton.setOnClickListener(v -> {
            final String driver = driver_ID.getText().toString();
            final String email = Email_id.getText().toString();
            final String passwrd = Password.getText().toString();


            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(passwrd) || TextUtils.isEmpty(driver)) {
                Toast.makeText(Login.this, "please Fill in all the required details", Toast.LENGTH_LONG).show();

            } else {
                startActivity(new Intent(Login.this, Homepage.class));
            }

        });
        registerNewAccount.setOnClickListener(v -> startActivity(new Intent(Login.this, register_page.class)));
        forgotPassword.setOnClickListener(v -> {
            Toast.makeText(Login.this, "You can now reset your password", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Login.this, forgotPassword.class));
        });
        google.setOnClickListener(view -> {
            Intent i = gsc.getSignInIntent();
            startActivityForResult(i, 1234);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);

                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                Intent intent = new Intent(getApplicationContext(), Homepage.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(Login.this, task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        });

            } catch (ApiException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(this, Homepage.class);
            startActivity(intent);
        }
    }
}

