package com.example.android.minireddit.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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

public class LogInFragment extends Fragment {


    // UI references.
    private AutoCompleteTextView mUserNameView;
    private EditText mPasswordView;
    private ProgressBar mProgressView;
    private View mLoginFormView;
    private Button mLoginButton;
    private TextView mSignUpView;
    private TextInputLayout mInputPassword;
    private TextInputLayout mInputUserNmae;
    private TextView mForgetPasswordView;


    // Helpers members
    private boolean mUserNameIsEmpety = true;
    private boolean mPasswordIsEmpety = true;
    private String mUserName;
    private String mPassword;

    public LogInFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_login, container, false);


        //Bind UI references
        mLoginButton = (Button) rootView.findViewById(R.id.log_in_button);
        mUserNameView = (AutoCompleteTextView) rootView.findViewById(R.id.email);
        mPasswordView = (EditText) rootView.findViewById(R.id.password);
        mSignUpView = (TextView) rootView.findViewById(R.id.sign_up_text_view);
        mInputPassword =(TextInputLayout) rootView.findViewById(R.id.password_auto);
        mInputUserNmae =(TextInputLayout) rootView.findViewById(R.id.user_name_auto);
        mProgressView = (ProgressBar)rootView.findViewById(R.id.login_progress);
        mForgetPasswordView = (TextView)rootView.findViewById(R.id.forget_password);
        //Listener part
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final boolean[] validUserName = {false};
                final boolean[] validePassword = {false};

                mInputUserNmae.setError(null);
                mInputPassword.setError(null);
                String password = mPasswordView.getText().toString();
                String userName = mUserNameView.getText().toString();
                validePassword[0] = true;
                validUserName[0] = true;
                if(userName.contains(" ")){
                    validUserName[0] = false;
                    mInputUserNmae.setError("Spaces not allowed");

                }
                if (password.contains(" ")){
                    validePassword[0] =false;
                    mInputPassword.setError("Spaces not allowed");
                }

                if(userName.length()<=3 || userName.length()>=20){
                    validUserName[0]=false;
                    mInputUserNmae.setError("Username have to be more than 3 and less than 20 char");
                }
                if(password.length()<8){
                    validePassword[0] = false;
                    mInputPassword.setError("Password have to be more than 8 char");
                }
                if (validePassword[0]&&validUserName[0]) {
                    unenableAll();
                    DependentClass dependentClass = DependentClass.getInstance();
                    dependentClass.logIn(getContext(), mUserName, mPassword);
                }else if(Constants.debug){
                    DependentClass dependentClass = DependentClass.getInstance();
                    dependentClass.logIn(getContext(), mUserName, mPassword);
                }
            }
        });

        mSignUpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewPager viewpager = getActivity().findViewById(R.id.pager);
                viewpager.setCurrentItem(1);
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

                if (!mPasswordIsEmpety && !mUserNameIsEmpety) {
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
                mUserName = text;
                if (text.length() == 0) mUserNameIsEmpety = true;
                else mUserNameIsEmpety = false;

                if (!mPasswordIsEmpety && !mUserNameIsEmpety) {
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


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Constants.mLogInSignUpSuccessful = new LogInSignUpSuccessful() {
            @Override
            public void Successful(boolean result) {
                if (result && Constants.debug) {
                    enableAll();
                    Toast.makeText(getContext(), "Log in as an admin successfully", Toast.LENGTH_SHORT).show();
                    Constants.user = new User("admin", "admin", null, "admin@gamil.com", null, 200, null, false);
                    Constants.mLogInSignUpSuccessful = null;
                    getActivity().finish();
                } else if (!result && Constants.debug) {
                    Toast.makeText(getContext(), "Log in as an admin unsuccessfully", Toast.LENGTH_SHORT).show();
                } else if (result && !Constants.debug) {
                    Constants.mLogInSignUpSuccessful = null;
                    getActivity().finish();
                } else {
                    Toast.makeText(getContext(), "Invalid user name or password", Toast.LENGTH_SHORT).show();
                }
                enableAll();
            }
        };
    }

    @Override
    public void onStop() {
        super.onStop();
        Constants.mLogInSignUpSuccessful = null;
    }
    private void unenableAll(){
        mLoginButton.setEnabled(false);
        mLoginButton.setBackgroundColor(getResources().getColor(R.color.buttonDisabledStateColor));
        mLoginButton.setTextColor(getResources().getColor(R.color.textDisabledStateColor));
        mProgressView.setVisibility(View.VISIBLE);

        mUserNameView.setEnabled(false);
        mProgressView.setEnabled(false);
        mSignUpView.setEnabled(false);
        mForgetPasswordView.setEnabled(false);

    }
    private void enableAll(){
        mLoginButton.setEnabled(true);
        mLoginButton.setBackgroundColor(getResources().getColor(R.color.buttonEnabledStateColor));
        mLoginButton.setTextColor(getResources().getColor(R.color.textEnabledStateColor));
        mProgressView.setVisibility(View.GONE);

        mUserNameView.setEnabled(true);
        mProgressView.setEnabled(true);
        mSignUpView.setEnabled(true);
        mForgetPasswordView.setEnabled(true);

    }
}
