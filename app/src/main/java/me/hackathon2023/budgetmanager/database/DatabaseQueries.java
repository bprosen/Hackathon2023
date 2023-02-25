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

    public static boolean emailExists(String email)
    {
        Cursor cursor = BudgetManager.getDatabaseManager().getReading().query(
                DatabaseManager.USERS_TABLE, null, "email", new String[]{email},
                null, null, "DESC");

        return cursor.moveToNext();
    }
}
