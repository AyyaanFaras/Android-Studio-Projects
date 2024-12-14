package com.example.counterapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView title, num;
    Button plus, minus, reset;
    int counter = 0;
    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.title);
        reset = findViewById(R.id.reset);
        num = findViewById(R.id.num);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num.setText(""+increaseCounter());
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num.setText(""+decreaseCounter());
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num.setText(""+resetCounter());
            }
        });



    }

    public int increaseCounter(){
        return ++counter;
    }

    public int decreaseCounter(){
        return --counter;
    }

    public int resetCounter(){
        counter = 0;
        return counter;
    }
}