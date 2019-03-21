package com.example.android.minireddit;

import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.minireddit.adapters.ProfilePagerAdapter;
import com.example.android.minireddit.datastructure.User;
import com.example.android.minireddit.fragments.ProfileAboutFragment;
import com.example.android.minireddit.fragments.ProfileCommentsFragment;
import com.example.android.minireddit.fragments.ProfilePostsFragment;
import com.example.android.minireddit.networking.DependentClass;
import com.example.android.minireddit.networking.MockRestService;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {
    ProfilePostsFragment profilePostsFragment;
    ProfileCommentsFragment profileCommentsFragment;
    ProfileAboutFragment profileAboutFragment;
    User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mUser = DependentClass.getInstance().getUser("karashily");
        profilePostsFragment=new ProfilePostsFragment();
        profileCommentsFragment=new ProfileCommentsFragment();
        profileAboutFragment=new ProfileAboutFragment();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tab_layout);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.profile_view_pager);

        ProfilePagerAdapter mProfilePagerAdapter = new ProfilePagerAdapter(getSupportFragmentManager());

        mProfilePagerAdapter.addFragment(profilePostsFragment,"Posts", mUser);
        mProfilePagerAdapter.addFragment(profileCommentsFragment,"Comments", mUser);
        mProfilePagerAdapter.addFragment(profileAboutFragment,"About", mUser);

        mViewPager.setAdapter(mProfilePagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ImageView headerImage=(ImageView) findViewById(R.id.header_photo);
        TextView displayName=(TextView) findViewById(R.id.display_name);
        Button followButton=(Button) findViewById(R.id.follow_button);
        TextView editButton=(TextView) findViewById(R.id.edit_profile);
        TextView userName=(TextView) findViewById(R.id.username);
        TextView userKarma=(TextView) findViewById(R.id.karma);
        TextView userDate=(TextView) findViewById(R.id.date);
        TextView userFollowersCount=(TextView) findViewById(R.id.followers_count);
        TextView dotBeforeFollowers=(TextView) findViewById(R.id.dot_before_followers);
        TextView userAbout=(TextView) findViewById(R.id.user_about);

        displayName.setText(mUser.getDisplayName());

        String mUserName="u/"+mUser.getUserName();
        userName.setText(mUserName);

        String mUserKarma=mUser.getKarma()+" karma";
        userKarma.setText(mUserKarma);

        userDate.setText(mUser.getRedditAge());

        String mUserFollowersCount=mUser.getFollowersNo()+" followers";
        userFollowersCount.setText(mUserFollowersCount);

        userAbout.setText(mUser.getAbout());

    }

}
