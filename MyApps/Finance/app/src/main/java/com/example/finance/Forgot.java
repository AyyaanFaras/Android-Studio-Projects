package com.example.finance;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        getWindow().setStatusBarColor(ContextCompat.getColor(Forgot.this, R.color.navbar));




        Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText emailreset = findViewById(R.id.emailreset);
                String emailre = String.valueOf(emailreset.getText());
                FirebaseAuth auth = FirebaseAuth.getInstance();
                String emailAddress = emailre;
                Log.e("", emailAddress);
                auth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Forgot.this, "Password Reset Link Sent", Toast.LENGTH_SHORT).show();
                                    Intent i  = new Intent(getApplicationContext(), LoginPage.class);
                                    startActivity(i);
                                }else{
                                    Intent i  = new Intent(getApplicationContext(), Signup.class);
                                    startActivity(i);
                                    Toast.makeText(Forgot.this, "Email not found", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}