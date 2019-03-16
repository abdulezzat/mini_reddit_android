package com.example.android.minireddit.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.android.minireddit.Networking.DependentClass;
import com.example.android.minireddit.Networking.MockRestService;
import com.example.android.minireddit.Networking.RestService;
import com.example.android.minireddit.datastructure.Post;
import com.example.android.minireddit.adapters.PosterAdapter;
import com.example.android.minireddit.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment {
     ArrayList<Post> posts;


    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_post, container, false);
        FrameLayout frameLayout=(FrameLayout)rootView.findViewById(R.id.framelayout);
        ImageView expand=(ImageView) rootView.findViewById(R.id.imageforanimation);
        final SwipeRefreshLayout refreshLayout=(SwipeRefreshLayout)rootView.findViewById(R.id.swipe);

        final boolean debug=true;
        DependentClass restClient = null;
        if (debug) {
            restClient = new DependentClass(new MockRestService());
        } else {
            restClient = new DependentClass(new RestService());
        }
        posts=restClient.getListOfTrendingPosts();

        final PosterAdapter adapter= new PosterAdapter(this.getContext(),posts,expand,frameLayout);
        final ListView listView =(ListView) rootView.findViewById (R.id.postsListView);
        listView.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                DependentClass restClient = null;
                if (debug) {
                    restClient = new DependentClass(new MockRestService());
                } else {
                    restClient = new DependentClass(new RestService());
                }
                posts=restClient.getListOfTrendingPosts();
                adapter.clear();
                adapter.addAll(posts);
                refreshLayout.setRefreshing(false);

            }
        });

        return rootView;
    }



}
