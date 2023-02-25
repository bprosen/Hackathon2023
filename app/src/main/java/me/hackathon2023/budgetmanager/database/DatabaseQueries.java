package me.hackathon2023.budgetmanager.database;

import android.database.Cursor;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.hackathon2023.budgetmanager.BudgetManager;

public class DatabaseQueries
{
    public static void registerUser(String name, String email, String password)
    {
        BudgetManager.getDatabaseManager().getWriting().execSQL(
                "INSERT INTO " + DatabaseManager.USERS_TABLE +
                        " (name, email, password)" +
                        " VALUES('" + name + "','" + email + "','" + password + "')"
        );
    }

    public static void addExpense(String email, String type, String name, float total)
    {
        BudgetManager.getDatabaseManager().getWriting().execSQL(
                "INSERT INTO " + DatabaseManager.EXPENSES_TABLE +
                        " (email, type, name, total) " +
                        "VALUES('" + email + "','" + type + "','" + name + "'," + total + ")"
        );
    }

    // used for checking when loggin in
    public static boolean correctPassword(String email, String enteredPassword)
    {

        boolean correct = false;

        Cursor cursor = BudgetManager.getDatabaseManager().getReading().query(
                DatabaseManager.USERS_TABLE, null, "email='" + email + "'", null,
                null, null, null);

        if (cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            correct = password.equals(enteredPassword);
        }

        return correct;
    }

    public static boolean emailExists(String email)
    {

        Cursor cursor = BudgetManager.getDatabaseManager().getReading().query(
            DatabaseManager.USERS_TABLE, null, "email='" + email + "'",
            null, null, null, null);

        return cursor.getCount() > 0;
    }
}
