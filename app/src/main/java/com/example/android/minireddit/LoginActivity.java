package com.example.android.minireddit;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.minireddit.datastructure.User;
import com.example.android.minireddit.networking.*;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button mLoginButton;
    private TextView mSignUpView;

    // Helpers members
    private boolean mEmailIsEmpety = true;
    private boolean mPasswordIsEmpety = true;
    private String mEmail;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().show();
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Bind UI references
        mLoginButton = (Button) findViewById(R.id.log_in_button);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mSignUpView = (TextView) findViewById(R.id.sign_up_text_view);


        //Listener part
        mLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = DependentClass.getInstance().logIn(LoginActivity.this,mEmail, mPassword);
                if (result && Constants.debug) {
                    Toast.makeText(getBaseContext(), "Log in as an admin successfully", Toast.LENGTH_SHORT).show();
                    Constants.user = new User("admin", "admin", null, "admin@gamil.com", null, 200, null, false);
                    finish();
                } else if (!result && Constants.debug) {
                    Toast.makeText(getBaseContext(), "Log in as an admin unsuccessfully", Toast.LENGTH_SHORT).show();
                } else if (result && !Constants.debug) {
                    //TODO handel this after the connection is complete "log in successfully"
                } else {
                    //TODO handel this after the connection is complete "log in unsuccessfully"
                }
            }
        });

        mSignUpView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivityForResult(intent,Constants.CREATE_ACCOUNT_SUCCESSFULLY);
            }
        });
        mPasswordView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String text = mPasswordView.getText().toString().replace(" ", "");
                mPassword = text;
                if (text.length() == 0) mPasswordIsEmpety = true;
                else mPasswordIsEmpety = false;

                if (!mPasswordIsEmpety && !mEmailIsEmpety) {
                    mLoginButton.setEnabled(true);
                    mLoginButton.setBackgroundColor(getResources().getColor(R.color.buttonEnabledStateColor));
                    mLoginButton.setTextColor(getResources().getColor(R.color.textEnabledStateColor));
                } else {
                    mLoginButton.setEnabled(false);
                    mLoginButton.setBackgroundColor(getResources().getColor(R.color.buttonDisabledStateColor));
                    mLoginButton.setTextColor(getResources().getColor(R.color.textDisabledStateColor));
                }
            }
        });

        mEmailView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String text = mEmailView.getText().toString().replace(" ", "");
                mEmail = text;
                if (text.length() == 0) mEmailIsEmpety = true;
                else mEmailIsEmpety = false;

                if (!mPasswordIsEmpety && !mEmailIsEmpety) {
                    mLoginButton.setEnabled(true);
                    mLoginButton.setBackgroundColor(getResources().getColor(R.color.buttonEnabledStateColor));
                    mLoginButton.setTextColor(getResources().getColor(R.color.textEnabledStateColor));
                } else {
                    mLoginButton.setEnabled(false);
                    mLoginButton.setBackgroundColor(getResources().getColor(R.color.buttonDisabledStateColor));
                    mLoginButton.setTextColor(getResources().getColor(R.color.textDisabledStateColor));
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // Check which request we're responding to
        if (resultCode == Constants.CREATE_ACCOUNT_SUCCESSFULLY) {
            if (Constants.debug) {
                Toast.makeText(getBaseContext(), "Log in as an admin successfully", Toast.LENGTH_SHORT).show();
                Constants.user = new User("admin", "admin", null, "admin@gamil.com", null, 200, null, false);
                finish();
            } else {
                //TODO handel this after the connection is complete "log in successfully as a user"
            }
        } else if (resultCode == Constants.CREATE_ACCOUNT_UNSUCCESSFULLY) {
            Toast.makeText(getBaseContext(), "sign up fail", Toast.LENGTH_SHORT).show();
        }
    }
}

