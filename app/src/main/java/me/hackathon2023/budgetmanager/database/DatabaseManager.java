package me.hackathon2023.budgetmanager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import me.hackathon2023.budgetmanager.Utils;

public class DatabaseManager
{
    private Connection connection;
    private final String URL = "jdbc:sqlite:C:/sqlite/budgetmanager.db";

    public static final String USERS_TABLE = "users";
    public static final String EXPENSES_TABLE = "expenses";

    public DatabaseManager()
    {
        open();
        createTables();
    }

    private void createTables()
    {
        // call build queries to make tables for users, and expenses
        new Query().build("CREATE TABLE " + USERS_TABLE + " IF NOT EXISTS (" +
                "id INTEGER AUTOINCREMENT, " +
                "name VARCHAR(100) DEFAULT NULL, " +
                "email VARCHAR(50) DEFAULT NULL, " +
                "password VARCHAR(100) DEFAULT NULL" +
                ")")
        .run();

        new Query().build("CREATE TABLE " + EXPENSES_TABLE + " IF NOT EXISTS (" +
                "id INTEGER DEFAULT 0, " +
                "type VARCHAR(30) DEFAULT NULL, " +
                "name VARCHAR(30) DEFAULT NULL, " +
                "total FLOAT DEFAULT 0.0, " +
                "amount SMALLINT DEFAULT 0" +
                ")")
        .run();
    }

    private void open()
    {
        try
        {
            // get from the driver manager
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
            // only close if we know it is not closed
            if (!isClosed())
                connection.close();
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
    }

    // useful for checking for errors/avoiding them
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

    public Connection getConnection()
    {
        return connection;
    }
}
