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

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class addexpense extends AppCompatActivity {

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
        getWindow().setStatusBarColor(ContextCompat.getColor(addexpense.this, R.color.navbar));
        setContentView(R.layout.activity_addexpense);


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


        calendarIcon = findViewById(R.id.calendar_icon);
        dateEditText = findViewById(R.id.dateFormatID); // Make sure this EditText is in your layout file

        final Calendar calendar = Calendar.getInstance();

        // Initialize the current date values
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        calendarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(addexpense.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                                // Format the selected date
                                Calendar selectedDate = Calendar.getInstance();
                                selectedDate.set(selectedYear, selectedMonth, selectedDay);

                                // SimpleDateFormat to format the date
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                                String formattedDate = sdf.format(selectedDate.getTime());
                                date = formattedDate;
                                // Set the formatted date into the EditText
                                dateEditText.setText(formattedDate);
                            }
                        }, year, month, day);

                datePickerDialog.show();
            }
        });

        Button btn  = findViewById(R.id.addexpense);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText amt = findViewById(R.id.fullname);
                amount = String.valueOf(amt.getText());
                DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
                Double a = Double.parseDouble(amount);
                dbHelper.insertExpense(Expense, date, a, 0);
                Toast.makeText(addexpense.this, "Added Successfully", Toast.LENGTH_SHORT).show();
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




}