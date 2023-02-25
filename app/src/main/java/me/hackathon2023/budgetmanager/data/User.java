package me.hackathon2023.budgetmanager.data;

import android.database.Cursor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import me.hackathon2023.budgetmanager.BudgetManager;
import me.hackathon2023.budgetmanager.database.DatabaseManager;

public class User
{
    private String name;
    private String email;
    private String password;
    private int id;

    private List<Expenses> expenses = new ArrayList<>();

    public static final int MIN_PASSWORD_LENGTH = 8;

    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
        this.id = loadID();

        loadExpenses();
    }

    private int loadID()
    {
        String[] emailList = new String[1];
        emailList[0] = email;

        Cursor cursor = BudgetManager.getDatabaseManager().getReading().query(
                DatabaseManager.USERS_TABLE, null, "email", emailList,
                null, null, "DESC");

        if (cursor != null)
        {
            id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        }
        return id;
    }

    private void loadExpenses()
    {
        String[] idList = new String[1];
        idList[0] = String.valueOf(id);

        Cursor cursor = BudgetManager.getDatabaseManager().getReading().query(
                DatabaseManager.EXPENSES_TABLE, null, "id", idList,
                null, null, "DESC");

        if (cursor != null)
        {
            // keep going until end of results
            while (cursor.moveToNext())
            {
                String type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                float total = cursor.getFloat(cursor.getColumnIndexOrThrow("total"));
                int amount = cursor.getInt(cursor.getColumnIndexOrThrow("amount"));

                // add into their expenses cache
                expenses.add(new Expenses(id, type, name, total, amount));
            }
        }
    }
}
