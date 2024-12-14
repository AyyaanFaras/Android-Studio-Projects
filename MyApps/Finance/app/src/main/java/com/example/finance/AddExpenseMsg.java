package com.example.finance;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddExpenseMsg extends AppCompatActivity {


    String[] item = {"Food","Shopping", "Travelling", "Entertainment", "Medical", "Personal Care", "Education", "Rent", "Gifts", "Insurance", "Add Category"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItem;

    ImageView calendarIcon;
    EditText dateEditText;
    int year;
    int month;
    int day;

    String Expense, amount, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(AddExpenseMsg.this, R.color.navbar));
        setContentView(R.layout.activity_add_expense_msg);


        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        Cursor s = dbHelper.getRecords("SELECT cat_type FROM cato");

        ArrayList<String> cat = new ArrayList<>();
        if (s != null) {
            try {
                while (s.moveToNext()) {
                    String type = s.getString(0);
                    cat.add(type);
                }
            } finally {
                s.close();
            }
        }


        autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        adapterItem = new ArrayAdapter<String>(this, R.layout.list_item, cat);
        autoCompleteTextView.setAdapter(adapterItem);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Expense = adapterView.getItemAtPosition(i).toString();
                if(Expense.equals("Add Category")){
                    Intent ii = new Intent(getApplicationContext(), addcato.class);
                    startActivity(ii);
                }
                //Toast.makeText(addexpense.this, "Item"+Expense, Toast.LENGTH_SHORT).show();
            }
        });




        Button btn  = findViewById(R.id.addexpense);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = getIntent();
                String amount = intent.getStringExtra("transactionAmount");

                Toast.makeText(getApplicationContext(), amount, Toast.LENGTH_SHORT).show();
                String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                String regex = "\\d+(?:\\.\\d+)?";

                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(amount);
                String amt="";
                if (matcher.find()) {
                    amt = matcher.group();

                } else {

                }

                DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
                Double a = Double.parseDouble(amt);
                dbHelper.insertExpense(Expense, currentDate, a, 0);
                Toast.makeText(AddExpenseMsg.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), home.class);
                startActivity(i);
            }
        });

        ImageView back = findViewById(R.id.backpro);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_expense_msg);
//
//
//        Intent intent = getIntent();
//        String amount = intent.getStringExtra("transactionAmount");
//        Toast.makeText(this, amount, Toast.LENGTH_SHORT).show();
//        Intent i = new Intent(getApplicationContext(), Messagecenter.class);
//        startActivity(i);
//
//
//
//
//    }
}