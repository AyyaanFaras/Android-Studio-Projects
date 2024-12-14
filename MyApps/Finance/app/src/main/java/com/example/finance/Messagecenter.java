package com.example.finance;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.Manifest;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Messagecenter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagecenter);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 1);

        TextView msg = findViewById(R.id.msg);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 1);
        } else {
            displayTodaysTransactionMessages(msg);
        }

        ImageView acback = findViewById(R.id.accback2);
        acback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        TextView msgView = findViewById(R.id.msg);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                displayTodaysTransactionMessages(msgView);
            } else {
                Toast.makeText(this, "Permission to read SMS was denied.", Toast.LENGTH_SHORT).show();
                msgView.setText("Permission denied.");
            }
        }
    }




    private void displayTodaysTransactionMessages(TextView msgView) {
        StringBuilder transactionMessages = new StringBuilder();
        Uri inboxUri = Uri.parse("content://sms/inbox");
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        LinearLayout transactionLayout = findViewById(R.id.transactionLayout);
        transactionLayout.removeAllViews();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Cursor cursor = getContentResolver().query(inboxUri, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {

                int bodyIndex = cursor.getColumnIndex("body");
                int dateIndex = cursor.getColumnIndex("date");

                if (bodyIndex != -1 && dateIndex != -1) {
                    String smsBody = cursor.getString(bodyIndex);
                    String smsDate = cursor.getString(dateIndex);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String smsDateFormatted = sdf.format(new Date(Long.parseLong(smsDate)));

                    // Check if the message is relevant (contains certain keywords) and matches today's date
                    if ((smsBody.toLowerCase().contains("transaction")
                            || smsBody.toLowerCase().contains("debited")
                            || smsBody.toLowerCase().contains("credited")
                            || smsBody.toLowerCase().contains("account")
                            || smsBody.toLowerCase().contains("payment")
                            || smsBody.toLowerCase().contains("balance"))
                            && smsDateFormatted.equals(currentDate)) {

                        // Check if the message was already accepted or rejected
                        if (!isMessageAccepted(sharedPreferences, smsBody) && !isMessageRejected(sharedPreferences, smsBody)) {
                            String amountPattern = "Rs\\.?\\s?\\d+(?:\\.\\d{1,2})?";
                            Pattern pattern = Pattern.compile(amountPattern);
                            Matcher matcher = pattern.matcher(smsBody);

                            StringBuilder messageWithButtons = new StringBuilder();
                            while (matcher.find()) {
                                messageWithButtons.append(matcher.group()).append("\n");
                            }

                            if (messageWithButtons.length() > 0) {
                                TextView messageText = new TextView(this);
                                messageText.setText(messageWithButtons.toString());
                                messageText.setTextSize(16);
                                messageText.setTypeface(null, Typeface.BOLD);
                                messageText.setTextColor(Color.parseColor("#000000"));

                                messageText.setPadding(0, 10, 50, 10);

                                Button acceptButton = new Button(this);

                                acceptButton.setText("Accept");
                                acceptButton.setOnClickListener(v -> {
                                    // Extract amount from the message using regex
                                    String amountPattern1 = "Rs\\.?\\s*\\d+(?:\\.\\d{2})?"; // Pattern to match "Rs 1.0", "Rs1.00", etc.
                                    Pattern pattern1 = Pattern.compile(amountPattern1);
                                    Matcher matcher1 = pattern1.matcher(smsBody);

                                    String amount = "";
                                    if (matcher1.find()) {
                                        amount = matcher1.group();  // Extract the matched amount (e.g., "Rs 100", "Rs1.00")
                                    }

                                    // Mark the message as accepted
                                    markMessageAsAccepted(sharedPreferences, smsBody);

                                    // Show a toast for the accepted transaction
                                    Toast.makeText(this, "Transaction Accepted", Toast.LENGTH_SHORT).show();

                                    // Remove this message from the layout
                                    transactionLayout.removeView(messageText);

                                    // Prepare the intent to pass only the amount to the next activity
                                    Intent i = new Intent(getApplicationContext(), AddExpenseMsg.class);
                                    i.putExtra("transactionAmount", amount);  // Pass only the extracted amount
                                    startActivity(i);
                                });

                                Button rejectButton = new Button(this);
                                rejectButton.setText("Reject");


                                rejectButton.setOnClickListener(v -> {
                                    // Mark the message as rejected
                                    markMessageAsRejected(sharedPreferences, smsBody);

                                    // Show a toast for the rejected transaction
                                    Toast.makeText(this, "Transaction Rejected", Toast.LENGTH_SHORT).show();

                                    // Remove this message from the layout
                                    transactionLayout.removeView(messageText);
                                    Intent i = new Intent(getApplicationContext(), Messagecenter.class);
                                    startActivity(i);
                                });

                                LinearLayout messageContainer = new LinearLayout(this);
                                messageContainer.setOrientation(LinearLayout.HORIZONTAL);
                                messageContainer.setPadding(0, 10, 0, 10);
                                messageContainer.addView(messageText);
                                messageContainer.addView(acceptButton);
                                messageContainer.addView(rejectButton);
                                transactionLayout.addView(messageContainer);
                            }
                        }
                    }
                }
            }
            cursor.close();
        }

        if (transactionLayout.getChildCount() == 0) {
            msgView.setText("No transaction-related amounts found for today.");
        }
    }

    // Check if the message has been rejected
    private boolean isMessageRejected(SharedPreferences sharedPreferences, String messageBody) {
        return sharedPreferences.getBoolean(messageBody + "_rejected", false);
    }

    // Mark the message as rejected
    private void markMessageAsRejected(SharedPreferences sharedPreferences, String messageBody) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(messageBody + "_rejected", true);  // Store the message as rejected
        editor.apply();
    }


    private boolean isMessageAccepted(SharedPreferences sharedPreferences, String messageBody) {
        return sharedPreferences.getBoolean(messageBody, false);
    }


    private void markMessageAsAccepted(SharedPreferences sharedPreferences, String messageBody) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(messageBody, true);  // Store the message as accepted
        editor.apply();
    }







}