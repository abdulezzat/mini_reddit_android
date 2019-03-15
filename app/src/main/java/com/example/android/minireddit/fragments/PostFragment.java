package com.example.android.minireddit.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.minireddit.datastructure.Post;
import com.example.android.minireddit.adapters.PosterAdapter;
import com.example.android.minireddit.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment {


    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_post, container, false);
        final ArrayList<Post> posts=new ArrayList<Post>();
        posts.add(new Post(0,String.valueOf(R.drawable.reddit_icon),"r/alyramzy","Posted by Aly Ramzy. 4h ago","This is Photo Hint","https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg",15,200,false,false,0));
        posts.add(new Post(1,String.valueOf(R.drawable.reddit_icon),"r/alyramzy","Posted by Aly Ramzy. 4h ago","This is Photo Hint","https://images.pexels.com/photos/207962/pexels-photo-207962.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",15,200,false,false,1));
        posts.add(new Post(2,String.valueOf(R.drawable.reddit_icon),"r/alyramzy","Posted by Aly Ramzy. 4h ago","This is Photo Hint","https://images.unsplash.com/photo-1531804055935-76f44d7c3621?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=80",15,200,false,false,1));
        posts.add(new Post(3,String.valueOf(R.drawable.reddit_icon),"r/alyramzy","Posted by Aly Ramzy. 4h ago","This is Text Message only works as a test for posts with no photos",null,15,200,false,false,-1));
        final PosterAdapter adapter= new PosterAdapter(this.getContext(),posts);
        final ListView listView =(ListView) rootView.findViewById (R.id.postsListView);
        listView.setAdapter(adapter);
        return rootView;
    }

}
