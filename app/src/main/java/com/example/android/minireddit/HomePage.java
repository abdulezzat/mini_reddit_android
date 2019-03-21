package com.example.android.minireddit;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.android.minireddit.adapters.ViewPagerAdapter;
import com.example.android.minireddit.libraries.BottomNavigationViewEx;


public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    private SearchView searchView;
    private NavigationView navigationView;
    private View JustView;
    private ImageView JustImage;
    private RelativeLayout RootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        //Set my custom toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(HomePage.this);

        //ViewPager setting
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);


        //Start motion configuration

        BottomNavigationViewEx bnve = (BottomNavigationViewEx) findViewById(R.id.navigation);

        bnve.enableAnimation(true);
        bnve.enableShiftingMode(false);
        bnve.enableItemShiftingMode(false);
        bnve.setTextVisibility(false);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        searchView = (SearchView) findViewById(R.id.search_bar);
        searchView.clearFocus();

        //Open navigation view when image get clicked
        ImageView userImage = (ImageView) findViewById(R.id.user_image);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //HIde the default image and put my image un the drawer
        toggle.setDrawerIndicatorEnabled(false);

        //listener to open the drawer
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Disable the internal motion in the bottom navigation bar

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms

                //Hide app image and the background view and make the root view visible
                JustImage = (ImageView) findViewById(R.id.just_image);
                JustView = (View) findViewById(R.id.just_view);
                RootView = (RelativeLayout) findViewById(R.id.rootView);

                JustView.setVisibility(View.GONE);
                JustImage.setVisibility(View.GONE);
                RootView.setVisibility(View.VISIBLE);


            }
        }, 2000);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Update the navigation view if the user is anonymous
        if (Constants.user == null) {
            navigationView.getMenu().getItem(0).setVisible(false);
            navigationView.getMenu().getItem(1).setVisible(false);
            navigationView.getMenu().getItem(2).setVisible(false);
            navigationView.getMenu().getItem(3).setVisible(false);
            navigationView.getMenu().getItem(4).setVisible(false);
            navigationView.getMenu().getItem(5).setVisible(true);
            View header = getLayoutInflater().inflate(R.layout.nav_header_home_page_anonymous, null);
            navigationView.addHeaderView(header);
            navigationView.removeHeaderView(navigationView.getHeaderView(0));
        } else {
            //Update the navigation view if the user isn't anonymous
            navigationView.getMenu().getItem(0).setVisible(true);
            navigationView.getMenu().getItem(2).setVisible(true);
            navigationView.getMenu().getItem(1).setVisible(true);
            navigationView.getMenu().getItem(3).setVisible(true);
            navigationView.getMenu().getItem(4).setVisible(true);
            navigationView.getMenu().getItem(5).setVisible(false);
            View header = getLayoutInflater().inflate(R.layout.nav_header_home_page, null);
            navigationView.addHeaderView(header);
            navigationView.removeHeaderView(navigationView.getHeaderView(0));

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_profile) {
            // Handle the camera action
        } else if (id == R.id.nav_my_profile) {

        } else if (id == R.id.nav_reddit_coin) {
            Toast.makeText(this, "Not available yet!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_reddit_premium) {
            Toast.makeText(this, "Not available yet!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_my_saved) {

        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_sign_up_log_in) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
