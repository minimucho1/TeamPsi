package com.psi.utascheduleplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateUser extends Activity {
    // Initializing variables
    EditText inputName;
    EditText inputPassword;
    EditText inputVerifyPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        inputName = (EditText) findViewById(R.id.name);
        inputPassword = (EditText) findViewById(R.id.password);
        inputVerifyPassword = (EditText) findViewById(R.id.passwordV);
        Button btnNextScreen = (Button) findViewById(R.id.btnNextScreen);

        //Listening to button event
        btnNextScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Validating username and password
                if(inputName.length() == 0) {
                    inputName.requestFocus();
                    inputName.setError("Field cannot be empty.");
                }
                else {
                    Intent nextScreen = new Intent(getApplicationContext(), Login.class);

                    startActivity(nextScreen);
                }

            }
        });
    }
}