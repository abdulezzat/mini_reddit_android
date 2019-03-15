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
    private boolean Deleted;

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
        Deleted=d;
        getFollowersNo();
        getFollowingNo();
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

    /**
     * sends a request to server to get user's followers.
     */
    private void getFollowers() {
        // TODO: send a request to server to get the followers.
    }

    /**
     * @return number of Users who follow the user.
     */
    public int getFollowersNo() {
        getFollowers();
        return followers.size();
    }

    /**
     * sends a request to server to get the users that the user is following.
     */
    private void getFollowing() {
        // TODO: send a request to server to get the following.
    }

    /**
     * @return number of Users that the user is following.
     */
    public int getFollowingNo() {
        getFollowing();
        return following.size();
    }
}
