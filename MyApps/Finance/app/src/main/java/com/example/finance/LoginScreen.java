package com.example.finance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        getWindow().setStatusBarColor(ContextCompat.getColor(LoginScreen.this, R.color.loginscreem));

        TextView log = findViewById(R.id.textView4);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginPage.class);
                startActivity(i);
            }
        });

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Signup.class);
                startActivity(i);
            }
        });
    }

    public void onBackPressed() {
        // This will exit the app when the back button is pressed
        super.onBackPressed();
        finishAffinity(); // Closes the app and removes all activities from the stack
        System.exit(0);   // Ensures the app is terminated
    }
}