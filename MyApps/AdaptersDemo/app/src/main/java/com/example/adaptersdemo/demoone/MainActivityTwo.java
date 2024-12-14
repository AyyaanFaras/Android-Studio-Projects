package com.example.adaptersdemo.demoone;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adaptersdemo.R;

public class MainActivityTwo extends AppCompatActivity {

    int[] arr = {R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green};
    String[] str = {"all", "black", "white", "green", "all", "black", "white", "green", "all", "black", "white", "green", "all", "black", "white", "green", "all", "black", "white", "green", "all", "black", "white", "green", "all", "black", "white", "green"};

    ListView listview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listview2 = (ListView) findViewById(R.id.listview2);

        MyAdapter2 adaptertwo = new MyAdapter2(getApplicationContext(), arr, str);
        listview2.setAdapter(adaptertwo);



    }
}