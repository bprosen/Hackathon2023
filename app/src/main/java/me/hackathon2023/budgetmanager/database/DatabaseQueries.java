package me.hackathon2023.budgetmanager.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseQueries
{
    public static void registerUser(String name, String username, String email, String password)
    {
        new Query().build(
                "INSERT INTO " + DatabaseManager.USERS_TABLE +
                " (name, username, email, password)" +
                " VALUES('" + name + "','" + username + "','" + email + "','" + password + "')"
        );
    }
    public static boolean usernameExists(String username)
    {
        ResultSet result = new Query().build("SELECT * FROM " + DatabaseManager.USERS_TABLE + " WHERE username='" + username + "'").get();
        return isEmpty(result);
    }

    public static boolean emailExists(String email)
    {
        ResultSet result = new Query().build("SELECT * FROM " + DatabaseManager.USERS_TABLE + " WHERE email='" + email + "'").get();
        return isEmpty(result);
    }

    private static boolean isEmpty(ResultSet result)
    {
        boolean exists = false;

        try
        {
            exists = result.next();
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }

        return exists;
    }
}
