package me.hackathon2023.budgetmanager;

import android.app.Activity;

import me.hackathon2023.budgetmanager.database.DatabaseManager;

public class BudgetManager extends Activity
{
    private static DatabaseManager databaseManager;

    public static void main(String[] args)
    {
        databaseManager = new DatabaseManager();
    }

    public static DatabaseManager getDatabaseManager()
    {
        return databaseManager;
    }
}
