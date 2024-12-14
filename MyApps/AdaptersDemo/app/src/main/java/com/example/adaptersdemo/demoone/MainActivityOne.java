package com.example.adaptersdemo.demoone;

import android.os.Bundle;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adaptersdemo.R;

public class MainActivityOne extends AppCompatActivity {

    GridView gridview_one;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_one);

        int[] arr = {R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green, R.drawable.all, R.drawable.black, R.drawable.white, R.drawable.green};

        gridview_one = (GridView) findViewById(R.id.gridview_one);

        MyAdapter1 adapterOne = new MyAdapter1(getApplicationContext(), arr);
        gridview_one.setAdapter(adapterOne);

    }
}