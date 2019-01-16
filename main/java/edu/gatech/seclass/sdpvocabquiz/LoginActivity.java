package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameForm;
    private Button registerButton, loginButton;
    private AuthenticationService authenticationService;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        authenticationService = AuthenticationService.getInstance();

        loginButton = findViewById(R.id.login);
        registerButton = findViewById(R.id.register);

        usernameForm = findViewById(R.id.userName);

        handleMessage();

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                authenticationService.populateRegisteredStudents();

                String username = usernameForm.getText().toString().trim();

                if(username != null && !username.equals("")) {
                    Student student = authenticationService.login(username);

                    if (student != null) {
                        Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                        intent.putExtra("errorMessage", "Username " + username + " does not exist!");
                        startActivity(intent);
                    }
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                authenticationService.populateRegisteredStudents();

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void handleMessage() {
        message = findViewById(R.id.loginMessage);
        message.setText("");

        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            if (extra.get("errorMessage") != null && !extra.get("errorMessage").equals("")) {
                message.setText(extra.get("errorMessage").toString());
                message.setTextColor(Color.RED);
            } else if (extra.get("infoMessage") != null && !extra.get("infoMessage").equals("")) {
                message.setText(extra.get("infoMessage").toString());
                message.setTextColor(Color.GREEN);
            }
        }
    }
}
