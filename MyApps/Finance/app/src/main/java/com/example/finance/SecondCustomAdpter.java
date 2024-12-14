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

public class SecondCustomAdpter extends ArrayAdapter<UpComingModel> {

    private final ArrayList<UpComingModel> upComingModels;

    public SecondCustomAdpter(@NonNull Context context, int resource, ArrayList<UpComingModel> upComingModels) {
        super(context,R.layout.upcomingbills_layout, upComingModels);
        this.upComingModels = upComingModels;
    }

    private static class MyViewHolder
    {
        TextView reason;
        Button button;
        TextView date;
        ImageView img;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        UpComingModel upComingModel = getItem(position);
        MyViewHolder myViewHolder;
        final View result;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.upcomingbills_layout, parent, false);



            myViewHolder = new MyViewHolder();
            myViewHolder.reason = convertView.findViewById(R.id.reason);
            myViewHolder.date = convertView.findViewById(R.id.date);
            myViewHolder.button = convertView.findViewById(R.id.button);
            myViewHolder.img = convertView.findViewById(R.id.image);

            result = convertView;
            convertView.setTag(myViewHolder);

        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
            result = convertView;
        }
        myViewHolder.reason.setText(upComingModel.getReason());
        myViewHolder.date.setText(upComingModel.getDate());
        myViewHolder.img.setImageResource(upComingModel.getImg());


        return result;

    }
}
