package me.hackathon2023.budgetmanager.data;

public class Expenses
{
    private String type;
    private String name;
    private float total;

    public Expenses(String type, String name, float total)
    {
        this.type = type;
        this.name = name;
        this.total = total;
    }
}
