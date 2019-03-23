package com.example.android.minireddit.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.minireddit.R;
import com.example.android.minireddit.datastructure.User;

/**
 * Fragment for User About shown in the third tab of his/her profile
 * <br>
 * Created by karashily on 17/03/19.
 */

public class ProfileAboutFragment extends Fragment {

    User mUser;

    /**
     * default constructor.
     */
    public ProfileAboutFragment() { // empty constructor required.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile_about, container, false);


        TextView about = (TextView) rootView.findViewById(R.id.user_about);
        TextView karma = (TextView) rootView.findViewById(R.id.karma);
        TextView redditAge = (TextView) rootView.findViewById(R.id.reddit_age);

        karma.setText(String.valueOf(mUser.getmKarma()));
        about.setText(mUser.getmAbout());
        redditAge.setText(mUser.getmCakeDay());

        return rootView;
    }

    public void setUser(User user) {
        mUser=user;
    }

}
