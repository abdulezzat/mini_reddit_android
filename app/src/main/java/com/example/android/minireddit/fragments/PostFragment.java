package com.example.android.minireddit.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.android.minireddit.EndlessScrollListener;
import com.example.android.minireddit.networking.DependentClass;
import com.example.android.minireddit.networking.MockRestService;
import com.example.android.minireddit.networking.RestService;

import com.example.android.minireddit.datastructure.Post;
import com.example.android.minireddit.adapters.PosterAdapter;
import com.example.android.minireddit.R;
import com.example.android.minireddit.Constants;


import java.sql.Struct;
import java.util.ArrayList;

import static com.example.android.minireddit.Constants.homeposts;
import static com.example.android.minireddit.Constants.poster;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment {
    ArrayList<Post> posts;

    private PostType mPostType;

    public PostFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public PostFragment(PostType postType) {
        mPostType = postType;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_post, container, false);
        FrameLayout frameLayout = (FrameLayout) rootView.findViewById(R.id.framelayout);
        ImageView expand = (ImageView) rootView.findViewById(R.id.imageforanimation);
        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe);
        final ListView listView = (ListView) rootView.findViewById(R.id.postsListView);


          if (mPostType == PostType.Popular){
              posts = DependentClass.getInstance().getListOfTrendingPosts(getContext());
              poster = new PosterAdapter(this.getContext(), posts, expand, frameLayout);
              listView.setAdapter(poster);
          }

         else{
              posts=DependentClass.getInstance().getListOfHomePosts(getContext());
              Constants.homeposts=new PosterAdapter(this.getContext(),posts,expand,frameLayout);
              listView.setAdapter(homeposts);

          }




        listView.setAdapter(poster);
        final EndlessScrollListener scrollListener = new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {


                if (mPostType == PostType.Popular) {
                    ArrayList<Post> morePosts = DependentClass.getInstance().getListOfMoreTrendingPosts(totalItemsCount);
                    poster.addAll(morePosts);
                    poster.notifyDataSetChanged();
                }
                else{
                    ArrayList<Post> morePosts = DependentClass.getInstance().getListOfMoreTrendingPosts(totalItemsCount);
                    homeposts.addAll(morePosts);
                    homeposts.notifyDataSetChanged();

                }
                return true;
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
        };
        listView.setOnScrollListener(scrollListener);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (mPostType == PostType.Popular) {
                    posts = DependentClass.getInstance().getListOfTrendingPosts(getContext());
                    poster.clear();
                    poster.addAll(posts);
                    refreshLayout.setRefreshing(false);
                }
                else{
                    posts = DependentClass.getInstance().getListOfTrendingPosts(getContext());
                    homeposts.clear();
                    homeposts.addAll(posts);
                    refreshLayout.setRefreshing(false);

                }


            }
        });

        return rootView;
    }

    public enum PostType {
        Popular,
        Home
    }

}
