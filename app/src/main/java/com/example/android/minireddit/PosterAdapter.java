package com.example.android.minireddit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PosterAdapter extends ArrayAdapter<Post> {
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View ListItemView = convertView;
        if(ListItemView==null){//for making new List_item if there is no main one to change its data
            ListItemView= LayoutInflater.from(getContext()).inflate(R.layout.post_list_item,parent,false);
        }
        Post currentPost=getItem(position);// getting the current word in the arraylist


        ImageView postLogo=(ImageView)ListItemView.findViewById(R.id.postLogo);
        postLogo.setImageResource(Integer.valueOf(currentPost.getPostLogoUrl()));

        TextView postUser=(TextView)ListItemView.findViewById(R.id.postUser);
        postUser.setText(currentPost.getPostUser());

        TextView postInfo=(TextView) ListItemView.findViewById(R.id.postInfo);
        postInfo.setText(currentPost.getPostInfo());

        TextView postText=(TextView)ListItemView.findViewById(R.id.postText);
        postText.setText(currentPost.getPostText());

        ImageView postImage=(ImageView)ListItemView.findViewById(R.id.postImage);
        if(currentPost.getPostImageUrl()!=null)
        {
            postImage.setImageResource(Integer.valueOf(currentPost.getPostImageUrl()));

        }
        else
        {
            postImage.setVisibility(View.GONE);
        }

        TextView postlikeCount=(TextView)ListItemView.findViewById(R.id.postLikeCount);
        postlikeCount.setText(String.valueOf(currentPost.getPostLikeCount()));

        TextView postCommentCount=(TextView)ListItemView.findViewById(R.id.postCommentCount);
        postlikeCount.setText(String.valueOf(currentPost.getPostCommentCount()));



        return ListItemView;
    }

    public PosterAdapter(@NonNull Context context, @NonNull List<Post> objects) {
        super(context, 0,objects);

    }


}
