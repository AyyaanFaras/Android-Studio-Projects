package com.example.finance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_securtiy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_securtiy);
        getWindow().setStatusBarColor(ContextCompat.getColor(Login_securtiy.this, R.color.navbar));


        Button logoutl = findViewById(R.id.logoutl);
      //  ImageView dele = findViewById(R.id.deleteacc);

        logoutl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("email", "hello");
                editor.putString("password", "hello");
                editor.apply(); // or editor.commit();
                Intent i = new Intent(getApplicationContext(), LoginScreen.class);
                startActivity(i);
            }
        });

//        dele.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        ImageView back = findViewById(R.id.accback1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        ImageView delete = findViewById(R.id.deleteacc);


            // When the submit button is clicked, show the custom popup
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Inflate the custom popup layout (popup_layout.xml)
                    View popupView = getLayoutInflater().inflate(R.layout.activity_pop_up_screen, null);

                    // Create a new Dialog or set the layout to an Activity
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Login_securtiy.this);
                    builder.setView(popupView);

                    // Show the popup (dialog)
                    android.app.AlertDialog dialog = builder.create();
                    dialog.show();

                    // Set button click listener inside the popup
                    Button closeButton = popupView.findViewById(R.id.yesButton);
                    closeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();  // Dismiss the popup when the button is clicked
                        }
                    });

                    Button b1 = popupView.findViewById(R.id.noButton);
                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                user.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login_securtiy.this, "Account Deleted Successfully", Toast.LENGTH_SHORT).show();
                                    DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext()); // 'this' is the context
                                    boolean isDeleted = dbHelper.deleteDatabase(getApplicationContext());

                                    Intent i = new Intent(getApplicationContext(), LoginScreen.class);
                                    startActivity(i);
                                }
                            }
                        });
                        }
                    });
                }
            });
        }
    }
