package me.hackathon2023.budgetmanager;

import java.util.regex.Pattern;

public class Utils
{
    public static void println(String printString)
    {
        System.out.println(printString);
    }

    public static boolean isEmail(String string)
    {
        Pattern regex = Pattern.compile("(.+)@(.+)$", Pattern.CASE_INSENSITIVE);
        return regex.matcher(string).matches();
    }
}
