package com.example.finance.statCards;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.finance.Blank;
import com.example.finance.CustomScrollView;
import com.example.finance.DatabaseHelper;
import com.example.finance.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.List;

public class categoryWiseSpending extends Fragment {


    ListView listview_categorywise;
    PieChart pieChart;

    // Data Arrays
    List<String> spendings = new ArrayList<>();
    List<String> names = new ArrayList<>();

    String[] colors = {
            "#e63946",  // red
            "#457b9d",  // steel blue
            "#1d3557",  // dark blue
            "#f1c40f",  // gold
            "#e67e22",  // orange
            "#2ecc71",  // emerald green
            "#9b59b6",  // purple
            "#34495e",  // dark grayish blue
            "#f39c12",  // mustard yellow
            "#7f8c8d",  // gray
            "#2980b9",  // blue
            "#16a085",  // teal
            "#d35400"   // deep orange
    };

    DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Blank parentFragment = (Blank) getParentFragment();
        View view = inflater.inflate(R.layout.fragment_category_wise_spending, container, false);

        // Initialize Database Helper
        dbHelper = new DatabaseHelper(getContext());

        // Fetch data from the database
        fetchCategoryWiseSpending();

        // Initialize Views
        pieChart = view.findViewById(R.id.pieChart);
        listview_categorywise = view.findViewById(R.id.listview_categorywise_spending);

        // Disable scrolling while interacting with the PieChart
        pieChart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (parentFragment != null) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            parentFragment.setScrollEnabled(false); // Disable scrolling
                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                            parentFragment.setScrollEnabled(true); // Enable scrolling
                            break;
                    }
                }
                return false;
            }
        });

        // Populate PieChart
        if (!names.isEmpty() && !spendings.isEmpty()) {
            for (int i = 0; i < names.size(); i++) {
                pieChart.addPieSlice(new PieModel(
                        names.get(i),
                        Integer.parseInt(spendings.get(i)),
                        Color.parseColor(colors[i % colors.length]) // Cycle through colors
                ));
            }
        }

        // Set Adapter for ListView
        SpendingAdapter adapter = new SpendingAdapter(getContext(), spendings, names, colors);
        listview_categorywise.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listview_categorywise);

        // Highlight corresponding PieChart slice on ListView item click
        listview_categorywise.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!spendings.isEmpty()) { // Avoid crashes if there is no data
                    pieChart.setCurrentItem(position);
                }
            }
        });

        return view;
    }

    private void fetchCategoryWiseSpending() {

        String query = "SELECT expense_type, SUM(expense_amount) AS total_amount FROM expense GROUP BY expense_type ORDER BY total_amount DESC";
        Cursor cursor = dbHelper.getRecords(query);

        if (cursor != null && cursor.moveToFirst()) {
            // Clear any previous data
            spendings.clear();
            names.clear();

            // Populate the data from the cursor
            do {
                String expenseType = cursor.getString(cursor.getColumnIndexOrThrow("expense_type"));
                String totalAmount = cursor.getString(cursor.getColumnIndexOrThrow("total_amount"));

                names.add(expenseType);
                spendings.add(totalAmount);
            } while (cursor.moveToNext());

            cursor.close();
        }

        // Handle case where there are no spendings
        if (names.isEmpty()) {
            names.add("No Data");
            spendings.add("0");
        }
    }

    private void setListViewHeightBasedOnChildren(ListView listView) {
        SpendingAdapter listAdapter = (SpendingAdapter) listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

}
