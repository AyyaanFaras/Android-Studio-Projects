package com.example.finance;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homefragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homefragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public homefragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homefragment.
     */
    // TODO: Rename and change types and number of parameters
    public static homefragment newInstance(String param1, String param2) {
        homefragment fragment = new homefragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    private DatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_homefragment, container, false);



        return v;
    }


    private DatabaseHelper databaseHelper;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Inflate the layout for this fragment
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        TextView greetingTextView = view.findViewById(R.id.GreetingTime);
        if (hourOfDay >= 6 && hourOfDay < 12) {
            greetingTextView.setText("Good Morning");
        } else if (hourOfDay >= 12 && hourOfDay < 18) {
            greetingTextView.setText("Good Afternoon");
        } else if (hourOfDay >= 18 && hourOfDay < 21) {
            greetingTextView.setText("Good Evening");
        } else {
            greetingTextView.setText("Hello");
        }
        double total = 0;

        TextView amtmain = view.findViewById(R.id.Incomemain);

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String name = sharedPreferences.getString("fullname", "Finance");
        TextView t = view.findViewById(R.id.Homename);
        t.setText(name);

        Button btn = view.findViewById(R.id.plusbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), addexpense.class);
                startActivity(i);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.transactionsRecyclerView);
        TextView noTransactionsTextView = view.findViewById(R.id.noTransactionsTextView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Transaction> transactions = fetchTransactions();


        if (transactions.isEmpty()) {

            noTransactionsTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {

            noTransactionsTextView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);


            List<Transaction> limitedTransactions = transactions.size() > 5 ? transactions.subList(0, 5) : transactions;

            TransactionAdapter adapter = new TransactionAdapter(limitedTransactions);
            recyclerView.setAdapter(adapter);

        }

        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        Cursor s =  dbHelper.getRecords("SELECT expense_amount FROM expense");
        ArrayList<ContactModel> arrcon = new ArrayList<>();
           while(s.moveToNext()){
               ContactModel model = new ContactModel();

               model.exp_amt = s.getDouble(0);
               arrcon.add(model);
           }
        if(arrcon.isEmpty()){
            TextView ttt = view.findViewById(R.id.expensemain);
            ttt.setText("₹"+"0.0");
        }else {

            for(int i=0; i<arrcon.size(); i++){
                total = total + arrcon.get(i).exp_amt;
            }
            TextView ttt = view.findViewById(R.id.expensemain);
            ttt.setText("₹"+ total);

        }

        incomemain(view);
        totalbalance(view);

        ImageView bell = view.findViewById(R.id.bell);
        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Messagecenter.class);
                startActivity(i);
            }
        });



    }


    public void incomemain(View view){
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        Cursor s =  dbHelper.getRecords("SELECT income_amount \n" +
                "FROM income \n" +
                "WHERE income_id = (SELECT MAX(income_id) FROM income);");
        ArrayList<ContactIncome> arrcon = new ArrayList<>();
        while(s.moveToNext()){
            ContactIncome model = new ContactIncome();

            model.inc_amt = s.getDouble(0);
            arrcon.add(model);
        }
        if(arrcon.isEmpty()){
            TextView ttt = view.findViewById(R.id.Incomemain);
            ttt.setText("₹"+"0.0");
        }else {
            double totalexp = 0;
            for(int i=0; i<arrcon.size(); i++){
                totalexp = totalexp + arrcon.get(i).inc_amt;
            }
            TextView ttt = view.findViewById(R.id.Incomemain);
            ttt.setText("₹"+ totalexp);
        }

    }

    public double totalbal(View view){
        double totalexp = 0;
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        Cursor s =  dbHelper.getRecords("SELECT expense_amount FROM expense");
        ArrayList<ContactModel> arrcon = new ArrayList<>();
        while(s.moveToNext()){
            ContactModel model = new ContactModel();

            model.exp_amt = s.getDouble(0);
            arrcon.add(model);
        }
        if(arrcon.isEmpty()){
          totalexp=0;
        }else {

            for(int i=0; i<arrcon.size(); i++){
                totalexp = totalexp + arrcon.get(i).exp_amt;
            }
        }
        return totalexp;
    }

    public void totalbalance(View view){


        double t = totalbal(view);
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        Cursor s1 =  dbHelper.getRecords("SELECT income_amount FROM income");
        ArrayList<ContactIncome> arrcon1 = new ArrayList<>();
        while(s1.moveToNext()){
            ContactIncome model = new ContactIncome();

            model.inc_amt = s1.getDouble(0);
            arrcon1.add(model);
        }
        if(arrcon1.isEmpty()){
            TextView ttt = view.findViewById(R.id.totalbalance);
            ttt.setText("₹ "+"0.0");
        }else {
            double total = 0;
            for(int i=0; i<arrcon1.size(); i++){
                total = total + arrcon1.get(i).inc_amt;
            }
            //Log.e("", String.valueOf(t));
            total = total-t;
            TextView ttt = view.findViewById(R.id.totalbalance);
            ttt.setText("₹ "+ total);
        }
    }




    private List<Transaction> fetchTransactions() {

        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        Cursor s =  dbHelper.getRecords("SELECT expense_type, expense_amount \n" +
                "FROM expense \n" +
                "ORDER BY expense_id DESC \n" +
                "LIMIT 5;\n");
        ArrayList<ContactModel> arrcon = new ArrayList<>();
        while(s.moveToNext()){
            ContactModel model = new ContactModel();
            model.expensetype = s.getString(0);
            model.exp_amt = s.getDouble(1);
            arrcon.add(model);
        }

        if(arrcon.isEmpty()){
            List<Transaction> transactions = new ArrayList<>();
            return transactions;
        }else {
            List<Transaction> transactions = new ArrayList<>();
            int z=1;
            for(int i=0; i<arrcon.size(); i++){
                transactions.add(new Transaction(z, arrcon.get(i).expensetype, "- ₹"+ arrcon.get(i).exp_amt));
                z++;
            }
            return transactions;
        }
    }
}