package com.example.finance;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Transactions extends Fragment {

    private ListView listView;
    private MyCustomAdapter myCustomAdapter;
    private ArrayList<TransactionModel> transactionList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transactions, container, false);

        // Initialize the ListView from the fragment's layout
        listView = view.findViewById(R.id.listview);

        // Fetch transactions from the database
        transactionList = fetchTransactions();

        // Initialize the adapter with fetched transactions
        myCustomAdapter = new MyCustomAdapter(getActivity(), R.layout.transaction_layout, transactionList);

        // Set the adapter to the ListView
        listView.setAdapter(myCustomAdapter);

        return view;
    }

    private ArrayList<TransactionModel> fetchTransactions() {
        ArrayList<TransactionModel> transactions = new ArrayList<>();
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());

        // Query to fetch all transactions including the date
        Cursor cursor = dbHelper.getRecords(
                "SELECT expense_type, expense_amount, expense_date " +
                        "FROM expense " +
                        "ORDER BY expense_id DESC;"
        );

        // Iterate over cursor and populate transaction list
        while (cursor.moveToNext()) {
            String expenseType = cursor.getString(0); // Column 1: expense_type
            double expenseAmount = cursor.getDouble(1); // Column 2: expense_amount
            String expenseDate = cursor.getString(2); // Column 3: expense_date

            // Map the expense type to an icon
            int expenseIcon = getExpenseIcon(expenseType);

            // Add transaction to the list
            transactions.add(new TransactionModel(
                    expenseIcon, // Image based on expense type
                    expenseType,
                    expenseDate,
                    "-₹ "+ expenseAmount
            ));
        }

        cursor.close(); // Close the cursor to free resources
        return transactions;
    }

    // //////////////YAsh ADd this is pending/////////////////
    private int getExpenseIcon(String expenseType) {
        switch (expenseType.toLowerCase()) {
            case "food":
                return R.drawable.food; // Replace with actual drawable
            case "shopping":
                return R.drawable.shopping; // Replace with actual drawable
            case "travelling":
                return R.drawable.travel; // Replace with actual drawable
            case "entertainment":
                return R.drawable.entertain; // Replace with actual drawable
            case "medical":
                return R.drawable.medicine;
            case "personal care":
                return R.drawable.pc;
            case "education":
                return R.drawable.education;
            case "rent":
                return R.drawable.rent;
            case "gifts":
                return R.drawable.giftbox;
            case "insurance":
                return R.drawable.insurance;

            default:
                return R.drawable.accountinfo; // A default icon if no match
        }
    }
}
