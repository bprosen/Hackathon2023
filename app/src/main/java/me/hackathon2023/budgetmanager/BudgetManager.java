package me.hackathon2023.budgetmanager;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import me.hackathon2023.budgetmanager.database.DatabaseManager;

public class BudgetManager extends AppCompatActivity
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
