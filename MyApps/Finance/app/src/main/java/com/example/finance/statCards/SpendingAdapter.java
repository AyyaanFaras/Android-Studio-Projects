package com.example.finance.statCards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finance.R;

import java.util.List;

public class SpendingAdapter extends BaseAdapter {

    Context context;
    List<String> transaction;
    List<String> names;
    String[] colors;
    LayoutInflater inflater;

    SpendingAdapter(Context context, List<String> transaction, List<String> names, String[] colors) {
        this.context = context;
        this.transaction = transaction;
        this.names = names;
        this.colors = colors;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // If no data, return 1 to display the "No Data" message
        return transaction.isEmpty() ? 1 : transaction.size();
    }

    @Override
    public Object getItem(int i) {
        // Return null for the "No Data" case
        if (transaction.isEmpty()) {
            return null;
        }
        return transaction.get(i);
    }

    @Override
    public long getItemId(int i) {
        // Return -1 for the "No Data" case
        if (transaction.isEmpty()) {
            return -1;
        }
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_element_two_category_wise, null);
        TextView name = view.findViewById(R.id.itemTitle);
        TextView cost = view.findViewById(R.id.itemCost);
        ImageView rupeeIcon = view.findViewById(R.id.rupeeicon);


        if (transaction.isEmpty()) {
            name.setText("               No Data");
            cost.setText(""); // Leave blank
            rupeeIcon.setColorFilter(
                    android.graphics.Color.parseColor("#00000000"), // Transparent
                    android.graphics.PorterDuff.Mode.SRC_IN
            );
            rupeeIcon.setVisibility(View.INVISIBLE); // Hide the icon
        } else {
            // Populate real data
            name.setText(names.get(i));
            cost.setText("₹ " + transaction.get(i));

            rupeeIcon.setColorFilter(
                    android.graphics.Color.parseColor(colors[i % colors.length]), // Cycle through colors
                    android.graphics.PorterDuff.Mode.SRC_IN
            );
            rupeeIcon.setVisibility(View.VISIBLE); // Ensure visibility if there is data
        }
        return view;
    }
}
