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
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class register extends AppCompatActivity {

    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }


        final EditText nameView = (EditText) findViewById(R.id.name);
        final EditText emailView = (EditText) findViewById(R.id.email);
        final EditText idView = (EditText) findViewById(R.id.id);
        final EditText passwordView = (EditText) findViewById(R.id.password);
        final EditText phNo = (EditText) findViewById(R.id.number);
        Button registerView = (Button) findViewById(R.id.btnRegister);
        Button back = (Button) findViewById(R.id.btnLinkToLoginScreen);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });

        registerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                progress = new ProgressDialog(register.this);
                progress.setTitle("Loading");
                progress.setMessage("Wait while loading...");
                progress.setCancelable(false);
                progress.show();


                final String name = nameView.getText().toString();
                final String email = emailView.getText().toString();
                final String id = idView.getText().toString();
                final String password = passwordView.getText().toString();
                long phone = 0;
                if(!phNo.getText().toString().equals(""))
                    phone = Long.parseLong(phNo.getText().toString());

                if (id.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty()) {
                    if(progress.isShowing())
                    {
                        progress.dismiss();
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(register.this);
                    builder.setMessage("Please make sure you entered all the fields correctly.")
                            .setTitle("Oops!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return;
                }

                boolean valid = (id != null) && id.matches("[A-Za-z0-9_]+");
                if(!valid)
                {
                    if(progress.isShowing())
                    {
                        progress.dismiss();
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(register.this);
                    builder.setMessage("Invalid username! Use only alphanumeric characters without blank space")
                            .setTitle("Oops!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return;
                }

                if(!isValidEmailAddress(email))
                {
                    if(progress.isShowing())
                    {
                        progress.dismiss();
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(register.this);
                    builder.setMessage("Invalid email!")
                            .setTitle("Oops!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return;
                }

                final ParseUser user = new ParseUser();
                user.setUsername(id);
                user.setPassword(password);
                user.setEmail(email);
                user.put("name", name);
                user.put("phone", phone);
                user.put("reg_auth",false);
                user.put("push_auth",false);
                user.put("results_auth",false);

                ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.whereEqualTo("username", id);
                query.findInBackground(new FindCallback<ParseUser>() {
                    @Override
                    public void done(List<ParseUser> parseUsers, ParseException e) {

                        if (e == null) {

                            if (parseUsers.size() > 0) {
                                Toast toast = Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT);
                                toast.show();
                            } else {
                                signupUser(user);
                            }
                        } else {
                            // Shit happened!
                            if(progress.isShowing())
                                progress.dismiss();
                            AlertDialog.Builder builder = new AlertDialog.Builder(register.this);
                            builder.setMessage(e.getMessage())
                                    .setTitle("Oops!")
                                    .setPositiveButton(android.R.string.ok, null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                });
            }
        });
    }



    private void signupUser(ParseUser user) {

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(progress.isShowing())
                    progress.dismiss();
                if (e == null) {

                    // Signup successful!
                    AlertDialog.Builder builder = new AlertDialog.Builder(register.this);
                    builder.setMessage("You've signed up successfully")
                            .setTitle("Success!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    navigateToHome();
                } else {
                    // Fail!
                    AlertDialog.Builder builder = new AlertDialog.Builder(register.this);
                    builder.setMessage(e.getMessage())
                            .setTitle("Oops! Signup failed")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }

    private void navigateToHome() {
        // Let's go to the MainActivity
        Intent intent = new Intent(register.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private void goToLogin() {
        // Let's go to the MainActivity
        Intent intent = new Intent(register.this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
