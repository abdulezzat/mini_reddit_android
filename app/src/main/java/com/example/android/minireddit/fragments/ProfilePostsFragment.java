package com.example.android.minireddit.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.minireddit.R;
import com.example.android.minireddit.adapters.PosterAdapter;
import com.example.android.minireddit.adapters.ProfilePostsAdapter;
import com.example.android.minireddit.datastructure.Post;
import com.example.android.minireddit.datastructure.User;

import java.util.ArrayList;

/**
 * Created by karashily on 15/03/19.
 */

public class ProfilePostsFragment extends Fragment {

    User mUser;

    public ProfilePostsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile_posts, container, false);

        ArrayList<Post> posts = new ArrayList<Post>();

        /*
        posts.add(new Post(0, String.valueOf(R.drawable.reddit_icon), "u/karashily", "u/karashily • 4h ago", "Post Title", "https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg", null, 15, 200, false, false, 0));
        posts.add(new Post(0, String.valueOf(R.drawable.reddit_icon), "u/karashily", "u/karashily • 4h ago", "An Example of upVotedPost", "https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg", null, 15, 200, false, false, 1));
        posts.add(new Post(0, String.valueOf(R.drawable.reddit_icon), "u/karashily", "u/karashily • 4h ago", "downVoted Post", "https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg", null, 15, 200, false, false, -1));
        */
        ProfilePostsAdapter adapter = new ProfilePostsAdapter(this.getContext(), posts);
        ListView listView = (ListView) rootView.findViewById(R.id.profile_posts_listview);
        listView.setAdapter(adapter);

        return rootView;
    }

    public void setUser(User user)
    {
        mUser=user;
    }

}
