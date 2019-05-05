package com.example.android.minireddit.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.minireddit.Constants;
import com.example.android.minireddit.R;
import com.example.android.minireddit.abs.LogInSignUpSuccessful;
import com.example.android.minireddit.datastructure.User;
import com.example.android.minireddit.networking.DependentClass;

public class SignUpFragment extends Fragment {

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


    public SignUpFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_sign_up, container, false);

        //Bind UI references
        mCreateAccountButton = (Button)rootView.findViewById(R.id.create_account_button);
        mEmailView = (AutoCompleteTextView)rootView.findViewById(R.id.email);
        mUserNameView = (AutoCompleteTextView)rootView.findViewById(R.id.user_name);
        mPasswordView = (EditText)rootView.findViewById(R.id.password);
        mLogInInstead = (TextView)rootView.findViewById(R.id.log_in_instead);



        //Listener part
        mCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DependentClass dependentClass =DependentClass.getInstance();
                dependentClass.signUp(getContext(),mEmail,mUserName,mPassword);

            }
        });

        mLogInInstead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AppCompatActivity act = (AppCompatActivity) getActivity();
                try {
                    act.onBackPressed();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
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

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Constants.mLogInSignUpSuccessful = new LogInSignUpSuccessful() {
            @Override
            public void Successful(boolean result) {
                if(result && Constants.debug){
                    Toast.makeText(getContext(),"Sign up as an admin successfully",Toast.LENGTH_SHORT).show();
                    Constants.user =new User("admin","admin",null,"admin@gamil.com",null,200,null,false);
                    Constants.mLogInSignUpSuccessful = null;
                    getActivity().finish();
                }else if(!result&&Constants.debug){
                    Toast.makeText(getContext(),"Sign up as an admin unsuccessfully",Toast.LENGTH_SHORT).show();
                }else if(result && !Constants.debug){
                    Constants.mLogInSignUpSuccessful = null;
                    getActivity().finish();
                } else {
                    Toast.makeText(getContext(),"Something wrong please try again later",Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    public void onStop() {
        super.onStop();
        Constants.mLogInSignUpSuccessful = null;
    }

}
