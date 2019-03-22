package com.example.android.minireddit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

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

    // Helpers members
    private boolean mEmailIsEmpety=false;
    private boolean mPasswordIsEmpety=false;
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
        mLoginButton = (Button)findViewById(R.id.log_in_button);
        mEmailView = (AutoCompleteTextView)findViewById(R.id.email);
        mPasswordView = (EditText)findViewById(R.id.password);


        //Listener part
        mLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = DependentClass.getInstance().logIn(mEmail,mPassword);
                if(result && Constants.debug){
                    Constants.user =new User("admin","admin",null,"admin@gamil.com",null,null,200,null,false);
                    finish();
                }
            }
        });


    }


}

