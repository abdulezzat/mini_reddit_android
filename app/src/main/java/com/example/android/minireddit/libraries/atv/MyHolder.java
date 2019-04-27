package com.example.android.minireddit.libraries.atv;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.minireddit.Constants;
import com.example.android.minireddit.R;
import com.example.android.minireddit.datastructure.Comment;
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

    public MyHolder(Context context, boolean toggle, int child, int leftMargin) {
        super(context);
        this.toggle = toggle;
        this.child = child;
        this.leftMargin = leftMargin;
    }


    @Override
    public View createNodeView(final TreeNode node, IconTreeItem value) {
        final Comment comment =value.comment;


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

        if (leftMargin == DEFAULT) {
            leftMargin = getDimens(R.dimen.treeview_left_padding);
        }
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
                        if(Constants.mToken.isEmpty())
                            popup.getMenu().findItem(R.id.blockUser).setVisible(false);
                        setForceShowIcon(popup);


                        popup.show();
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {

                                switch (item.getItemId()) {
                                    case R.id.save:
                                        Toast.makeText(context,"tes",Toast.LENGTH_SHORT).show();
                                        if(!node.getParent().isRoot())
                                        MyHolder.this.getTreeView().collapseNode(node.getParent());


                                        break;
                                    case R.id.blockUser:


                                        break;
                                    case R.id.hidePost:


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
