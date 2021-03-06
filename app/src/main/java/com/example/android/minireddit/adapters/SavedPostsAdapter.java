package com.example.android.minireddit.adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.minireddit.R;
import com.example.android.minireddit.datastructure.Post;
import com.example.android.minireddit.networking.DependentClass;
import com.example.android.minireddit.networking.DownloadImageTask;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by karashily on 08/04/19.
 */

public class SavedPostsAdapter extends ArrayAdapter<Post> {
    private Animator currentAnimator;
    private int shortAnimationDuration;
    /**
     * The Expanded image To Animate.
     */
    ImageView expanded_image;
    /**
     * The Container View Of The Animation.
     */
    FrameLayout container;

    /**
     * Instantiates a new Poster adapter.
     *
     * @param context   the context of the fragment
     * @param objects   the objects to be in the list
     * @param expand    the expand view to animate
     * @param container the container view of the animation
     */
    public SavedPostsAdapter(@NonNull Context context, @NonNull List<Post> objects, ImageView expand, FrameLayout container) {
        super(context, 0, objects);
        expanded_image = expand;
        this.container = container;

        shortAnimationDuration = getContext().getResources().getInteger(
                android.R.integer.config_shortAnimTime);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View ListItemView = convertView;
        if (ListItemView == null) { //for making new List_item if there is no main one to change its data
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.profile_posts_list_item, parent, false);
        }
        final Post currentPost = getItem(position); // getting the current Post in the ArrayList

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

        final TextView postVotesCount = (TextView) ListItemView.findViewById(R.id.post_votes_count);
        postVotesCount.setText(String.valueOf(currentPost.getPostLikeCount()));

        TextView postCommentCount = (TextView) ListItemView.findViewById(R.id.post_comments_count);
        postCommentCount.setText(String.valueOf(currentPost.getPostCommentCount()));

        final ImageView postUpVote = (ImageView) ListItemView.findViewById(R.id.post_up_vote);
        final ImageView postDownVote = (ImageView) ListItemView.findViewById(R.id.post_down_vote);
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
        postUpVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DependentClass.getInstance().votePostUp(getContext(), currentPost.getPostId())) {
                    if (currentPost.getVoteStatus() == 0) {
                        postUpVote.setImageResource(R.drawable.upvote_clr);
                        currentPost.setVoteStatus(1);
                        currentPost.setPostLikeCount(currentPost.getPostLikeCount() + 1);
                        //todo send request to upvote
                    } else if (currentPost.getVoteStatus() == 1) {

                        postUpVote.setImageResource(R.drawable.upvote);
                        currentPost.setVoteStatus(0);
                        currentPost.setPostLikeCount(currentPost.getPostLikeCount() - 1);
                        //todo send request to cancel upvote
                    } else {
                        postDownVote.setImageResource(R.drawable.downvote);
                        postUpVote.setImageResource(R.drawable.upvote_clr);
                        currentPost.setVoteStatus(1);
                        currentPost.setPostLikeCount(currentPost.getPostLikeCount() + 2);
                    }
                    postVotesCount.setText(String.valueOf(currentPost.getPostLikeCount()));
                } else {
                    Toast.makeText(getContext(), "Failed To Vote", Toast.LENGTH_SHORT).show();
                }
            }

        });
        postDownVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DependentClass.getInstance().votePostDown(getContext(), currentPost.getPostId())) {
                    if (currentPost.getVoteStatus() == 0) {
                        postDownVote.setImageResource(R.drawable.downvote_clr);
                        currentPost.setVoteStatus(-1);
                        currentPost.setPostLikeCount(currentPost.getPostLikeCount() - 1);
                    } else if (currentPost.getVoteStatus() == 1) {
                        postDownVote.setImageResource(R.drawable.downvote_clr);
                        postUpVote.setImageResource(R.drawable.upvote);
                        currentPost.setVoteStatus(-1);
                        currentPost.setPostLikeCount(currentPost.getPostLikeCount() - 2);
                    } else {
                        postDownVote.setImageResource(R.drawable.downvote);
                        currentPost.setVoteStatus(0);
                        currentPost.setPostLikeCount(currentPost.getPostLikeCount() + 1);
                    }
                    postVotesCount.setText(String.valueOf(currentPost.getPostLikeCount()));
                } else {
                    Toast.makeText(getContext(), "Failed To Vote", Toast.LENGTH_SHORT).show();
                }
            }
        });

        WebView youtubeWebView = (WebView) ListItemView.findViewById(R.id.youtube_web_view);
        if (currentPost.getPostVideoUrl() != null) {
            youtubeWebView.setVisibility(View.VISIBLE);
            String item = "http://www.youtube.com/embed/";
            String url = currentPost.getPostVideoUrl();
            if (url.contains("&")) {
                url = url.substring(url.indexOf("v=") + 2, url.indexOf('&'));
            } else
                url = url.substring(url.indexOf("v=") + 2);
            item += url;

            youtubeWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });

            WebSettings webSettings = youtubeWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setUseWideViewPort(true);

            webSettings.setBuiltInZoomControls(true);
            webSettings.setAppCacheEnabled(true);
            webSettings.setSaveFormData(true);

            youtubeWebView.loadUrl(item);
        } else {
            youtubeWebView.setVisibility(View.GONE);

        }


        ImageView menu = (ImageView) ListItemView.findViewById(R.id.post_menu);


        menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                switch (v.getId()) {
                    case (R.id.post_menu):

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

    private void zoomImageFromThumb(final View thumbView, Drawable imageResId) {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        if (currentAnimator != null) {
            currentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.
        final ImageView expandedImageView = this.expanded_image;
        expandedImageView.setImageDrawable(imageResId);

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        thumbView.getGlobalVisibleRect(startBounds);
        this.container
                .getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f))
                .with(ObjectAnimator.ofFloat(expandedImageView,
                        View.SCALE_Y, startScale, 1f));
        set.setDuration(shortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                currentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                currentAnimator = null;
            }
        });
        set.start();
        currentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentAnimator != null) {
                    currentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel,
                // back to their original values.
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y, startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(shortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        currentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        currentAnimator = null;
                    }
                });
                set.start();
                currentAnimator = set;
            }
        });
    }

}
