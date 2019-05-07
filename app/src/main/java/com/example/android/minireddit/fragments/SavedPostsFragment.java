package com.example.android.minireddit.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.android.minireddit.Constants;
import com.example.android.minireddit.R;
import com.example.android.minireddit.adapters.SavedPostsAdapter;
import com.example.android.minireddit.datastructure.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karashily on 08/04/19.
 */

public class SavedPostsFragment extends Fragment {
    public SavedPostsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_saved_posts, container, false);

        FrameLayout frameLayout = (FrameLayout) rootView.findViewById(R.id.frame_layout);
        ImageView expand = (ImageView) rootView.findViewById(R.id.animation_image);

        SavedPostsAdapter savedPostsAdapter = new SavedPostsAdapter(this.getContext(), Constants.savedPosts, expand, frameLayout);
        ListView listView = (ListView) rootView.findViewById(R.id.saved_posts_list);
        listView.setAdapter(savedPostsAdapter);

        return rootView;
    }
}
