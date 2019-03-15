package com.example.android.minireddit.datastructure;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by karashily on 15/03/19.
 *
 * User Class To Store Users Info.
 */

public class User {

    /**
     * The Unique User Name for the user.
     */
    private String userName;

    /**
     * The User Display Name That's Shown on his/her Profile.
     */
    private String displayName;

    /**
     * The Path of the User's Profile Image.
     */
    private String profileImage;

    /**
     * The User's Email.
     */
    private String email;

    /**
     * User's Password.
     */
    private String password;

    /**
     * The text shown on User's Profile.
     */
    private String about;

    /**
     * User's karma.
     */
    private int karma;

    /**
     * The Date on which the user joined reddit.
     */
    private Date cakeDay;

    /**
     * Whether the User was deleted or not.
     */
    private boolean deleted;

    /**
     * The List of UserName's of users who follow the User.
     */
    private ArrayList<String> followers;

    /**
     * The Number of Users that follow the User.
     */
    private int followersNo;

    /**
     * The List of UserName's of users who The User is following.
     */
    private ArrayList<String> following;

    /**
     * The Number of Users that the User is following.
     */
    private int followingNo;

    /**
     * An Array of the Posts posted by the User.
     */
    private ArrayList<Post> userPosts;

    /**
     * A constructor that Instantiates a new user with full info.
     *
     * @param uN User's userName
     * @param dN User's displayName
     * @param pI User's profileImage
     * @param eM User's Email
     * @param pW User's Password
     * @param a User's About
     * @param k User's Karma
     * @param cD User's Join Day
     * @param d Whether the User is deleted or not
     */
    public User (String uN, String dN, String pI, String eM, String pW, String a, int k, Date cD, boolean d){
        userName=uN;
        displayName=dN;
        profileImage=pI;
        email=eM;
        password=pW;
        about=a;
        karma=k;
        cakeDay=cD;
        deleted=d;
    }

    /**
     * A method for creating a new User in the database.
     *
     * @param uN User's entered Name.
     * @param pW1 User's entered Password.
     * @param pW2 User's re-entered Password.
     * @param eM User's entered E-mail.
     * @return a new User created object.
     */
    public User signUpUser(String uN, String pW1, String pW2, String eM) {
        return new User(uN, uN, "", eM, pW1, "", 0,new Date() , false);
    }


    public void setUserName(String uN){
        userName=uN;
    }

    public String getUserName() { return userName; }

    public void setDisplayName (String dN) {
        displayName=dN;
    }

    public String getDisplayName() { return displayName; }

    public void setProfileImage(String pI) {
        profileImage=pI;
    }

    public String getProfileImage() { return profileImage; }

    public void setEmail(String eM) {
        email=eM;
    }

    public String getEmail() { return getEmail(); }

    public void setPassword(String pW) {
        password=pW;
    }

    public String getPassword() { return password; }

    public void setAbout (String a) {
        about=a;
    }

    public String getAbout() { return about; }

    /**
     * validates that the entered karma is postive and then sets it.
     * @param k The New Karma
     */
    public void setKarma(int k) {
        karma=(k>=0)?k:0;
    }

    public int getKarma() { return karma; }

    public void setCakeDay(Date cD) {
        cakeDay=cD;
    }

    public Date getCakeDay() { return cakeDay; }

    public void setDeleted(boolean d) {
        deleted=d;
    }

    public boolean isDeleted() { return deleted; }

    /**
     * clears the followers Array and adds them from scratch.
     * @param followersArr the followers to add.
     */
    public void setFollowers(ArrayList<String> followersArr) {
        followers.clear();
        for (int i=0;i<followersArr.size();i++) {
            followers.add(followersArr.get(i));
        }
        updateFollowersNo();
    }

    /**
     * Adds New followers to the existing ones.
     * @param newFollowers the followers to add.
     */
    public void addFollowers(ArrayList<String> newFollowers) {
        for (int i=0;i<newFollowers.size();i++) {
            followers.add(newFollowers.get(i));
        }
        updateFollowersNo();
    }

    public ArrayList<String> getfollowers() { return followers; }

    private void updateFollowersNo() { followersNo=followers.size(); }

    /**
     * Updates the followers Number and returns it.
     *
     * @return Number of the followers.
     */
    public int getFollowersNo() {
        updateFollowersNo();
        return followersNo;
    }

    /**
     * Clears the following list and then add them from scratch.
     * @param followingArr the users to add to the following list.
     */
    public void setFollowing(ArrayList<String> followingArr) {
        following.clear();
        for(int i=0;i<followingArr.size();i++) {
            following.add(followingArr.get(i));
        }
        updateFollowingNo();
    }

    /**
     * adds new Users to the following list.
     * @param followingArr the users to add to the following list.
     */
    public void addFollowing(ArrayList<String> followingArr) {
        for(int i=0;i<followingArr.size();i++) {
            following.add(followingArr.get(i));
        }
        updateFollowingNo();
    }

    public ArrayList<String> getFollowing() { return following; }

    private void updateFollowingNo() { followingNo=following.size(); }

    /**
     * Update the Number of following and returns it.
     * 
     * @return number of Users that the user is following.
     */
    public int getFollowingNo() {
        updateFollowingNo();
        return followingNo;
    }

}
