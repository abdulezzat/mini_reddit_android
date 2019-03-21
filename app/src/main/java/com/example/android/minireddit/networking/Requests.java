package com.example.android.minireddit.networking;

import com.example.android.minireddit.datastructure.Post;
import com.example.android.minireddit.datastructure.User;

import java.util.ArrayList;

/**
 * Created by Aly on 3/15/2019.
 */

public interface Requests {
     ArrayList<Post>  getTrendingPost();
     boolean votePost(int postId);
     User getUserPublicInfo(String uN);

}
