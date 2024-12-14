package com.example.finance;

import android.graphics.Color;
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

import com.example.finance.statCards.StatsPage;
import com.example.finance.statCards.StatsPageMonth;
import com.example.finance.statCards.StatsPageWeek;
import com.example.finance.statCards.StatsPageYear;
import com.example.finance.statCards.categoryWiseIncome;
import com.example.finance.statCards.categoryWiseIncomeMonth;
import com.example.finance.statCards.categoryWiseIncomeWeek;
import com.example.finance.statCards.categoryWiseIncomeYear;
import com.example.finance.statCards.categoryWiseSpending;
import com.example.finance.statCards.categoryWiseSpendingmonth;
import com.example.finance.statCards.categoryWiseSpendingweek;
import com.example.finance.statCards.categoryWiseSpendingyear;
import com.example.finance.statCards.topSpendings;
import com.example.finance.statCards.topSpendingsMonth;
import com.example.finance.statCards.topSpendingsWeek;
import com.example.finance.statCards.topSpendingsYear;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

public class Blank extends Fragment {

    Button week, month, year;

    String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    int[] spent = {18000, 16000, 20000, 22000, 15000, 17000, 25000, 24000, 19000, 21000, 23000, 26000};
    int val;

    private CustomScrollView scrollView;
    ValueLineChart valueLineChart;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        scrollView = view.findViewById(R.id.scrollView);

//        LineChartCode Here. Not in fragment, directly added here.
        valueLineChart = view.findViewById(R.id.valueLineChart);
        ValueLineSeries series = new ValueLineSeries();
        series.setColor(Color.parseColor("#378a84"));

        series.addPoint(new ValueLinePoint("Sun", 3));
        series.addPoint(new ValueLinePoint("Mon", 4));
        series.addPoint(new ValueLinePoint("Tue", 5));
        series.addPoint(new ValueLinePoint("Wed", 2));
        series.addPoint(new ValueLinePoint("Thu", 6));
        series.addPoint(new ValueLinePoint("Fri", 3));
        series.addPoint(new ValueLinePoint("Sat", 4));
        valueLineChart.addSeries(series);
        valueLineChart.startAnimation();
//        LineChartCode Ends Here!!!!!!!!

        val = 0;
        week = view.findViewById(R.id.week);
        month = view.findViewById(R.id.month);
        year = view.findViewById(R.id.year);

        loadFragment(new topSpendings());
        LoadFragmentCategoryWiseSpending(new categoryWiseSpending());
        LoadFragmentCategoryWiseIncome(new categoryWiseIncome());
        LoadFragmentStatsPage(new StatsPage());

        // Set click listeners
        week.setOnClickListener(v -> {
            val = 1; // Update val for weekButton
            highlightButton(week);
            loadFragment(new topSpendingsWeek());
            LoadFragmentCategoryWiseSpending(new categoryWiseSpendingweek());
            LoadFragmentCategoryWiseIncome(new categoryWiseIncomeWeek());
            LoadFragmentStatsPage(new StatsPageWeek());
            Log.d("ButtonClick", "Week button clicked. val = " + val); // Debug log
        });

        month.setOnClickListener(v -> {
            val = 2; // Update val for monthButton
            highlightButton(month);
            loadFragment(new topSpendingsMonth());
            LoadFragmentCategoryWiseSpending(new categoryWiseSpendingmonth());
            LoadFragmentCategoryWiseIncome(new categoryWiseIncomeMonth());
            LoadFragmentStatsPage(new StatsPageMonth());
            Log.d("ButtonClick", "Month button clicked. val = " + val); // Debug log
        });

        year.setOnClickListener(v -> {
            val = 3; // Update val for yearButton
            highlightButton(year);
            loadFragment(new topSpendingsYear());
            LoadFragmentCategoryWiseSpending(new categoryWiseSpendingyear());
            LoadFragmentCategoryWiseIncome(new categoryWiseIncomeYear());
            LoadFragmentStatsPage(new StatsPageYear());
            Log.d("ButtonClick", "Year button clicked. val = " + val); // Debug log
        });


        return view;
    }

    private void highlightButton(Button selectedButton) {
        // Reset all buttons
        week.setSelected(false);
        month.setSelected(false);
        year.setSelected(false);

        // Highlight the selected button
        selectedButton.setSelected(true);
    }

    public void loadFragment(Fragment fragment){

        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.topSpending, fragment);
        ft.commit();
    }

    public void LoadFragmentCategoryWiseSpending(Fragment fragment){
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.categoryWiseSpending, fragment);
        ft.commit();
    }

    public void LoadFragmentCategoryWiseIncome(Fragment fragment){
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.categoryWiseIncome, fragment);
        ft.commit();
    }

    public void LoadFragmentStatsPage(Fragment fragment){
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.stats, fragment);
        ft.commit();
    }

    public void setScrollEnabled(boolean enabled) {
        if (scrollView != null) {
            scrollView.setScrollingEnabled(enabled);
        }
    }


}