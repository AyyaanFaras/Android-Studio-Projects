package com.example.adaptersdemo.fragsdemo;

import android.content.Context;
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

public class fragsample1 extends Fragment {

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
//        Toast.makeText(context, "onAttach() called", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Toast.makeText(getActivity(), "onCreate() called", Toast.LENGTH_SHORT).show();
        
    }

    @Override
    public void onResume() {
        super.onResume();
//        Toast.makeText(getActivity(), "onResume() called", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_fragsample1, container, false);

        Button btn_f1 = view.findViewById(R.id.btn_f1);
        TextView txt_f1 = view.findViewById(R.id.txt_f1);

        btn_f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Welcome to Fragment 1", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}