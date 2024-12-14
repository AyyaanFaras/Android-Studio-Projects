package com.example.adaptersdemo.demoone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.adaptersdemo.R;

public class MyAdapter1 extends BaseAdapter {

    Context context;
    int[] data;
    LayoutInflater inflater;

    MyAdapter1(Context context, int[] data){
        this.context=context;
        this.data=data;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int i) {
        return null; //return position
    }

    @Override
    public long getItemId(int i) {
        return 0; //return id
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        return null; //set data on some view and returns that view.
        view = inflater.inflate(R.layout.ui_view_one, null);

        ImageView imgview_one = (ImageView) view.findViewById(R.id.imgview_one);

        imgview_one.setImageResource(data[i]);


        return view;
    }
}
