package com.example.android.minireddit;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.minireddit.datastructure.Comment;
import com.example.android.minireddit.datastructure.Post;
import com.example.android.minireddit.libraries.atv.model.TreeNode;
import com.example.android.minireddit.networking.DependentClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CommentActivity extends AppCompatActivity {
    TextView user;
    TextView date;
    TextView body;
    EditText content;
    String Type;
    Comment mComment=null;
    Post mPost=null;
    TreeNode parent=null;
    String func;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        this.setTitle("Reply to comment");
        user=(TextView)findViewById(R.id.comment_username);
        date=(TextView)findViewById(R.id.comment_dateago);
        body=(TextView)findViewById(R.id.comment_bodyold);
        content=(EditText)findViewById(R.id.comment_bodynew);

        Type=this.getIntent().getStringExtra("Type");
        func=getIntent().getStringExtra("Func");
        if(func.equals("Write")) {
            if (Type.equals("Reply")) {
                mComment = Constants.commentReply;
                user.setText(mComment.getmUser());
                date.setText("  ."+mComment.getmDate());
                body.setText(mComment.getmBody());
            } else {
                mPost = Constants.postComment;
                user.setText(mPost.getPostUser());
                date.setText("  ."+mPost.getPostInfo());
                body.setText(mPost.getPostText());
            }
            parent = Constants.commentReplyNode;
        }else{
            this.setTitle("Edit a Comment");
            mComment = Constants.commentReply;
            user.setText(mComment.getmUser());
            date.setText("  ."+mComment.getmDate());
            body.setVisibility(View.GONE);
            content.setText(mComment.getmBody());


        }
    }
    @Override
    public void onBackPressed() {
        if(content.getText().toString().isEmpty())
            super.onBackPressed();
        else{
            showDiscardDialog();

        }

    }
    private void showDiscardDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Discard comment ?");
        builder.setPositiveButton("DISCARD", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                CommentActivity.super.onBackPressed();


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
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case R.id.post:
                if(func.equals("Write")){
                if(content.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter a message",Toast.LENGTH_SHORT).show();
                }
                else{
                    Comment comment = new Comment(0, content.getText().toString(), Constants.user.getmUserName(), null, null, 0, null, null, 0, 0, "1 min ago", 0, false, false, false);

                    if(Type.equals("Reply")) {
                        DependentClass.getInstance().replyOnReply(getApplicationContext(),parent,mComment,comment);

                    }
                    else{
                        DependentClass.getInstance().commentOnPost(getApplicationContext(),parent,mPost,comment);

                    }
                    super.onBackPressed();
                }}
                else{
                    DependentClass.getInstance().editComment(getApplicationContext(),mComment.getmCommentId(),content.getText().toString());
                    super.onBackPressed();

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
        getMenuInflater().inflate(R.menu.singlecomment,menu);

        return true;
    }
}
