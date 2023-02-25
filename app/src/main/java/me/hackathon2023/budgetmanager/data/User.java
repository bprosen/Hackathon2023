package me.hackathon2023.budgetmanager.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import me.hackathon2023.budgetmanager.database.DatabaseManager;
import me.hackathon2023.budgetmanager.database.Query;

public class User
{
    private String name;
    private String email;
    private String password;
    private int id = -1;

    private List<Expenses> expenses = new ArrayList<>();

    public static final int MIN_PASSWORD_LENGTH = 8;

    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
        this.id = loadID();

        loadExpenses();
    }

    private int loadID()
    {
        ResultSet result = new Query().build("SELECT id FROM " + DatabaseManager.USERS_TABLE + " WHERE email='" + email + "'").get();

        if (result != null)
        {
            try
            {
                id = result.getInt("id");
            }
            catch (SQLException exception)
            {
                exception.printStackTrace();
            }
        }
        return id;
    }

    private void loadExpenses()
    {
        ResultSet result = new Query().build("SELECT * FROM " + DatabaseManager.EXPENSES_TABLE + " WHERE id=" + id).get();

        if (result != null)
        {
            try
            {
                // keep going until end of results
                while (result.next())
                {
                    String type = result.getString("type");
                    String name = result.getString("name");
                    float total = result.getFloat("total");
                    int amount = result.getInt("amount");

                    // add into their expenses cache
                    expenses.add(new Expenses(id, type, name, total, amount));
                }
            }
            catch (SQLException exception)
            {
                exception.printStackTrace();
            }
        }
    }
}
