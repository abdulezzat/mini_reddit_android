package com.example.android.minireddit.networking;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android.minireddit.Constants;
import com.example.android.minireddit.R;
import com.example.android.minireddit.datastructure.Comment;
import com.example.android.minireddit.datastructure.Post;
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
        int pageType = 0;
        String connectionString = Constants.TRENDING_POSTS;
        if (Constants.mToken != null) {
            connectionString += "&token=" + Constants.mToken;
        }

        Uri.Builder builder = Uri.parse(connectionString).buildUpon();
        StringRequest stringrequest = new StringRequest(Request.Method.GET,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
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
                                int community_id = post.getInt("community_id");
                                // Toast.makeText(context,"outer loop 5 ",Toast.LENGTH_SHORT).show();
                                boolean subs = post.getBoolean("subscribed");
                                String userlogo = String.valueOf(R.drawable.default_avatar);//post.getString("userimagelogo");
                                // Toast.makeText(context,"outer loop ",Toast.LENGTH_SHORT).show();
                                int postLikeCount = post.getInt("upvotes") - post.getInt("downvotes");
                                String postInfo = post.getString("date");
                                int postCommentCount = post.getInt("comments_num");
                                if (postVideoUrl == "-1")
                                    postVideoUrl = null;
                                if (postImage == "-1")
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


                                Post newPost = new Post(id, community_id, community, userlogo, postUser, postInfo, postText, postImage, postVideoUrl, postLikeCount, postCommentCount, saved, hidden, false, voteStatus);
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
        int pageType = 0;
        String connectionStrong = Constants.HOME_POSTS + Constants.mToken;
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();
        StringRequest stringrequest = new StringRequest(Request.Method.GET,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
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
                                int community_id = post.getInt("community_id");
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


                                Post newPost = new Post(id, community_id, community, userlogo, postUser, postInfo, postText, postImage, postVideoUrl, postLikeCount, postCommentCount, saved, hidden, false, voteStatus);
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
        String connectionStrong = Constants.VOTE_POST_UP;
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
                params.put("token", Constants.mToken);

                return params;
            }

        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);
        return true;

    }

    @Override
    public boolean votePostDown(final Context context, final int postId) {
        String connectionStrong = Constants.VOTE_LINK_DOWN;
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
                params.put("token", Constants.mToken);

                return params;
            }

        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);
        return true;
    }

    @Override
    public boolean subscribeCommunity(final Context context, final int commId) {
        String connectionStrong = Constants.SUBSCRIBE_COMMUNITY;
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
                params.put("token", Constants.mToken);

                return params;
            }

        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);
        return true;
    }

    @Override
    public boolean unsubscribeCommunity(final Context context, final int commId) {
        String connectionStrong = Constants.UNSUBSCRIBE_COMMUNITY + Constants.mToken + "&community_id=" + commId;
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
                            Constants.mToken = jsonObject.getString("token");
                            Constants.user = new User(username, username, null, null, null, 1, null, false);
                            if (Constants.mLogInSignUpSuccessful != null) {
                                Constants.mLogInSignUpSuccessful.Successful();
                            }
                            //TODO handel this after the connection is complete
                            Toast.makeText(context, "Login Done", Toast.LENGTH_SHORT).show();
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
                params.put(Constants.USERNAME, username);
                params.put(Constants.PASSWORD, password);

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
                            Constants.user = new User(username, username, null, email, null, 1, null, false);
                            if (Constants.mLogInSignUpSuccessful != null) {
                                Constants.mLogInSignUpSuccessful.Successful();
                            }
                            //TODO handel this after the connection is complete
                            Toast.makeText(context, "Signup Done", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Constants.USERNAME, username);
                params.put(Constants.PASSWORD, password);
                params.put(Constants.EMAIL, email);
                params.put("password_confirmation", password);

                return params;
            }

        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);
        return true;
    }

    @Override
    public User getUserPublicInfo(final Context context, final String username) {
        String connectionStrong = Constants.GET_USER_PUBLIC_INFO + "?" + Constants.USERNAME + "=" + username;
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();

        final User user = new User("", "", 0, "", "", "", "");

        StringRequest stringrequest = new StringRequest(Request.Method.GET,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(context,response,Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String username = jsonObject.getString("username");
                            Constants.visitedUser.setmUserName(username);

                            String displayName = jsonObject.getString("name");
                            Constants.visitedUser.setmDisplayName(username);

                            int karma = jsonObject.getInt("karma");
                            Constants.visitedUser.setmKarma(karma);

                            String cakeDay = jsonObject.getString("cake_day");
                            Constants.visitedUser.setmCakeDay(cakeDay);

                            String about = jsonObject.getString("about");
                            Constants.visitedUser.setmAbout(about);

                            String profileImage = jsonObject.getString("photo_path");
                            Constants.visitedUser.setmProfileImage(profileImage);

                            String headerImage = jsonObject.getString("cover_path");
                            Constants.visitedUser.setmHeaderImage(headerImage);

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
                headers.put("Content-Type", "application/json");
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);

        return user;
    }

    @Override
    public User getUserPrivateInfo(final Context context) {
        String connectionStrong = Constants.GET_USER_PRIVATE_INFO + "?" + Constants.TOKEN + Constants.mToken;
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();

        final User user = new User("", "", 0, "", "", "", "");

        StringRequest stringrequest = new StringRequest(Request.Method.GET,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(context,response,Toast.LENGTH_SHORT).show();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String email = jsonObject.getString("email");
                            Constants.user.setmEmail(email);

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
    public String getUsername(final Context context) {
        String connectionStrong = Constants.GET_USERNAME + "?" + Constants.TOKEN + Constants.mToken;
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();

        final String string = "";

        StringRequest stringrequest = new StringRequest(Request.Method.GET,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(context,response,Toast.LENGTH_SHORT).show();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String username = jsonObject.getString("username");
                            Constants.user.setmUserName(username);

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
                //headers.put("Authorization","Bearer: "+ Constants.mToken );
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);

        return string;
    }

    public boolean updateUserDisplayName(final Context context, final String displayName) {
        String connectionStrong = Constants.UPDATE_USER_DISPLAY_NAME + Constants.TOKEN + Constants.mToken + "&name=" + displayName;
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();
        StringRequest stringrequest = new StringRequest(Request.Method.PATCH,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Constants.user.setmDisplayName(displayName);
                        Constants.visitedUser.setmDisplayName(displayName);
                        Toast.makeText(context, "Display Name Updated", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                headers.put("Authorization", "Bearer " + Constants.mToken);
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", displayName);
                return params;
            }

        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);
        return true;
    }

    public boolean updateUserAbout(final Context context, final String about) {
        String connectionStrong = Constants.UPDATE_USER_ABOUT + Constants.TOKEN + Constants.mToken + "&" + "about=" + about;
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();
        StringRequest stringrequest = new StringRequest(Request.Method.PATCH,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Constants.user.setmAbout(about);
                        Constants.visitedUser.setmAbout(about);
                        Toast.makeText(context, "User About Updated", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                headers.put("Authorization", "Bearer: " + Constants.mToken);
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("about", about);
                return params;
            }

        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);
        return true;
    }

    public boolean updateUserProfileImage(final Context context, final String profileImage) {
        String connectionStrong = Constants.UPDATE_USER_PROFILE_IMAGE + Constants.TOKEN + Constants.mToken + "&profile_image=" + profileImage + "&profile_or_cover=1";
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();
        StringRequest stringrequest = new StringRequest(Request.Method.POST,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Constants.user.setmProfileImage(profileImage);
                        Constants.visitedUser.setmProfileImage(profileImage);
                        Toast.makeText(context, "Profile Picture Updated", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                headers.put("Authorization", "Bearer " + Constants.mToken);
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("profile_image", profileImage);
                params.put("profile_or_cover", String.valueOf(1));
                params.put("token", Constants.mToken);
                return params;
            }

        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);
        return true;
    }

    public boolean updateUserHeaderImage(final Context context, final String headerImage) {
        String connectionStrong = Constants.UPDATE_USER_PROFILE_IMAGE + Constants.TOKEN + Constants.mToken + "&profile_image=" + headerImage + "&profile_or_cover=2";
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();
        StringRequest stringrequest = new StringRequest(Request.Method.POST,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Constants.user.setmHeaderImage(headerImage);
                        Constants.visitedUser.setmHeaderImage(headerImage);
                        Toast.makeText(context, "Header Image Updated", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                headers.put("Authorization", "Bearer " + Constants.mToken);
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("profile_image", headerImage);
                params.put("profile_or_cover", String.valueOf(2));
                params.put("token", Constants.mToken);
                return params;
            }

        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);
        return true;
    }

    public void ViewSavedLinks(final Context context) {
        String connectionStrong = Constants.VIEW_SAVED_LINKS + "?" + Constants.TOKEN + Constants.mToken;
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();

        StringRequest stringrequest = new StringRequest(Request.Method.GET,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(context,response,Toast.LENGTH_SHORT).show();
                        try {

                            ArrayList<Post> posts = new ArrayList<>();
                            ArrayList<Comment> comments = new ArrayList<>();

                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String type = jsonObject.getString("type");
                                if (type.equals("post")) {

                                    int postId = jsonObject.getInt("post_id");
                                    int communityId = jsonObject.getInt("community_id");
                                    String communityName = jsonObject.getString("community");
                                    String postLogoUrl = "";
                                    String postUser = "Essam";
                                    String postInfo = "";
                                    String PostText = jsonObject.getString("body");
                                    String PostImageUrl = jsonObject.getString("post_image");
                                    String PostVideoUrl = jsonObject.getString("video_url");
                                    int postLikeCount = jsonObject.getInt("upvotes") - jsonObject.getInt("downvotes");
                                    int postCommentCount = jsonObject.getInt("comments_num");
                                    boolean saved = true;
                                    boolean hidden = jsonObject.getBoolean("hidden");
                                    boolean subscribed = jsonObject.getBoolean("subscribed");
                                    boolean upvoted = jsonObject.getBoolean("upvoted");
                                    boolean downvoted = jsonObject.getBoolean("downvoted");
                                    int voteStatus;
                                    if (upvoted) {
                                        voteStatus = 1;
                                    } else if (downvoted) {
                                        voteStatus = -1;
                                    } else {
                                        voteStatus = 0;
                                    }

                                    Post post = new Post(postId, communityId, communityName, postLogoUrl, postUser, postInfo, PostText, PostImageUrl, PostVideoUrl, postLikeCount, postCommentCount, saved, hidden, subscribed, voteStatus);
                                    posts.add(post);

                                } else if (type.equals("comment")) {

                                    JSONObject jsonCommentPost = jsonObject.getJSONObject("post");

                                    String postTitle = jsonCommentPost.getString("title");
                                    String postBody = jsonCommentPost.getString("body");
                                    int postCommunity = jsonCommentPost.getInt("community_id");
                                    String postAuthor = jsonCommentPost.getString("author_username");
                                    String AuthorPhoto = "";

                                    JSONArray jsonComments = jsonObject.getJSONArray("comments");

                                    for (int j = 0; j < jsonComments.length(); j++) {

                                        JSONObject jsonComment = jsonComments.getJSONObject(i);

                                        int commentId = jsonComment.getInt("comment_id");
                                        String commentBody = jsonComment.getString("body");
                                        String commentUser = jsonComment.getString("author_username");
                                        int downVotes = jsonComment.getInt("downvotes");
                                        int upVotes = jsonComment.getInt("upvotes");
                                        String date = jsonComment.getString("link_date");
                                        int commentsNum = 0;
                                        boolean saved = true;
                                        boolean upVoted = false;
                                        boolean downVoted = false;

                                        Comment comment = new Comment(commentId, commentBody, commentUser, postTitle, postBody, postCommunity, postAuthor, AuthorPhoto, downVotes, upVotes, date, commentsNum, saved, upVoted, downVoted);
                                        comments.add(comment);
                                    }
                                }
                            }

                            Constants.savedPosts.addAll(posts);
                            Constants.savedComments.addAll(comments);

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
                //headers.put("Authorization","Bearer: "+ Constants.mToken );
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);
    }

    public boolean followUser(final Context context, final String username) {
        String connectionStrong = Constants.FOLLOW_USER;
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();
        StringRequest stringrequest = new StringRequest(Request.Method.POST,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<String> newFollowings = new ArrayList<>();
                        newFollowings.add(username);
                        Constants.user.addFollowing(newFollowings);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                headers.put("Authorization", "Bearer: " + Constants.mToken);
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                return params;
            }

        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);
        return true;
    }

    public boolean unFollowUser(final Context context, final String username) {
        String connectionStrong = Constants.UN_FOLLOW_USER;
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();
        StringRequest stringrequest = new StringRequest(Request.Method.POST,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<String> removedFollowings = new ArrayList<>();
                        removedFollowings.add(username);
                        Constants.user.removeFollowing(removedFollowings);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                headers.put("token", Constants.mToken);
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                return params;
            }

        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);
        return true;
    }

    public void getUserFollowers(final Context context, final String username) {
        String connectionStrong = Constants.GET_USER_FOLLOWERS + "?" + Constants.USERNAME + "=" + username + "&" + Constants.TOKEN + Constants.mToken;
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();

        StringRequest stringrequest = new StringRequest(Request.Method.GET,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(context,response,Toast.LENGTH_SHORT).show();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray followers = jsonObject.getJSONArray("follwersList");

                            ArrayList<String> newFollowers = new ArrayList<>();
                            for (int i = 0; i < followers.length(); i++) {
                                newFollowers.add(followers.getString(i));
                            }
                            Constants.visitedUser.addFollowers(newFollowers);

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
                //headers.put("Authorization","Bearer: "+ Constants.mToken );
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);
    }

    public void getUserFollowing(final Context context, final String username) {
        String connectionStrong = Constants.GET_USER_FOLLOWING + "?" + Constants.USERNAME + "=" + username + "&" + Constants.TOKEN + Constants.mToken;
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();

        StringRequest stringrequest = new StringRequest(Request.Method.GET,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(context,response,Toast.LENGTH_SHORT).show();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray followers = jsonObject.getJSONArray("follwingList");

                            ArrayList<String> newFollowings = new ArrayList<>();
                            for (int i = 0; i < followers.length(); i++) {
                                newFollowings.add(followers.getString(i));
                            }
                            Constants.visitedUser.addFollowing(newFollowings);

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
                //headers.put("Authorization","Bearer: "+ Constants.mToken );
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);

    }

    @Override
    public boolean hidePost(final Context context, final int postID) {
        String connectionStrong = Constants.HIDE_POST;
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();
        StringRequest stringrequest = new StringRequest(Request.Method.POST,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Post Hidden Succesfull", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Post Hidden Failed", Toast.LENGTH_SHORT).show();

                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("post_id", String.valueOf(postID));
                params.put("token", Constants.mToken);

                return params;
            }

        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);

        return true;
    }

    @Override
    public boolean blockUser(final Context context, final String username) {
        String connectionStrong = Constants.BLOCK_USER;
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();
        StringRequest stringrequest = new StringRequest(Request.Method.POST,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "User Blocked Succesfull", Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "User Blocked Faield", Toast.LENGTH_SHORT).show();

                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("token", Constants.mToken);

                return params;
            }

        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);


        return true;
    }

    public void getUserPostsAndComments(final Context context, final String username) {

        String connectionStrong = Constants.GET_USER_POSTS_AND_COMMENTS + "?" + Constants.USERNAME + "=" + username + "&" + Constants.TOKEN + Constants.mToken;
        Uri.Builder builder = Uri.parse(connectionStrong).buildUpon();

        StringRequest stringrequest = new StringRequest(Request.Method.GET,
                builder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(context,response,Toast.LENGTH_SHORT).show();
                        try {

                            ArrayList<Post> posts = new ArrayList<>();
                            ArrayList<Comment> comments = new ArrayList<>();

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonPostsArray = jsonObject.getJSONArray("posts");
                            for (int i = 0; i < jsonPostsArray.length(); i++) {
                                JSONObject jsonPost = jsonPostsArray.getJSONObject(i);

                                int postId = jsonPost.getInt("post_id");
                                int communityId = jsonPost.getInt("community_id");
                                String communityName = jsonPost.getString("community");
                                String postLogoUrl = jsonPost.getString("author_photo_path");
                                String postUser = jsonPost.getString("username");
                                String postInfo = jsonPost.getString("date");
                                String PostText = jsonPost.getString("body");
                                String PostImageUrl = jsonPost.getString("image");
                                String PostVideoUrl = jsonPost.getString("video_url");
                                int postLikeCount = jsonPost.getInt("upvotes") - jsonPost.getInt("downvotes");
                                int postCommentCount = jsonPost.getInt("comments_num");
                                boolean saved = jsonPost.getBoolean("saved");
                                boolean hidden = jsonPost.getBoolean("hidden");
                                boolean subscribed = jsonPost.getBoolean("subscribed");
                                boolean upvoted = jsonPost.getBoolean("upvoted");
                                boolean downvoted = jsonPost.getBoolean("downvoted");
                                int voteStatus;
                                if (upvoted) {
                                    voteStatus = 1;
                                } else if (downvoted) {
                                    voteStatus = -1;
                                } else {
                                    voteStatus = 0;
                                }

                                Post post = new Post(postId, communityId, communityName, postLogoUrl, postUser, postInfo, PostText, PostImageUrl, PostVideoUrl, postLikeCount, postCommentCount, saved, hidden, subscribed, voteStatus);
                                posts.add(post);
                            }

                            JSONArray jsonCommentsArray = jsonObject.getJSONArray("comments");
                            for (int i = 0; i < jsonCommentsArray.length(); i++) {
                                JSONObject jsonComment = jsonCommentsArray.getJSONObject(i);

                                String postTitle = "";
                                String postBody = "";
                                int postCommunity = -1;
                                String postAuthor = "";
                                String AuthorPhoto = jsonComment.getString("author_photo_path");
                                int commentId = jsonComment.getInt("comment_id");
                                String commentBody = jsonComment.getString("body");
                                String commentUser = jsonComment.getString("username");
                                int downVotes = jsonComment.getInt("downvotes");
                                int upVotes = jsonComment.getInt("upvotes");
                                String date = jsonComment.getString("date");
                                int commentsNum = jsonComment.getInt("comments_num");
                                boolean saved = jsonComment.getBoolean("saved");
                                boolean upVoted = jsonComment.getBoolean("upvoted");
                                boolean downVoted = jsonComment.getBoolean("downvoted");

                                Comment comment = new Comment(commentId, commentBody, commentUser, postTitle, postBody, postCommunity, postAuthor, AuthorPhoto, downVotes, upVotes, date, commentsNum, saved, upVoted, downVoted);
                                comments.add(comment);
                            }

                            Constants.visitedUser.addPosts(posts);
                            Constants.visitedUser.addComments(comments);

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
                //headers.put("Authorization","Bearer: "+ Constants.mToken );
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(stringrequest);

    }
}
