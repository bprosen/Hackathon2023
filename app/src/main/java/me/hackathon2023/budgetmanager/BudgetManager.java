package me.hackathon2023.budgetmanager;

import android.app.Activity;

import me.hackathon2023.budgetmanager.database.DatabaseManager;

public class BudgetManager extends Activity
{
    public static void main(String[] args)
    {
        DatabaseManager databaseManager = new DatabaseManager();
    }
}
