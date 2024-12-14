package com.example.luckynumberapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

import java.util.Random;

public class LuckyNumberPage extends AppCompatActivity {
//    static int num;
    int count = 1;
    TextView title, number;
    EditText guessing;
    Button sharebtn, guessbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_number_page);
        title = findViewById(R.id.title);
        guessing = findViewById(R.id.guessing);
        number = findViewById(R.id.number);
        sharebtn = findViewById(R.id.sharebtn);
        guessbtn = findViewById(R.id.guessbtn);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Log.i("TAG", "Name: "+name);

        int num = randomization();
        number.setText(""+num);

        guessbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("guessingnum", "Number is: "+num);
                int input = Integer.parseInt(guessing.getText().toString());
                if(num==input){
                    number.setVisibility(View.VISIBLE);
                    sharebtn.setVisibility(View.VISIBLE);
                    guessbtn.setVisibility(View.INVISIBLE);
                    guessing.setVisibility(View.INVISIBLE);
                    title.setText( name + ", you guessed your number right!");
                    Toast.makeText(LuckyNumberPage.this, "You Guessed it Right!", Toast.LENGTH_LONG).show();

                }
                else if(num>input){
                    count++;
                    Toast.makeText(LuckyNumberPage.this, "lucky number is bigger", Toast.LENGTH_SHORT).show();
                }
                else{
                    count++;
                    Toast.makeText(LuckyNumberPage.this, "lucky number is smaller", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shareData(name, num);
            }
        });

    }

    public void shareData(String name, int num){

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "" + name + " got lucky Today!");
        intent.putExtra(Intent.EXTRA_TEXT, "" + name + " found Lucky number " + num + " in " +count+ " turns!");
        startActivity(Intent.createChooser(intent, "Choose a platform to share"));
    }

    private int randomization(){
        Random random = new Random();
        return random.nextInt(1000);
    }
}