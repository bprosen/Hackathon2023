package me.hackathon2023.budgetmanager.data;

public class Expenses
{
    private String type;
    private String date;
    private float total;

    public Expenses(String type, String date, float total)
    {
        this.type = type;
        this.date = date;
        this.total = total;
    }
}
