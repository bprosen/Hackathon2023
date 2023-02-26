package me.hackathon2023.budgetmanager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import me.hackathon2023.budgetmanager.BudgetManager;
import me.hackathon2023.budgetmanager.R;
import me.hackathon2023.budgetmanager.Utils;
import me.hackathon2023.budgetmanager.data.User;
import me.hackathon2023.budgetmanager.database.DatabaseQueries;

//import com.example.myapplication.R;

public class LoginScreen extends AppCompatActivity {

    //Variables
    EditText newUserName, newEmail, newPassword, confirmPassword;

    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }


    //Buttons
    private Button GetStarted;
    private static Button Signup;
    private Button Cancel;
    private Button createAccount;
    private Button login;

    private Button AddTxn;


    Button Add;
    Button CancelTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        // initialize database on login creation
        BudgetManager.setDatabaseManager(this);
        setContentView(R.layout.activity_login_screen);

        GetStarted = findViewById(R.id.button6);

        GetStarted.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_login);


                //-------------------------------------------------------------------------------
                // Login
                //-------------------------------------------------------------------------------
                EditText newEmailLogin, newPasswordLogin;
                newEmailLogin = findViewById(R.id.email);
                newPasswordLogin = findViewById(R.id.userPassword);

                login = findViewById(R.id.loginButton);

                login.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        String emailAddress = newEmailLogin.getText().toString();
                        String password = newPasswordLogin.getText().toString();

                        if (!Utils.isEmail(emailAddress)) {
                            newEmailLogin.getText().clear();
                            Toast.makeText(getApplicationContext(), "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                        } else if (!DatabaseQueries.emailExists(emailAddress)) {
                            newEmailLogin.getText().clear();
                            Toast.makeText(getApplicationContext(), "User not found!", Toast.LENGTH_SHORT).show();

                        } else if (!DatabaseQueries.correctPassword(emailAddress, password)) {
                            newEmailLogin.getText().clear();
                            newPasswordLogin.getText().clear();
                            Toast.makeText(getApplicationContext(), "Email and Password combination is incorrect.", Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                            //setContentView(R.layout.activity_dashboard);
                            BudgetManager.setUser(new User(emailAddress, password));
                            loginDone();

                        }

                    }
                });


                //-------------------------------------------------------------------------------
                // Signup
                //-------------------------------------------------------------------------------

                Signup = findViewById(R.id.SignupButton);

                Signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setContentView(R.layout.activity_register);
                        newUserName = findViewById(R.id.fullName);
                        newPassword = findViewById(R.id.userPassword);
                        newEmail = findViewById(R.id.userEmail);
                        confirmPassword = findViewById(R.id.userConfirmPassword);
                        Cancel = findViewById(R.id.cancel_button);
                        createAccount = findViewById(R.id.button);

                        Cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                setContentView(R.layout.activity_login);
                                Reset();
                            }

                        });

                        createAccount.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String userName = newUserName.getText().toString();
                                String emailAddress = newEmail.getText().toString();
                                String password = newPassword.getText().toString();
                                String confirmPass = confirmPassword.getText().toString();

                                if (password.length() < User.MIN_PASSWORD_LENGTH) {
                                    newPassword.getText().clear();
                                    confirmPassword.getText().clear();
                                    Toast.makeText(getApplicationContext(), "Please enter atleast 8 digits for your password", Toast.LENGTH_SHORT).show();

                                } else if (!Utils.isEmail(emailAddress)) {
                                    newEmail.getText().clear();
                                    Toast.makeText(getApplicationContext(), "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                                } else if (!password.equals(confirmPass)) {
                                    Toast.makeText(getApplicationContext(), "Passwords do not match!", Toast.LENGTH_SHORT).show();
                                } else if (DatabaseQueries.emailExists(emailAddress)) {
                                    newEmail.getText().clear();
                                    Toast.makeText(getApplicationContext(), "Email already exists, Please try Logging in!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Account Succesfully created", Toast.LENGTH_SHORT).show();
                                    DatabaseQueries.registerUser(userName, emailAddress, password);
                                    BudgetManager.setUser(new User(emailAddress, password));
                                    setContentView(R.layout.activity_dashboard);
                                    loginDone();
                                }

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

    //----------------------------------------------------------------------------
    // Reset the buttons in case the user presses the "Cancel" button
    //----------------------------------------------------------------------------
    private void Reset() {

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

    //----------------------------------------------------------------------------
    //Login Done: Enter the Dashboard
    //----------------------------------------------------------------------------
    private void loginDone() {
        setContentView(R.layout.activity_dashboard);
        TextView incomeView = ((TextView)findViewById(R.id.income_data));
        TextView expenseView = ((TextView)findViewById(R.id.expense_data));
        TextView balanceView = ((TextView)findViewById(R.id.balance_data));

        String income = incomeView.getText().toString();
        String expense = expenseView.getText().toString();
        String balance = balanceView.getText().toString();

        income = income.replace("(income)", String.valueOf(BudgetManager.getLoggedInUser().getIncome()));
        expense = expense.replace("(expense)", String.valueOf(BudgetManager.getLoggedInUser().getExpense()));
        balance = balance.replace("(balance)", String.valueOf(BudgetManager.getLoggedInUser().getBalance()));

        incomeView.setText(income);
        expenseView.setText(expense);
        balanceView.setText(balance);

        AddTxn = findViewById(R.id.add_button);

        AddTxn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setContentView(R.layout.activity_money_matters);
                processTransaction();
            }
        });

    }

    private void processTransaction() {

        Add = findViewById(R.id.AddButton);
        CancelTransaction = findViewById(R.id.CancelButton);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToDataBase();
            }
        });

        CancelTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackToDashBoard();
            }
        });

    }

    //add transcation to database
    private void addToDataBase(){

        EditText amount, type;

        amount = findViewById(R.id.money_amount);
        type = findViewById(R.id.txn_type);

        float amountEntered = Float.parseFloat(amount.getText().toString());
        String typeExpense = type.getText().toString();

        System.out.println(amountEntered);
        System.out.println(typeExpense);



    }

    //cancel was pressed
    private void goBackToDashBoard(){
        loginDone();
    }


}

