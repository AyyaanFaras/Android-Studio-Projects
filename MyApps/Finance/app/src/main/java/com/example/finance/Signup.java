package com.example.finance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    EditText efullname;
    EditText eemail;
    EditText epassword;
    Button esignup;
    ImageView egoogle;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);
        getWindow().setStatusBarColor(ContextCompat.getColor(Signup.this, R.color.navbar));

        efullname = findViewById(R.id.editTextText2);
        eemail = findViewById(R.id.editTextText3);
        epassword = findViewById(R.id.editTextTextPassword2);
        esignup = findViewById(R.id.button3);
        egoogle = findViewById(R.id.imageView3);

        esignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname, email, password;
                fullname = String.valueOf(efullname.getText());
                email = String.valueOf(eemail.getText());
                password = String.valueOf(epassword.getText());
                if(TextUtils.isEmpty(fullname)){
                    Toast.makeText(Signup.this, "Full Name is not valid", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Signup.this, "Email Type is not valid", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Signup.this, "Password is not valid", Toast.LENGTH_SHORT).show();
                }

//                Toast.makeText(Signup.this, email+password, Toast.LENGTH_SHORT).show();
               mAuth = FirebaseAuth.getInstance();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Signup.this, "Account Created Successfully.",
                                            Toast.LENGTH_SHORT).show();

                                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("fullname", fullname);
                                    editor.putString("email", email);
                                    editor.putString("password", password);
                                    editor.apply(); // or editor.commit();


                                    Intent i = new Intent(getApplicationContext(), LoginPage.class);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(Signup.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });



    }
}