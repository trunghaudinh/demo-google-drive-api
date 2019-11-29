package com.example.demogg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    TextView txtName;
    TextView txtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);

        FirebaseUser firebaseUser = getIntent().getParcelableExtra("profile");

        txtName.setText(firebaseUser.getDisplayName());
        txtEmail.setText(firebaseUser.getEmail());



    }
}
