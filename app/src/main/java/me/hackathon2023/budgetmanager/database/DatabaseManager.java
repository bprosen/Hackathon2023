package me.hackathon2023.budgetmanager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import me.hackathon2023.budgetmanager.Utils;

public class DatabaseManager
{
    private Connection connection;
    private final String URL = "jdbc:sqlite:C:/sqlite/budgetmanager.db";

    public DatabaseManager()
    {
        open();
    }

    private void open()
    {
        try
        {
            connection = DriverManager.getConnection(URL);
            Utils.println("Successfully connected to the database");
        }
        catch (SQLException exception)
        {
            Utils.println("Something went wrong when connecting to the database...");
            exception.printStackTrace();
        }
    }

    public void close()
    {
        try
        {
            if (!isClosed())
                connection.close();
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
    }

    public boolean isClosed()
    {
        boolean closed = true;

        try
        {
            closed = connection.isClosed();
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }

        return closed;
    }
}
