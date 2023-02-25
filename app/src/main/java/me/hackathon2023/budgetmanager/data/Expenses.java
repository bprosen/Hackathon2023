package me.hackathon2023.budgetmanager.data;

public class Expenses
{
    private int id;
    private String type;
    private String name;
    private float total;

    public Expenses(int id, String type, String name, float total)
    {
        this.id = id;
        this.type = type;
        this.name = name;
        this.total = total;
    }
}
