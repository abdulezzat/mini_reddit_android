package com.example.android.minireddit.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
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
import android.widget.ProgressBar;
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
    private TextInputLayout mEmailAuto;
    private TextInputLayout mUserNmaeAuto;
    private TextInputLayout mPasswordAuto;
    private TextView mLogInInstead;
    private View mProgressView;
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
        mProgressView = (ProgressBar)rootView.findViewById(R.id.login_progress);
        mEmailAuto = (TextInputLayout) rootView.findViewById(R.id.email_auto);
        mPasswordAuto =(TextInputLayout)rootView.findViewById(R.id.password_auto);
        mUserNmaeAuto = (TextInputLayout)rootView.findViewById(R.id.user_name_auto);


        //Listener part
        mCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final boolean[] validUserName = {false};
                final boolean[] validePassword = {false};
                final boolean[] validEmail = {false};
                mPasswordAuto.setError(null);
                mUserNmaeAuto.setError(null);
                mEmailAuto.setError(null);
                String password = mPasswordView.getText().toString();
                String userName = mUserNameView.getText().toString();
                String email = mEmailView.getText().toString();
                validePassword[0] = true;
                validUserName[0] = true;
                validEmail[0] = true;
                if(userName.contains(" ")){
                    validUserName[0] = false;
                    mUserNmaeAuto.setError("Spaces not allowed");

                }

                if(email.contains(" ")){
                    validEmail[0] = false;
                    mEmailAuto.setError("Spaces not allowed");

                }
                if (password.contains(" ")){
                    validePassword[0] =false;
                    mPasswordAuto.setError("Spaces not allowed");
                }

                if(userName.length()<=3 || userName.length()>=20){
                    validUserName[0]=false;
                    mUserNmaeAuto.setError("Username have to be more than 3 and less than 20 char");
                }
                if(password.length()<8){
                    validePassword[0] = false;
                    mPasswordAuto.setError("Password have to be more than 8 char");
                }

                if(!email.contains("@gmail.com")&&!email.contains("@hotmail.com")&&!email.contains("@yahoo.com")){
                    validEmail[0] = false;
                    mEmailAuto.setError("Invalid email address");
                }

                if (validePassword[0]&&validUserName[0]&&validEmail[0]) {
                    unenableAll();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            DependentClass dependentClass =DependentClass.getInstance();
                            dependentClass.signUp(getContext(),mEmail,mUserName,mPassword);
                        }
                    }, 1000);

                }else if(Constants.debug){
                    DependentClass dependentClass =DependentClass.getInstance();
                    dependentClass.signUp(getContext(),mEmail,mUserName,mPassword);
                }


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
        Constants.mSignUpSuccessful = new LogInSignUpSuccessful() {
            @Override
            public void Successful(boolean result) {
                enableAll();
                if(result && Constants.debug){
                    Toast.makeText(getContext(),"Sign up as an admin successfully",Toast.LENGTH_SHORT).show();
                    Constants.user =new User("admin","admin",null,"admin@gamil.com",null,200,null,false);
                    Constants.mSignUpSuccessful = null;
                    getActivity().finish();
                }else if(!result&&Constants.debug){
                    Toast.makeText(getContext(),"Sign up as an admin unsuccessfully",Toast.LENGTH_SHORT).show();
                }else if(result && !Constants.debug){
                    Constants.mSignUpSuccessful = null;
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
        Constants.mSignUpSuccessful = null;
    }

    private void unenableAll(){
        mCreateAccountButton.setEnabled(false);
        mCreateAccountButton.setBackgroundColor(getResources().getColor(R.color.buttonDisabledStateColor));
        mCreateAccountButton.setTextColor(getResources().getColor(R.color.textDisabledStateColor));
        mProgressView.setVisibility(View.VISIBLE);

        mUserNameView.setEnabled(false);
        mPasswordView.setEnabled(false);
        mEmailView.setEnabled(false);
        mLogInInstead.setEnabled(false);


    }
    private void enableAll(){
        mCreateAccountButton.setEnabled(true);
        mCreateAccountButton.setBackgroundColor(getResources().getColor(R.color.buttonEnabledStateColor));
        mCreateAccountButton.setTextColor(getResources().getColor(R.color.textEnabledStateColor));
        mProgressView.setVisibility(View.GONE);

        mUserNameView.setEnabled(true);
        mPasswordView.setEnabled(true);
        mEmailView.setEnabled(true);
        mLogInInstead.setEnabled(true);


    }

}
