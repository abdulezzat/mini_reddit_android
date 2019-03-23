package com.example.android.minireddit.networking;

import android.content.Context;

import com.example.android.minireddit.Constants;
import com.example.android.minireddit.datastructure.Post;
import com.example.android.minireddit.datastructure.User;

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

    public ArrayList<Post> getListOfHomePosts(Context context) {

        return mSupplier.getHomePost(context);
    }

    public ArrayList<Post> getListOfTrendingPosts(Context context) {

        return mSupplier.getTrendingPost(context);
    }

    public ArrayList<Post> getListOfMoreTrendingPosts(int index) {

        return mSupplier.getMoreTrendingPost(index);
    }

    public boolean votePostUp(Context context, int postId) {
        return mSupplier.votePostUp(context, postId);
    }

    public boolean votePostDown(Context context, int postId) {
        return mSupplier.votePostDown(context, postId);
    }

    public boolean subscribeCommunity(Context context, int commId) {
        return mSupplier.subscribeCommunity(context, commId);
    }

    public boolean unsubscribeCommunity(Context context, int commId) {
        return mSupplier.unsubscribeCommunity(context, commId);
    }


    public boolean logIn(String email, String password) {
        return mSupplier.logIn(email, password);
    }

    public User getUserPublicInfo(Context context, String username) {
        return mSupplier.getUserPublicInfo(context, username);
    }

    public User getUserPrivateInfo(Context context) {
        return mSupplier.getUserPrivateInfo(context);
    }


}
