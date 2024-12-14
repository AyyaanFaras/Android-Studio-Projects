package com.example.adaptersdemo.demoone;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adaptersdemo.R;

public class MyAdapter2 extends BaseAdapter {

    Context context;
    int[] data;
    String[] name;
    LayoutInflater inflater;

    MyAdapter2(Context context, int[] data, String[] name){
        this.context=context;
        this.data=data;
        this.name=name;

        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return data.length;
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

        view = inflater.inflate(R.layout.ui_view_two, null);

        ImageView imgView2 = (ImageView) view.findViewById(R.id.imgview2);
        TextView txtview2 = (TextView) view.findViewById(R.id.textview2);

        imgView2.setImageResource(data[i]);
        txtview2.setText(name[i]);

        return view;
    }
}
