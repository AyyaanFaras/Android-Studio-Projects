package com.example.finance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class addcato extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcato);



        Button addcat = findViewById(R.id.addcato);

        addcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText cat = findViewById(R.id.cat);
                String type = String.valueOf(cat.getText());
                DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
                dbHelper.addcat(type);
                Intent i = new Intent(getApplicationContext(), addexpense.class);
                startActivity(i);
            }
        });

        ImageView back = findViewById(R.id.backpro);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}