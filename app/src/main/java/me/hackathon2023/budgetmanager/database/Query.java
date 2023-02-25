package me.hackathon2023.budgetmanager.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.hackathon2023.budgetmanager.BudgetManager;

public class Query
{
    private String query;

    private Connection connection;

    public Query()
    {
        // get connection
        connection = BudgetManager.getDatabaseManager().getConnection();
        query = null;
    }

    public Query build(String query)
    {
        // return the current class for running/getting
        this.query = query;

        return this;
    }

    public ResultSet get()
    {
        ResultSet result = null;
        try
        {
            // prepare result from select query
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            result = preparedStatement.executeQuery();
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }

        return result;
    }

    public void run()
    {
        try
        {
            // go striaght into updating database
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
    }
}
