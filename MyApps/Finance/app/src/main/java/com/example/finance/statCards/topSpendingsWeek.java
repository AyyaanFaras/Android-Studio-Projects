package com.example.finance.statCards;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.finance.DatabaseHelper;
import com.example.finance.R;

public class topSpendingsWeek extends Fragment {

    ListView listview1;

    Cursor cursor;

    String[] spendings = new String[3];
    String[] names = new String[3];
    String[] dates = new String[3];
    DatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top_spendings, container, false);
        dbHelper = new DatabaseHelper(getContext());
        fetchTopSpendings();

        listview1 = view.findViewById(R.id.listview1);

        myAdapter1 adapter = new myAdapter1(requireContext(), spendings, names, dates);
        listview1.setAdapter(adapter);
        // Inflate the layout for this fragment
        return view;
    }

    private void fetchTopSpendings() {
        // Fetch the latest 3 expenses
        cursor = dbHelper.getTopExpensesWeek(3);

        // Initialize default values
        for (int i = 0; i < 3; i++) {
            spendings[i] = null;
            names[i] = null;
            dates[i] = null;
        }

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int index = 0;

                // Loop through the cursor and populate arrays
                do {
                    spendings[index] = cursor.getString(cursor.getColumnIndexOrThrow("expense_amount"));
                    names[index] = cursor.getString(cursor.getColumnIndexOrThrow("expense_type"));
                    dates[index] = cursor.getString(cursor.getColumnIndexOrThrow("expense_date"));
                    index++;
                } while (cursor.moveToNext() && index < 3);
            }

            cursor.close(); // Always close the cursor to prevent memory leaks
        }

        // Handle cases where no spendings or fewer than three spendings exist
        if (spendings[0] == null) {
            // No spendings at all
            spendings = new String[]{"", "", ""};
            names = new String[]{"No Spendings", "", ""};
            dates = new String[]{"", "", ""};
        } else {
            // Replace null values in the arrays with empty strings
            for (int i = 0; i < 3; i++) {
                if (spendings[i] == null) spendings[i] = "";
                if (names[i] == null) names[i] = "";
                if (dates[i] == null) dates[i] = "";
            }
        }
    }
}