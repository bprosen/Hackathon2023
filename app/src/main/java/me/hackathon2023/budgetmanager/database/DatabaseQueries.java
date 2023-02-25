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

    // used for checking when loggin in
    public static boolean correctPassword(String email, String enteredPassword)
    {

        Cursor cursor = BudgetManager.getDatabaseManager().getReading().query(
                DatabaseManager.USERS_TABLE, null, "email='" + email + "'", null,
                null, null, "DESC");

        String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));

        return password.equals(enteredPassword);
    }

    public static boolean emailExists(String email)
    {

            Cursor cursor = BudgetManager.getDatabaseManager().getReading().query(
                DatabaseManager.USERS_TABLE, null, "email='" + email + "'",
                null, null, null, null);

        return cursor.moveToNext();
    }
}
