package com.example.android.minireddit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.minireddit.datastructure.User;
import com.example.android.minireddit.networking.DependentClass;

public class SignUpActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private AutoCompleteTextView mUserNameView;
    private EditText mPasswordView;
    private TextView mLogInInstead;
    private View mProgressView;
    private View mLoginFormView;
    private Button mCreateAccountButton;

    // Helpers members
    private boolean mEmailIsEmpety=true;
    private boolean mUserNameIsEmpety=true;
    private boolean mPasswordIsEmpety=true;
    private String mEmail;
    private String mPassword;
    private String mUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().show();
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Bind UI references
        mCreateAccountButton = (Button)findViewById(R.id.create_account_button);
        mEmailView = (AutoCompleteTextView)findViewById(R.id.email);
        mUserNameView = (AutoCompleteTextView)findViewById(R.id.user_name);
        mPasswordView = (EditText)findViewById(R.id.password);
        mLogInInstead = (TextView)findViewById(R.id.log_in_instead);

        //Listener part
        mCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = DependentClass.getInstance().signUp(SignUpActivity.this,mEmail,mUserName,mPassword);
                if(result && Constants.debug){
                    Toast.makeText(getBaseContext(),"Sign up as an admin successfully",Toast.LENGTH_SHORT).show();
                    Constants.user =new User("admin","admin",null,"admin@gamil.com",null,200,null,false);
                    setResult(Constants.CREATE_ACCOUNT_SUCCESSFULLY);
                    finish();
                }else if(!result&&Constants.debug){
                    Toast.makeText(getBaseContext(),"Sign up as an admin unsuccessfully",Toast.LENGTH_SHORT).show();
                }else if(result && !Constants.debug){
                    DependentClass.getInstance().signUp(SignUpActivity.this,mEmail,mUserName,mPassword);
                    //TODO handel this after the connection is complete "sign up successfully"
                }else{
                    //TODO handel this after the connection is complete "sign up unsuccessfully"
                }
            }
        });

        mLogInInstead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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
                mPassword =text;
                if(text.length()==0) mPasswordIsEmpety = true;
                else mPasswordIsEmpety = false;

                if(!mPasswordIsEmpety&&!mEmailIsEmpety&&!mUserNameIsEmpety) {
                    mCreateAccountButton.setEnabled(true);
                    mCreateAccountButton.setBackgroundColor(getResources().getColor(R.color.buttonEnabledStateColor));
                    mCreateAccountButton.setTextColor(getResources().getColor(R.color.textEnabledStateColor));
                }
                else {
                    mCreateAccountButton.setEnabled(false);
                    mCreateAccountButton.setBackgroundColor(getResources().getColor(R.color.buttonDisabledStateColor));
                    mCreateAccountButton.setTextColor(getResources().getColor(R.color.textDisabledStateColor));
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
                mEmail= text;
                if(text.length()==0) mEmailIsEmpety = true;
                else mEmailIsEmpety = false;

                if(!mPasswordIsEmpety&&!mEmailIsEmpety&&!mUserNameIsEmpety) {
                    mCreateAccountButton.setEnabled(true);
                    mCreateAccountButton.setBackgroundColor(getResources().getColor(R.color.buttonEnabledStateColor));
                    mCreateAccountButton.setTextColor(getResources().getColor(R.color.textEnabledStateColor));
                }
                else {
                    mCreateAccountButton.setEnabled(false);
                    mCreateAccountButton.setBackgroundColor(getResources().getColor(R.color.buttonDisabledStateColor));
                    mCreateAccountButton.setTextColor(getResources().getColor(R.color.textDisabledStateColor));
                }
            }
        });

        mUserNameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String text = mUserNameView.getText().toString().replace(" ", "");
                mUserName =text;
                if(text.length()==0) mUserNameIsEmpety = true;
                else mUserNameIsEmpety = false;

                if(!mPasswordIsEmpety&&!mEmailIsEmpety&&!mUserNameIsEmpety) {
                    mCreateAccountButton.setEnabled(true);
                    mCreateAccountButton.setBackgroundColor(getResources().getColor(R.color.buttonEnabledStateColor));
                    mCreateAccountButton.setTextColor(getResources().getColor(R.color.textEnabledStateColor));
                }
                else {
                    mCreateAccountButton.setEnabled(false);
                    mCreateAccountButton.setBackgroundColor(getResources().getColor(R.color.buttonDisabledStateColor));
                    mCreateAccountButton.setTextColor(getResources().getColor(R.color.textDisabledStateColor));
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
        setResult(Constants.CREATE_ACCOUNT_UNSUCCESSFULLY);
        finish();
    }
}
