package com.example.android.minireddit.datastructure;

/**
 * Created by karashily on 11/04/19.
 * <p>
 * A class for holding comments data.
 */

public class Comment {

    /**
     * the Comment ID.
     */
    private int mCommentId;

    /**
     * The Content of the Comment.
     */
    private String mBody;

    /**
     * The Commenting User Username.
     */
    private String mUser;

    /**
     * The title of the Post.
     */
    private String mPostTitle;

    /**
     * The Post Body.
     */
    private String mPostBody;

    /**
     * The Post Community.
     */
    private int mPostCommunity;

    /**
     * The Username of the post author.
     */
    private String mPostAuthor;
    /**
     * Author's photo.
     */
    private String mAuthorPhoto;

    /**
     * Number of downvotes on the comment.
     */
    private int mDownVotes;

    /**
     * Number of Up votes on the comment.
     */
    private int mUpVotes;

    /**
     * the Date of the comment.
     */
    private String mDate;

    /**
     * Number of the Comments on this comment.
     */
    private int mCommentsNum;

    /**
     * Whether this comment is saved by current user or not.
     */
    private boolean mSaved;

    /**
     * Whether this comment is upvoted by the current user or not.
     */
    private boolean mUpVoted;

    /**
     * Whether this comment is downvoted by the current user or not.
     */
    private boolean mDownVoted;


    /**
     * A Constructor to instantiate a new comment object.
     *
     * @param mCommentId     the comment id
     * @param mBody          the comment body
     * @param mUser          the username of the commenter
     * @param mPostTitle     the post title
     * @param mPostBody      the post body
     * @param mPostCommunity the post community
     * @param mPostAuthor    the username of the post author
     * @param mAuthorPhoto   the author photo
     * @param mDownVotes     number of down votes on the comment
     * @param mUpVotes       number of up votes on the comment
     * @param mDate          date of the comment
     * @param mCommentsNum   the number of comments on the comment
     * @param mSaved         whether the comment is saved or not
     * @param mUpVoted       whether the comment is up voted or not
     * @param mDownVoted     whether comment is down voted or not
     */
    public Comment(int mCommentId, String mBody, String mUser, String mPostTitle, String mPostBody, int mPostCommunity, String mPostAuthor, String mAuthorPhoto, int mDownVotes, int mUpVotes, String mDate, int mCommentsNum, boolean mSaved, boolean mUpVoted, boolean mDownVoted) {
        this.mCommentId = mCommentId;
        this.mBody = mBody;
        this.mUser = mUser;
        this.mPostTitle = mPostTitle;
        this.mPostBody = mPostBody;
        this.mPostCommunity = mPostCommunity;
        this.mPostAuthor = mPostAuthor;
        this.mAuthorPhoto = mAuthorPhoto;
        this.mDownVotes = mDownVotes;
        this.mUpVotes = mUpVotes;
        this.mDate = mDate;
        this.mCommentsNum = mCommentsNum;
        this.mSaved = mSaved;
        this.mUpVoted = mUpVoted;
        this.mDownVoted = mDownVoted;
    }

    public int getmCommentId() {
        return mCommentId;
    }

    public void setmCommentId(int mCommentId) {
        this.mCommentId = mCommentId;
    }

    public String getmBody() {
        return mBody;
    }

    public void setmBody(String mBody) {
        this.mBody = mBody;
    }

    public String getmUser() {
        return mUser;
    }

    public void setmUser(String mUser) {
        this.mUser = mUser;
    }

    public String getmPostTitle() {
        return mPostTitle;
    }

    public void setmPostTitle(String mPostTitle) {
        this.mPostTitle = mPostTitle;
    }

    public String getmPostBody() {
        return mPostBody;
    }

    public void setmPostBody(String mPostBody) {
        this.mPostBody = mPostBody;
    }

    public int getmPostCommunity() {
        return mPostCommunity;
    }

    public void setmPostCommunity(int mPostCommunity) {
        this.mPostCommunity = mPostCommunity;
    }

    public String getmPostAuthor() {
        return mPostAuthor;
    }

    public void setmPostAuthor(String mPostAuthor) {
        this.mPostAuthor = mPostAuthor;
    }

    public String getmAuthorPhoto() {
        return mAuthorPhoto;
    }

    public void setmAuthorPhoto(String mAuthorPhoto) {
        this.mAuthorPhoto = mAuthorPhoto;
    }

    public int getmDownVotes() {
        return mDownVotes;
    }

    public void setmDownVotes(int mDownVotes) {
        this.mDownVotes = mDownVotes;
    }

    public int getmUpVotes() {
        return mUpVotes;
    }

    public void setmUpVotes(int mUpVotes) {
        this.mUpVotes = mUpVotes;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public int getmCommentsNum() {
        return mCommentsNum;
    }

    public void setmCommentsNum(int mCommentsNum) {
        this.mCommentsNum = mCommentsNum;
    }

    public boolean ismSaved() {
        return mSaved;
    }

    public void setmSaved(boolean mSaved) {
        this.mSaved = mSaved;
    }

    public boolean ismUpVoted() {
        return mUpVoted;
    }

    public void setmUpVoted(boolean mUpVoted) {
        this.mUpVoted = mUpVoted;
    }

    public boolean ismDownVoted() {
        return mDownVoted;
    }

    public void setmDownVoted(boolean mDownVoted) {
        this.mDownVoted = mDownVoted;
    }
}
