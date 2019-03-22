package com.example.android.minireddit.networking;

import android.content.Context;

import com.example.android.minireddit.datastructure.Post;

import java.util.ArrayList;

/**
 * Created by Aly on 3/15/2019.
 */

public interface Requests {
     ArrayList<Post>  getTrendingPost(Context context);
     ArrayList<Post>  getMoreTrendingPost(int index);
     boolean votePostUp(int postId);
     boolean votePostDown(int postId);
     boolean subscribeCommunity(int commId );


}
