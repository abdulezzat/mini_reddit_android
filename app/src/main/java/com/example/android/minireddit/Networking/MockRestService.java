package com.example.android.minireddit.Networking;

import com.example.android.minireddit.R;
import com.example.android.minireddit.datastructure.Post;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Aly on 3/15/2019.
 */

public class MockRestService implements Requests {

    @Override
    public ArrayList<Post> getTrendingPost() {
        ArrayList<Post> posts=new ArrayList<>();
        posts.add(new Post(0,String.valueOf(R.drawable.reddit_icon),"r/alyramzy","Posted by Aly Ramzy. 4h ago","This is Photo Hint","https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg",15,200,false,false,0));
        posts.add(new Post(1,String.valueOf(R.drawable.reddit_icon),"r/alyramzy","Posted by Aly Ramzy. 4h ago","This is Photo Hint","https://images.pexels.com/photos/207962/pexels-photo-207962.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",15,200,false,false,1));
        posts.add(new Post(2,String.valueOf(R.drawable.reddit_icon),"r/alyramzy","Posted by Aly Ramzy. 4h ago","This is Photo Hint","https://images.unsplash.com/photo-1531804055935-76f44d7c3621?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=80",15,200,false,false,1));
        posts.add(new Post(3,String.valueOf(R.drawable.reddit_icon),"r/alyramzy","Posted by Aly Ramzy. 4h ago","This is Text Message only works as a test for posts with no photos",null,15,200,false,false,-1));
        return posts;
    }
}
