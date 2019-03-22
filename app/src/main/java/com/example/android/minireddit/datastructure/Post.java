package com.example.android.minireddit.datastructure;

/**
 * The Post Data.
 */
public class Post {

    /**
     * Post ID
     */

    int postId;
    /**
     * The Community id.
     */
    int communityId;

    /**
     * The Community name.
     */

    String communityName;
    /**
     * The Post logo url.
     */
    String postLogoUrl;
    /**
     * The Post user.
     */
    String postUser;
    /**
     * The Post info.
     */
    String postInfo;
    /**
     * The Post Text Content.
     */
    String postText;

    /**
     * The Url of The YouTube embedded Video in The Post.
     */
    String postVideoUrl;
    /**
     * The Post image url.
     */
    String postImageUrl;
    /**
     * The Post like count.
     */
    int postLikeCount;
    /**
     * The Post comment count.
     */
    int postCommentCount;
    /**
     * Saved Post or not.
     */
    boolean saved;
    /**
     * Hidden Post or Not.
     */
    boolean hidden;
    /**
     * The User Has Subscribed The Post Publisher or Not .
     */
    boolean subscribed;
    /**
     * The Vote status.
     */
    int voteStatus;


    /**
     * Instantiates a new Post.
     *
     * @param postId           the id of the post
     * @param communityId      the community id
     * @param communityName    the community name
     * @param postLogoUrl      the post logo url
     * @param postUser         the post user
     * @param postInfo         the post info
     * @param postText         the post text
     * @param postImageUrl     the post image url
     * @param postVideoUrl     the post video url
     * @param postLikeCount    the post like count
     * @param postCommentCount the post comment count
     * @param saved            saved
     * @param hidden           hidden
     * @param subscribed       the user has subscribed the post publisher or not .
     * @param voteStatus       the vote status
     */
    public Post(int postId,int communityId,String communityName,String postLogoUrl, String postUser, String postInfo, String postText, String postImageUrl,String postVideoUrl, int postLikeCount, int postCommentCount, boolean saved, boolean hidden,boolean subscribed, int voteStatus) {
        this.postId=postId;
        this.communityId=communityId;
        this.communityName=communityName;
        this.postLogoUrl = postLogoUrl;
        this.postUser = postUser;
        this.postInfo = postInfo;
        this.postText = postText;
        this.postImageUrl = postImageUrl;
        this.postVideoUrl=postVideoUrl;
        this.postLikeCount = postLikeCount;
        this.postCommentCount = postCommentCount;
        this.saved = saved;
        this.hidden = hidden;
        this.subscribed=subscribed;
        this.voteStatus = voteStatus;
    }

    /**
     * Gets post id.
     *
     * @return the post id
     */
    public int getPostId() {
        return postId;
    }

    /**
     * Sets post id.
     *
     * @param postId the post id
     */
    public void setPostId(int postId) {
        this.postId = postId;
    }

    /**
     * Gets community id.
     *
     * @return the community id
     */
    public int getCommunityId() {
        return communityId;
    }

    /**
     * Sets community id.
     *
     * @param communityId the community id
     */
    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    /**
     * Gets community name.
     *
     * @return the community name
     */
    public String getCommunityName() {
        return communityName;
    }

    /**
     * Sets community name.
     *
     * @param communityName the community name
     */
    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }


    /**
     * Gets post logo url.
     *
     * @return post logo url
     */
    public String getPostLogoUrl() {
        return postLogoUrl;
    }


    /**
     * Sets post logo url.
     *
     * @param postLogoUrl the post logo url
     */
    public void setPostLogoUrl(String postLogoUrl) {
        this.postLogoUrl = postLogoUrl;
    }

    /**
     * Gets post user.
     *
     * @return the post user
     */
    public String getPostUser() {
        return postUser;
    }

    /**
     * Sets post user.
     *
     * @param postUser the post user
     */
    public void setPostUser(String postUser) {
        this.postUser = postUser;
    }

    /**
     * Gets post info.
     *
     * @return the post info
     */
    public String getPostInfo() {
        return postInfo;
    }

    /**
     * Sets post info.
     *
     * @param postInfo the post info
     */
    public void setPostInfo(String postInfo) {
        this.postInfo = postInfo;
    }

    /**
     * Gets post text.
     *
     * @return the post text
     */
    public String getPostText() {
        return postText;
    }

    /**
     * Sets post text.
     *
     * @param postText the post text
     */
    public void setPostText(String postText) {
        this.postText = postText;
    }

    /**
     * Gets post image url.
     *
     * @return the post image url
     */
    public String getPostImageUrl() {
        return postImageUrl;
    }

    /**
     * Sets post image url.
     *
     * @param postImageUrl the post image url
     */
    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }

    /**
     * Gets post like count.
     *
     * @return the post like count
     */
    public int getPostLikeCount() {
        return postLikeCount;
    }

    /**
     * Sets post like count.
     *
     * @param postLikeCount the post like count
     */
    public void setPostLikeCount(int postLikeCount) {
        this.postLikeCount = postLikeCount;
    }

    /**
     * Gets post comment count.
     *
     * @return the post comment count
     */
    public int getPostCommentCount() {
        return postCommentCount;
    }

    /**
     * Sets post comment count.
     *
     * @param postCommentCount the post comment count
     */
    public void setPostCommentCount(int postCommentCount) {
        this.postCommentCount = postCommentCount;
    }

    /**
     * Is saved boolean.
     *
     * @return the boolean
     */
    public boolean isSaved() {
        return saved;
    }

    /**
     * Sets saved.
     *
     * @param saved the saved
     */
    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    /**
     * Is hidden boolean.
     *
     * @return the boolean
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * Sets hidden.
     *
     * @param hidden the hidden
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * Is subscribed boolean.
     *
     * @return the boolean
     */
    public boolean isSubscribed() {
        return subscribed;
    }

    /**
     * Sets subscribed.
     *
     * @param subscribed the subscribed
     */
    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    /**
     * Gets vote status.
     *
     * @return the vote status
     */
    public int getVoteStatus() {
        return voteStatus;
    }

    /**
     * Sets vote status.
     *
     * @param voteStatus the vote status
     */
    public void setVoteStatus(int voteStatus) {
        this.voteStatus = voteStatus;
    }

    /**
     * Gets post video url.
     *
     * @return the post video url
     */
    public String getPostVideoUrl() {
        return postVideoUrl;
    }

    /**
     * Sets post video url.
     *
     * @param postVideoUrl the post video url
     */
    public void setPostVideoUrl(String postVideoUrl) {
        this.postVideoUrl = postVideoUrl;
    }
}
