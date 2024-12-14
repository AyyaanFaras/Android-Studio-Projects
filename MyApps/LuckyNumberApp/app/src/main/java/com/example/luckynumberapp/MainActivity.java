package com.example.luckynumberapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView title;
    EditText name;
    Button btn;
    String myName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title=findViewById(R.id.title);
        name=findViewById(R.id.name);
        btn=findViewById(R.id.getluckybtn);
        myName = name.getText().toString();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myName = name.getText().toString();
                Intent i = new Intent(getApplicationContext(), LuckyNumberPage.class);
                i.putExtra("name", myName);
                startActivity(i);
            }
        });

    }


}