package com.example.android.minireddit.networking;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android.minireddit.Constants;
import com.example.android.minireddit.R;
import com.example.android.minireddit.datastructure.Post;
import com.example.android.minireddit.fragments.PostFragment;
import com.example.android.minireddit.networking.Requests;
import com.example.android.minireddit.datastructure.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aly on 3/15/2019.
 */
public class RestService implements Requests {
    @Override
    public ArrayList<Post> getTrendingPost(final Context context) {
        Toast.makeText(context, "Function Triggered", Toast.LENGTH_SHORT).show();
        //"https://api.myjson.com/bins/9xf3m"
        //"http://192.168.43.118:8000/api/unauth/ViewPosts"
        //"https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10"
        //"http://127.0.0.1:8000/api/unauth/ViewPosts?page_type=0"
        //"http://localhost:8000/api/unauth/ViewPosts"
        int pageType = 0;
        String connectionStrong = "http://127.0.0.1:8000/api/unauth/ViewPosts?page_type=0";
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();
        //"http://192.168.43.223:82/api/unauth/ViewPosts"
        StringRequest stringrequest = new StringRequest(Request.Method.GET,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(context,response,Toast.LENGTH_SHORT).show();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("posts");
                            final ArrayList<Post> posts = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                //  Toast.makeText(context,"inside loop "+String.valueOf(i),Toast.LENGTH_SHORT).show();

                                JSONObject post = jsonArray.getJSONObject(i);
                                int id = post.getInt("post_id");
                                String postText = post.getString("body");
                                // Toast.makeText(context,"outer loop 1 ",Toast.LENGTH_SHORT).show();
                                String postVideoUrl = post.getString("video_url");
                                // Toast.makeText(context,"outer loop 2",Toast.LENGTH_SHORT).show();
                                String postImage = post.getString("image");
                                // Toast.makeText(context,"outer loop 3",Toast.LENGTH_SHORT).show();
                                String postUser = post.getString("username");
                                String community = post.getString("community");
                                // Toast.makeText(context,"outer loop 4",Toast.LENGTH_SHORT).show();
                                //int community_id=post.getInt("community_id");
                                // Toast.makeText(context,"outer loop 5 ",Toast.LENGTH_SHORT).show();
                                boolean subs = post.getBoolean("subscribed");
                                String userlogo = String.valueOf(R.drawable.default_avatar);//post.getString("userimagelogo");
                                // Toast.makeText(context,"outer loop ",Toast.LENGTH_SHORT).show();
                                int postLikeCount = post.getInt("upvotes") - post.getInt("downvotes");
                                String postInfo = post.getString("date");
                                int postCommentCount = post.getInt("comments_num");
                                //Toast.makeText(context,"outer loop ",Toast.LENGTH_SHORT).show();
                                //  Toast.makeText(context,postVideoUrl,Toast.LENGTH_SHORT).show();


                                if (postVideoUrl == "null")
                                    postVideoUrl = null;
                                if (postImage == "null")
                                    postImage = null;


                                int voteStatus = 0;
                                boolean saved = post.getBoolean("saved");
                                boolean hidden = post.getBoolean("hidden");
                                boolean upvote = post.getBoolean("upvoted");
                                boolean downvote = post.getBoolean("downvoted");
                                if (upvote == false && downvote == true)
                                    voteStatus = -1;
                                else if (upvote == true && downvote == false)
                                    voteStatus = 1;


                                Post newPost = new Post(id, 1, null, userlogo, postUser, postInfo, postText, postImage, postVideoUrl, postLikeCount, postCommentCount, saved, hidden, false, voteStatus);
                                // Post newPost =new Post(0,0,"null",String.valueOf(R.drawable.default_avatar),"test","test","test",null,null,0,0,false,false,false,0);
                                posts.add(newPost);


                            }
                            Constants.poster.addAll(posts);
                            Constants.poster.notifyDataSetChanged();
                            Toast.makeText(context, "Loaded Done ", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();

                    }
                })


        {

            @Override
            protected Map<String, String> getParams() {


                Map<String, String> params = new HashMap<>();
                params.put("page_type", "0");
                Toast.makeText(context, params.toString(), Toast.LENGTH_SHORT).show();

                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "null");
                return headers;
            }
        };

        MySingleton.getInstance(context).addToRequestQueue(stringrequest);
        //String JSON = "{ \"posts\": [ { \"post_id\": 1, \"body\": \"post1\", \"username\": \"ahmed\", \"downvotes\": 17, \"upvotes\": 30, \"date\": \" 2 days ago \", \"comments_num\": 0, \"saved\": \"true\", \"hidden\": \"false\", \"postimageurl\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\", \"votestatus\":-1, \"videourl\":\"https://www.youtube.com/watch?v=4PUFV17_VkA\" , \"userimagelogo\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\" }, { \"post_id\": 2, \"body\": \"post2\", \"username\": \"ahmed\", \"downvotes\": 15, \"upvotes\": 20, \"date\": \" 2 days ago \", \"comments_num\": 0, \"saved\": \"false\", \"hidden\": \"true\", \"postimageurl\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\", \"votestatus\":-1, \"videourl\":\"https://www.youtube.com/watch?v=_kYJDce1Hcw\", \"userimagelogo\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\" }, { \"post_id\": 3, \"body\": \"post3\", \"username\": \"ahmed\", \"downvotes\": 15, \"upvotes\": 20, \"date\": \" 2 days ago \", \"comments_num\": 0, \"saved\": \"true\", \"hidden\": \"true\", \"postimageurl\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\", \"votestatus\":-1 , \"videourl\":\"https://www.youtube.com/watch?v=_kYJDce1Hcw\", \"userimagelogo\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\" } ] }";
        //JSONObject jsonObject = null;

        final ArrayList<Post> postssss = new ArrayList<>();
        return postssss;
    }

    @Override
    public ArrayList<Post> getHomePost(final Context context) {
        Toast.makeText(context, "Function Triggered", Toast.LENGTH_SHORT).show();
        //"https://api.myjson.com/bins/9xf3m"
        //"http://192.168.43.118:8000/api/unauth/ViewPosts"
        //"https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10"
        //"http://127.0.0.1:8000/api/unauth/ViewPosts?page_type=0"
        //"http://localhost:8000/api/unauth/ViewPosts"
        int pageType = 0;
        String connectionStrong = "http://127.0.0.1:8000/api/unauth/ViewPosts?page_type=1&token=";//+token
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();
        //"http://192.168.43.223:82/api/unauth/ViewPosts"
        StringRequest stringrequest = new StringRequest(Request.Method.GET,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(context,response,Toast.LENGTH_SHORT).show();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("posts");
                            final ArrayList<Post> posts = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                //  Toast.makeText(context,"inside loop "+String.valueOf(i),Toast.LENGTH_SHORT).show();

                                JSONObject post = jsonArray.getJSONObject(i);
                                int id = post.getInt("post_id");
                                String postText = post.getString("body");
                                // Toast.makeText(context,"outer loop 1 ",Toast.LENGTH_SHORT).show();
                                String postVideoUrl = post.getString("video_url");
                                // Toast.makeText(context,"outer loop 2",Toast.LENGTH_SHORT).show();
                                String postImage = post.getString("image");
                                // Toast.makeText(context,"outer loop 3",Toast.LENGTH_SHORT).show();
                                String postUser = post.getString("username");
                                String community = post.getString("community");
                                // Toast.makeText(context,"outer loop 4",Toast.LENGTH_SHORT).show();
                                //int community_id=post.getInt("community_id");
                                // Toast.makeText(context,"outer loop 5 ",Toast.LENGTH_SHORT).show();
                                boolean subs = post.getBoolean("subscribed");
                                String userlogo = String.valueOf(R.drawable.default_avatar);//post.getString("userimagelogo");
                                // Toast.makeText(context,"outer loop ",Toast.LENGTH_SHORT).show();
                                int postLikeCount = post.getInt("upvotes") - post.getInt("downvotes");
                                String postInfo = post.getString("date");
                                int postCommentCount = post.getInt("comments_num");
                                //Toast.makeText(context,"outer loop ",Toast.LENGTH_SHORT).show();
                                //  Toast.makeText(context,postVideoUrl,Toast.LENGTH_SHORT).show();


                                if (postVideoUrl == "null")
                                    postVideoUrl = null;
                                if (postImage == "null")
                                    postImage = null;


                                int voteStatus = 0;
                                boolean saved = post.getBoolean("saved");
                                boolean hidden = post.getBoolean("hidden");
                                boolean upvote = post.getBoolean("upvoted");
                                boolean downvote = post.getBoolean("downvoted");
                                if (upvote == false && downvote == true)
                                    voteStatus = -1;
                                else if (upvote == true && downvote == false)
                                    voteStatus = 1;


                                Post newPost = new Post(id, 1, null, userlogo, postUser, postInfo, postText, postImage, postVideoUrl, postLikeCount, postCommentCount, saved, hidden, false, voteStatus);
                                // Post newPost =new Post(0,0,"null",String.valueOf(R.drawable.default_avatar),"test","test","test",null,null,0,0,false,false,false,0);
                                posts.add(newPost);


                            }
                            Constants.homeposts.addAll(posts);
                            Constants.homeposts.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();

                    }
                })


        {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                Toast.makeText(context, params.toString(), Toast.LENGTH_SHORT).show();
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "null");
                return headers;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);


        //String JSON = "{ \"posts\": [ { \"post_id\": 1, \"body\": \"post1\", \"username\": \"ahmed\", \"downvotes\": 17, \"upvotes\": 30, \"date\": \" 2 days ago \", \"comments_num\": 0, \"saved\": \"true\", \"hidden\": \"false\", \"postimageurl\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\", \"votestatus\":-1, \"videourl\":\"https://www.youtube.com/watch?v=4PUFV17_VkA\" , \"userimagelogo\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\" }, { \"post_id\": 2, \"body\": \"post2\", \"username\": \"ahmed\", \"downvotes\": 15, \"upvotes\": 20, \"date\": \" 2 days ago \", \"comments_num\": 0, \"saved\": \"false\", \"hidden\": \"true\", \"postimageurl\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\", \"votestatus\":-1, \"videourl\":\"https://www.youtube.com/watch?v=_kYJDce1Hcw\", \"userimagelogo\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\" }, { \"post_id\": 3, \"body\": \"post3\", \"username\": \"ahmed\", \"downvotes\": 15, \"upvotes\": 20, \"date\": \" 2 days ago \", \"comments_num\": 0, \"saved\": \"true\", \"hidden\": \"true\", \"postimageurl\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\", \"votestatus\":-1 , \"videourl\":\"https://www.youtube.com/watch?v=_kYJDce1Hcw\", \"userimagelogo\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\" } ] }";
        //JSONObject jsonObject = null;

        final ArrayList<Post> postssss = new ArrayList<>();
        return postssss;

    }

    @Override
    public ArrayList<Post> getMoreTrendingPost(int index) {
        ArrayList<Post> posts = new ArrayList<>();
        String JSON = "{ \"posts\": [ { \"post_id\": 1, \"body\": \"post1\", \"username\": \"ahmed\", \"downvotes\": 17, \"upvotes\": 30, \"date\": \" 2 days ago \", \"comments_num\": 0, \"saved\": \"true\", \"hidden\": \"false\", \"postimageurl\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\", \"votestatus\":-1, \"videourl\":\"https://www.youtube.com/watch?v=4PUFV17_VkA\" , \"userimagelogo\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\" }, { \"post_id\": 2, \"body\": \"post2\", \"username\": \"ahmed\", \"downvotes\": 15, \"upvotes\": 20, \"date\": \" 2 days ago \", \"comments_num\": 0, \"saved\": \"false\", \"hidden\": \"true\", \"postimageurl\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\", \"votestatus\":-1, \"videourl\":\"https://www.youtube.com/watch?v=_kYJDce1Hcw\", \"userimagelogo\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\" }, { \"post_id\": 3, \"body\": \"post3\", \"username\": \"ahmed\", \"downvotes\": 15, \"upvotes\": 20, \"date\": \" 2 days ago \", \"comments_num\": 0, \"saved\": \"true\", \"hidden\": \"true\", \"postimageurl\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\", \"votestatus\":-1 , \"videourl\":\"https://www.youtube.com/watch?v=_kYJDce1Hcw\", \"userimagelogo\":\"https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_960_720.jpg\" } ] }";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(JSON);
            JSONArray jsonArray = jsonObject.getJSONArray("posts");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject post = jsonArray.getJSONObject(i);
                int id = post.getInt("post_id");
                String userlogo = String.valueOf(R.drawable.default_avatar);//post.getString("userimagelogo");
                String postUser = post.getString("username");
                String postInfo = post.getString("date");
                String postText = post.getString("body");
                String postImage = post.getString("postimageurl");
                String postVideoUrl = post.getString("videourl");
                if (postVideoUrl.isEmpty())
                    postVideoUrl = null;
                if (postImage.isEmpty())
                    postImage = null;
                int postLikeCount = post.getInt("upvotes") - post.getInt("downvotes");
                int postCommentCount = post.getInt("comments_num");
                boolean saved = post.getBoolean("saved");
                boolean hidden = post.getBoolean("hidden");
                int voteStatus = post.getInt("votestatus");
                Post newPost = new Post(id, 0, null, userlogo, postUser, postInfo, postText, postImage, postVideoUrl, postLikeCount, postCommentCount, saved, hidden, false, voteStatus);
                posts.add(newPost);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return posts;
    }

    @Override
    public boolean votePostUp(final Context context, final int postId) {
        String connectionStrong = "http://127.0.0.1:8000/api/auth/upvoteLink?";
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();
        StringRequest stringrequest = new StringRequest(Request.Method.POST,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("link_id", String.valueOf(postId));
                params.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMjcuMC4wLjE6ODAwMFwvYXBpXC91bmF1dGhcL3NpZ25JbiIsImlhdCI6MTU1MzI2ODA4NSwiZXhwIjoxNTUzODcyODg1LCJuYmYiOjE1NTMyNjgwODUsImp0aSI6IkpidmtvVXdZd0hETjh0aUciLCJzdWIiOiJhbHkxMjMiLCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.4BOQwLz6hUcTE-CH57iPPDdWkLxlrkhshZGgGZryHZE");

                return params;
            }

        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);
        return true;

    }

    @Override
    public boolean votePostDown(final Context context, final int postId) {
        String connectionStrong = "http://127.0.0.1:8000/api/auth/downvoteLink?";
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();
        StringRequest stringrequest = new StringRequest(Request.Method.POST,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("link_id", String.valueOf(postId));
                params.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMjcuMC4wLjE6ODAwMFwvYXBpXC91bmF1dGhcL3NpZ25JbiIsImlhdCI6MTU1MzI2ODA4NSwiZXhwIjoxNTUzODcyODg1LCJuYmYiOjE1NTMyNjgwODUsImp0aSI6IkpidmtvVXdZd0hETjh0aUciLCJzdWIiOiJhbHkxMjMiLCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.4BOQwLz6hUcTE-CH57iPPDdWkLxlrkhshZGgGZryHZE");

                return params;
            }

        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);
        return true;
    }

    @Override
    public boolean subscribeCommunity(final Context context, final int commId) {
        String connectionStrong = "http://127.0.0.1:8000/api/auth/subscribeCommunity?";
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();
        StringRequest stringrequest = new StringRequest(Request.Method.POST,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("community_id", String.valueOf(commId));
                params.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMjcuMC4wLjE6ODAwMFwvYXBpXC91bmF1dGhcL3NpZ25JbiIsImlhdCI6MTU1MzI2ODA4NSwiZXhwIjoxNTUzODcyODg1LCJuYmYiOjE1NTMyNjgwODUsImp0aSI6IkpidmtvVXdZd0hETjh0aUciLCJzdWIiOiJhbHkxMjMiLCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.4BOQwLz6hUcTE-CH57iPPDdWkLxlrkhshZGgGZryHZE");

                return params;
            }

        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);
        return true;
    }

    @Override
    public boolean unsubscribeCommunity(final Context context, final int commId) {
        String connectionStrong = "http://127.0.0.1:8000/api/auth/unSubscribeCommunity?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMjcuMC4wLjE6ODAwMFwvYXBpXC91bmF1dGhcL3NpZ25JbiIsImlhdCI6MTU1MzI2ODA4NSwiZXhwIjoxNTUzODcyODg1LCJuYmYiOjE1NTMyNjgwODUsImp0aSI6IkpidmtvVXdZd0hETjh0aUciLCJzdWIiOiJhbHkxMjMiLCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.4BOQwLz6hUcTE-CH57iPPDdWkLxlrkhshZGgGZryHZE&community_id=" + commId;
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();
        StringRequest stringrequest = new StringRequest(Request.Method.DELETE,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                // params.put("community_id",String.valueOf(commId));
                //params.put("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMjcuMC4wLjE6ODAwMFwvYXBpXC91bmF1dGhcL3NpZ25JbiIsImlhdCI6MTU1MzI2ODA4NSwiZXhwIjoxNTUzODcyODg1LCJuYmYiOjE1NTMyNjgwODUsImp0aSI6IkpidmtvVXdZd0hETjh0aUciLCJzdWIiOiJhbHkxMjMiLCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.4BOQwLz6hUcTE-CH57iPPDdWkLxlrkhshZGgGZryHZE");

                return params;
            }

        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);
        return true;
    }

    @Override
    public boolean logIn(final Context context, final String username, final String password) {
        StringRequest stringrequest = new StringRequest(Request.Method.POST,
                (Constants.LOG_IN_URL),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            //TODO handel this after the connection is complete
                            Toast.makeText(context,"Login Done",Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Constants.username, username);
                params.put(Constants.password, password);

                return params;
            }

        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);
        return true;
    }

    @Override
    public boolean signUp(final Context context, final String email, final String username, final String password) {
        StringRequest stringrequest = new StringRequest(Request.Method.POST,
                (Constants.SIGN_UP_URL),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            //TODO handel this after the connection is complete
                            Toast.makeText(context,"Signup Done",Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Constants.username, username);
                params.put(Constants.password, password);
                params.put(Constants.email, email);

                return params;
            }

        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);
        return true;
    }

    @Override
    public User getUserPublicInfo(final Context context, final String username) {
        String connectionStrong = "http://127.0.0.1:8000/api/auth/viewPublicUserInfo?username=" + username; // +token
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();

        final User user = new User("", 0, "", "");

        StringRequest stringrequest = new StringRequest(Request.Method.GET,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(context,response,Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String username = jsonObject.getString("username");
                            user.setmUserName(username);
                            user.setmDisplayName(username);

                            int karma = jsonObject.getInt("karma");
                            user.setmKarma(karma);

                            String cakeDay = jsonObject.getString("cake_day");
                            user.setmCakeDay(cakeDay);

                            String about = jsonObject.getString("about");
                            user.setmAbout(about);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                    }
                })


        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                Toast.makeText(context, params.toString(), Toast.LENGTH_SHORT).show();
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                //headers.put("Authorization","Bearer: "+ token );
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);

        return user;
    }

    @Override
    public User getUserPrivateInfo(final Context context) {
        String connectionStrong = "http://127.0.0.1:8000/api/auth/viewPrivateUserInfo?&token=";//+token
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();

        final User user = new User("", 0, "", "");

        //"http://192.168.43.223:82/api/unauth/ViewPosts"
        StringRequest stringrequest = new StringRequest(Request.Method.GET,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(context,response,Toast.LENGTH_SHORT).show();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String email = jsonObject.getString("email");
                            user.setmEmail(email);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                    }
                })


        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                Toast.makeText(context, params.toString(), Toast.LENGTH_SHORT).show();
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                //headers.put("Authorization","Bearer: "+ token );
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);

        return user;
    }

    public String getUsername (Context context){
        return null;
    }

    public boolean updateUserDisplayName(Context context, String displayName){
        return true;
    }

    public boolean updateUserAbout (Context context, String about){
        return true;
    }

    public boolean updateUserProfileImage (Context context, String profileImage) {
        return true;
    }

    public void ViewSavedLinks (Context context) {
        ArrayList<Post> savedPosts=new ArrayList<>();
        //ArrayList<Comment> savedComments=new ArrayList<>();


        Constants.savedPosts.addAll(savedPosts);
        //Constants.savedComments.addAll(savedComments);
    }

}
