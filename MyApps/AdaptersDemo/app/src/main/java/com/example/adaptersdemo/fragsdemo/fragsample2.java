package com.example.adaptersdemo.fragsdemo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adaptersdemo.R;

public class fragsample2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragsample2, container, false);

        Button btnf2 = view.findViewById(R.id.btn_f2);
        TextView txtf2 = view.findViewById(R.id.txt_f2);
        
        btnf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Byee", Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }
}