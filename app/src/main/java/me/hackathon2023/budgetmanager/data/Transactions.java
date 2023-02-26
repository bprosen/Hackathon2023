package me.hackathon2023.budgetmanager.data;

public class Transactions
{
    private String type;
    private String date;
    private float total;

    public Transactions(String type, String date, float total)
    {
        this.type = type;
        this.date = date;
        this.total = total;
    }

    public String getType()
    {
        return type;
    }

    public String getDate()
    {
        return date;
    }

    public float getTotal()
    {
        return total;
    }
}
