package com.example.android.minireddit.adapters;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.minireddit.R;
import com.example.android.minireddit.datastructure.Comment;
import com.example.android.minireddit.datastructure.Post;
import com.example.android.minireddit.networking.DependentClass;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by karashily on 08/04/19.
 */

public class SavedCommentsAdapter extends ArrayAdapter<Comment> {
    public SavedCommentsAdapter(@NonNull Context context, @NonNull List<Comment> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View ListItemView = convertView;
        if (ListItemView == null) { //for making new List_item if there is no main one to change its data
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.saved_comments_list_item, parent, false);
        }
        final Comment currentComment = getItem(position); // getting the current Post in the ArrayList

        TextView postTitle = ListItemView.findViewById(R.id.post_title);
        postTitle.setText(currentComment.getmPostTitle());

        TextView commentUser = ListItemView.findViewById(R.id.comment_user);
        commentUser.setText(currentComment.getmUser());

        TextView commentCommunity = ListItemView.findViewById(R.id.comment_community);
        commentCommunity.setText(currentComment.getmPostCommunity());

        TextView commentDate = ListItemView.findViewById(R.id.comment_date);
        commentDate.setText(currentComment.getmDate());

        final TextView commentBody = ListItemView.findViewById(R.id.comment_body);
        commentBody.setText(currentComment.getmBody());

        final TextView upvoteCount = ListItemView.findViewById(R.id.upvotes_count);
        upvoteCount.setText(currentComment.getmUpVotes() - currentComment.getmDownVotes());

        final ImageView upvoteButton = ListItemView.findViewById(R.id.upvote);
        final ImageView downvoteButton = ListItemView.findViewById(R.id.downvote);

        upvoteButton.setImageResource(R.drawable.upvote);
        downvoteButton.setImageResource(R.drawable.downvote);

        if (currentComment.ismUpVoted()) {
            upvoteButton.setImageResource(R.drawable.upvote_clr);
        } else if (currentComment.ismDownVoted()) {
            downvoteButton.setImageResource(R.drawable.downvote_clr);
        }

        upvoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentComment.ismUpVoted()) {
                    currentComment.setmUpVoted(false);
                    upvoteButton.setImageResource(R.drawable.upvote);
                    int currentUpvotes = currentComment.getmUpVotes() - currentComment.getmDownVotes() - 1;
                    upvoteCount.setText(String.valueOf(currentUpvotes));
                    // TODO: send request to cancel upvote
                } else {
                    currentComment.setmUpVoted(true);
                    upvoteButton.setImageResource(R.drawable.upvote_clr);
                    int currentUpvotes = currentComment.getmUpVotes() - currentComment.getmDownVotes() + 1;
                    upvoteCount.setText(String.valueOf(currentUpvotes));
                    //TODO: send request to upvote
                }
            }
        });

        downvoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentComment.ismDownVoted()) {
                    currentComment.setmDownVoted(false);
                    downvoteButton.setImageResource(R.drawable.downvote);
                    int currentUpvotes = currentComment.getmUpVotes() - currentComment.getmDownVotes() + 1;
                    upvoteCount.setText(String.valueOf(currentUpvotes));
                    // TODO: send request to cancel downvote
                } else {
                    currentComment.setmDownVoted(true);
                    downvoteButton.setImageResource(R.drawable.downvote_clr);
                    int currentUpvotes = currentComment.getmUpVotes() - currentComment.getmDownVotes() - 1;
                    upvoteCount.setText(String.valueOf(currentUpvotes));
                }
            }
        });

        return ListItemView;
    }
}
