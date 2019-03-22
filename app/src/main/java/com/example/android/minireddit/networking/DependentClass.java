package com.example.android.minireddit.networking;

import android.content.Context;

import com.example.android.minireddit.Constants;
import com.example.android.minireddit.datastructure.Post;

import java.util.ArrayList;

/**
 * Created by Aly on 3/15/2019.
 */

public class DependentClass {
    private final com.example.android.minireddit.networking.Requests mSupplier;
    private static final DependentClass restClient = new DependentClass();


    private DependentClass() {
        if (Constants.debug) {
            mSupplier = new MockRestService();
        } else {
            mSupplier = new RestService();
        }

    }

    public static DependentClass getInstance() {
        return restClient;
    }

    public ArrayList<Post> getListOfTrendingPosts(Context context) {

        return mSupplier.getTrendingPost(context);
    }
    public ArrayList<Post> getListOfMoreTrendingPosts(int index) {

        return mSupplier.getMoreTrendingPost( index);
    }

    public boolean votePostUp(int postId) {
        return mSupplier.votePostUp(postId);
    }
    public boolean votePostDown(int postId) {
        return mSupplier.votePostDown(postId);
    }
    public boolean subscribeCommunity(int commId){
        return mSupplier.subscribeCommunity(commId);

    }
}
