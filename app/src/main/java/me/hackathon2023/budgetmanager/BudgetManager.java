package me.hackathon2023.budgetmanager;

import android.app.Activity;
import android.content.ContextWrapper;

import androidx.appcompat.app.AppCompatActivity;

import me.hackathon2023.budgetmanager.data.User;
import me.hackathon2023.budgetmanager.database.DatabaseManager;

public class BudgetManager extends AppCompatActivity
{
    private static DatabaseManager databaseManager;
    private static User loggedInUser;

    public static void setUser(User user)
    {
        loggedInUser = user;
    }

    public static void setDatabaseManager(ContextWrapper wrapper)
    {
        databaseManager = new DatabaseManager(wrapper);
    }

    public static User getLoggedInUser() { return loggedInUser; }

    public static DatabaseManager getDatabaseManager()
    {
        return databaseManager;
    }



}
