package com.example.android.minireddit.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.minireddit.R;
import com.example.android.minireddit.adapters.ProfilePagerAdapter;
import com.example.android.minireddit.datastructure.User;
import com.example.android.minireddit.networking.DependentClass;

/**
 * A simple {@link Fragment} subclass.
 *
 * the profile fragment to show different user's profile.
 */
public class MyProfileFragment extends Fragment {

    ProfilePostsFragment mProfilePostsFragment;
    ProfileCommentsFragment mProfileCommentsFragment;
    ProfileAboutFragment mProfileAboutFragment;
    User mUser;

    public MyProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my_profile, container, false);

        // get the user of the profile
        mUser = DependentClass.getInstance().getUser("karashily");
        mProfilePostsFragment =new ProfilePostsFragment();
        mProfileCommentsFragment =new ProfileCommentsFragment();
        mProfileAboutFragment =new ProfileAboutFragment();


        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);

        TabLayout mTabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout);

        ViewPager mViewPager = (ViewPager) rootView.findViewById(R.id.profile_view_pager);

        ProfilePagerAdapter mProfilePagerAdapter = new ProfilePagerAdapter(getFragmentManager());

        mProfilePagerAdapter.addFragment(mProfilePostsFragment,"Posts", mUser);
        mProfilePagerAdapter.addFragment(mProfileCommentsFragment,"Comments", mUser);
        mProfilePagerAdapter.addFragment(mProfileAboutFragment,"About", mUser);

        mViewPager.setAdapter(mProfilePagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


//        final AppCompatActivity act = (AppCompatActivity) getActivity();

//        if (act.getSupportActionBar() != null) {
//            act.setSupportActionBar(toolbar);
//            act.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
//        if((AppCompatActivity) getActivity().getSupportActionBar()!=null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }

        ImageView headerImage=(ImageView) rootView.findViewById(R.id.header_photo);

        Button followButton=(Button) rootView.findViewById(R.id.follow_button);

        TextView editButton=(TextView) rootView.findViewById(R.id.edit_profile);

        TextView dotBeforeFollowers=(TextView) rootView.findViewById(R.id.dot_before_followers);

        TextView displayName=(TextView) rootView.findViewById(R.id.display_name);
        displayName.setText(mUser.getmDisplayName());

        TextView userName=(TextView) rootView.findViewById(R.id.username);
        String mUserName="u/"+mUser.getmUserName();
        userName.setText(mUserName);

        TextView userKarma=(TextView) rootView.findViewById(R.id.karma);
        String mUserKarma=mUser.getmKarma()+" karma";
        userKarma.setText(mUserKarma);

        TextView userDate=(TextView) rootView.findViewById(R.id.date);
        userDate.setText(mUser.getmCakeDay());

        TextView userFollowersCount=(TextView) rootView.findViewById(R.id.followers_count);
        String mUserFollowersCount=mUser.getmFollowersNo()+" followers";
        userFollowersCount.setText(mUserFollowersCount);

        TextView userAbout=(TextView) rootView.findViewById(R.id.user_about);
        userAbout.setText(mUser.getmAbout());

        ImageView backButton=(ImageView) rootView.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AppCompatActivity act = (AppCompatActivity) getActivity();
                try {
                    act.onBackPressed();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });

        return rootView;
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final AppCompatActivity act = (AppCompatActivity) getActivity();


        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                Toast.makeText(getContext(),"bbb",Toast.LENGTH_SHORT).show();
                act.onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
*/
}
