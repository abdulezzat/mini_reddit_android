package com.example.android.minireddit.networking;

import android.content.Context;

import com.example.android.minireddit.Constants;
import com.example.android.minireddit.R;
import com.example.android.minireddit.datastructure.Comment;
import com.example.android.minireddit.datastructure.Post;
import com.example.android.minireddit.datastructure.User;

import java.util.ArrayList;

/**
 * Created by Aly on 3/15/2019.
 */
public class MockRestService implements com.example.android.minireddit.networking.Requests {

    @Override
    public ArrayList<Post> getTrendingPost(Context context) {
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post(0, -1, null, String.valueOf(R.drawable.reddit_icon), "u/alyramzy", " 9h ago", "This is Photo Hint", "https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg", null, 15, 200, false, false, false, 0));
        posts.add(new Post(1, -1, null, String.valueOf(R.drawable.reddit_icon), "u/alyramzy", " 1h ago", "This is Photo Hint", "https://images.pexels.com/photos/207962/pexels-photo-207962.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", null, 15, 200, false, false, false, 1));
        posts.add(new Post(2, -1, null, String.valueOf(R.drawable.reddit_icon), "u/alyramzy", "2h ago", "This is Photo Hint", "https://images.unsplash.com/photo-1531804055935-76f44d7c3621?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=80", null, 15, 200, false, false, false, 1));
        posts.add(new Post(3, 3, "r/Communityname", String.valueOf(R.drawable.reddit_icon), "u/alyramzy", " 4h ago", "This is Text Message only works as a test for posts with no photos", null, "https://www.youtube.com/watch?v=U2dv_oDof4M&list=RDWfJD6ode3Sw&index=5", 15, 200, false, false, false, -1));
        posts.add(new Post(4, 4, "r/Space", String.valueOf(R.drawable.reddit_icon), "u/alyramzy", " 6h ago", "This is Text Message only works as a test for posts with no photos but got video", null, "https://www.youtube.com/watch?v=U2dv_oDof4M&list=RDWfJD6ode3Sw&index=5", 15, 200, false, false, false, -1));
        posts.add(new Post(5, 5, "r/Pubg", String.valueOf(R.drawable.reddit_icon), "u/alyramzy", ". 6h ago", "This is Text Message only works as a test for posts with no photos and no  video", null, null, 15, 200, false, false, false, -1));
        posts.add(new Post(6, 6, "r/CUFE", String.valueOf(R.drawable.reddit_icon), "u/alyramzy", " 6h ago", "Ahmed Kamel Song Kan fe tfl i love it <3", null, "https://www.youtube.com/watch?v=2Rq0eRj7xVs&index=9&list=RDWfJD6ode3Sw", 15, 200, false, false, false, -1));


