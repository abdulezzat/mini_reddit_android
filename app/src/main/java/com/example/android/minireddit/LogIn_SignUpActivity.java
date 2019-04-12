package com.example.android.minireddit;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.android.minireddit.adapters.ViewPagerAdapter;
import com.example.android.minireddit.adapters.ViewPagerAdapter_LogIn_SignUp;

public class LogIn_SignUpActivity extends AppCompatActivity {

    //UI elements
    private ViewPager mViewPager;

    //Adapter
    private ViewPagerAdapter_LogIn_SignUp mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in__sign_up);
        getSupportActionBar().show();
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Bind UI references
        mViewPager = (ViewPager)findViewById(R.id.pager);
        mAdapter = new ViewPagerAdapter_LogIn_SignUp(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() == 1) {
            mViewPager.setCurrentItem(0);
        } else {
            finish();
        }
    }

    public void navigateToSignUp(){
        mViewPager.setCurrentItem(1);
    }
    public void navigateToLogIn(){
        mViewPager.setCurrentItem(0);
    }

}
