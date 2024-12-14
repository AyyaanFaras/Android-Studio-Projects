package com.example.finance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "finance.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_INCOME = "income";
    private static final String TABLE_EXPENSE = "expense";
    private static final String TABLE_CAT = "cato";


    private static final String COLUMN_CAT_ID = "cat_id";
    private static final String COLUMN_CAT_TYPE = "cat_type";


    // Income table columns
    private static final String COLUMN_INCOME_ID = "income_id";
    private static final String COLUMN_INCOME_TYPE = "income_type";
    private static final String COLUMN_INCOME_DATE = "income_date";
    private static final String COLUMN_INCOME_AMOUNT = "income_amount";

    // Expense table columns
    private static final String COLUMN_EXPENSE_ID = "expense_id";
    private static final String COLUMN_EXPENSE_TYPE = "expense_type";
    private static final String COLUMN_EXPENSE_DATE = "expense_date";
    private static final String COLUMN_EXPENSE_AMOUNT = "expense_amount";
    private static final String COLUMN_REMAINING_AMOUNT = "remaining_amount";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public String getColumnExpenseAmount() {
        return COLUMN_EXPENSE_AMOUNT;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createIncomeTable = "CREATE TABLE " + TABLE_INCOME + " (" +
                COLUMN_INCOME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_INCOME_TYPE + " TEXT, " +
                COLUMN_INCOME_DATE + " DATE, " +
                COLUMN_INCOME_AMOUNT + " REAL)";
        db.execSQL(createIncomeTable);

        String createExpenseTable = "CREATE TABLE " + TABLE_EXPENSE + " (" +
                COLUMN_EXPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EXPENSE_TYPE + " TEXT, " +
                COLUMN_EXPENSE_DATE + " DATE, " +
                COLUMN_EXPENSE_AMOUNT + " REAL, " +
                COLUMN_REMAINING_AMOUNT + " REAL)";
        db.execSQL(createExpenseTable);

        String createCatTable = "CREATE TABLE " + TABLE_CAT + " (" +
                COLUMN_CAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CAT_TYPE + " TEXT )";
        db.execSQL(createCatTable);

        String[] categories = {"Add Category","Food", "Shopping", "Travelling", "Entertainment", "Medical",
                "Personal Care", "Education", "Rent", "Gifts", "Insurance"};

        for (String category : categories) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_CAT_TYPE, category);
            db.insert(TABLE_CAT, null, values);  // Use the db instance provided
        }
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INCOME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE);
        onCreate(db);
    }

    // Insert sample data into income table
    public void insertIncome(String type, String date, double amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_INCOME_TYPE, type);
        values.put(COLUMN_INCOME_DATE, date);
        values.put(COLUMN_INCOME_AMOUNT, amount);
        db.insert(TABLE_INCOME, null, values);
    }

    // Insert sample data into expense table
    public void insertExpense(String type, String date, double amount, double remaining) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EXPENSE_TYPE, type);
        values.put(COLUMN_EXPENSE_DATE, date);
        values.put(COLUMN_EXPENSE_AMOUNT, amount);
        values.put(COLUMN_REMAINING_AMOUNT, remaining);
        db.insert(TABLE_EXPENSE, null, values);
    }

    public void addcat(String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CAT_TYPE, type);
        db.insert(TABLE_CAT, null, values);
    }

    // Get records by query
    public Cursor getRecords(String query) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(query, null);
    }

    public boolean deleteDatabase(Context context) {
        return context.deleteDatabase(DATABASE_NAME);
    }

    public Cursor getLatestExpenses(int limit) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_EXPENSE_AMOUNT + ", " +
                COLUMN_EXPENSE_TYPE + ", " + COLUMN_EXPENSE_DATE +
                " FROM " + TABLE_EXPENSE +
                " ORDER BY " + COLUMN_EXPENSE_DATE + " DESC LIMIT " + limit;
        return db.rawQuery(query, null);
    }

    public Cursor getTopExpenses(int limit) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_EXPENSE_AMOUNT + ", " +
                COLUMN_EXPENSE_TYPE + ", " + COLUMN_EXPENSE_DATE +
                " FROM " + TABLE_EXPENSE +
                " ORDER BY " + COLUMN_EXPENSE_AMOUNT + " DESC LIMIT " + limit;
        return db.rawQuery(query, null);
    }
    public Cursor getTopExpensesWeek(int limit) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_EXPENSE_AMOUNT + ", " +
                COLUMN_EXPENSE_TYPE + ", " + COLUMN_EXPENSE_DATE +
                " FROM " + TABLE_EXPENSE +
                " WHERE substr(" + COLUMN_EXPENSE_DATE + ", 7, 4) || '-' || " +
                "substr(" + COLUMN_EXPENSE_DATE + ", 4, 2) || '-' || " +
                "substr(" + COLUMN_EXPENSE_DATE + ", 1, 2) >= date('now', '-7 days')" +
                " ORDER BY " + COLUMN_EXPENSE_AMOUNT + " DESC LIMIT " + limit;
        return db.rawQuery(query, null);
    }
    public Cursor getTopExpensesMonth(int limit) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_EXPENSE_AMOUNT + ", " +
                COLUMN_EXPENSE_TYPE + ", " + COLUMN_EXPENSE_DATE +
                " FROM " + TABLE_EXPENSE +
                " WHERE substr(" + COLUMN_EXPENSE_DATE + ", 7, 4) || '-' || " +
                "substr(" + COLUMN_EXPENSE_DATE + ", 4, 2) || '-' || " +
                "substr(" + COLUMN_EXPENSE_DATE + ", 1, 2) >= date('now', '-30 days')" +
                " ORDER BY " + COLUMN_EXPENSE_AMOUNT + " DESC LIMIT " + limit;
        return db.rawQuery(query, null);
    }
    public Cursor getTopExpensesYear(int limit) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_EXPENSE_AMOUNT + ", " +
                COLUMN_EXPENSE_TYPE + ", " + COLUMN_EXPENSE_DATE +
                " FROM " + TABLE_EXPENSE +
                " WHERE substr(" + COLUMN_EXPENSE_DATE + ", 7, 4) || '-' || " +
                "substr(" + COLUMN_EXPENSE_DATE + ", 4, 2) || '-' || " +
                "substr(" + COLUMN_EXPENSE_DATE + ", 1, 2) >= date('now', '-365 days')" +
                " ORDER BY " + COLUMN_EXPENSE_AMOUNT + " DESC LIMIT " + limit;
        return db.rawQuery(query, null);
    }

    public String getColumnExpenseDate() {
        return COLUMN_EXPENSE_DATE;
    }

    public void deleteTransaction(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("expense", "expense_id = ?", new String[]{String.valueOf(id)});
        db.close();
    }


}
