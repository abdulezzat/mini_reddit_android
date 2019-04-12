package com.example.android.minireddit.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.minireddit.Constants;
import com.example.android.minireddit.R;
import com.example.android.minireddit.datastructure.User;
import com.example.android.minireddit.networking.DependentClass;

public class LogInFragment extends Fragment {


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

    public LogInFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_login, container, false);

        //Bind UI references
        mLoginButton = (Button) rootView.findViewById(R.id.log_in_button);
        mEmailView = (AutoCompleteTextView) rootView.findViewById(R.id.email);
        mPasswordView = (EditText) rootView.findViewById(R.id.password);
        mSignUpView = (TextView) rootView.findViewById(R.id.sign_up_text_view);


        //Listener part
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = DependentClass.getInstance().logIn(getContext(),mEmail, mPassword);
                if (result && Constants.debug) {
                    Toast.makeText(getContext(), "Log in as an admin successfully", Toast.LENGTH_SHORT).show();
                    Constants.user = new User("admin", "admin", null, "admin@gamil.com", null, 200, null, false);
                    getActivity().finish();
                } else if (!result && Constants.debug) {
                    Toast.makeText(getContext(), "Log in as an admin unsuccessfully", Toast.LENGTH_SHORT).show();
                } else if (result && !Constants.debug) {
                    //TODO handel this after the connection is complete "log in successfully"
                } else {
                    //TODO handel this after the connection is complete "log in unsuccessfully"
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



        return rootView;
    }
}
