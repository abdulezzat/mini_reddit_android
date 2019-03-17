package com.example.android.minireddit.Networking;

import com.example.android.minireddit.R;
import com.example.android.minireddit.datastructure.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Aly on 3/15/2019.
 */

public class RestService implements Requests {
    @Override
    public ArrayList<Post> getTrendingPost() {
        ArrayList<Post> posts=new ArrayList<>();
        String JSON="{ \"posts\": [ { \"post_id\": 1, \"body\": \"post1\", \"username\": \"ahmed\", \"downvotes\": 17, \"upvotes\": 30, \"date\": \" 2 days ago \", \"comments_num\": 0, \"saved\": \"true\", \"hidden\": \"false\", \"postimageurl\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\", \"votestatus\":-1, \"videourl\":\"https://www.youtube.com/watch?v=4PUFV17_VkA\" , \"userimagelogo\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\" }, { \"post_id\": 2, \"body\": \"post2\", \"username\": \"ahmed\", \"downvotes\": 15, \"upvotes\": 20, \"date\": \" 2 days ago \", \"comments_num\": 0, \"saved\": \"false\", \"hidden\": \"true\", \"postimageurl\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\", \"votestatus\":-1, \"videourl\":\"https://www.youtube.com/watch?v=_kYJDce1Hcw\", \"userimagelogo\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\" }, { \"post_id\": 3, \"body\": \"post3\", \"username\": \"ahmed\", \"downvotes\": 15, \"upvotes\": 20, \"date\": \" 2 days ago \", \"comments_num\": 0, \"saved\": \"true\", \"hidden\": \"true\", \"postimageurl\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\", \"votestatus\":-1 , \"videourl\":\"https://www.youtube.com/watch?v=_kYJDce1Hcw\", \"userimagelogo\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\" } ] }";
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(JSON);
            JSONArray jsonArray=jsonObject.getJSONArray("posts");
            for (int i=0;i<jsonArray.length();i++)
            {
                JSONObject post=jsonArray.getJSONObject(i);
                int id=post.getInt("post_id");
                String userlogo=String.valueOf(R.drawable.default_avatar);//post.getString("userimagelogo");
                String postUser=post.getString("username");
                String postInfo=post.getString("date");
                String postText=post.getString("body");
                String postImage=post.getString("postimageurl");
                String postVideoUrl=post.getString("videourl");
                if(postVideoUrl.isEmpty())
                    postVideoUrl=null;
                if(postImage.isEmpty())
                    postImage=null;
                int postLikeCount=post.getInt("upvotes")-post.getInt("downvotes");
                int postCommentCount=post.getInt("comments_num");
                boolean saved=post.getBoolean("saved");
                boolean hidden=post.getBoolean("hidden");
                int voteStatus=post.getInt("votestatus");
                Post newPost=new Post(id,userlogo,postUser,postInfo,postText,postImage,postVideoUrl,postLikeCount,postCommentCount,saved,hidden,voteStatus);
                posts.add(newPost);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return posts;
    }
}
