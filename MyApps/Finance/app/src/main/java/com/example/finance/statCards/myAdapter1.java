package com.example.finance.statCards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finance.R;

public class myAdapter1 extends BaseAdapter {
    ImageView imgv;

    Context context;
    String[] transaction;
    String[] names;
    String[] dates;
    LayoutInflater inflater;

    myAdapter1(Context context, String[] transaction, String[] names, String[] dates){
        this.context=context;
        this.transaction=transaction;
        this.names=names;
        this.dates=dates;

        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return transaction.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.list_element_one_top_spendings, null);
        TextView name = view.findViewById(R.id.itemTitle);
        TextView date = view.findViewById(R.id.itemDate);
        TextView cost = view.findViewById(R.id.itemCost);

        name.setText(names[i]);
        date.setText(dates[i]);
        cost.setText("" + transaction[i]);
        imgv = view.findViewById(R.id.imgview2);

        imgv.setColorFilter(
                android.graphics.Color.parseColor("#00000000"),
                android.graphics.PorterDuff.Mode.SRC_IN
        );
        return view;

    }
}
