package com.example.android.minireddit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.minireddit.abs.NavigateToAnotherUserProfile;
import com.example.android.minireddit.datastructure.User;
import com.example.android.minireddit.fragments.HomePageFragment;
import com.example.android.minireddit.fragments.MyProfileFragment;
import com.example.android.minireddit.fragments.SavedFragment;
import com.example.android.minireddit.libraries.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationViewEx.OnNavigationItemSelectedListener {


    //UI elements
    private Toolbar toolbar;
    private SearchView searchView;
    private NavigationView navigationView;
    private View JustView;
    private ImageView JustImage;
    private RelativeLayout RootView;
    private DrawerLayout drawer;

    //My fragments
    private HomePageFragment mHomePageFragment;
    private MyProfileFragment mMyProfileFragment;
    private SavedFragment mMySavedFragment;

    //helper members
    private boolean mInHomeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        //Set my custom toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //apply listener to my navigation view
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(HomePage.this);


        //Start motion configuration
        BottomNavigationViewEx bnve = (BottomNavigationViewEx) findViewById(R.id.navigation);

        bnve.enableAnimation(true);
        bnve.enableShiftingMode(false);
        bnve.enableItemShiftingMode(false);
        bnve.setTextVisibility(false);

        //apply listener to my bottom navigation bar
        bnve.setOnNavigationItemSelectedListener(HomePage.this);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        searchView = (SearchView) findViewById(R.id.search_bar);
        searchView.clearFocus();

        //Open navigation view when image get clicked
        ImageView userImage = (ImageView) findViewById(R.id.user_image);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //hide the default image and put my image un the drawer
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

        //initialize fragments
        mHomePageFragment = new HomePageFragment();
        mMyProfileFragment = new MyProfileFragment();
        mMySavedFragment = new SavedFragment();


        //set default fragment homePage
        loadFragment(mHomePageFragment);
        mInHomeScreen = true;

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
            // you need to have a list of data that you want the spinner to display
            List<String> spinnerArray =  new ArrayList<String>();
            spinnerArray.add(Constants.user.getmUserName());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, spinnerArray);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Spinner sItems = (Spinner) findViewById(R.id.log_in_spinner);
            sItems.setAdapter(adapter);

        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
           int count = getSupportFragmentManager().getBackStackEntryCount();

            if (count == 1 ||mInHomeScreen) {
                finish();
            } else {
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                setSupportActionBar(toolbar);
                getSupportActionBar().show();
                getSupportFragmentManager().popBackStack();
            }
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


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        boolean result = true;
        int id = item.getItemId();

        if (id == R.id.nav_my_profile) {
            mInHomeScreen = false;
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            getSupportActionBar().hide();
            Constants.visitedUser=new User();
            Constants.visitedUser.setmUserName(Constants.user.getmUserName());
            result = loadFragment(mMyProfileFragment);
        } else if (id == R.id.nav_reddit_coin) {
            Toast.makeText(this, "Not available yet!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_reddit_premium) {
            Toast.makeText(this, "Not available yet!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_my_saved) {
            mInHomeScreen = false;
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            getSupportActionBar().hide();
            result = loadFragment(mMySavedFragment);

        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_sign_up_log_in) {
            Intent intent = new Intent(this, LogIn_SignUpActivity.class);
            startActivity(intent);

        } else if (id == R.id.navigation_home) {
            mInHomeScreen = true;
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            setSupportActionBar(toolbar);
            getSupportActionBar().show();
            result = loadFragment(mHomePageFragment);
        } else if (id == R.id.navigation_dashboard) {

        } else if (id == R.id.navigation_new_post) {
            if(Constants.mToken.isEmpty())
                Toast.makeText(getApplicationContext(),"Please Login First To Write Post.",Toast.LENGTH_SHORT).show();
            else {
                Intent intent = new Intent(this, WritePost.class);
                intent.putExtra("Type","Video");
                intent.putExtra("Post","Write");
                startActivity(intent);
            }
        } else if (id == R.id.navigation_chat) {
            Toast.makeText(this, "Not available yet!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.navigation_message) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return result;
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();

            fragmentManager.executePendingTransactions();

            /*Constants.poster.mNavigateToAnotherUserProfile = new NavigateToAnotherUserProfile() {
                @Override
                public void navigateToAnotherUserProfile(String userName) {

                    mInHomeScreen = false;
                    drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    getSupportActionBar().hide();
                    Constants.visitedUser=new User();
                    Constants.visitedUser.setmUserName(userName);
                    loadFragment(mMyProfileFragment);
                }
            };

            Constants.homeposts.mNavigateToAnotherUserProfile = new NavigateToAnotherUserProfile() {
                @Override
                public void navigateToAnotherUserProfile(String userName) {
                    //super.navigateToAnotherUserProfile();
                    mInHomeScreen = false;
                    drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    getSupportActionBar().hide();
                    Constants.visitedUser=new User();
                    Constants.visitedUser.setmUserName(userName);
                    loadFragment(mMyProfileFragment);
                }
            };
*/
            return true;
        } else return false;
    }

//    public void navigator() {
//        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().show();
//        mInHomeScreen = true;
//        loadFragment(mHomePageFragment);
//
//    }
}
