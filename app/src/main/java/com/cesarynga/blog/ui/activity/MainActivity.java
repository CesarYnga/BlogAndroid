package com.cesarynga.blog.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cesarynga.blog.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEdtEmail, mEdtPassword;
    private TextInputLayout mTilEmail, mTilPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        mTilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
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
            mTilEmail.setError(getString(R.string.text_error_empty_field));
            return false;
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(mEdtEmail.getText()).matches()) {
                mTilEmail.setError(getString(R.string.text_error_email_invalid));
                return false;
            }
            mTilEmail.setError(null);
        }
        if (mEdtPassword.getText().toString().isEmpty()) {
            mTilPassword.setError(getString(R.string.text_error_empty_field));
            return false;
        } else {
            mTilPassword.setError(null);
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
