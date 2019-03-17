package com.example.android.minireddit.Networking;

import com.example.android.minireddit.datastructure.Post;

import java.util.ArrayList;

/**
 * Created by Aly on 3/15/2019.
 */

public class DependentClass {
    private final Requests mSupplier;

    public DependentClass(Requests dataSupplier) {
        mSupplier = dataSupplier;
    }

    public ArrayList<Post> getListOfTrendingPosts() {

      return mSupplier.getTrendingPost();
    }
}
