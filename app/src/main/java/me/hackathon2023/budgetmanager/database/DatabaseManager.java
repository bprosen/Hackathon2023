package me.hackathon2023.budgetmanager.database;

import android.content.ContextWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import me.hackathon2023.budgetmanager.BudgetManager;
import me.hackathon2023.budgetmanager.Utils;

public class DatabaseManager extends SQLiteOpenHelper
{
    public static final String DB_NAME = "database.db";

    public static final String USERS_TABLE = "users";
    public static final String EXPENSES_TABLE = "expenses";

    public DatabaseManager(ContextWrapper contextWrapper)
    {
        super(contextWrapper, DB_NAME, null, 1);
        onCreate(super.getWritableDatabase());
    }

    private void createTables(SQLiteDatabase database)
    {
        // call build queries to make tables for users, and expenses
        database.execSQL("CREATE TABLE IF NOT EXISTS " + USERS_TABLE + " (" +
                "name VARCHAR(100) DEFAULT NULL, " +
                "email VARCHAR(50) DEFAULT NULL, " +
                "password VARCHAR(100) DEFAULT NULL" +
                ")");

        database.execSQL("CREATE TABLE IF NOT EXISTS " + EXPENSES_TABLE + " (" +
                "email VARCHAR(50) DEFAULT 0, " +
                "type VARCHAR(30) DEFAULT NULL, " +
                "name VARCHAR(30) DEFAULT NULL, " +
                "total FLOAT DEFAULT 0.0, " +
                "amount SMALLINT DEFAULT 0" +
                ")");
    }

    public void close()
    {
        // only close if we know it is not closed
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTables(sqLiteDatabase);
        Utils.println("SQLite Connection has been established");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public SQLiteDatabase getReading() { return getReadableDatabase(); }
    public SQLiteDatabase getWriting()
    {
        return getWritableDatabase();
    }
}
