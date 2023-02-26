package me.hackathon2023.budgetmanager.data;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import me.hackathon2023.budgetmanager.BudgetManager;
import me.hackathon2023.budgetmanager.database.DatabaseManager;

public class User
{
    private String name;
    private String email;
    private String password;

    private float income;
    private float expense;
    private float balance;

    private List<Transactions> transactions = new ArrayList<>();

    public static final int MIN_PASSWORD_LENGTH = 8;

    public User(String email, String password)
    {
        this.email = email;
        this.password = password;

        loadName();
        loadTransactions();
    }

    private void loadName()
    {
        Cursor cursor = BudgetManager.getDatabaseManager().getReading().query(
                DatabaseManager.USERS_TABLE, null, "email='" + email + "'", null,
                null, null, null);

        if (cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        }

        cursor.close();
    }

    public void addTransaction(String type, String date, float total)
    {
        // add to appropriate balances
        if (total < 0.0)
            expense += total;
        else
            income += total;

        balance += total;
        transactions.add(new Transactions(type, date, total));
    }

    private void loadTransactions()
    {

        Cursor cursor = BudgetManager.getDatabaseManager().getReading().query(
                DatabaseManager.TRANSACTIONS_TABLE, null, "email='" + email + "'", null,
                null, null, null);

        if (cursor != null)
        {
            // keep going until end of results
            while (cursor.moveToNext())
            {
                String type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                float total = cursor.getFloat(cursor.getColumnIndexOrThrow("total"));

                // add into their expenses cache
                addTransaction(type, date, total);
            }
        }
    }

    public float getIncome()
    {
        return income;
    }

    public float getExpense()
    {
        return expense;
    }

    public float getBalance()
    {
        return balance;
    }

    public String getEmail(){
        return email;
    }

    public List<Transactions> getTransactions()
    {
        return transactions;
    }
}
