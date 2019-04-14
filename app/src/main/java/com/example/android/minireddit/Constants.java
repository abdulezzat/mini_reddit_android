package com.example.android.minireddit;

import com.example.android.minireddit.abs.LogInSignUpSuccessful;
import com.example.android.minireddit.abs.UpdateProfileInfo;
import com.example.android.minireddit.adapters.PosterAdapter;
import com.example.android.minireddit.adapters.SavedCommentsAdapter;
import com.example.android.minireddit.adapters.SavedPostsAdapter;
import com.example.android.minireddit.datastructure.Comment;
import com.example.android.minireddit.datastructure.Post;
import com.example.android.minireddit.datastructure.User;

import java.util.ArrayList;

//
public class Constants {
    public static String mToken = "";

    public static final boolean debug = true;
    public static User user = null;
    public static User visitedUser = null;
    // public static User user = new User("admin","admin",null,"admin@gamil.com",null,200,null,false);
    public static final String TRENDIN_POST = "";
    public static PosterAdapter poster = null;
    public static PosterAdapter homeposts = null;


    //Saved Links
    public static ArrayList<Post> savedPosts = new ArrayList<>();
    public static ArrayList<Comment> savedComments = new ArrayList<>();


    //URL
    final static public String LOG_IN_URL = "http://35.204.169.121/api/unauth/signIn?";
    final static public String SIGN_UP_URL = "http://35.204.169.121/api/unauth/signUp?";
    final static public String TRENDING_POSTS = "http://35.204.169.121/api/unauth/ViewPosts?page_type=0";
    final static public String HOME_POSTS = "http://35.204.169.121/api/unauth/ViewPosts?page_type=1&token=";
    final static public String VOTE_POST_UP = "http://35.204.169.121/api/auth/upvoteLink?";
    final static public String VOTE_LINK_DOWN = "http://35.204.169.121/api/auth/downvoteLink?";
    final static public String SUBSCRIBE_COMMUNITY = "http://35.204.169.121/api/auth/subscribeCommunity?";
    final static public String UNSUBSCRIBE_COMMUNITY = "http://35.204.169.121/api/auth/unSubscribeCommunity?token=";
    final static public String HIDE_POST = "http://35.204.169.121/api/auth/hidePost?";
    final static public String BLOCK_USER = "http:/35.204.169.121/api/auth/blockUser?";
    final static public String GET_USER_PUBLIC_INFO = "http:/35.204.169.121/api/unauth/viewPublicUserInfo";
    final static public String GET_USER_PRIVATE_INFO = "http:/35.204.169.121/api/auth/viewPrivateUserInfo";
    final static public String GET_USERNAME = "http://35.204.169.121/api/auth/getUsername";
    final static public String UPDATE_USER_DISPLAY_NAME = "http://35.204.169.121/api/auth/updateDisplayName?";
    final static public String UPDATE_USER_ABOUT = "http://35.204.169.121/api/auth/updateAbout?";
    final static public String UPDATE_USER_PROFILE_IMAGE = "http://35.204.169.121/api/auth/updateProfileImage?";
    final static public String VIEW_SAVED_LINKS = "http://35.204.169.121/api/auth/viewSavedLinks";
    final static public String FOLLOW_USER = "http://35.204.169.121/api/auth/follow";
    final static public String UN_FOLLOW_USER = "http://35.204.169.121/api/auth/unfollow";
    final static public String GET_USER_FOLLOWERS = "http://35.204.169.121/api/auth/followers";
    final static public String GET_USER_FOLLOWING = "http://35.204.169.121/api/auth/following";
    final static public String GET_USER_POSTS_AND_COMMENTS = "http://35.204.169.121/api/auth/viewOverview";


    //API parameters name
    final static public String USERNAME = "username";
    final static public String PASSWORD = "password";
    final static public String EMAIL = "email";
    final static public String TOKEN = "token=";

    //result for start activity for result
    final static public int CREATE_ACCOUNT_SUCCESSFULLY = 0;
    final static public int CREATE_ACCOUNT_UNSUCCESSFULLY = 1;

    //callback
    public static LogInSignUpSuccessful mLogInSignUpSuccessful;
    public static UpdateProfileInfo mUpdateProfileInfo;

}
