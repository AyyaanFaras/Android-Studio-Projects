package com.example.widgetusepractice;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PlaySounds extends AppCompatActivity implements View.OnClickListener{
    Button black, green, purple, red, yellow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_sounds);

        black = findViewById(R.id.black);
        green = findViewById(R.id.green);
        purple = findViewById(R.id.purple);
        red = findViewById(R.id.red);
        yellow = findViewById(R.id.yellow);


        black.setOnClickListener(this);
        green.setOnClickListener(this);
        purple.setOnClickListener(this);
        red.setOnClickListener(this);
        yellow.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        player(view.getId());
    }


    public void player(int id){
        if(id==R.id.black){
            MediaPlayer mp = MediaPlayer.create(this, R.raw.black);
            mp.start();
        }

        if(id==R.id.green){
            MediaPlayer mp = MediaPlayer.create(this, R.raw.green);
            mp.start();
        }

        if(id==R.id.purple){
            MediaPlayer mp = MediaPlayer.create(this, R.raw.purple);
            mp.start();
        }

        if(id==R.id.red){
            MediaPlayer mp = MediaPlayer.create(this, R.raw.red);
            mp.start();
        }

        if(id==R.id.yellow){
            MediaPlayer mp = MediaPlayer.create(this, R.raw.yellow);
            mp.start();
        }
    }
}