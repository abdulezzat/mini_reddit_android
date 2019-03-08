package com.example.android.minireddit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
        posts.add(new Post(String.valueOf(R.drawable.reddit_icon),"r/alyramzy","Posted by Aly Ramzy. 4h ago","This is Photo Hint",String.valueOf(R.drawable.iamadreamer),15,200));



        posts.add(new Post(String.valueOf(R.drawable.reddit_icon),"r/alyramzy","Posted by Aly Ramzy. 4h ago","This is Photo Hint",null,15,200));
        posts.add(new Post(String.valueOf(R.drawable.reddit_icon),"r/alyramzy","Posted by Aly Ramzy. 4h ago","This is Photo Hint",String.valueOf(R.drawable.iamadreamer),15,200));
        posts.add(new Post(String.valueOf(R.drawable.reddit_icon),"r/alyramzy","Posted by Aly Ramzy. 4h ago","This is Text Message only works as a test for posts with no photos",null,15,200));
        final  PosterAdapter adapter= new PosterAdapter(this.getContext(),posts);
        final ListView listView =(ListView) rootView.findViewById (R.id.postsListView);
        listView.setAdapter(adapter);
        return rootView;
    }

}
