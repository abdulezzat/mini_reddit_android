package com.example.android.minireddit.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.minireddit.R;
import com.example.android.minireddit.datastructure.Post;

import java.util.List;

/**
 * Created by karashily on 08/04/19.
 */

public class SavedPostsAdapter extends ArrayAdapter {
    public SavedPostsAdapter(@NonNull Context context, @NonNull List<Post> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View ListItemView = convertView;
        if (ListItemView == null) { //for making new List_item if there is no main one to change its data
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.profile_posts_list_item, parent, false);
        }
        //Post currentPost = getItem(position); // getting the current Post in the ArrayList

        //TODO: set the data inside each item

        return ListItemView;
    }
}