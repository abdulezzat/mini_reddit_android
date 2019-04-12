package com.example.android.minireddit.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.minireddit.Constants;
import com.example.android.minireddit.R;
import com.example.android.minireddit.adapters.SavedCommentsAdapter;
import com.example.android.minireddit.datastructure.Comment;

import java.util.ArrayList;

/**
 * Created by karashily on 08/04/19.
 */

public class SavedCommentsFragment extends Fragment {
    public SavedCommentsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_saved_posts, container, false);

        ArrayList<Comment> comments = new ArrayList<>();

        Constants.savedComments = new SavedCommentsAdapter(this.getContext(), comments);
        ListView listView = (ListView) rootView.findViewById(R.id.saved_posts_list);
        listView.setAdapter(Constants.savedComments);

        return rootView;
    }
}
