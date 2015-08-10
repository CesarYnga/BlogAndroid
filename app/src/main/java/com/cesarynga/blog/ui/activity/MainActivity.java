package com.cesarynga.blog.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cesarynga.blog.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEdtEmail, mEdtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEdtEmail = (EditText) findViewById(R.id.edtEmail);
        mEdtPassword = (EditText) findViewById(R.id.edtPassword);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        TextView txtSignUp = (TextView) findViewById(R.id.txtSignUp);
        btnLogin.setOnClickListener(this);
        txtSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                login();
                break;
            case R.id.txtSignUp:
                signUp();
                break;
        }
    }

    private void login() {
        if (validateLoginInput()) {
            performLoginRequest();
        }
    }

    private void signUp() {

    }

    private boolean validateLoginInput() {
        if (mEdtEmail.getText().toString().isEmpty()) {
            mEdtEmail.setError(getString(R.string.text_error_empty_field));
            return false;
        }
        if (mEdtPassword.getText().toString().isEmpty()) {
            mEdtPassword.setError(getString(R.string.text_error_empty_field));
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mEdtEmail.getText()).matches()) {
            mEdtEmail.setError(getString(R.string.text_error_email_invalid));
            return false;
        }
        return true;
    }

    private void performLoginRequest() {
        // TODO: Make the request
        startHomeActivity();
    }

    private void startHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
