package com.example.android.minireddit.datastructure;

import com.example.android.minireddit.R;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Aly on 3/15/2019.
 */
public class PostTest {


    @Test
    public void constructor_isCorrect() throws Exception {
        Post post=new Post(1,String.valueOf(R.drawable.reddit_icon),"r/alyramzy","Posted by Aly Ramzy. 4h ago","This is Photo Hint","https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg",null,15,200,false,false,0);
        assertEquals(post.getPostLogoUrl(),String.valueOf( R.drawable.reddit_icon));
        assertEquals(post.getPostInfo(), "Posted by Aly Ramzy. 4h ago");
        assertEquals(post.getPostUser(), "r/alyramzy");
        assertEquals(post.getPostText(), "This is Photo Hint");
        assertEquals(post.getPostId(),1);
        assertEquals(post.getPostImageUrl(), "https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg");
        assertEquals(post.isHidden(),false);
        assertEquals(post.isSaved(),false);
        assertEquals(post.getVoteStatus(),0);
        assertEquals(post.getPostLikeCount(), 15);

    }
    @Test
    public void setPostLogoUrl_isCorrect(){
        Post post=new Post(0,null,null,null,null,null,null,0,0,false,false,0);

        post.setPostLogoUrl("Test");
        assertEquals(post.getPostLogoUrl(),"Test");

    }
    @Test
    public void setPostId_isCorrect(){
        Post post=new Post(0,null,null,null,null,null,null,0,0,false,false,0);

        post.setPostId(5);
        assertEquals(post.getPostId(),5);

    }
    @Test
    public void setPostUser_isCorrect(){
        Post post=new Post(0,null,null,null,null,null,null,0,0,false,false,0);
        post.setPostUser("Ramzy");
        assertEquals(post.getPostUser(),"Ramzy");

    }
    @Test
    public void setPostInfo_isCorrect(){
        Post post=new Post(0,null,null,null,null,null,null,0,0,false,false,0);
        post.setPostInfo("2 min ago");
        assertEquals(post.getPostInfo(),"2 min ago");

    }
    @Test
    public void setPostText_isCorrect(){
        Post post=new Post(0,null,null,null,null,null,null,0,0,false,false,0);

        post.setPostText("Text for profile_header_test");
        assertEquals(post.getPostText(),"Text for profile_header_test");

    }
    @Test
    public void setPostImageUrl_isCorrect(){
        Post post=new Post(0,null,null,null,null,null,null,0,0,false,false,0);

        post.setPostImageUrl("Test image");
        assertEquals(post.getPostImageUrl(),"Test image");

    }
    @Test
    public void setPostLikeCount_isCorrect(){
        Post post=new Post(0,null,null,null,null,null,null,0,0,false,false,0);

        post.setPostLikeCount(20);
        assertEquals(post.getPostLikeCount(),20);

    }
    @Test
    public void setPostCommentCount_isCorrect(){
        Post post=new Post(0,null,null,null,null,null,null,0,0,false,false,0);

        post.setPostCommentCount(31);
        assertEquals(post.getPostCommentCount(),31);

    }
    @Test
    public void setPostSaved_isCorrect(){
        Post post=new Post(0,null,null,null,null,null,null,0,0,false,false,0);

        post.setSaved(true);
        assertEquals(post.isSaved(),true);

    }
    @Test
    public void setPostHidden_isCorrect(){
        Post post=new Post(0,null,null,null,null,null,null,0,0,false,false,0);

        post.setHidden(true);
        assertEquals(post.isHidden(),true);

    }
    @Test
    public void setPostVoteStatus_isCorrect(){
        Post post=new Post(0,null,null,null,null,null,null,0,0,false,false,0);

        post.setVoteStatus(-1);
        assertEquals(post.getVoteStatus(),-1);

    }
}