package me.hackathon2023.budgetmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

//import com.example.myapplication.R;

public class LoginScreen extends AppCompatActivity {

    private Button pushMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        pushMe=findViewById(R.id.button6);

        pushMe.setOnClickListener(new View.OnClickListener() {

                                      @Override
                                      public void onClick(View v) {
                                          setContentView(R.layout.activity_login);
                                      }

                                  });
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent i = new Intent(LoginScreen.this, BudgetManager.class);
//                        startActivity(i);
                      //  setContentView(R.layout.activity_login);
//
//                finish();
//            }
//        }, 3000);

    }
}

