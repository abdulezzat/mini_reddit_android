package com.example.android.minireddit.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            DependentClass dependentClass = DependentClass.getInstance();
                            dependentClass.logIn(getContext(), mUserName, mPassword);
                        }
                    }, 1000);
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


        mForgetPasswordView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Constants.mLogInSuccessful = new LogInSignUpSuccessful() {
            @Override
            public void Successful(boolean result) {
                enableAll();
                if (result && Constants.debug) {
                    Toast.makeText(getContext(), "Log in as an admin successfully", Toast.LENGTH_SHORT).show();
                    Constants.user = new User("admin", "admin", null, "admin@gamil.com", null, 200, null, false);
                    Constants.mLogInSuccessful = null;
                    getActivity().finish();
                } else if (!result && Constants.debug) {
                    Toast.makeText(getContext(), "Log in as an admin unsuccessfully", Toast.LENGTH_SHORT).show();
                } else if (result && !Constants.debug) {
                    Constants.mLogInSuccessful = null;
                    getActivity().finish();
                } else {
                    Toast.makeText(getContext(), "Invalid user name or password", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    public void onStop() {
        super.onStop();
        Constants.mLogInSuccessful = null;
    }
    private void unenableAll(){
        mLoginButton.setEnabled(false);
        mLoginButton.setBackgroundColor(getResources().getColor(R.color.buttonDisabledStateColor));
        mLoginButton.setTextColor(getResources().getColor(R.color.textDisabledStateColor));
        mProgressView.setVisibility(View.VISIBLE);

        mUserNameView.setEnabled(false);
        mPasswordView.setEnabled(false);
        mSignUpView.setEnabled(false);
        mForgetPasswordView.setEnabled(false);

    }
    private void enableAll(){
        mLoginButton.setEnabled(true);
        mLoginButton.setBackgroundColor(getResources().getColor(R.color.buttonEnabledStateColor));
        mLoginButton.setTextColor(getResources().getColor(R.color.textEnabledStateColor));
        mProgressView.setVisibility(View.GONE);

        mUserNameView.setEnabled(true);
        mPasswordView.setEnabled(true);
        mSignUpView.setEnabled(true);
        mForgetPasswordView.setEnabled(true);

    }

    private void openDialog(){


        AlertDialog.Builder mBuilder = new AlertDialog.Builder( getContext());
        View mView = getLayoutInflater().inflate(R.layout.dialog_login, null);
        final EditText mEmail = (EditText) mView.findViewById(R.id.email);
        final EditText mUsername = (EditText) mView.findViewById(R.id.username);
        Button send = (Button) mView.findViewById(R.id.email_me);
        Button cancel = (Button) mView.findViewById(R.id.cancel_action);
        final TextInputLayout dmInputEmail = (TextInputLayout)mView.findViewById(R.id.email_auto);
        final TextInputLayout dmInputUserNmae = (TextInputLayout)mView.findViewById(R.id.user_name_auto);

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final boolean[] validUserName = {false};
                final boolean[] validEmail = {false};

                dmInputEmail.setError(null);
                dmInputUserNmae.setError(null);
                String email = mEmail.getText().toString();
                String userName = mUsername.getText().toString();
                validEmail[0] = true;
                validUserName[0] = true;
                if(userName.contains(" ")){
                    validUserName[0] = false;
                    dmInputUserNmae.setError("Spaces not allowed");

                }
                if (email.contains(" ")){
                    validEmail[0] =false;
                    dmInputEmail.setError("Spaces not allowed");
                }

                if(userName.length()<=3 || userName.length()>=20){
                    validUserName[0]=false;
                    dmInputUserNmae.setError("Username have to be more than 3 and less than 20 char");
                }
                if(!email.contains("@gmail.com")&&!email.contains("@hotmail.com")&&!email.contains("@yahoo.com")){
                    validEmail[0] = false;
                    dmInputEmail.setError("Invalid email address");
                }
                if (email.length() == 0){
                    validEmail[0] =false;
                    dmInputEmail.setError("Empty field");
                }
                if (userName.length() == 0){
                    validEmail[0] =false;
                    dmInputUserNmae.setError("Empty field");
                }
                if (validEmail[0]&&validUserName[0]) {
                    DependentClass dependentClass = DependentClass.getInstance();
                    dependentClass.forgetPassword(getContext(),email);
                    dialog.cancel();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });


    }
}
