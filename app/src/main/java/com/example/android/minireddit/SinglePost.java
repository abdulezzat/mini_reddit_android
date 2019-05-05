package com.example.android.minireddit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.minireddit.abs.GetSinglePost;
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
    int mPostId=0;
    TextView writeComment;
    MenuItem save;
    MenuItem edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        writeComment = (TextView) findViewById(R.id.addcomment);
        // ATTENTION: This was auto-generated to handle app links.
         Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
        if (Intent.ACTION_VIEW.equals(appLinkAction) && appLinkData != null){
            String recipeId = appLinkData.getLastPathSegment();
            mPostId=Integer.valueOf(recipeId);
            Toast.makeText(getApplicationContext(),recipeId,Toast.LENGTH_SHORT).show();


        }else
        {
            mPostId = getIntent().getIntExtra("id", 0);

        }
        writeComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Constants.mToken.isEmpty()) {
                    Intent intent = new Intent(SinglePost.this, CommentActivity.class);
                    Constants.postComment = mCurrentPost;
                    Constants.commentReplyNode = TreeNode.BaseNodeViewHolder.tView.getmRoot();
                    intent.putExtra("Type", "Comment");
                    intent.putExtra("Func", "Write");
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please Login First", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Constants.SINGLE_POST = new GetSinglePost() {
            @Override
            public void SinglePost(Post post) {
                mCurrentPost = post;
                if (Constants.user != null) {
                    if (Constants.user.getmUserName().equals(mCurrentPost.getPostUser())) {
                        edit.setVisible(true);
                    } else {
                        edit.setVisible(false);
                    }
                } else {
                    edit.setVisible(false);
                }
                if (!mCurrentPost.isSaved()) {
                    save.setIcon(R.drawable.baseline_bookmark_black_48dp);
                    save.setTitle("save");
                } else {
                    save.setIcon(R.drawable.unsave);
                    save.setTitle("unsave");
                }
                ImageView postLogo = (ImageView) findViewById(R.id.postLogo);
                postLogo.setImageResource(Integer.valueOf(mCurrentPost.getPostLogoUrl()));

                TextView postUser = (TextView) findViewById(R.id.postUser);
                postUser.setText(mCurrentPost.getPostUser());

                TextView postInfo = (TextView) findViewById(R.id.postInfo);
                postInfo.setText(mCurrentPost.getPostInfo());

                TextView postText = (TextView) findViewById(R.id.postText);
                postText.setText(mCurrentPost.getPostTitle() + "\n" + mCurrentPost.getPostText());


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
                postCommentCount.setText(String.valueOf(mCurrentPost.getPostCommentCount()));


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
                        if(!Constants.mToken.isEmpty()){
                        if (DependentClass.getInstance().votePostUp(SinglePost.this, mCurrentPost.getPostId())) {
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
                        }}
                        else{
                            Toast.makeText(getApplicationContext(),"Please Login First",Toast.LENGTH_SHORT).show();
                        }
                    }

                });
                postDownVote.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!Constants.mToken.isEmpty()) {
                            if (DependentClass.getInstance().votePostDown(SinglePost.this, mCurrentPost.getPostId())) {
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
                            } else {
                                Toast.makeText(SinglePost.this, "Failed To Vote", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SinglePost.this,"Please Login First",Toast.LENGTH_SHORT).show();

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


            }
        };
        //Root
        TreeNode root = TreeNode.root();

        //Parent
     /*   MyHolder.IconTreeItem nodeItem = new MyHolder.IconTreeItem(new Comment(0,"Aly Ramzy is onFire ","AlyRamzy",null,null,5,null,null,5,6,"2day ago",5,true,false,true));
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

*/
        //Add AndroidTreeView into view.
        AndroidTreeView tView = new AndroidTreeView(SinglePost.this, root);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.comments);
        linearLayout.addView(tView.getView());

        DependentClass.getInstance().getComments(SinglePost.this, TreeNode.BaseNodeViewHolder.tView.getmRoot(), mPostId, 0);



    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if(Constants.mToken.isEmpty())
           menu.findItem(R.id.block_user).setVisible(false);

        save=menu.findItem(R.id.save_post);
        edit=menu.findItem(R.id.editpost);
        edit.setVisible(false);
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
        DependentClass.getInstance().getSinglePost(getApplicationContext(), mPostId);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
          switch (item.getItemId()) {

            case R.id.save_post:
                if(!Constants.mToken.isEmpty()) {
                    if (save.getTitle().equals("save")) {
                        DependentClass.getInstance().saveLink(SinglePost.this,mPostId);
                        save.setTitle("unsave");
                        save.setIcon(R.drawable.unsave);
                        //save
                    } else {
                        DependentClass.getInstance().unsaveLink(SinglePost.this,mPostId);
                        save.setIcon(R.drawable.baseline_bookmark_black_48dp);
                        save.setTitle("save");
                        //unsave
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please Login First",Toast.LENGTH_SHORT).show();
                }

                return true;
                case R.id.edit:
                    Intent intent=new Intent(SinglePost.this,WritePost.class);
                    intent.putExtra("Post","Edit");
                    intent.putExtra("Type","None");
                    Constants.postComment=mCurrentPost;
                    startActivity(intent);


                    return true;


            case R.id.block_user:
                showBlockDialog();

                return true;
              case R.id.hide_post:
                  if(!Constants.mToken.isEmpty()){
                      DependentClass.getInstance().hidePost(getApplicationContext(),mPostId);
                  }

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
    private void showBlockDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SinglePost.this);
        builder.setTitle("Block u/" + mCurrentPost.getPostUser() + "?" + "\n \n");
        String message ="You Will no Longer see thier comments,posts,and message-except in group chat.They Will not Know that you have blocked them.You will no longer get notifications from this user. \n\n";
        builder.setMessage(message);
        builder.setPositiveButton("BLOCK USER", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                DependentClass.getInstance().blockUser(SinglePost.this,mCurrentPost.getPostUser());


            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Button btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        btnNegative. setBackgroundColor(Color.parseColor("#d3d3d3"));
        btnPositive.setBackgroundColor(Color.parseColor("#8B0000"));

        btnNegative.setTextColor(Color.parseColor("#808080"));
        btnPositive.setTextColor(Color.parseColor("#ffffff"));

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) btnPositive.getLayoutParams();
        layoutParams.weight = 10;
        btnPositive.setLayoutParams(layoutParams);
        btnNegative.setLayoutParams(layoutParams);

    }


}
