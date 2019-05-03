package com.example.android.minireddit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.minireddit.adapters.PosterAdapter;
import com.example.android.minireddit.datastructure.Comment;
import com.example.android.minireddit.datastructure.Post;
import com.example.android.minireddit.fragments.PostFragment;
import com.example.android.minireddit.libraries.atv.MyHolder;
import com.example.android.minireddit.libraries.atv.model.TreeNode;
import com.example.android.minireddit.libraries.atv.view.AndroidTreeView;
import com.example.android.minireddit.networking.DependentClass;
import com.example.android.minireddit.networking.DownloadImageTask;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SinglePost extends AppCompatActivity {
    Post mCurrentPost=null;
    int mPostType=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        int position=getIntent().getIntExtra("position",0);
        mPostType=getIntent().getIntExtra("type",0);
        if(mPostType == 1){
            mCurrentPost=Constants.homeposts.getItem(position);

        }
        else{
            mCurrentPost=Constants.poster.getItem(position);

        }
        ImageView postLogo = (ImageView) findViewById(R.id.postLogo);
        postLogo.setImageResource(Integer.valueOf(mCurrentPost.getPostLogoUrl()));

        TextView postUser = (TextView) findViewById(R.id.postUser);
        postUser.setText(mCurrentPost.getPostUser());

        TextView postInfo = (TextView) findViewById(R.id.postInfo);
        postInfo.setText(mCurrentPost.getPostInfo());

        TextView postText = (TextView) findViewById(R.id.postText);
        postText.setText(mCurrentPost.getPostText());

        final ImageView postImage = (ImageView) findViewById(R.id.postImage);
        if (mCurrentPost.getPostImageUrl() != null) {
            postImage.setVisibility(View.VISIBLE);
            boolean connected = isNetworkAvailable();
            if (connected)
                new DownloadImageTask(postImage).execute(mCurrentPost.getPostImageUrl());
            else
                postImage.setImageResource(R.drawable.internet_error);


        } else {
            postImage.setVisibility(View.GONE);
        }

        final TextView postlikeCount = (TextView) findViewById(R.id.postLikeCount);
        postlikeCount.setText(String.valueOf(mCurrentPost.getPostLikeCount()));

        TextView postCommentCount = (TextView) findViewById(R.id.postCommentCount);
        postCommentCount.setText(String.valueOf( mCurrentPost.getPostCommentCount()));



        final ImageView postUpVote = (ImageView) findViewById(R.id.postLike);
        final ImageView postDownVote = (ImageView) findViewById(R.id.postDislike);

        switch (mCurrentPost.getVoteStatus()) {

            case 1:
                postUpVote.setImageResource(R.drawable.upvote_clr);
                postDownVote.setImageResource(R.drawable.downvote);
                break;
            case -1:
                postUpVote.setImageResource(R.drawable.upvote);
                postDownVote.setImageResource(R.drawable.downvote_clr);
                break;
            default:
                postUpVote.setImageResource(R.drawable.upvote);
                postDownVote.setImageResource(R.drawable.downvote);
                break;

        }
        postUpVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DependentClass.getInstance().votePostUp(SinglePost.this,mCurrentPost.getPostId())) {
                    if (mCurrentPost.getVoteStatus() == 0) {
                        postUpVote.setImageResource(R.drawable.upvote_clr);
                        mCurrentPost.setVoteStatus(1);
                        mCurrentPost.setPostLikeCount(mCurrentPost.getPostLikeCount() + 1);
                        //todo send request to upvote
                    } else if (mCurrentPost.getVoteStatus() == 1) {

                        postUpVote.setImageResource(R.drawable.upvote);
                        mCurrentPost.setVoteStatus(0);
                        mCurrentPost.setPostLikeCount(mCurrentPost.getPostLikeCount() - 1);
                        //todo send request to cancel upvote
                    } else {
                        postDownVote.setImageResource(R.drawable.downvote);
                        postUpVote.setImageResource(R.drawable.upvote_clr);
                        mCurrentPost.setVoteStatus(1);
                        mCurrentPost.setPostLikeCount(mCurrentPost.getPostLikeCount() + 2);
                    }
                    postlikeCount.setText(String.valueOf(mCurrentPost.getPostLikeCount()));
                } else {
                    Toast.makeText(SinglePost.this, "Failed To Vote", Toast.LENGTH_SHORT).show();
                }
            }

        });
        postDownVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DependentClass.getInstance().votePostDown(SinglePost.this,mCurrentPost.getPostId())) {
                    if (mCurrentPost.getVoteStatus() == 0) {
                        postDownVote.setImageResource(R.drawable.downvote_clr);
                        mCurrentPost.setVoteStatus(-1);
                        mCurrentPost.setPostLikeCount(mCurrentPost.getPostLikeCount() - 1);
                    } else if (mCurrentPost.getVoteStatus() == 1) {
                        postDownVote.setImageResource(R.drawable.downvote_clr);
                        postUpVote.setImageResource(R.drawable.upvote);
                        mCurrentPost.setVoteStatus(-1);
                        mCurrentPost.setPostLikeCount(mCurrentPost.getPostLikeCount() - 2);
                    } else {
                        postDownVote.setImageResource(R.drawable.downvote);
                        mCurrentPost.setVoteStatus(0);
                        mCurrentPost.setPostLikeCount(mCurrentPost.getPostLikeCount() + 1);
                    }
                    postlikeCount.setText(String.valueOf(mCurrentPost.getPostLikeCount()));
                }
                else{
                    Toast.makeText(SinglePost.this,"Failed To Vote",Toast.LENGTH_SHORT).show();
                }
            }
        });

        WebView youtubeWebView = (WebView) findViewById(R.id.youtube_web_view);
        if (mCurrentPost.getPostVideoUrl() != null) {
            youtubeWebView.setVisibility(View.VISIBLE);
            String item = "http://www.youtube.com/embed/";
            String url = mCurrentPost.getPostVideoUrl();
            if (url.contains("&")) {
                url = url.substring(url.indexOf("v=") + 2, url.indexOf('&'));
            } else
                url = url.substring(url.indexOf("v=") + 2);
            item += url;

            youtubeWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });

            WebSettings webSettings = youtubeWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setUseWideViewPort(true);

            webSettings.setBuiltInZoomControls(true);
            webSettings.setAppCacheEnabled(true);
            webSettings.setSaveFormData(true);

            youtubeWebView.loadUrl(item);
        } else {
            youtubeWebView.setVisibility(View.GONE);

        }

        //Root
        TreeNode root = TreeNode.root();

        //Parent
        MyHolder.IconTreeItem nodeItem = new MyHolder.IconTreeItem(new Comment(0,"Aly Ramzy is onFire ","AlyRamzy",null,null,5,null,null,5,6,"2day ago",5,true,false,true));
        TreeNode parent = new TreeNode(nodeItem).setViewHolder(new MyHolder(SinglePost.this, true, MyHolder.DEFAULT, MyHolder.DEFAULT));

        //Child
        MyHolder.IconTreeItem childItem = new MyHolder.IconTreeItem(new Comment(0,"Aly Ramzy is onFire ","AlyRamzy",null,null,5,null,null,5,6,"2day ago",5,true,false,true));
        TreeNode child = new TreeNode(childItem).setViewHolder(new MyHolder(SinglePost.this, false, R.layout.child, 25));

        //Sub Child
        MyHolder.IconTreeItem subChildItem = new MyHolder.IconTreeItem(new Comment(0,"Aly Ramzy is onFire ","AlyRamzy",null,null,5,null,null,5,6,"2day ago",5,true,false,true));
        TreeNode subChild = new TreeNode(subChildItem).setViewHolder(new MyHolder(SinglePost.this, false, R.layout.child, 50));

        //Add sub child.
        child.addChild(subChild);


        //Add child.
        parent.addChildren(child);
        root.addChild(parent);

        //Parent
        MyHolder.IconTreeItem nodeItem2 = new MyHolder.IconTreeItem(new Comment(0,"Aly Ramzy is onFire ","AlyRamzy",null,null,5,null,null,5,6,"2day ago",5,true,false,true));
        TreeNode parent2 = new TreeNode(nodeItem).setViewHolder(new MyHolder(SinglePost.this, true, MyHolder.DEFAULT, MyHolder.DEFAULT));

        //Child
        MyHolder.IconTreeItem childItem2 = new MyHolder.IconTreeItem(new Comment(0,"Aly Ramzy is onFire ","AlyRamzy",null,null,5,null,null,5,6,"2day ago",5,true,false,true));
        TreeNode child2 = new TreeNode(childItem2).setViewHolder(new MyHolder(SinglePost.this, false, R.layout.child, 25));

        //Sub Child
        MyHolder.IconTreeItem subChildItem2 = new MyHolder.IconTreeItem(new Comment(0,"Aly Ramzy is onFire ","AlyRamzy",null,null,5,null,null,5,6,"2day ago",5,true,false,true));
        TreeNode subChild2 = new TreeNode(subChildItem2).setViewHolder(new MyHolder(SinglePost.this, false, R.layout.child, 50));

        //Add sub child.
        child2.addChild(subChild2);
        MyHolder.IconTreeItem subChildItem22 = new MyHolder.IconTreeItem(new Comment(0,"Aly Ramzy is onFire ","AlyRamzy",null,null,5,null,null,5,6,"2day ago",5,true,false,true));
        TreeNode subChild22 = new TreeNode(subChildItem22).setViewHolder(new MyHolder(SinglePost.this, false, R.layout.child, 150));


        //Add child.
        parent2.addChildren(child2);
        root.addChild(parent2);


        MyHolder.IconTreeItem subChildItem3 = new MyHolder.IconTreeItem(new Comment(0,"Aly Ramzy is onFire ","AlyRamzy",null,null,5,null,null,5,6,"2day ago",5,true,false,true));
        TreeNode subChild3 = new TreeNode(subChildItem3).setViewHolder(new MyHolder(SinglePost.this, false, R.layout.child, 50));

        //Add sub child.
        child2.addChild(subChild3);
        MyHolder.IconTreeItem subChildItem4 = new MyHolder.IconTreeItem(new Comment(0,"Aly Ramzy is onFire ","AlyRamzy",null,null,5,null,null,5,6,"2day ago",5,true,false,true));
        TreeNode subChild4 = new TreeNode(subChildItem4).setViewHolder(new MyHolder(SinglePost.this, false, R.layout.child, 150));

        //Add sub child.
        subChild3.addChild(subChild4);
        MyHolder.IconTreeItem subChildItem5 = new MyHolder.IconTreeItem(new Comment(0,"Aly Ramzy is onFire ","AlyRamzy",null,null,5,null,null,5,6,"2day ago",5,true,false,true));
        TreeNode subChild5 = new TreeNode(subChildItem5).setViewHolder(new MyHolder(SinglePost.this, false, R.layout.child, 250));

        //Add sub child.
        subChild4.addChild(subChild5);
        MyHolder.IconTreeItem subChildItem6 = new MyHolder.IconTreeItem(new Comment(0,"Aly Ramzy is onFire ","AlyRamzy",null,null,5,null,null,5,6,"2day ago",5,true,false,true));
        TreeNode subChild6 = new TreeNode(subChildItem6).setViewHolder(new MyHolder(SinglePost.this, false, R.layout.child, 350));

        //Add sub child.
        subChild5.addChild(subChild6);



        subChild2.addChild(subChild22);


        //Add AndroidTreeView into view.
        AndroidTreeView tView = new AndroidTreeView(SinglePost.this, root);
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.comments);
        linearLayout.addView(tView.getView());






    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Method m = null;
        try {
            m = menu.getClass().getDeclaredMethod(
                    "setOptionalIconsVisible", Boolean.TYPE);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        m.setAccessible(true);
        try {
            m.invoke(menu, true);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
          switch (item.getItemId()) {

            case R.id.save_post:

                return true;

            case R.id.block_user:

                return true;
              case R.id.hide_post:

                  return true;

            case android.R.id.home:
                onBackPressed();


                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_single_post,menu);

        return true;
    }
    protected boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) SinglePost.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
