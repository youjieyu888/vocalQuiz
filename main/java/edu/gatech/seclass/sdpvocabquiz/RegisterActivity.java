package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText usernameForm, realNameForm, majorForm, emailForm;
    private Spinner senioritySpinner;
    private Button registerButton;
    private AuthenticationService authenticationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        authenticationService = AuthenticationService.getInstance();

        usernameForm = findViewById(R.id.username);
        realNameForm = findViewById(R.id.realname);
        majorForm = findViewById(R.id.major);
        emailForm = findViewById(R.id.email);
        senioritySpinner = findViewById(R.id.seniority);
        senioritySpinner.setAdapter(new ArrayAdapter<Student.Seniority>(this, android.R.layout.simple_spinner_item, Student.Seniority.values()));

        registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String username = usernameForm.getText().toString().trim().toLowerCase();
        String realName = realNameForm.getText().toString().trim();
        String major = majorForm.getText().toString().trim().toLowerCase();
        String email = emailForm.getText().toString().trim().toLowerCase();
        Student.Seniority seniority = (Student.Seniority) senioritySpinner.getSelectedItem();

        if(!validate(username, realName, major, email)){
            return;
        }

        Student student = new Student(username, realName, major, seniority, email);
        if (authenticationService.register(student)){
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            intent.putExtra("infoMessage", "Username " + username + " is registered successfully!");
            startActivity(intent);
        }else{
            Intent intent = new Intent(RegisterActivity.this, RegisterActivity.class);
            intent.putExtra("errorMessage", "Username " + username + " failed to be register!");
            startActivity(intent);
        }

    }

    private boolean validate(String username, String realName, String major, String email){
        boolean valid = true;

        if(TextUtils.isEmpty(username)){
            usernameForm.setError(getString(R.string.username_required));
            valid = false;
        }else if(authenticationService.checkIfUsernameExist(username)){
            usernameForm.setError(getString(R.string.username_already_exists));
            valid = false;
        }

        if(TextUtils.isEmpty(realName)){
            realNameForm.setError(getString(R.string.real_name_required));
            valid = false;
        }

        if(TextUtils.isEmpty(major)){
            majorForm.setError(getString(R.string.major_required));
            valid = false;
        }

        if(TextUtils.isEmpty(email)){
            emailForm.setError(getString(R.string.email_required));
            valid = false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailForm.setError(getString(R.string.invalid_email_address));
            valid = false;
        }

        return valid;
    }
}
