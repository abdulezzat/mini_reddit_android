package com.example.android.minireddit.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.minireddit.R;
import com.example.android.minireddit.adapters.ProfilePagerAdapter;
import com.example.android.minireddit.datastructure.User;
import com.example.android.minireddit.networking.DependentClass;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends Fragment {

    ProfilePostsFragment profilePostsFragment;
    ProfileCommentsFragment profileCommentsFragment;
    ProfileAboutFragment profileAboutFragment;
    User mUser;

    public MyProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my_profile, container, false);;

        mUser = DependentClass.getInstance().getUser("karashily");
        profilePostsFragment=new ProfilePostsFragment();
        profileCommentsFragment=new ProfileCommentsFragment();
        profileAboutFragment=new ProfileAboutFragment();


        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        TabLayout mTabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout);

        ViewPager mViewPager = (ViewPager) rootView.findViewById(R.id.profile_view_pager);

        ProfilePagerAdapter mProfilePagerAdapter = new ProfilePagerAdapter(getFragmentManager());

        mProfilePagerAdapter.addFragment(profilePostsFragment,"Posts", mUser);
        mProfilePagerAdapter.addFragment(profileCommentsFragment,"Comments", mUser);
        mProfilePagerAdapter.addFragment(profileAboutFragment,"About", mUser);

        mViewPager.setAdapter(mProfilePagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


        final AppCompatActivity act = (AppCompatActivity) getActivity();
        if (act.getSupportActionBar() != null) {
            act.setSupportActionBar(toolbar);
            act.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
//        if((AppCompatActivity) getActivity().getSupportActionBar()!=null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }


        ImageView headerImage=(ImageView) rootView.findViewById(R.id.header_photo);
        TextView displayName=(TextView) rootView.findViewById(R.id.display_name);
        Button followButton=(Button) rootView.findViewById(R.id.follow_button);
        TextView editButton=(TextView) rootView.findViewById(R.id.edit_profile);
        TextView userName=(TextView) rootView.findViewById(R.id.username);
        TextView userKarma=(TextView) rootView.findViewById(R.id.karma);
        TextView userDate=(TextView) rootView.findViewById(R.id.date);
        TextView userFollowersCount=(TextView) rootView.findViewById(R.id.followers_count);
        TextView dotBeforeFollowers=(TextView) rootView.findViewById(R.id.dot_before_followers);
        TextView userAbout=(TextView) rootView.findViewById(R.id.user_about);

        displayName.setText(mUser.getDisplayName());

        String mUserName="u/"+mUser.getUserName();
        userName.setText(mUserName);

        String mUserKarma=mUser.getKarma()+" karma";
        userKarma.setText(mUserKarma);

        userDate.setText(mUser.getRedditAge());

        String mUserFollowersCount=mUser.getFollowersNo()+" followers";
        userFollowersCount.setText(mUserFollowersCount);

        userAbout.setText(mUser.getAbout());
        return rootView;
    }

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

}
