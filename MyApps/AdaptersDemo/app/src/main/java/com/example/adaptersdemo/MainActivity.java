package com.example.adaptersdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adaptersdemo.demoone.MainActivityOne;
import com.example.adaptersdemo.demoone.MainActivityTwo;
import com.example.adaptersdemo.fragsdemo.FragPage;

public class MainActivity extends AppCompatActivity {

    Button grid, list, frags;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.grid);
        list = findViewById(R.id.list);
        frags = findViewById(R.id.frags);

        grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivityOne.class);
                startActivity(i);
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivityTwo.class);
                startActivity(i);
            }
        });

        frags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FragPage.class);
                startActivity(i);
            }
        });

    }
}