package com.msrit.abhilash.udbhavinsights;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class Login extends AppCompatActivity {

    public static ParseUser currentUser;
    ProgressDialog progress;

    /*public Login(Context context) {
        this.context = context;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
/*
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            navigateToHome();
        }



        final EditText usernameView = (EditText) findViewById(R.id.email);
        final EditText passView = (EditText) findViewById(R.id.password);
        Button loginButton = (Button) findViewById(R.id.btnLogin);
        Button register = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                progress = new ProgressDialog(Login.this);
                progress.setTitle("Please wait");
                progress.setMessage("Logging you in...");
                progress.setCancelable(false);
                progress.show();

                final String id = usernameView.getText().toString();
                final String pass = passView.getText().toString();

                if (id.isEmpty() || pass.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setMessage("Please make sure you entered all the fields correctly.")
                            .setTitle("Oops!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    return;
                }

                final ParseUser user = new ParseUser();
                user.setUsername(id);
                user.setPassword(pass);
                ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.whereEqualTo("username", id);

                query.findInBackground(new FindCallback<ParseUser>() {
                    @Override
                    public void done(List<ParseUser> parseUsers, ParseException e) {

                        if (e == null) {
                            // Successful Query

                            // User already exists ? then login
                            if (parseUsers.size() > 0) {
                                loginUser(id, pass);
                            } else {
                                // No user found, so signup
                                /*Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();*/

                                Toast toast = Toast.makeText(Login.this, "User not registered", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        } else {
                            // Shit happened!
                            progress.dismiss();
                            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                            builder.setMessage("Bad internet connection. Connect to the internet and try again! Error: "+e.getMessage())
                                    .setTitle("Oops!")
                                    .setPositiveButton(android.R.string.ok, null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                });
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToRegister();
            }
        });
    }

    private void loginUser(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                progress.dismiss();
                if (user != null) {
                    // Hooray! The user is logged in.
                    navigateToHome();
                } else {
                    // Login failed!
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setMessage("Bad internet connection. Connect to the internet and try again! Error: "+e.getMessage())
                            .setTitle("Oops!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }

    private void navigateToHome() {
        // Let's go to the MainActivity
        Intent intent = new Intent(Login.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private void navigateToRegister() {
        // Let's go to the MainActivity
        Intent intent = new Intent(Login.this, register.class);
        startActivity(intent);
    }

}