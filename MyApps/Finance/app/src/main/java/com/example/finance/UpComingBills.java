package com.example.finance;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class UpComingBills extends Fragment {

    private ListView listView; // Replace with RecyclerView if using RecyclerView
    private SecondCustomAdpter secondAdapter; // Your custom adapter
    private ArrayList<UpComingModel> dataList; // Data for the adapter

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_up_coming_bills, container, false);

        // Initialize the ListView
        listView = view.findViewById(R.id.list); // Ensure this matches your XML ID

        // Populate the data list
        dataList = new ArrayList<>();
        dataList.add(new UpComingModel("Rent", "Tomorrow", R.drawable.rent ));
        dataList.add(new UpComingModel("Education", "12/12/2024", R.drawable.education));
        dataList.add(new UpComingModel("Insurance", "15/12/2024", R.drawable.insurance));

        // Initialize the adapter
        secondAdapter = new SecondCustomAdpter(getContext(), R.layout.upcomingbills_layout, dataList);

        // Set the adapter to the ListView
        listView.setAdapter(secondAdapter);

        return view;
    }


}