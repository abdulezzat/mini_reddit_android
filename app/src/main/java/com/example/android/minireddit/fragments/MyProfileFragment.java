package com.example.android.minireddit.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.minireddit.Constants;
import com.example.android.minireddit.EditProfileActivity;
import com.example.android.minireddit.R;
import com.example.android.minireddit.adapters.ProfilePagerAdapter;
import com.example.android.minireddit.datastructure.User;
import com.example.android.minireddit.networking.DependentClass;
import com.example.android.minireddit.networking.DownloadImageTask;

/**
 * A simple {@link Fragment} subclass.
 * <p>
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
        DependentClass.getInstance().getUserPublicInfo(getContext(), Constants.visitedUser.getmUserName());
        DependentClass.getInstance().getUserFollowers(getContext(), Constants.visitedUser.getmUserName());
        DependentClass.getInstance().getUserFollowing(getContext(), Constants.visitedUser.getmUserName());
        mUser = Constants.visitedUser;

        mProfilePostsFragment = new ProfilePostsFragment();
        mProfileCommentsFragment = new ProfileCommentsFragment();
        mProfileAboutFragment = new ProfileAboutFragment();

        DependentClass.getInstance().getMyPostsAndComments(getContext(), Constants.visitedUser.getmUserName());

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout);

        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.profile_view_pager);

        ProfilePagerAdapter profilePagerAdapter = new ProfilePagerAdapter(getFragmentManager());

        profilePagerAdapter.addFragment(mProfilePostsFragment, "Posts", mUser);
        profilePagerAdapter.addFragment(mProfileCommentsFragment, "Comments", mUser);
        profilePagerAdapter.addFragment(mProfileAboutFragment, "About", mUser);

        viewPager.setAdapter(profilePagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        // Setting Fragment Views values to be equal to values retrieved form server.
        ImageView headerImage = (ImageView) rootView.findViewById(R.id.header_photo);
        new DownloadImageTask(headerImage).execute(mUser.getmHeaderImage());

        ImageView avatar = (ImageView) rootView.findViewById(R.id.avatar);
        new DownloadImageTask(avatar).execute(mUser.getmProfileImage());

        final Button followButton = (Button) rootView.findViewById(R.id.follow_button);
        final Button unFollowButton = (Button) rootView.findViewById(R.id.unfollow_button);

        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DependentClass.getInstance().followUser(getContext(), Constants.visitedUser.getmUserName());
                followButton.setVisibility(View.GONE);
                unFollowButton.setVisibility(View.VISIBLE);
            }
        });

        unFollowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DependentClass.getInstance().unFollowUser(getContext(), Constants.visitedUser.getmUserName());
                followButton.setVisibility(View.VISIBLE);
                unFollowButton.setVisibility(View.GONE);
            }
        });

        TextView editButton = (TextView) rootView.findViewById(R.id.edit_profile);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(), EditProfileActivity.class);
                startActivity(i);
            }
        });

        TextView dotBeforeFollowers = (TextView) rootView.findViewById(R.id.dot_before_followers);

        TextView displayName = (TextView) rootView.findViewById(R.id.display_name);
        displayName.setText(mUser.getmDisplayName());

        TextView userName = (TextView) rootView.findViewById(R.id.username);
        String mUserName = "u/" + mUser.getmUserName();
        userName.setText(mUserName);

        TextView userKarma = (TextView) rootView.findViewById(R.id.karma);
        String mUserKarma = mUser.getmKarma() + " karma";
        userKarma.setText(mUserKarma);

        TextView userDate = (TextView) rootView.findViewById(R.id.date);
        userDate.setText(mUser.getmCakeDay());

        TextView userFollowersCount = (TextView) rootView.findViewById(R.id.followers_count);
        String mUserFollowersCount = String.valueOf(mUser.getmFollowersNo()) + " followers";
        userFollowersCount.setText(mUserFollowersCount);

        TextView userAbout = (TextView) rootView.findViewById(R.id.user_about);
        userAbout.setText(mUser.getmAbout());

        boolean isCurrentUser = (Constants.visitedUser.getmUserName() == Constants.user.getmUserName());
        boolean followed = Constants.user.isFollowed(Constants.visitedUser.getmUserName());

        // Viewing other User's Profile
        if (isCurrentUser) {
            followButton.setVisibility(View.GONE);
            editButton.setVisibility(View.VISIBLE);
            dotBeforeFollowers.setVisibility(View.VISIBLE);
            userFollowersCount.setVisibility(View.VISIBLE);
        } else {
            editButton.setVisibility(View.GONE);
            dotBeforeFollowers.setVisibility(View.GONE);
            userFollowersCount.setVisibility(View.GONE);
            if (followed) {
                followButton.setVisibility(View.GONE);
                unFollowButton.setVisibility(View.VISIBLE);
            } else {
                followButton.setVisibility(View.VISIBLE);
                unFollowButton.setVisibility(View.GONE);
            }
        }

        // setting backButton Action
        ImageView backButton = (ImageView) rootView.findViewById(R.id.back_button);
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
