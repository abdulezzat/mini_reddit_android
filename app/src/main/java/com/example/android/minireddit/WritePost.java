package com.example.android.minireddit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.minireddit.abs.ChooseCommunityCallback;
import com.example.android.minireddit.datastructure.*;
import com.example.android.minireddit.datastructure.Community;

public class WritePost extends AppCompatActivity {
    EditText postTitle;
    EditText postText;
    TextView communityName;
    Community currentCommunity=null;
    LinearLayout choose;
    MenuItem post;
    boolean hasCommunity=false;
    boolean hasText=false;
    boolean hasTitle=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wirte_post);
        postTitle=(EditText)findViewById(R.id.title) ;
        postText=(EditText)findViewById(R.id.text_post) ;
        communityName=(TextView)findViewById(R.id.choosen_community);
        choose=(LinearLayout)findViewById(R.id.choose);
        this.setTitle("Write Post");
        postText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()) {
                    hasText = true;
                    if (hasCommunity && hasText && hasTitle) {
                        post.setEnabled(true);
                    }
                }
                else{
                    hasText=false;
                    post.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        postTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()){
                    hasTitle=true;
                    if(hasCommunity&&hasText&&hasTitle) {
                        post.setEnabled(true);
                    }

                }
                else{
                    hasTitle=false;
                    post.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(WritePost.this,ChooseCommunity.class);
                startActivity(intent);
            }
        });
        Constants.CHOOSEN_COMMUNITY=new ChooseCommunityCallback() {
            @Override
            public void returnedCommunity(Community community) {
                hasCommunity=true;
               communityName.setText(community.getCommunityName());
               currentCommunity=community;
               if(hasCommunity&&hasText&&hasTitle){
                   post.setEnabled(true);
               }
            }
        };
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        post=menu.findItem(R.id.post);
        post.setEnabled(false);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {



            case R.id.post:
                Toast.makeText(getApplicationContext(),"Working",Toast.LENGTH_SHORT).show();

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
