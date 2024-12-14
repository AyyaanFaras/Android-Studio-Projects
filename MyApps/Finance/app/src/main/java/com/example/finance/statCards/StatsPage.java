package com.example.finance.statCards;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.finance.DatabaseHelper;
import com.example.finance.R;

import java.util.ArrayList;
import java.util.Collections;


public class StatsPage extends Fragment {

    ArrayList<Double> moneySpent = new ArrayList<>();
    ArrayList<String> dates = new ArrayList<>();

    private DatabaseHelper dbHelper;

    // Declare TextViews
    private TextView totalMonthlyTransaction;
    private TextView dailyAverageSpendingPerMonth;
    private TextView averageSpendingPerTransaction;
    private TextView highestMoneySpentThisMonth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View statsViewPage = inflater.inflate(R.layout.fragment_stats_page, container, false);

        // Initialize TextViews
        totalMonthlyTransaction = statsViewPage.findViewById(R.id.totalMonthlyTransaction);
        dailyAverageSpendingPerMonth = statsViewPage.findViewById(R.id.dailyAverageSpendingPerMonth);
        averageSpendingPerTransaction = statsViewPage.findViewById(R.id.averageSpendingPerTransaction);
        highestMoneySpentThisMonth = statsViewPage.findViewById(R.id.highestMoneySpentThisMonth);

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(getContext());

        // Fetch stats and update the UI
        fetchStatsData();

        return statsViewPage;

    }


    private void fetchStatsData() {
        // ArrayLists for storing money spent and dates


        double totalSpent = 0.0;
        Log.e("", "hello");


        String query = "SELECT expense_amount, expense_date FROM expense " +
                "WHERE (strftime('%Y-%m', " +
                "       CASE " +
                "           WHEN expense_date LIKE '__/__/____' THEN " +
                "               SUBSTR(expense_date, 7, 4) || '-' || SUBSTR(expense_date, 4, 2) || '-' || SUBSTR(expense_date, 1, 2) " +
                "           ELSE " +
                "               expense_date " +
                "       END) = strftime('%Y-%m', 'now'))";

        Cursor cursor = dbHelper.getRecords(query);


        // Populate the ArrayLists and calculate the total spending
        if (cursor != null) {
//            Log.e("", "hellocursor");
            int amountIndex = cursor.getColumnIndex("expense_amount");
            int dateIndex = cursor.getColumnIndex("expense_date");

//            moneySpent.add(120.1);
//            dates.add("11/12/2023");
//            totalSpent = 120.1;


            while (cursor.moveToNext()) {

                Log.e("", "hellocursor");
                double amount = cursor.getDouble(amountIndex);
                moneySpent.add(amount);
                totalSpent += amount; // Calculate total spent
                Log.e("", String.valueOf(amount));
                String date = cursor.getString(dateIndex);
                dates.add(date); // Add dates to the list

            }
            cursor.close();
        }

        // Update TextViews
        if (!moneySpent.isEmpty()) {
            int transactionCount = moneySpent.size(); // Total transactions
            double dailyAverage0 = totalSpent / 30; // Assuming 30 days in the current month
            double dailyAverage = Math.round(dailyAverage0 * 10) / 10.0;
            double avgPerTransaction0 = totalSpent / transactionCount; // Average spending per transaction
            double avgPerTransaction = Math.round(avgPerTransaction0 * 10) / 10.0;
            double maxSpent = Collections.max(moneySpent); // Highest money spent

            // Set values to TextViews
            totalMonthlyTransaction.setText("" + transactionCount);
            dailyAverageSpendingPerMonth.setText("₹ " + dailyAverage);
            averageSpendingPerTransaction.setText("₹ " + avgPerTransaction);
            highestMoneySpentThisMonth.setText("₹ " + maxSpent);
        } else {
            // If no transactions, set all values to ₹ 0
            totalMonthlyTransaction.setText("₹ 0");
            dailyAverageSpendingPerMonth.setText("₹ 0");
            averageSpendingPerTransaction.setText("₹ 0");
            highestMoneySpentThisMonth.setText("₹ 0");
        }
    }


}

