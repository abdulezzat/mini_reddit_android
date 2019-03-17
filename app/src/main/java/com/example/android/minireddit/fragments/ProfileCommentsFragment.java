package com.example.android.minireddit.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.minireddit.R;
import com.example.android.minireddit.adapters.ProfileCommentsAdapter;

import org.w3c.dom.Comment;

import java.util.ArrayList;

/**
 * Created by karashily on 16/03/19.
 */

public class ProfileCommentsFragment extends Fragment {

    public ProfileCommentsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile_comments, container, false);

        //ArrayList<Comment> comments = new ArrayList<Comment>();

        //comments.add( );

        //ProfileCommentsAdapter adapter = new ProfileCommentsAdapter(this.getContext(), comments);
        ListView listView = (ListView) rootView.findViewById(R.id.ProfileCommentsListView);
        //listView.setAdapter(adapter);

        return rootView;
    }
}
