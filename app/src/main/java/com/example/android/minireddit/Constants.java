package com.example.android.minireddit;

import com.example.android.minireddit.adapters.PosterAdapter;
import com.example.android.minireddit.adapters.SavedCommentsAdapter;
import com.example.android.minireddit.adapters.SavedPostsAdapter;
import com.example.android.minireddit.datastructure.Comment;
import com.example.android.minireddit.datastructure.Post;
import com.example.android.minireddit.datastructure.User;

import java.util.ArrayList;

//
public class Constants {
    public static String mToken =null;

    public static final boolean debug = true;
    //public static User user = null;
    public static User visitedUser = null;
    public static User user = new User("admin","admin",null,"admin@gamil.com",null,200,null,false);
    public static final String TRENDIN_POST = "";
    public static PosterAdapter poster = null;
    public static PosterAdapter homeposts = null;


    //Saved Links
    public static ArrayList<Post> savedPosts = new ArrayList<>();
    public static ArrayList<Comment> savedComments = new ArrayList<>();


    //URL
    final static public String LOG_IN_URL = "http://127.0.0.1:8000/api/unauth/signIn?";
    final static public String SIGN_UP_URL = "http://127.0.0.1:8000/api/unauth/signUp?";

    //API parameters name
    final static public String username = "username";
    final static public String password = "password";
    final static public String email = "email";

    //result for start activity for result
    final static public int CREATE_ACCOUNT_SUCCESSFULLY = 0;
    final static public int CREATE_ACCOUNT_UNSUCCESSFULLY = 1;

}
