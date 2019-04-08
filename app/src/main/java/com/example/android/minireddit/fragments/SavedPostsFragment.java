package com.example.android.minireddit.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

        ArrayList<Post> posts=new ArrayList<>();

        SavedPostsAdapter savedPostsAdapter=new SavedPostsAdapter(this.getContext(),posts);
        ListView listView=(ListView) rootView.findViewById(R.id.saved_posts_list);
        listView.setAdapter(savedPostsAdapter);

        return rootView;
    }
}
