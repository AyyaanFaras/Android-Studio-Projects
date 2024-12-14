package com.example.finance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.finance.databinding.ActivityHomeBinding;

public class home extends AppCompatActivity {

    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(ContextCompat.getColor(home.this, R.color.navbar));

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadfragment(new homefragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.homee){
                loadfragment(new homefragment());
            } else if (item.getItemId() == R.id.stats) {
                loadfragment(new stats());
            }else if (item.getItemId() == R.id.wallet) {
                loadfragment(new HomeFragment());
            }else if (item.getItemId() == R.id.profile) {
                loadfragment(new profile());
            }
            return true;
        });


//        Button btn = findViewById(R.id.plusbtn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), addexpense.class);
//                startActivity(i);
//            }
//        });



    }

    private void loadfragment(Fragment fra){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fra);
        ft.commit();
    }


}