package com.example.counterapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Greetings extends AppCompatActivity {


    EditText edittext;
    Button btn;
    TextView textview;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greetings);


        edittext = findViewById(R.id.edittext);
        btn = findViewById(R.id.btn);
        textview = findViewById(R.id.textview);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edittext.getText().toString();
                boolean a = true;
                String str = name.toLowerCase();
//                if(str.length()>=6) str = str.substring(0,6);

                if((str.length()>=6 && str.substring(0,6).equals("ayyaan")) || name.toLowerCase().equals("zeyrotic")){
                    Toast.makeText(Greetings.this, "Hi "+name+" What's up!", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Greetings.this, "Welcome "+name+" to my App!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }




}