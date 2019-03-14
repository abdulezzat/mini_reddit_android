package com.example.android.minireddit.datastructure;

public class Post {



    String postLogoUrl;
    String postUser;
    String postInfo;
    String postText;
    String postImageUrl;
    int postLikeCount;
    int postCommentCount;
    boolean saved;
    boolean hidden;
    int voteStatus;

    public Post(String postLogoUrl, String postUser, String postInfo, String postText, String postImageUrl, int postLikeCount, int postCommentCount, boolean saved, boolean hidden, int voteStatus) {
        this.postLogoUrl = postLogoUrl;
        this.postUser = postUser;
        this.postInfo = postInfo;
        this.postText = postText;
        this.postImageUrl = postImageUrl;
        this.postLikeCount = postLikeCount;
        this.postCommentCount = postCommentCount;
        this.saved = saved;
        this.hidden = hidden;
        this.voteStatus = voteStatus;
    }


    public String getPostLogoUrl() {
        return postLogoUrl;
    }

    public void setPostLogoUrl(String postLogoUrl) {
        this.postLogoUrl = postLogoUrl;
    }

    public String getPostUser() {
        return postUser;
    }

    public void setPostUser(String postUser) {
        this.postUser = postUser;
    }

    public String getPostInfo() {
        return postInfo;
    }

    public void setPostInfo(String postInfo) {
        this.postInfo = postInfo;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }

    public int getPostLikeCount() {
        return postLikeCount;
    }

    public void setPostLikeCount(int postLikeCount) {
        this.postLikeCount = postLikeCount;
    }

    public int getPostCommentCount() {
        return postCommentCount;
    }

    public void setPostCommentCount(int postCommentCount) {
        this.postCommentCount = postCommentCount;
    }
    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }
    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
    public int getVoteStatus() {
        return voteStatus;
    }

    public void setVoteStatus(int voteStatus) {
        this.voteStatus = voteStatus;
    }
}
