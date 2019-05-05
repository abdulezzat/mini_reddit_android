package com.example.android.minireddit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
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
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.minireddit.abs.LogOutCallBack;
import com.example.android.minireddit.adapters.AccountsAdapter;
import com.example.android.minireddit.datastructure.User;
import com.example.android.minireddit.fragments.HomePageFragment;
import com.example.android.minireddit.fragments.InboxFragment;
import com.example.android.minireddit.fragments.MyProfileFragment;
import com.example.android.minireddit.fragments.SavedFragment;
import com.example.android.minireddit.libraries.BottomNavigationViewEx;
import com.onesignal.OneSignal;

import java.util.ArrayList;


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
    private RelativeLayout mAccountsRelative;
    private TextView mUsername;

    //My fragments
    private HomePageFragment mHomePageFragment;
    private MyProfileFragment mMyProfileFragment;
    private SavedFragment mMySavedFragment;
    private InboxFragment mInboxFragment;

    //helper members
    private boolean mInHomeScreen;
    private Point mPoint;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

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
        mPoint = new Point();
        Constants.logOutCallBack = new LogOutCallBack() {
            @Override
            public void logOut() {
                logOut();
            }
        };
        mInboxFragment = new InboxFragment();


        findViewById(R.id.settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SettingsActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        //set default fragment homePage
        loadFragment(mHomePageFragment);
        mInHomeScreen = true;

        //pref
        pref = getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();


    }

    @Override
    protected void onStart() {
        super.onStart();
        //Disable the internal motion in the bottom navigation bar

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 2s = 2000ms

                //Hide app image and the background view and make the root view visible
                JustImage = (ImageView) findViewById(R.id.just_image);
                JustView = (View) findViewById(R.id.just_view);
                RootView = (RelativeLayout) findViewById(R.id.rootView);

                JustView.setVisibility(View.GONE);
                JustImage.setVisibility(View.GONE);
                RootView.setVisibility(View.VISIBLE);
                checkLogenIn();

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
            mAccountsRelative = (RelativeLayout) findViewById(R.id.username);
            mUsername = (TextView) findViewById(R.id.username_text);
            mUsername.setText(Constants.user.getmUserName());

            mAccountsRelative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    int height = displayMetrics.heightPixels;
                    int width = displayMetrics.widthPixels;
                    mPoint.x = width * 0;
                    mPoint.y = height - height / 2;

                    showPopup(HomePage.this, mPoint);

                    OneSignal.startInit(HomePage.this)
                            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                            .unsubscribeWhenNotificationsAreDisabled(true)
                            .init();

                    OneSignal.sendTag("username", Constants.user.getmUserName());
                }
            });

        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int count = getSupportFragmentManager().getBackStackEntryCount();

            if (count == 1 || mInHomeScreen) {
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
            Constants.visitedUser = new User();
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
            if (Constants.mToken.isEmpty())
                Toast.makeText(getApplicationContext(), "Please Login First To Write Post.", Toast.LENGTH_SHORT).show();
            else {
                Point point = new Point();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int height = displayMetrics.heightPixels;
                int width = displayMetrics.widthPixels;
                point.x = width * 0;
                point.y = height - height / 2;
                showPopupCreatePost(this, point);
            }
        } else if (id == R.id.navigation_chat) {
            Toast.makeText(this, "Not available yet!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.navigation_message) {
            if (Constants.mToken.isEmpty())
                Toast.makeText(getApplicationContext(), "Please Login First To Write Post.", Toast.LENGTH_SHORT).show();
            else {
                mInHomeScreen = false;
                getSupportActionBar().hide();
                result = loadFragment(mInboxFragment);
            }
        }
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
            return true;
        } else return false;
    }

    // Get the x and y position after the button is draw on screen
    // (It's important to note that we can't get the position in the onCreate(),
    // because at that stage most probably the view isn't drawn yet, so it will return (0, 0))

    // The method that displays the popup.
    private void showPopup(final Activity context, Point p) {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int popupWidth = size.x;
        double y = 0.333 * size.y;
        int popupHeight = (int) y;

        drawer.closeDrawer(GravityCompat.START);

        // Inflate the popup_layout.xml
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_layout, null);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's
        // position.
        int OFFSET_X = width;
        int OFFSET_Y = height / 2;

        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);

        ArrayList<String> usernames = new ArrayList<String>();
        usernames.add(Constants.user.getmUserName());
        AccountsAdapter accountsAdapter = new AccountsAdapter(this, usernames);
        ListView listView = (ListView) layout.findViewById(R.id.accounts_list);
        listView.setAdapter(accountsAdapter);
        RelativeLayout anon = (RelativeLayout) layout.findViewById(R.id.anonymous);
        RelativeLayout add = (RelativeLayout) layout.findViewById(R.id.add_account);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, LogIn_SignUpActivity.class);
                startActivity(intent);
            }
        });
        anon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
                popup.dismiss();
            }
        });
    }


    private void showPopupCreatePost(final Activity context, Point p) {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int popupWidth = size.x;
        double y = 0.3 * size.y;
        int popupHeight = (int) y;

        drawer.closeDrawer(GravityCompat.START);

        // Inflate the popup_layout.xml
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_create_post_layout, null);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's
        // position.
        int OFFSET_X = width;
        int OFFSET_Y = height / 2;

        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);


        ImageView cross = (ImageView) layout.findViewById(R.id.cross);
        RelativeLayout link = (RelativeLayout) layout.findViewById(R.id.link);
        RelativeLayout image = (RelativeLayout) layout.findViewById(R.id.image);
        RelativeLayout video = (RelativeLayout) layout.findViewById(R.id.video);
        RelativeLayout text = (RelativeLayout) layout.findViewById(R.id.text);

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup.dismiss();
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, WritePost.class);
                intent.putExtra("Type", "Image");
                intent.putExtra("Post", "Write");
                startActivity(intent);
            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, WritePost.class);
                intent.putExtra("Type", "Text");
                intent.putExtra("Post", "Write");
                startActivity(intent);
            }
        });

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, WritePost.class);
                intent.putExtra("Type", "Text");
                intent.putExtra("Post", "Write");
                startActivity(intent);
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, WritePost.class);
                intent.putExtra("Type", "Video");
                intent.putExtra("Post", "Write");
                startActivity(intent);
            }
        });

    }

    private void logOut() {
        Toast.makeText(this, "logOut", Toast.LENGTH_SHORT).show();
        Constants.user = null;
        onResume();
        Constants.mToken = "";
        editor.clear();
        editor.commit();
        Constants.homeposts.clear();
        Constants.homeposts.notifyDataSetChanged();
    }

    private void checkLogenIn() {
        if (pref.getString("token", null) != null) {
            if (Constants.user == null) {
                Constants.user = new User();
            }
            Constants.user.setmUserName(pref.getString("acc", null));
            Constants.mToken = pref.getString("token", null);
            onResume();
        }
    }
}

