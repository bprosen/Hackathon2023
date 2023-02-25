package me.hackathon2023.budgetmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        new Handler().postDelayed(() -> {
            Intent i = new Intent(LoginScreen.this, BudgetManager.class);
                    startActivity(i);

            finish();
        }, 3000);

    }
}