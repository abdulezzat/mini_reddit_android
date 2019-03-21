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

import com.example.android.minireddit.networking.DownloadImageTask;
import com.example.android.minireddit.R;
import com.example.android.minireddit.datastructure.Post;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by karashily on 15/03/19.
 *
 * An Adapter For the ProfilePostsListView
 */

public class ProfilePostsAdapter extends ArrayAdapter<Post> {
    /**
     * A Constructor that takes the Context and the Array of Objects(Posts) and passes them to the Superclass Constructor
     * @param context Context of the view
     * @param objects The List of objects (Posts)
     */
    public ProfilePostsAdapter(@NonNull Context context, @NonNull List<Post> objects) {
        super(context, 0, objects);
    }

    /**
     * A method that takes a view and recycles it and gets a new view
     * @param position the position of the current view
     * @param convertView the view to recycle
     * @param parent needed to inflate new views
     * @return the recycled view
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View ListItemView = convertView;
        if (ListItemView == null) { //for making new List_item if there is no main one to change its data
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.profile_posts_list_item, parent, false);
        }
        Post currentPost = getItem(position);// getting the current Post in the ArrayList

        TextView postInfo = (TextView) ListItemView.findViewById(R.id.post_info);
        postInfo.setText(currentPost.getPostInfo());

        TextView postText = (TextView) ListItemView.findViewById(R.id.post_title);
        postText.setText(currentPost.getPostText());

        ImageView postImage = (ImageView) ListItemView.findViewById(R.id.post_image);
        if (currentPost.getPostImageUrl() != null) {
            boolean connected = isNetworkAvailable();
            if (connected) {
                new DownloadImageTask(postImage).execute(currentPost.getPostImageUrl());
            } else {
                postImage.setImageResource(R.drawable.internet_error);
            }

        } else {
            postImage.setVisibility(View.GONE);
        }

        TextView postVotesCount = (TextView) ListItemView.findViewById(R.id.post_votes_count);
        postVotesCount.setText(String.valueOf(currentPost.getPostLikeCount()));

        TextView postCommentCount = (TextView) ListItemView.findViewById(R.id.post_comments_count);
        postCommentCount.setText(String.valueOf(currentPost.getPostCommentCount()));

        ImageView postUpVote = (ImageView) ListItemView.findViewById(R.id.post_up_vote);
        ImageView postDownVote = (ImageView) ListItemView.findViewById(R.id.post_down_vote);
        switch (currentPost.getVoteStatus()) {

            case 1:
                postUpVote.setImageResource(R.drawable.upvote_clr);
                postDownVote.setImageResource(R.drawable.downvote);
                break;
            case -1:
                postUpVote.setImageResource(R.drawable.upvote);
                postDownVote.setImageResource(R.drawable.downvote_clr);
                break;
            default:
                postUpVote.setImageResource(R.drawable.upvote);
                postDownVote.setImageResource(R.drawable.downvote);
                break;

        }

        ImageView menu = (ImageView) ListItemView.findViewById(R.id.post_menu);


        menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                switch (v.getId()) {
                    case (R.id.postMenu):

                        PopupMenu popup = new PopupMenu(getContext(), v);
                        popup.getMenuInflater().inflate(R.menu.post_menu, popup.getMenu());
                        setForceShowIcon(popup);

                        popup.show();
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case (R.id.save):
                                        //TODO: add Post to Saved Posts.
                                        Toast.makeText(getContext(), "Post Saved", Toast.LENGTH_LONG).show();
                                        break;

                                    case (R.id.hidePost):
                                        //TODO: Hide the Post.
                                        Toast.makeText(getContext(), "post Hidden", Toast.LENGTH_LONG).show();
                                        break;

                                    case (R.id.blockUser):
                                        //TODO: add The User who wrote the post to the BlockedUsers List.
                                        Toast.makeText(getContext(), "post Hidden", Toast.LENGTH_LONG).show();
                                        break;

                                    default:
                                        break;
                                }
                                return true;
                            }
                        });
                        break;

                    default:
                        break;
                }
            }
        });

        return ListItemView;
    }


    protected boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public static void setForceShowIcon(PopupMenu popupMenu) {
        try {
            Field[] fields = popupMenu.getClass().getDeclaredFields();
            for (Field field : fields) {
                if ("mPopup".equals(field.getName())) {
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popupMenu);
                    Class<?> classPopupHelper = Class.forName(menuPopupHelper
                            .getClass().getName());
                    Method setForceIcons = classPopupHelper.getMethod(
                            "setForceShowIcon", boolean.class);
                    setForceIcons.invoke(menuPopupHelper, true);
                    break;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
