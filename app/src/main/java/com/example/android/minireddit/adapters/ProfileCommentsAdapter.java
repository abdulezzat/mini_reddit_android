package com.example.android.minireddit.adapters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.minireddit.R;
import com.example.android.minireddit.datastructure.Post;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by karashily on 16/03/19.
 * <p>
 *     an adapter for the profile comments fragment that populates user comments with data.
 * </p>
 */

public class ProfileCommentsAdapter extends ArrayAdapter<Post> {

    /**
     * A Constructor that takes the Context and the Array of Objects(Posts) and passes them to the Superclass Constructor
     * @param context Context of the view
     * @param objects The List of objects (Comments)
     */
    public ProfileCommentsAdapter(@NonNull Context context, @NonNull List<Post> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View ListItemView = convertView;
        if (ListItemView == null) { //for making new List_item if there is no main one to change its data
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.profile_comments_list_item, parent, false);
        }
        //Comment currentComment = getItem(position); // getting the current Comment in the ArrayList

        TextView postTitle = (TextView) ListItemView.findViewById(R.id.post_title);
        //TODO: Set Title from currentComment.

        TextView postSource = (TextView) ListItemView.findViewById(R.id.post_source);
        //TODO: Set Source from currentComment.

        TextView commentDate = (TextView) ListItemView.findViewById(R.id.comment_date);
        //TODO: Set Date from currentComment.

        TextView commentVoteCount = (TextView) ListItemView.findViewById(R.id.comment_vote_count);
        //commentVotesCount.setText(String.valueOf(currentComment.getCommentLikeCount()));

        TextView commentText = (TextView) ListItemView.findViewById(R.id.comment_text);
        //TODO: Set Comment Text from currentComment.

        return ListItemView;
    }

}
