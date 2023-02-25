package me.hackathon2023.budgetmanager.data;

import android.database.Cursor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import me.hackathon2023.budgetmanager.BudgetManager;
import me.hackathon2023.budgetmanager.database.DatabaseManager;

public class User
{
    private String name;
    private String email;
    private String password;

    private List<Expenses> expenses = new ArrayList<>();

    public static final int MIN_PASSWORD_LENGTH = 8;

    public User(String email, String password)
    {
        loadName();

        this.email = email;
        this.password = password;

        loadExpenses();
    }

    private void loadName()
    {
        Cursor cursor = BudgetManager.getDatabaseManager().getReading().query(
                DatabaseManager.USERS_TABLE, null, "email='" + email + "'", null,
                null, null, null);

        name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
    }

    private void loadExpenses()
    {

        Cursor cursor = BudgetManager.getDatabaseManager().getReading().query(
                DatabaseManager.EXPENSES_TABLE, null, "email='" + email + "'", null,
                null, null, null);

        if (cursor != null)
        {
            // keep going until end of results
            while (cursor.moveToNext())
            {
                String type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                float total = cursor.getFloat(cursor.getColumnIndexOrThrow("total"));

                // add into their expenses cache
                expenses.add(new Expenses(type, name, total));
            }
        }
    }
}
