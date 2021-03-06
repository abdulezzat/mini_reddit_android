package com.example.android.minireddit.networking;

import android.content.Context;
import android.view.MenuItem;

import com.example.android.minireddit.Constants;
import com.example.android.minireddit.datastructure.Comment;
import com.example.android.minireddit.datastructure.Community;
import com.example.android.minireddit.datastructure.Post;
import com.example.android.minireddit.datastructure.User;
import com.example.android.minireddit.libraries.atv.model.TreeNode;

import java.util.ArrayList;

/**
 * Created by Aly on 3/15/2019.
 */
public class DependentClass {
    private final com.example.android.minireddit.networking.Requests mSupplier;
    private static final DependentClass restClient = new DependentClass();

    public Requests getmSupplier() {
        return mSupplier;
    }

    private DependentClass() {
        if (Constants.debug) {
            mSupplier = new MockRestService();
        } else {
            mSupplier = new RestService();
        }

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DependentClass getInstance() {
        return restClient;
    }

    /**
     * Gets list of home posts.
     *
     * @param context the context
     * @return the list of home posts
     */
    public ArrayList<Post> getListOfHomePosts(Context context) {

        return mSupplier.getHomePost(context);
    }

    /**
     * Gets list of trending posts.
     *
     * @param context the context
     * @return the list of trending posts
     */
    public ArrayList<Post> getListOfTrendingPosts(Context context) {

        return mSupplier.getTrendingPost(context);
    }

    /**
     * Gets list of more trending posts.
     *
     * @param index the index
     * @return the list of more trending posts
     */
    public ArrayList<Post> getListOfMoreTrendingPosts(int index) {

        return mSupplier.getMoreTrendingPost(index);
    }

    /**
     * Vote post up boolean.
     *
     * @param context the context
     * @param postId  the post id
     * @return the boolean
     */
    public boolean votePostUp(Context context, int postId) {
        return mSupplier.votePostUp(context, postId);
    }

    /**
     * Vote post down boolean.
     *
     * @param context the context
     * @param postId  the post id
     * @return the boolean
     */
    public boolean votePostDown(Context context, int postId) {
        return mSupplier.votePostDown(context, postId);
    }

    /**
     * Subscribe community boolean.
     *
     * @param context the context
     * @param commId  the comm id
     * @return the boolean
     */
    public boolean subscribeCommunity(Context context, int commId) {
        return mSupplier.subscribeCommunity(context, commId);
    }

    /**
     * Unsubscribe community boolean.
     *
     * @param context the context
     * @param commId  the comm id
     * @return the boolean
     */
    public boolean unsubscribeCommunity(Context context, int commId) {
        return mSupplier.unsubscribeCommunity(context, commId);
    }


    /**
     * Log in boolean.
     *
     * @param context  the context
     * @param email    the email
     * @param password the password
     * @return the boolean
     */
    public boolean logIn(Context context, String email, String password) {
        return mSupplier.logIn(context, email, password);
    }

    /**
     * Sign up boolean.
     *
     * @param context  the context
     * @param email    the email
     * @param username the username
     * @param password the password
     * @return the boolean
     */
    public boolean signUp(Context context, String email, String username, String password) {
        return mSupplier.signUp(context, email, username, password);
    }


    /**
     * Gets user public info.
     *
     * @param context  the context
     * @param username the username
     * @return the user public info
     */
    public User getUserPublicInfo(Context context, String username) {
        return mSupplier.getUserPublicInfo(context, username);
    }

    /**
     * Gets user private info.
     *
     * @param context the context
     * @return the user private info
     */
    public User getUserPrivateInfo(Context context) {
        return mSupplier.getUserPrivateInfo(context);
    }

    public boolean updateUserDisplayName(Context context, String displayName) {
        return mSupplier.updateUserDisplayName(context, displayName);
    }

    public boolean updateUserAbout(Context context, String about) {
        return mSupplier.updateUserAbout(context, about);
    }

    public boolean updateUserProfileImage(Context context, String profileImage) {
        return mSupplier.updateUserProfileImage(context, profileImage);
    }

    public boolean updateUserHeaderImage(Context context, String headerImage) {
        return mSupplier.updateUserHeaderImage(context, headerImage);
    }

    public void getMyPostsAndComments(Context context, String username) {
        mSupplier.getUserPostsAndComments(context, username);
    }

    public void ViewSavedLinks(Context context) {
        mSupplier.ViewSavedLinks(context);
    }

    public boolean followUser(Context context, String username) {
        return mSupplier.followUser(context, username);
    }

    public boolean unFollowUser(Context context, String username) {
        return mSupplier.unFollowUser(context, username);
    }

    public void getUserFollowers(Context context, String username) {
        mSupplier.getUserFollowers(context, username);
    }

    public void getUserFollowing(Context context, String username) {
        mSupplier.getUserFollowing(context, username);
    }

    public void blockUser(Context context, String username) {
        mSupplier.blockUser(context, username);
    }

    public void hidePost(Context context, int postID) {
        mSupplier.hidePost(context, postID);
    }


    public void forgetPassword(final Context context, final String email) {mSupplier.forgetPassword(context,email);}

    public void getListOfCommunities(Context context){
        mSupplier.getListofCommunities(context);
    }

    public void replyOnReply(Context context, TreeNode parent, Comment oldComment,Comment newComment){
       mSupplier.replyOnReply(context,parent,oldComment,newComment);
    }

    public void commentOnPost(Context context, TreeNode root, Post post, Comment newComment) {
        mSupplier.commentOnPost(context,root,post,newComment);

    }
    public void getSinglePost(Context context,int id){
        mSupplier.getSinglePost(context,id);
    }
    public void getComments(Context context,TreeNode node,int link_id,int type){
        mSupplier.getComments(context,link_id,node,type);
    }
    public void writeTextandVideoPosts(Context context, String inputString1, String inputString2, Community community,int type){
        mSupplier.writePostVideoAndText(context,inputString1,inputString2,community,type);
    }
    public void saveLink(Context context, int id){
        mSupplier.saveLink(context,id);
    }
    public void unsaveLink(Context context, int id){
        mSupplier.unSaveLink(context,id);
    }
    public void editComment(Context context,int id,String content){
        mSupplier.editComment(context,id,content);
    }
    public void editPost(Context context,Post post){
        mSupplier.editPost(context,post);
    }

}
