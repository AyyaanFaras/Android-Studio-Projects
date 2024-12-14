package com.example.finance;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyCustomAdapter extends ArrayAdapter<TransactionModel> {

    private final ArrayList<TransactionModel> transactionArrayList;

    public MyCustomAdapter(@NonNull Context context, int resource, ArrayList<TransactionModel> transactionArrayList) {
        super(context, R.layout.transaction_layout, transactionArrayList);
        this.transactionArrayList = transactionArrayList;
    }

private static class MyViewHolder
{
    TextView reason;
    TextView rupees;
    TextView date;
    ImageView img;
}

@NonNull
@Override
public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    TransactionModel transactionModel = getItem(position);
    MyViewHolder myViewHolder;
    final View result;

    if (convertView == null) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.transaction_layout, parent, false);

        myViewHolder = new MyViewHolder();
        myViewHolder.reason = convertView.findViewById(R.id.reason);
        myViewHolder.date = convertView.findViewById(R.id.date);
        myViewHolder.rupees = convertView.findViewById(R.id.rupees);
        myViewHolder.img = convertView.findViewById(R.id.image);

        result = convertView;
        convertView.setTag(myViewHolder);
    }
    else{
        myViewHolder=(MyViewHolder) convertView.getTag();
        result=convertView;
    }

    myViewHolder.reason.setText(transactionModel.getReason());
    myViewHolder.date.setText(transactionModel.getDate());
    myViewHolder.rupees.setText(transactionModel.getRupees());
    myViewHolder.img.setImageResource(transactionModel.getImage());


    return result;

}


}
