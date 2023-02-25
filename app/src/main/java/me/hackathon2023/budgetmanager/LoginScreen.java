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

    private Button GetStarted;
    private static Button Signup;
    private Button Cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        GetStarted=findViewById(R.id.button6);

        GetStarted.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_login);
                Signup = findViewById(R.id.SignupButton);

                Signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setContentView(R.layout.activity_register);
                        Cancel = findViewById(R.id.cancel_button);

                        Cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                setContentView(R.layout.activity_login);
                                Reset();
                            }

                        });


                    }
                });

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

    private void Reset(){

        Signup = findViewById(R.id.SignupButton);

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_register);
                Cancel = findViewById(R.id.cancel_button);

                Cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setContentView(R.layout.activity_login);
                        Reset();
                    }

                });


            }
        });

    }



}

