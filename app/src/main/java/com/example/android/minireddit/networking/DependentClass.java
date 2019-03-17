package com.example.android.minireddit.networking;

import com.example.android.minireddit.datastructure.Post;

import java.util.ArrayList;

/**
 * Created by Aly on 3/15/2019.
 */

public class DependentClass {
    private final com.example.android.minireddit.networking.Requests mSupplier;

    public DependentClass(com.example.android.minireddit.networking.Requests dataSupplier) {
        mSupplier = dataSupplier;
    }

    public ArrayList<Post> getListOfTrendingPosts() {

      return mSupplier.getTrendingPost();
    }
}
