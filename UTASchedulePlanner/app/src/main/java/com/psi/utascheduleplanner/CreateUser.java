package com.psi.utascheduleplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class CreateUser extends Activity {
    // Initializing variables
    EditText inputName;
    EditText inputPassword;
    EditText inputVerifyPassword;

    UserDataBaseAdapter newUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        newUser = new UserDataBaseAdapter(this);
        try {
            newUser = newUser.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        inputName = (EditText) findViewById(R.id.name);
        inputPassword = (EditText) findViewById(R.id.password);
        inputVerifyPassword = (EditText) findViewById(R.id.passwordV);
        Button btnNextScreen = (Button) findViewById(R.id.btnNextScreen);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);

        //Listening to button event
        btnNextScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Validating username and password
                if(inputName.length() == 0) {
                    inputName.requestFocus();
                    inputName.setError("Username field cannot be empty.");
                }

                else if(inputPassword.length() == 0) {
                    inputPassword.requestFocus();
                    inputPassword.setError("Password field cannot be empty.");
                }

                else if(!inputVerifyPassword.getText().toString().equals(inputPassword.getText().toString())) {
                    inputVerifyPassword.requestFocus();
                    inputVerifyPassword.setError("Password does not match first input.");
                }
                else {
                    newUser.insertEntry(inputName.getText().toString(),inputPassword.getText().toString());
                    Toast.makeText(getApplicationContext(), "User created.", Toast.LENGTH_SHORT).show();
                    Intent nextScreen = new Intent(getApplicationContext(), Login.class);
                    startActivity(nextScreen);
                }

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Cancel will return to main log in screen
                Intent nextScreen = new Intent(getApplicationContext(), Login.class);
                startActivity(nextScreen);
                }


        });
    }

    @Override
    public void onBackPressed() {
        //Do nothing, disabling back button on android to prevent from returning to pages incorrectly
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        newUser.close();
    }
}