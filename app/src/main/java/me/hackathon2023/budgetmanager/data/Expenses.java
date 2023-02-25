package me.hackathon2023.budgetmanager.data;

public class Expenses
{
    private int id;
    private String type;
    private String name;
    private float total;
    private int amount;

    public Expenses(int id, String type, String name, float total, int amount)
    {
        this.id = id;
        this.type = type;
        this.name = name;
        this.total = total;
        this.amount = amount;
    }
}
