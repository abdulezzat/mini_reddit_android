package com.example.android.minireddit.libraries.atv;


import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.minireddit.CommentActivity;
import com.example.android.minireddit.Constants;
import com.example.android.minireddit.R;
import com.example.android.minireddit.datastructure.Comment;
import com.example.android.minireddit.datastructure.Post;
import com.example.android.minireddit.libraries.atv.model.TreeNode;
import com.example.android.minireddit.networking.DependentClass;

import static com.example.android.minireddit.adapters.PosterAdapter.setForceShowIcon;


/**
 * Created by admin on 31-03-2016.
 */
public class MyHolder extends TreeNode.BaseNodeViewHolder<MyHolder.IconTreeItem> {
    public static final int DEFAULT = 0;

    RelativeLayout container;
    View viewVertical;

    ImageView menu;
    TextView user;
    TextView content;
    TextView reply;
    TextView votes;
    ImageView upvote;
    ImageView downvote;
    boolean toggle;
    int child;
    int leftMargin;
    public Comment mComment;
    MenuItem saveicon;

    public MyHolder(Context context, boolean toggle, int child, int leftMargin) {
        super(context);
        this.toggle = toggle;
        this.child = child;
        this.leftMargin = leftMargin;
    }


    @Override
    public View createNodeView(final TreeNode node, IconTreeItem value) {
        final Comment comment =value.comment;
        mComment=value.comment;



        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view;
        if (child == DEFAULT) {
            view = inflater.inflate(R.layout.parent, null, false);
             menu = (ImageView) view.findViewById(R.id.menu_parent);
             user=(TextView) view.findViewById(R.id.user_parent);
            content=(TextView) view.findViewById(R.id.comment_parent);
            reply=(TextView) view.findViewById(R.id.reply_parent);
            votes=(TextView) view.findViewById(R.id.postLikeCount);
            upvote=(ImageView) view.findViewById(R.id.upvote_parent);
            downvote=(ImageView) view.findViewById(R.id.downvote_parent);
        } else {
            view = inflater.inflate(child, null, false);
            menu = (ImageView) view.findViewById(R.id.menu_child);
            user=(TextView) view.findViewById(R.id.user_child);
            content=(TextView) view.findViewById(R.id.comment_child);
            reply=(TextView) view.findViewById(R.id.reply_child);
            votes=(TextView) view.findViewById(R.id.postLikeCount);
            upvote=(ImageView) view.findViewById(R.id.upvote_child);
            downvote=(ImageView) view.findViewById(R.id.downvote_child);
            viewVertical=(View)view.findViewById(R.id.view);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                   1, LinearLayout.LayoutParams.MATCH_PARENT
            );
            params.setMargins(leftMargin, 0, 0, 0);
            viewVertical.setLayoutParams(params);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mComment.getmCommentsNum()!=0&&mComment.getmCommentsNum()!=node.size()){
                    DependentClass.getInstance().getComments(context,node,mComment.getmCommentId(),1);
                }
            }
        });

        if (leftMargin == DEFAULT) {
            leftMargin = getDimens(R.dimen.treeview_left_padding);
        }
        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Constants.mToken.isEmpty()){
                Constants.commentReply=mComment;
                Constants.commentReplyNode=node;
                Intent intent=new Intent(context,CommentActivity.class);
                intent.putExtra("Type","Reply");
                intent.putExtra("Func","Write");
                context.startActivity(intent);
                }
                else{
                    Toast.makeText(context,"Please Login First",Toast.LENGTH_SHORT).show();
                }
            }
        });
        user.setText(comment.getmUser()+"."+comment.getmDate());
        content.setText(comment.getmBody());
        votes.setText(String.valueOf(comment.getmUpVotes()-comment.getmDownVotes()));
        if(comment.ismDownVoted()&&!comment.ismUpVoted()){
            upvote.setImageResource(R.drawable.upvote);
            downvote.setImageResource(R.drawable.downvote_clr);
        }
        else if(!comment.ismDownVoted()&&comment.ismUpVoted()){
            upvote.setImageResource(R.drawable.upvote_clr);
            downvote.setImageResource(R.drawable.downvote);
        }
        else{
            upvote.setImageResource(R.drawable.upvote);
            downvote.setImageResource(R.drawable.downvote);

        }
        upvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constants.mToken.isEmpty()) {
                    Toast.makeText(context,"Please Login First",Toast.LENGTH_SHORT).show();


                } else {
                    if (DependentClass.getInstance().votePostUp(context, comment.getmCommentId())) {
                        if (!comment.ismUpVoted()&&!comment.ismDownVoted()) {
                            upvote.setImageResource(R.drawable.upvote_clr);
                            comment.setmUpVoted(true);
                            comment.setmUpVotes(comment.getmUpVotes() + 1);
                            //todo send request to upvote
                        } else if (comment.ismUpVoted()&&!comment.ismDownVoted()) {

                            upvote.setImageResource(R.drawable.upvote);
                            comment.setmUpVoted(false);
                            comment.setmUpVotes(comment.getmUpVotes() - 1);
                            //todo send request to cancel upvote
                        } else {
                            downvote.setImageResource(R.drawable.downvote);
                            upvote.setImageResource(R.drawable.upvote_clr);
                            comment.setmUpVoted(true);
                            comment.setmDownVoted(false);
                            comment.setmUpVotes(comment.getmUpVotes() + 1);
                            comment.setmDownVotes(comment.getmDownVotes() - 1);

                        }
                        votes.setText(String.valueOf(comment.getmUpVotes()-comment.getmDownVotes()));
                    } else {

                    }
                }
            }

        });
        downvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constants.mToken.isEmpty()) {
                    Toast.makeText(context,"Please Login First",Toast.LENGTH_SHORT).show();

                } else {
                    if (DependentClass.getInstance().votePostDown(context, comment.getmCommentId())) {
                        if (!comment.ismUpVoted()&&!comment.ismDownVoted()) {
                            downvote.setImageResource(R.drawable.downvote_clr);
                            comment.setmDownVoted(true);
                            comment.setmDownVotes(comment.getmDownVotes() + 1);
                        } else if (comment.ismUpVoted()&&!comment.ismDownVoted()) {
                            downvote.setImageResource(R.drawable.downvote_clr);
                            upvote.setImageResource(R.drawable.upvote);
                            comment.setmUpVoted(false);
                            comment.setmDownVoted(true);
                            comment.setmUpVotes(comment.getmUpVotes() - 1);
                            comment.setmDownVotes(comment.getmDownVotes() + 1);
                        } else {
                            downvote.setImageResource(R.drawable.downvote);
                            comment.setmDownVoted(false);
                            comment.setmDownVotes(comment.getmDownVotes()- 1);
                        }
                        votes.setText(String.valueOf(comment.getmUpVotes()-comment.getmDownVotes()));
                    } else {

                    }
                }
            }
        });





        //Menu


        menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(context, v);
                        popup.getMenuInflater().inflate(R.menu.comment_menu,
                                popup.getMenu());
                        saveicon=popup.getMenu().findItem(R.id.save);
                if(!mComment.ismSaved()){
                    saveicon.setIcon(R.drawable.baseline_bookmark_black_48dp);
                    saveicon.setTitle("save");
                }
                else{
                    saveicon.setIcon(R.drawable.unsave);
                    saveicon.setTitle("unsave");
                }

                        if(Constants.mToken.isEmpty())
                            popup.getMenu().findItem(R.id.blockUser).setVisible(false);
                        setForceShowIcon(popup);
                        if(Constants.user!=null){
                            if(Constants.user.getmUserName().equals(comment.getmUser())){
                                popup.getMenu().findItem(R.id.edit).setVisible(true);
                            }
                            else{
                                popup.getMenu().findItem(R.id.edit).setVisible(false);
                            }
                        }
                        else{
                            popup.getMenu().findItem(R.id.edit).setVisible(false);
                        }


                        popup.show();
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {

                                switch (item.getItemId()) {
                                    case R.id.edit:
                                        Constants.commentReply=comment;
                                        Intent intent=new Intent(context,CommentActivity.class);
                                        intent.putExtra("Type","empty");
                                        intent.putExtra("Func","Edit");
                                        context.startActivity(intent);

                                        break;
                                    case R.id.save:
                                        if(!Constants.mToken.isEmpty()) {
                                            if (saveicon.getTitle().equals("save")) {
                                                DependentClass.getInstance().saveLink(context,comment.getmCommentId());
                                                saveicon.setTitle("unsave");
                                                saveicon.setIcon(R.drawable.baseline_bookmark_black_48dp);
                                                comment.setmSaved(true);
                                                //save
                                            } else {
                                                DependentClass.getInstance().unsaveLink(context,comment.getmCommentId());
                                                saveicon.setIcon(R.drawable.unsave);
                                                saveicon.setTitle("save");
                                                comment.setmSaved(false);
                                                //unsave
                                            }
                                        }
                                        else{
                                            Toast.makeText(context,"Please Login First",Toast.LENGTH_SHORT).show();
                                        }




                                        break;
                                    case R.id.blockUser:
                                        showBlockDialog(comment);


                                        break;


                                    case R.id.copy:
                                        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                                        ClipData clip = ClipData.newPlainText("reddit", comment.getmBody());
                                        clipboard.setPrimaryClip(clip);
                                        Toast.makeText(context,"Text Copied",Toast.LENGTH_SHORT).show();

                                        break;

                                    case R.id.collapse:
                                        if(!node.getParent().isRoot()){


                                        TreeNode root=node;
                                        while(!root.getParent().isRoot())
                                            root=root.getParent();
                                        MyHolder.this.getTreeView().collapseNode(root);
                                        }

                                        break;




                                    default:
                                        break;
                                }

                                return true;
                            }
                        });






            }
        });

        return view;
    }
    private void showBlockDialog(final Comment comment) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Block u/" + comment.getmUser() + "?" + "\n \n");
        String message ="You Will no Longer see thier comments,posts,and message-except in group chat.They Will not Know that you have blocked them.You will no longer get notifications from this user. \n\n";
        builder.setMessage(message);
        builder.setPositiveButton("BLOCK USER", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                DependentClass.getInstance().blockUser(context,comment.getmUser());


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

    public void toggle(boolean active) {

    }

    public static class IconTreeItem {
        public Comment comment;

        public IconTreeItem(Comment comment) {
            this.comment=comment;
        }
    }

    private int getDimens(int resId) {
        return (int) (context.getResources().getDimension(resId) / context.getResources().getDisplayMetrics().density)
                ;
    }
}
