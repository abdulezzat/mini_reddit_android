package com.example.android.minireddit.adapters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.minireddit.R;
import com.example.android.minireddit.datastructure.Comment;
import com.example.android.minireddit.datastructure.Post;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by karashily on 16/03/19.
 * <p>
 * an adapter for the profile comments fragment that populates user comments with data.
 * </p>
 */

public class ProfileCommentsAdapter extends RecyclerView.Adapter {

    Context mContext;
    List<Comment> mComments;

    //UI Elements
    TextView postTitle;
    TextView postSource;
    TextView commentDate;
    TextView commentVoteCount;
    TextView commentText;

    /**
     * A Constructor that takes the Context and the Array of Objects(Posts) and passes them to the Superclass Constructor
     *
     * @param context Context of the view
     * @param objects The List of objects (Comments)
     */
    public ProfileCommentsAdapter(@NonNull Context context, @NonNull List<Comment> objects) {
        this.mContext=context;
        this.mComments=objects;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View Item = inflater.inflate(R.layout.profile_comments_list_item, parent, false);
        return new ProfilePostsItemHolder(Item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Comment currentComment = mComments.get(position); // getting the current Comment in the ArrayList

        postTitle.setText(currentComment.getmPostTitle());
        postSource.setText(String.valueOf(currentComment.getmPostCommunity()));
        commentDate.setText(currentComment.getmDate());
        commentVoteCount.setText(String.valueOf(currentComment.getmUpVotes()));
        commentText.setText(currentComment.getmBody());
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public class ProfilePostsItemHolder extends RecyclerView.ViewHolder {

        public ProfilePostsItemHolder(View ListItemView) {
            super(ListItemView);
            postTitle = (TextView) ListItemView.findViewById(R.id.post_title);
            postSource = (TextView) ListItemView.findViewById(R.id.post_source);
            commentDate = (TextView) ListItemView.findViewById(R.id.comment_date);
            commentVoteCount = (TextView) ListItemView.findViewById(R.id.comment_vote_count);
            commentText = (TextView) ListItemView.findViewById(R.id.comment_text);
        }
    }
}
