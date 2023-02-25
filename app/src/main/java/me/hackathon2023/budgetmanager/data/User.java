package me.hackathon2023.budgetmanager.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import me.hackathon2023.budgetmanager.BudgetManager;
import me.hackathon2023.budgetmanager.Utils;
import me.hackathon2023.budgetmanager.database.Query;

public class User
{
    private String username;
    private String name;
    private String email;
    private String password;
    private int id;

    private List<Expenses> expenses = new ArrayList<>();

    public User(String userOrEmail, String password)
    {
        if (Utils.isEmail(userOrEmail))
            email = userOrEmail;
        else
            name = userOrEmail;

        this.password = password;
        this.id = loadID();

        loadExpenses();
    }

    private int loadID()
    {
        ResultSet result = null;

        if (username != null)
            result = new Query().build("SELECT id FROM users WHERE username='" + username + "'").get();
        else if (email != null)
            result = new Query().build("SELECT id FROM users WHERE email='" + email + "'").get();

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
        ResultSet result = new Query().build("SELECT * FROM expenses WHERE id=" + id).get();

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
