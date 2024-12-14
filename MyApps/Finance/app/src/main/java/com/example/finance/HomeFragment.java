package com.example.finance;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Get references to buttons
        Button btnTransactions = view.findViewById(R.id.btn_transactions);
        Button btnUpcomingBills = view.findViewById(R.id.btn_upcoming_bills);

        // Set initial fragment
        if (savedInstanceState == null) {
            replaceFragment(new Transactions(), view);
        }

        // Set click listeners for buttons
        btnTransactions.setOnClickListener(v -> {
            Log.d(TAG, "Transactions button clicked");
            replaceFragment(new Transactions(), view);
        });
        btnUpcomingBills.setOnClickListener(v -> {
            Log.d(TAG, "Upcoming Bills button clicked");
            replaceFragment(new UpComingBills(), view);
        });

        ImageView adm = view.findViewById(R.id.addmoney);
        adm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getContext(), AddIncome.class);
                startActivity(i);
            }
        });

        totalbalance(view);

        ImageView del = view.findViewById(R.id.delete);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getContext(), Delete.class);
                startActivity(i);
            }
        });


        ImageView back1 = view.findViewById(R.id.backpro);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new homefragment();

                // Perform FragmentTransaction
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                transaction.replace(R.id.frameLayout, fragment); // Replace with your container ID
                transaction.addToBackStack(null); // Optional: Add to back stack for navigation
                transaction.commit();
            }
        });

        return view;



    }


    public double totalbal(View view){
        double totalexp = 0;
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        Cursor s =  dbHelper.getRecords("SELECT expense_amount FROM expense");
        ArrayList<ContactModel> arrcon = new ArrayList<>();
        while(s.moveToNext()){
            ContactModel model = new ContactModel();

            model.exp_amt = s.getDouble(0);
            arrcon.add(model);
        }
        if(arrcon.isEmpty()){
            totalexp=0;
        }else {

            for(int i=0; i<arrcon.size(); i++){
                totalexp = totalexp + arrcon.get(i).exp_amt;
            }
        }
        return totalexp;
    }
    public void totalbalance(View view){
        double t = totalbal(view);
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        Cursor s =  dbHelper.getRecords("SELECT income_amount FROM income");
        ArrayList<ContactIncome> arrcon = new ArrayList<>();
        while(s.moveToNext()){
            ContactIncome model = new ContactIncome();

            model.inc_amt = s.getDouble(0);
            arrcon.add(model);
        }
        if(arrcon.isEmpty()){
            TextView ttt = view.findViewById(R.id.viewmoney);
            ttt.setText("₹ "+"0.0");
        }else {
            double total = 0;
            for(int i=0; i<arrcon.size(); i++){
                total = total + arrcon.get(i).inc_amt;
            }
            total = total - t;
            TextView ttt = view.findViewById(R.id.viewmoney);
            ttt.setText("₹ "+ total);
        }
    }

    // Method to replace fragments
    private void replaceFragment(Fragment fragment, View view) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }


}

