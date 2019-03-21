package com.example.android.minireddit.networking;

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

    public ArrayList<Post> getListOfTrendingPosts() {

        return mSupplier.getTrendingPost();
    }


    public boolean logIn (String email,String password){ return  mSupplier.logIn(email,password);}

    public User getUser(String uN) {
        return mSupplier.getUserPublicInfo(uN);
    }

    public boolean votePost(int postId) {
        return mSupplier.votePost(postId);
    }
}
