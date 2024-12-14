package com.example.finance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyCustomAdapter12 extends ArrayAdapter<TransactionModel12> {

    private Context context;
    private int resource;
    private ArrayList<TransactionModel12> transactions;

    public MyCustomAdapter12(Context context, int resource, ArrayList<TransactionModel12> transactions) {
        super(context, resource, transactions);
        this.context = context;
        this.resource = resource;
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
        }

        TransactionModel12 transaction = transactions.get(position);

        // Bind data
        ImageView icon = convertView.findViewById(R.id.delete);
        TextView type = convertView.findViewById(R.id.delete);
        TextView date = convertView.findViewById(R.id.delete);
        TextView amount = convertView.findViewById(R.id.delete);
        Button deleteButton = convertView.findViewById(R.id.delete);

        icon.setImageResource(transaction.getIcon());
        type.setText(transaction.getType());
        date.setText(transaction.getDate());
        amount.setText(transaction.getAmount());

        // Handle delete button click
        deleteButton.setOnClickListener(v -> {
            // Delete the transaction from the database
            DatabaseHelper dbHelper = new DatabaseHelper(context);
            dbHelper.deleteTransaction(transaction.getId()); // Ensure `getId()` exists in your model

            // Remove the transaction from the list and notify the adapter
            transactions.remove(position);
            notifyDataSetChanged();
        });

        return convertView;
    }
}

