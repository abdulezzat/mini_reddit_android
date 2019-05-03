package com.example.android.minireddit.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.android.minireddit.R;
import com.example.android.minireddit.adapters.PosterAdapter;
import com.example.android.minireddit.adapters.ProfilePostsAdapter;
import com.example.android.minireddit.datastructure.Post;
import com.example.android.minireddit.datastructure.User;

import java.util.ArrayList;

/**
 * Fragment for User Posts shown in the first tab of his/her profile
 * <p>
 * Created by karashily on 15/03/19.
 */

public class ProfilePostsFragment extends Fragment {

    User mUser;

    public ProfilePostsFragment() { // empty constructor required.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile_posts, container, false);

        FrameLayout frameLayout = (FrameLayout) rootView.findViewById(R.id.frame_layout);
        ImageView expand = (ImageView) rootView.findViewById(R.id.animation_image);

        ProfilePostsAdapter adapter = new ProfilePostsAdapter(this.getContext(), mUser.getmPosts(), expand, frameLayout);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.profile_posts_recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return rootView;
    }

    public void setUser(User user) {
        mUser = user;
    }

}
