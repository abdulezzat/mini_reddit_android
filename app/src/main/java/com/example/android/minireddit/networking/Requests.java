package com.example.android.minireddit.networking;

import android.content.Context;

import com.example.android.minireddit.datastructure.Post;
import com.example.android.minireddit.datastructure.User;

import java.util.ArrayList;

/**
 * Created by Aly on 3/15/2019.
 */
public interface Requests {
    /**
     * Gets trending post.
     *
     * @param context the context
     * @return the trending post
     */
    ArrayList<Post> getTrendingPost(Context context);

    /**
     * Gets home post.
     *
     * @param context the context
     * @return the home post
     */
    ArrayList<Post> getHomePost(Context context);

    /**
     * Gets more trending post.
     *
     * @param index the index
     * @return the more trending post
     */
    ArrayList<Post> getMoreTrendingPost(int index);

    /**
     * Vote post up boolean.
     *
     * @param context the context
     * @param postId  the post id
     * @return the boolean
     */
    boolean votePostUp(Context context, int postId);

    /**
     * Vote post down boolean.
     *
     * @param context the context
     * @param postId  the post id
     * @return the boolean
     */
    boolean votePostDown(Context context, int postId);

    /**
     * Subscribe community boolean.
     *
     * @param context the context
     * @param commId  the comm id
     * @return the boolean
     */
    boolean subscribeCommunity(Context context, int commId);

    /**
     * Unsubscribe community boolean.
     *
     * @param context the context
     * @param commId  the comm id
     * @return the boolean
     */
    boolean unsubscribeCommunity(Context context, int commId);

    /**
     * Log in boolean.
     *
     * @param context  the context
     * @param username the username
     * @param password the password
     * @return the boolean
     */
    boolean logIn(Context context, String username, String password);

    /**
     * Sign up boolean.
     *
     * @param context  the context
     * @param email    the email
     * @param username the username
     * @param password the password
     * @return the boolean
     */
    boolean signUp(Context context, String email, String username, String password);

    /**
     * Gets user public info.
     *
     * @param context  the context
     * @param username the username
     * @return the user public info
     */
    User getUserPublicInfo(Context context, String username);

    String getUsername(Context context);

    /**
     * Gets user private info.
     *
     * @param context the context
     * @return the user private info
     */
    User getUserPrivateInfo(Context context);

    boolean updateUserDisplayName(Context context, String displayName);

    boolean updateUserAbout(Context context, String about);

    boolean updateUserProfileImage(Context context, String profileImage);

    void getMyPostsAndComments(Context context,String username);

    void ViewSavedLinks(Context context);

    boolean followUser(Context context, String username);

    boolean unFollowUser(Context context, String username);

    void getUserFollowers(Context context, String username);

    void getUserFollowing(Context context, String username);
}
