package me.hackathon2023.budgetmanager;

import android.app.Activity;
import android.content.ContextWrapper;

import androidx.appcompat.app.AppCompatActivity;

import me.hackathon2023.budgetmanager.database.DatabaseManager;

public class BudgetManager extends AppCompatActivity
{
    private static DatabaseManager databaseManager;

    public static void main(String[] args)
    {
    }

    public static void setDatabaseManager(ContextWrapper wrapper)
    {
        databaseManager = new DatabaseManager(wrapper);
    }

    public static DatabaseManager getDatabaseManager()
    {
        return databaseManager;
    }
}
