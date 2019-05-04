package com.example.android.minireddit;

import com.example.android.minireddit.abs.ChooseCommunityCallback;
import com.example.android.minireddit.abs.GetListOfCommunities;
import com.example.android.minireddit.abs.GetSinglePost;
import com.example.android.minireddit.abs.LogInSignUpSuccessful;
import com.example.android.minireddit.abs.UpdateProfileInfo;
import com.example.android.minireddit.adapters.PosterAdapter;
import com.example.android.minireddit.adapters.SavedCommentsAdapter;
import com.example.android.minireddit.adapters.SavedPostsAdapter;
import com.example.android.minireddit.datastructure.*;
import com.example.android.minireddit.libraries.atv.model.TreeNode;

import java.util.ArrayList;

//
public class Constants {
    public static String mToken = "";


    public static final boolean debug = false;
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
    final static public String BASE_URL="http://35.204.169.121";
    final static public String LOG_IN_URL  = BASE_URL+"/api/unauth/signIn?";
    final static public String SIGN_UP_URL = BASE_URL+"/api/unauth/signUp?";
    final static public String TRENDING_POSTS = BASE_URL+"/api/unauth/ViewPosts?page_type=0";
    final static public String HOME_POSTS = BASE_URL+"/api/unauth/ViewPosts?page_type=1&token=";
    final static public String VOTE_POST_UP = BASE_URL+"/api/auth/upvoteLink?";
    final static public String VOTE_LINK_DOWN = BASE_URL+"/api/auth/downvoteLink?";
    final static public String SUBSCRIBE_COMMUNITY = BASE_URL+"/api/auth/subscribeCommunity?";
    final static public String UNSUBSCRIBE_COMMUNITY = BASE_URL+"/api/auth/unSubscribeCommunity?";
    final static public String HIDE_POST = BASE_URL+"/api/auth/hidePost?";
    final static public String BLOCK_USER = BASE_URL+"/api/auth/blockUser?";
    final static public String ADD_LINKK=BASE_URL+"/api/auth/addLink?";
    final static public String GET_USER_PUBLIC_INFO = BASE_URL+"/api/unauth/viewPublicUserInfo";
    final static public String GET_USER_PRIVATE_INFO = BASE_URL+"/api/auth/viewPrivateUserInfo";
    final static public String GET_USERNAME = BASE_URL+"/api/auth/getUsername";
    final static public String UPDATE_USER_DISPLAY_NAME = BASE_URL+"/api/auth/updateDisplayName?";
    final static public String UPDATE_USER_ABOUT = BASE_URL+"/api/auth/updateAbout?";
    final static public String UPDATE_USER_PROFILE_IMAGE = BASE_URL+"/api/auth/updateProfileImage?";
    final static public String VIEW_SAVED_LINKS = BASE_URL+"/api/auth/viewSavedLinks";
    final static public String FOLLOW_USER = BASE_URL+"/api/auth/follow";
    final static public String UN_FOLLOW_USER = BASE_URL+"/api/auth/unfollow";
    final static public String GET_USER_FOLLOWERS = BASE_URL+"/api/auth/followers";
    final static public String GET_USER_FOLLOWING = BASE_URL+"/api/auth/following";
    final static public String GET_USER_POSTS_AND_COMMENTS = BASE_URL+"/api/auth/viewOverview";
    final static public String GET_LIST_OF_COMMUNITIES=BASE_URL+"/api/unauth/viewUserCommunities?username=";
    final static public String VIEW_SINGLE_POST=BASE_URL+"/api/unauth/viewSinglePost?";
    final static public String GET_COMMENTS=BASE_URL+"/api/unauth/viewCommentsReplies?";
    //930d0c7c.ngrok.io
    //35.204.169.121


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
    public static ChooseCommunityCallback CHOOSEN_COMMUNITY;
    public static GetListOfCommunities COMMUNITIES;
    public static GetSinglePost SINGLE_POST;

    public static TreeNode commentReplyNode;
    public static Comment commentReply;
    public static Post postComment;
    public final static int SHIFT_NODE=25;








}