        return posts;
    }

    @Override
    public ArrayList<Post> getHomePost(Context context) {
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post(0, -1, null, String.valueOf(R.drawable.reddit_icon), "u/alyramzy", " 9h ago", "This is Photo Hint", "https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg", null, 15, 200, false, false, false, 0));
        posts.add(new Post(1, -1, null, String.valueOf(R.drawable.reddit_icon), "u/alyramzy", " 1h ago", "This is Photo Hint", "https://images.pexels.com/photos/207962/pexels-photo-207962.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", null, 15, 200, false, false, false, 1));
        posts.add(new Post(2, -1, null, String.valueOf(R.drawable.reddit_icon), "u/alyramzy", " 2h ago", "This is Photo Hint", "https://images.unsplash.com/photo-1531804055935-76f44d7c3621?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=80", null, 15, 200, false, false, false, 1));
        posts.add(new Post(3, 3, "r/Communityname", String.valueOf(R.drawable.reddit_icon), "u/alyramzy", " 4h ago", "This is Text Message only works as a test for posts with no photos", null, "https://www.youtube.com/watch?v=U2dv_oDof4M&list=RDWfJD6ode3Sw&index=5", 15, 200, false, false, false, -1));
        posts.add(new Post(4, 4, "r/Space", String.valueOf(R.drawable.reddit_icon), "u/alyramzy", " 6h ago", "This is Text Message only works as a test for posts with no photos but got video", null, "https://www.youtube.com/watch?v=U2dv_oDof4M&list=RDWfJD6ode3Sw&index=5", 15, 200, false, false, false, -1));
        posts.add(new Post(5, 5, "r/Pubg", String.valueOf(R.drawable.reddit_icon), "u/alyramzy", " 6h ago", "This is Text Message only works as a test for posts with no photos and no  video", null, null, 15, 200, false, false, false, -1));
        posts.add(new Post(6, 6, "r/CUFE", String.valueOf(R.drawable.reddit_icon), "u/alyramzy", " 6h ago", "Ahmed Kamel Song Kan fe tfl i love it <3", null, "https://www.youtube.com/watch?v=2Rq0eRj7xVs&index=9&list=RDWfJD6ode3Sw", 15, 200, false, false, false, -1));


        return posts;

    }


    @Override
    public ArrayList<Post> getMoreTrendingPost(int index) {
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post(0, -1, null, String.valueOf(R.drawable.reddit_icon), "u/alyramzy", " 9h ago", "This is Photo Hint", "https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg", null, 15, 200, false, false, false, 0));
        posts.add(new Post(1, -1, null, String.valueOf(R.drawable.reddit_icon), "u/alyramzy", " 1h ago", "This is Photo Hint", "https://images.pexels.com/photos/207962/pexels-photo-207962.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", null, 15, 200, false, false, false, 1));
        posts.add(new Post(2, -1, null, String.valueOf(R.drawable.reddit_icon), "u/alyramzy", " 2h ago", "This is Photo Hint", "https://images.unsplash.com/photo-1531804055935-76f44d7c3621?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=80", null, 15, 200, false, false, false, 1));
        posts.add(new Post(3, 3, "r/Communityname", String.valueOf(R.drawable.reddit_icon), "u/alyramzy", " 4h ago", "This is Text Message only works as a test for posts with no photos", null, "https://www.youtube.com/watch?v=U2dv_oDof4M&list=RDWfJD6ode3Sw&index=5", 15, 200, false, false, false, -1));
        posts.add(new Post(4, 4, "r/Space", String.valueOf(R.drawable.reddit_icon), "u/alyramzy", " 6h ago", "This is Text Message only works as a test for posts with no photos but got video", null, "https://www.youtube.com/watch?v=U2dv_oDof4M&list=RDWfJD6ode3Sw&index=5", 15, 200, false, false, false, -1));
        posts.add(new Post(5, 5, "r/Pubg", String.valueOf(R.drawable.reddit_icon), "u/alyramzy", " 6h ago", "This is Text Message only works as a test for posts with no photos and no  video", null, null, 15, 200, false, false, false, -1));
        posts.add(new Post(6, 6, "r/CUFE", String.valueOf(R.drawable.reddit_icon), "u/alyramzy", " 6h ago", "Ahmed Kamel Song Kan fe tfl i love it <3", null, "https://www.youtube.com/watch?v=2Rq0eRj7xVs&index=9&list=RDWfJD6ode3Sw", 15, 200, false, false, false, -1));


        return posts;

    }

    @Override
    public boolean votePostUp(Context context, int postId) {
        return true;
    }

    @Override
    public boolean votePostDown(Context context, int postId) {
        return true;
    }


    @Override
    public boolean subscribeCommunity(Context context, int commId) {
        return true;
    }

    @Override
    public boolean unsubscribeCommunity(Context context, int commId) {
        return true;
    }


    @Override
    public boolean logIn(Context context, String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            Constants.mToken = "TEST";
            return true;
        }


        return false;
    }

    @Override
    public boolean signUp(Context context, String email, String username, String password) {
        if (username.equals("admin") && password.equals("admin") && email.equals("admin"))
            return true;
        return false;
    }


    public User getUserPublicInfo(Context context, String username) {
        User user = new User("admin", "Ahmed Elkarashily", 49415, "4m 24d", "just a place holder for user about section. hello. hope you enjoy your visit with us.", "https://theme.zdassets.com/theme_assets/2219439/89cbe072bbb76fc29a82367bd19b511df487d018.png", "https://data.whicdn.com/images/290284739/superthumb.jpg?t=1498330484");
        Constants.visitedUser = user;
        return user;
    }

    public User getUserPrivateInfo(Context context) {
        User user = new User("", "", 0, "", "", "", "");
        user.setmEmail("karashily@gmail.com");
        return user;
    }

    public String getUsername(Context context) {
        Constants.user.setmUserName("MockedUserName");
        return "karashily";
    }

    public boolean updateUserDisplayName(Context context, String displayName) {
        Constants.user.setmDisplayName(displayName);
        Constants.visitedUser.setmDisplayName(displayName);
        return true;
    }

    public boolean updateUserAbout(Context context, String about) {
        Constants.user.setmAbout(about);
        Constants.visitedUser.setmAbout(about);
        return true;
    }

    public boolean updateUserProfileImage(Context context, String profileImage) {
        Constants.user.setmProfileImage(profileImage);
        Constants.visitedUser.setmProfileImage(profileImage);
        return true;
    }

    public boolean updateUserHeaderImage(final Context context, final String headerImage) {
        Constants.user.setmHeaderImage(headerImage);
        Constants.visitedUser.setmHeaderImage(headerImage);
        return true;
    }

    public void ViewSavedLinks(Context context) {
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post(0, 0, null, String.valueOf(R.drawable.reddit_icon), "r/alyramzy", "Posted by Aly Ramzy. 9h ago", "This is Photo Hint", "https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg", null, 15, 200, false, false, false, 0));
        posts.add(new Post(1, 1, null, String.valueOf(R.drawable.reddit_icon), "r/alyramzy", "Posted by Aly Ramzy. 1h ago", "This is Photo Hint", "https://images.pexels.com/photos/207962/pexels-photo-207962.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", null, 15, 200, false, false, false, 1));
        posts.add(new Post(2, 2, null, String.valueOf(R.drawable.reddit_icon), "r/alyramzy", "Posted by Aly Ramzy. 2h ago", "This is Photo Hint", "https://images.unsplash.com/photo-1531804055935-76f44d7c3621?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=80", null, 15, 200, false, false, false, 1));
        Constants.savedPosts.addAll(posts);

        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment(0, "I disagree with you", "karashily", "I really like this project", "this project is making me learn a lot of things", 2, "abdallaezzat", "https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg", 2, 8, "3d", 1, true, true, false));
        comments.add(new Comment(0, "I agree with you", "alyramzy", "I really like this project", "this project is making me learn a lot of things", 2, "abdallaezzat", "https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg", 5, 9, "3m 2d", 0, true, false, true));
        comments.add(new Comment(0, "what project?", "kokowawa", "I really like this project", "this project is making me learn a lot of things", 2, "abdallaezzat", "https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg", 0, 0, "3d", 0, true, false, false));
        Constants.savedComments.addAll(comments);
    }

    public void getUserPostsAndComments(Context context, String username) {
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post(0, 0, null, String.valueOf(R.drawable.reddit_icon), "r/alyramzy", "Posted by Aly Ramzy. 9h ago", "This is Photo Hint", "https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg", null, 15, 200, false, false, false, 0));
        posts.add(new Post(1, 1, null, String.valueOf(R.drawable.reddit_icon), "r/alyramzy", "Posted by Aly Ramzy. 1h ago", "This is Photo Hint", "https://images.pexels.com/photos/207962/pexels-photo-207962.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", null, 15, 200, false, false, false, 1));
        posts.add(new Post(2, 2, null, String.valueOf(R.drawable.reddit_icon), "r/alyramzy", "Posted by Aly Ramzy. 2h ago", "This is Photo Hint", "https://images.unsplash.com/photo-1531804055935-76f44d7c3621?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=80", null, 15, 200, false, false, false, 1));
        Constants.visitedUser.addPosts(posts);

        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment(0, "I disagree with you", "karashily", "I really like this project", "this project is making me learn a lot of things", 2, "abdallaezzat", "https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg", 2, 8, "3d", 1, true, true, false));
        comments.add(new Comment(0, "I agree with you", "alyramzy", "I really like this project", "this project is making me learn a lot of things", 2, "abdallaezzat", "https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg", 5, 9, "3m 2d", 0, true, false, true));
        comments.add(new Comment(0, "what project?", "kokowawa", "I really like this project", "this project is making me learn a lot of things", 2, "abdallaezzat", "https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg", 0, 0, "3d", 0, true, false, false));
        Constants.visitedUser.addComments(comments);
    }

    public boolean followUser(Context context, String username) {
        ArrayList<String> newFollowings = new ArrayList<>();
        newFollowings.add(username);
        Constants.user.addFollowing(newFollowings);
        return true;
    }

    public boolean unFollowUser(Context context, String username) {
        ArrayList<String> removedFollowings = new ArrayList<>();
        removedFollowings.add(username);
        Constants.user.removeFollowing(removedFollowings);
        return true;
    }

    public void getUserFollowers(Context context, String username) {
        ArrayList<String> newFollowers = new ArrayList<>();
        newFollowers.add("alyramzy");
        newFollowers.add("abdallaezzat");
        Constants.visitedUser.addFollowers(newFollowers);
    }

    public void getUserFollowing(Context context, String username) {
        ArrayList<String> newFollowings = new ArrayList<>();
        newFollowings.add("3lool");
        newFollowings.add("gedo");
        Constants.visitedUser.addFollowing(newFollowings);
    }

    @Override
    public boolean hidePost(Context context, int postID) {
        return true;
    }

    @Override
    public boolean blockUser(Context context, String username) {
        return true;
    }

}
