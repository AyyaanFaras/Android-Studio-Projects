package com.example.widgetusepractice;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    CheckBox checkBox;
    RadioGroup radioGroup;
    RadioButton radio1, radio2;
    Spinner spinner;
    TimePicker timepicker;
    DatePicker datepicker;
    Button showdate, progressbtn;
    ProgressBar progressbar1, progressbar2;

    Button sounds;

    int progress = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timepicker = findViewById(R.id.timepicker);
        datepicker = findViewById(R.id.datepicker);
        showdate = findViewById(R.id.showdate);

//        ******************************
//        ********** Default **********
//        ******************************

        sounds = findViewById(R.id.sounds);
        sounds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PlaySounds.class);
                startActivity(i);
            }
        });




//        ******************************
//        ********** CheckBox **********
//        ******************************

        checkBox = findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                Toast t2 = Toast.makeText(MainActivity.this, "unchecked!", Toast.LENGTH_SHORT);
                Toast t1 = Toast.makeText(MainActivity.this, "Checkbox checked", Toast.LENGTH_SHORT);
                new Handler().postDelayed(()->t1.cancel(), 500);
                new Handler().postDelayed(()->t2.cancel(), 500);
                    if(b) t1.show();
                else t2.show();
            }
        });
//        **************************************************
//        ********** Radio Buttons and Radio Groups **********
//        **************************************************

        radioGroup = findViewById(R.id.radioGroup);
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton radio = findViewById(checkedId);
                Toast.makeText(MainActivity.this, "You Selected " + radio.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
//        ******************************
//        ********** Spinner **********
//        ******************************

        spinner = findViewById(R.id.spinner);
        String[] courses = {"Blank", "sounds", "date", "time", "progressbar"};

        //ArrayAdapter: used to populate the spinner with items and from string array resource.

        ArrayAdapter<String> adapter = new ArrayAdapter(
                            this, android.R.layout.simple_spinner_item, courses
                );
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String curr = courses[position];
                timepicker.setVisibility(View.INVISIBLE);
                datepicker.setVisibility(View.INVISIBLE);
                showdate.setVisibility(View.INVISIBLE);
                progressbar1.setVisibility(View.INVISIBLE);
                progressbar2.setVisibility(View.INVISIBLE);
                progressbtn.setVisibility(View.INVISIBLE);
                sounds.setVisibility(View.INVISIBLE);
                if(curr.equals("date")) {
                    datepicker.setVisibility(View.VISIBLE);
                    showdate.setVisibility(View.VISIBLE);
                }
                else if (curr.equals("progressbar")) {
                    progressbar1.setVisibility(View.VISIBLE);
                    progressbar2.setVisibility(View.VISIBLE);
                    progressbtn.setVisibility(View.VISIBLE);
                }
                else if (curr.equals("time")) timepicker.setVisibility(View.VISIBLE);
                else if(curr.equals("sounds")) sounds.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        ******************************
//        ********** TimePicker **********
//        ******************************

        timepicker = findViewById(R.id.timepicker);
        timepicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                if(hour<12){
                    if(hour==0){
                        Toast.makeText(MainActivity.this, "Time set to 12:"+minute+" AM", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Time set to "+ hour +":"+minute+" AM", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    hour = hour%12;
                    if(hour==0){
                        Toast.makeText(MainActivity.this, "Time set to 12:"+minute+" PM", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Time set to "+ hour +":"+minute+" PM", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        datepicker = findViewById(R.id.datepicker);
        showdate = findViewById(R.id.showdate);

        showdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int month = datepicker.getMonth()+1;
                String today = datepicker.getDayOfMonth() + " / " + month + " / " + datepicker.getYear();
                Toast.makeText(MainActivity.this, today, Toast.LENGTH_SHORT).show();
            }
        });

//        ******************************
//        ********** Progressabar **********
//        ******************************
        progressbar1 = findViewById(R.id.progressbar1);
        progressbar2 = findViewById(R.id.progressbar2);

        progressbtn = findViewById(R.id.progressbtn);

        progressbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(progress<100){
                    progress = progress + 15;
                    progressbar1.setProgress(progress, true);
//                    progressbar2.setProgress(progress, true);
                    progressbar2.setProgress(progress);
                    if(progress>=100){
                        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.red);
                        mp.start();
                        Toast.makeText(MainActivity.this, "Progress is Complete!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    progress = progress - 100;
                    progressbar1.setProgress(progress, true);
//                    progressbar2.setProgress(progress, true);
                    progressbar2.setProgress(progress);
                    Toast.makeText(MainActivity.this, "Progress Restart!", Toast.LENGTH_SHORT).show();
                }
            }
        });






    }
}