package com.cesarynga.blog.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.cesarynga.blog.R;
import com.cesarynga.blog.model.User;
import com.cesarynga.blog.networking.GsonRequest;
import com.cesarynga.blog.networking.VolleyManager;
import com.cesarynga.blog.networking.requestbody.LoginRequestBody;
import com.cesarynga.blog.networking.url.URLS;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private EditText mEdtEmail, mEdtPassword;
    private TextInputLayout mTilEmail, mTilPassword;
    private ProgressDialog mProgressDialog;

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
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading ...");
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
        mProgressDialog.show();
        LoginRequestBody body = new LoginRequestBody();
        body.email = mEdtEmail.getText().toString();
        body.password = mEdtPassword.getText().toString();
        GsonRequest<User> request = new GsonRequest<>(Request.Method.POST, URLS.URL_LOGIN,
                body, User.class, new Response.Listener<User>() {
            @Override
            public void onResponse(User response) {
                mProgressDialog.dismiss();
                startHomeActivity(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(error, "Error during login request");
                mProgressDialog.dismiss();
            }
        });
        VolleyManager.getInstance(this).addToRequestQueue(request);
    }

    private void startHomeActivity(User user) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(HomeActivity.EXTRA_USER, user);
        startActivity(intent);
        finish();
    }
}
