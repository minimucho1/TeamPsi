package com.psi.utascheduleplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
    // Initializing variables
    EditText inputName;
    EditText inputPassword;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputName = (EditText) findViewById(R.id.name);
        inputPassword = (EditText) findViewById(R.id.password);
        Button submitButton = (Button) findViewById(R.id.btnNextScreen);
        Button newUserButton = (Button) findViewById(R.id.newUserButton);

        //Listening to button event
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
                Toast.makeText(getApplicationContext(), "Log in successful.", Toast.LENGTH_SHORT).show();
                startActivity(nextScreen);
            }
        });

        newUserButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent nextScreen = new Intent(getApplicationContext(), CreateUser.class);
                startActivity(nextScreen);
            }
        });
    }
}