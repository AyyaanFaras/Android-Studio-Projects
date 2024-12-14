package com.example.finance;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Account_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_account_info);
        getWindow().setStatusBarColor(ContextCompat.getColor(Account_info.this, R.color.navbar));

        TextView fullname = findViewById(R.id.fullname);
        TextView email = findViewById(R.id.email);
        Button changepass = findViewById(R.id.changepass);


        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String name = sharedPreferences.getString("fullname", "Name not found");
        fullname.setText(name);
        String emaill = sharedPreferences.getString("email", "Email not found");
        email.setText(emaill);

        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                String emailAddress = emaill;

                auth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Account_info.this, "Password Change Link Sent", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        ImageView acback = findViewById(R.id.accback2);
        acback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });




    }
}