package com.example.unitconverterapp;

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
    Button btn,  btn1;
    EditText input;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.convert);
        btn1 = findViewById(R.id.convert1);
        input = findViewById(R.id.inputWeight);
        result = findViewById(R.id.result);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numstr = input.getText().toString();
                double num = 0;
                if (!input.getText().toString().trim().isEmpty()){
                    num = Double.parseDouble(numstr) * 2.20462;
                }
                result.setText(""+(num));
//                result.setText(String.format("%.2f", num));
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numstr = input.getText().toString();
                double num = 0;
                if (!input.getText().toString().trim().isEmpty()) {
                    num = Double.parseDouble(numstr) / 2.20462;
                }
                result.setText(""+(num));
//                result.setText(String.format("%.2f", num));
            }
        });
    }
}