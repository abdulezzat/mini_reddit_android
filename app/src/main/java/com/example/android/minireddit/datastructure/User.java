package com.example.android.minireddit.datastructure;

import java.util.ArrayList;

/**
 * Created by karashily on 15/03/19.
 * <p>
 * User Class To Store Users Info.
 * </p>
 */

public class User {

    /**
     * The Unique User Name for the user.
     */
    private String mUserName;

    /**
     * The User Display Name That's Shown on his/her Profile.
     */
    private String mDisplayName;

    /**
     * The Path of the User's Profile Image.
     */
    private String mProfileImage;

    /**
     * The Path of the User's Header Image.
     */
    private String mHeaderImage;

    /**
     * The User's Email.
     */
    private String mEmail;

    /**
     * The text shown on User's Profile.
     */
    private String mAbout;

    /**
     * User's mKarma.
     */
    private int mKarma;

    /**
     * The Date on which the user joined reddit.
     */
    private String mCakeDay;

    /**
     * Whether the User was mDeleted or not.
     */
    private boolean mDeleted;

    /**
     * The List of UserName's of users who follow the User.
     */
    private ArrayList<String> mFollowers;

    /**
     * The Number of Users that follow the User.
     */
    private int mFollowersNo;

    /**
     * The List of UserName's of users who The User is mFollowing.
     */
    private ArrayList<String> mFollowing;

    /**
     * The Number of Users that the User is mFollowing.
     */
    private int mFollowingNo;

    /**
     * An Array of the Posts posted by the User.
     */
    private ArrayList<Post> mUserPosts;

    /**
     * A constructor that Instantiates a new user with full info.
     *
     * @param userName     User's mUserName
     * @param displayName  User's mDisplayName
     * @param profileImage User's mProfileImage
     * @param email        User's Email
     * @param about        User's About
     * @param karma        User's Karma
     * @param cakeDay      User's Join Day
     * @param deleted      Whether the User is mDeleted or not
     */
    public User(String userName, String displayName, String profileImage, String email, String about, int karma, String cakeDay, boolean deleted) {
        mUserName = userName;
        mDisplayName = displayName;
        mProfileImage = profileImage;
        mEmail = email;
        mAbout = about;
        mKarma = karma;
        mCakeDay = cakeDay;
        mDeleted = deleted;
        mFollowers=new ArrayList<>();
        mFollowing=new ArrayList<>();
    }

    /**
     * A constructor for constructing a User object from the data returned from the getUserPublicInfo request.
     * @param username  user's unique username.
     * @param karma     user's karma.
     * @param cakeDay   the day on which the user joined reddit.
     * @param about     user's about.
     */
    public User(String username, int karma, String cakeDay, String about) {
        mUserName = username;
        mDisplayName = username;
        mCakeDay = cakeDay;
        mKarma = karma;
        mAbout = about;
        mFollowers=new ArrayList<>();
        mFollowing=new ArrayList<>();
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String username) {
        mUserName = username;
    }

    public String getmDisplayName() {
        return mDisplayName;
    }

    public void setmDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    public String getmProfileImage() {
        return mProfileImage;
    }

    public void setmProfileImage(String profileImage) {
        mProfileImage = profileImage;
    }

    public String getmHeaderImage() {
        return mHeaderImage;
    }

    public void setmHeaderImage(String headerImage) {
        mHeaderImage = headerImage;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String email) {
        mEmail = email;
    }

    public String getmAbout() {
        return mAbout;
    }

    public void setmAbout(String about) {
        mAbout = about;
    }

    public int getmKarma() {
        return mKarma;
    }

    /**
     * validates that the entered mKarma is postive and then sets it.
     *
     * @param karma The New Karma
     */
    public void setmKarma(int karma) {
        mKarma = (karma >= 0) ? karma : 0;
    }

    public String getmCakeDay() {
        return mCakeDay;
    }

    public void setmCakeDay(String cakeDay) {
        mCakeDay = cakeDay;
    }

    public boolean ismDeleted() {
        return mDeleted;
    }

    public void setmDeleted(boolean deleted) {
        mDeleted = deleted;
    }

    /**
     * clears the mFollowers Array and adds them from scratch.
     *
     * @param followersArr the mFollowers to add.
     */
    public void setmFollowers(ArrayList<String> followersArr) {
        mFollowers.clear();
        mFollowers.addAll(followersArr);
        updatemFollowersNo();
    }

    /**
     * Adds New mFollowers to the existing ones.
     *
     * @param newFollowers the mFollowers to add.
     */
    public void addFollowers(ArrayList<String> newFollowers) {
        mFollowers.addAll(newFollowers);
        updatemFollowersNo();
    }

    public ArrayList<String> getmFollowers() {
        return mFollowers;
    }

    /**
     * updates the current followers no.
     */
    private void updatemFollowersNo() {
        if (mFollowers != null || mFollowers.size() != 0) { // first check if the list is empty
            mFollowersNo = mFollowers.size();
        } else {
            mFollowersNo = 0; // if empty just return Zero
        }
    }

    /**
     * Updates the mFollowers Number and returns it.
     *
     * @return Number of the mFollowers.
     */
    public int getmFollowersNo() {
        updatemFollowersNo();
        return mFollowersNo;
    }


    public ArrayList<String> getmFollowing() {
        return mFollowing;
    }

    /**
     * Clears the mFollowing list and then add them from scratch.
     *
     * @param followingArr the users to add to the mFollowing list.
     */
    public void setmFollowing(ArrayList<String> followingArr) {
        mFollowing.clear();
        mFollowing.addAll(followingArr);
        updatemFollowingNo();
    }

    /**
     * adds new Users to the mFollowing list.
     *
     * @param newFollowing the users to add to the mFollowing list.
     */
    public void addFollowing(ArrayList<String> newFollowing) {
        mFollowing.addAll(newFollowing);
        updatemFollowingNo();
    }

    /**
     * updates the current mfollowing no.
     */
    private void updatemFollowingNo() {
        if (mFollowing != null || mFollowing.size() != 0) { // first check if the list is empty.
            mFollowingNo = mFollowing.size();
        } else {
            mFollowingNo = 0; // if empty just return 0.
        }
    }

    /**
     * Update the Number of mFollowing and returns it.
     *
     * @return number of Users that the user is mFollowing.
     */
    public int getmFollowingNo() {
        updatemFollowingNo();
        return mFollowingNo;
    }

}
