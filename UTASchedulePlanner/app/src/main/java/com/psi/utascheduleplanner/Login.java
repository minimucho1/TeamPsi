package com.psi.utascheduleplanner;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class Login extends ActionBarActivity {
    UserDataBaseAdapter userDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //this.deleteDatabase("users.db");

        userDB = new UserDataBaseAdapter(this);
        try {
            userDB = userDB.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        final EditText inputName = (EditText) findViewById(R.id.name);
        final EditText inputPassword = (EditText) findViewById(R.id.password);
        Button btnNextScreen = (Button) findViewById(R.id.btnNextScreen);
        Button newUserButton = (Button) findViewById(R.id.newUserButton);

        //If login successful, go to main page
        btnNextScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Take in entered values
                String enteredName = inputName.getText().toString();
                String enteredPassword = inputPassword.getText().toString();

                //Fetch stored password from database of given username
                String storedPassword = userDB.getPassword(enteredName);

                if(storedPassword.equals(enteredPassword)) {
                    Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
                    Toast.makeText(getApplicationContext(), "Log in successful.", Toast.LENGTH_SHORT).show();
                    //Sending username and password into next intent for use
                    nextScreen.putExtra("username", enteredName);
                    nextScreen.putExtra("password", storedPassword);
                    Toast.makeText(getApplicationContext(), "Welcome " + enteredName + ".", Toast.LENGTH_SHORT).show();
                    inputName.getText().clear();
                    inputPassword.getText().clear();
                    startActivity(nextScreen);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Username and password does not match.", Toast.LENGTH_LONG).show();
                    inputName.getText().clear();
                    inputPassword.getText().clear();
                }
            }
        });

        //Create a new user
        newUserButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent nextScreen = new Intent(getApplicationContext(), CreateUser.class);
                startActivity(nextScreen);
                finish();
            }
        });
    }
}