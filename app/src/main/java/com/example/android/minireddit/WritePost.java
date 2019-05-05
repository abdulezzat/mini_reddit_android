package com.example.android.minireddit;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.minireddit.abs.ChooseCommunityCallback;
import com.example.android.minireddit.datastructure.*;
import com.example.android.minireddit.datastructure.Community;
import com.example.android.minireddit.networking.DependentClass;

import java.io.IOException;


public class WritePost extends AppCompatActivity {
    final static  int CAMERA=1;
    final static int GALLERY=0;
    EditText postTitle;
    EditText postText;
    TextView communityName;
    ImageView camera;
    ImageView gallery;
    ImageView uploaded;
    Community currentCommunity=null;
    LinearLayout choose;
    MenuItem post;
    boolean hasCommunity=false;
    boolean hasText=false;
    boolean hasTitle=false;
    boolean hasPhoto=false;
    String type="";
    String postFunc="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wirte_post);
        postTitle=(EditText)findViewById(R.id.title) ;
        postText=(EditText)findViewById(R.id.text_post) ;
        communityName=(TextView)findViewById(R.id.choosen_community);
        choose=(LinearLayout)findViewById(R.id.choose);
        camera=(ImageView)findViewById(R.id.camera) ;
        gallery=(ImageView)findViewById(R.id.gallery);
        uploaded=(ImageView)findViewById(R.id.uploaded);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhotoFromCamera();
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhotoFromGallary();
            }
        });
        type=this.getIntent().getStringExtra("Type");
        postFunc=this.getIntent().getStringExtra("Post");
        if(postFunc.equals("Write")){
            if(type.equals("Photo")){
                this.setTitle("Image post");
                postText.setVisibility(View.GONE);
                hasText=true;
            }
            else if(type.equals("Text")){
                this.setTitle("Text post");
                camera.setVisibility(View.GONE);
                gallery.setVisibility(View.GONE);
                hasPhoto=true;
            }
            else if(type.equals("Video")){
                this.setTitle("Video post");
                camera.setVisibility(View.GONE);
                gallery.setVisibility(View.GONE);
                postText.setHint("Your YouTube Video Url");

                hasPhoto=true;

            }


        }else{
            this.setTitle("Edit Post");
            camera.setVisibility(View.GONE);
            gallery.setVisibility(View.GONE);
           postTitle.setText(Constants.postComment.getPostText());
           postText.setVisibility(View.GONE);
           choose.setVisibility(View.GONE);
           post.setEnabled(true);


        }

        postText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()) {
                    if(type.equals("Video")){
                       if(Patterns.WEB_URL.matcher(s.toString()).matches()){
                           hasText = true;
                           if (hasCommunity && hasText && hasTitle&&hasPhoto) {
                               post.setEnabled(true);
                           }

                       }
                       else{
                           hasText=false;
                           post.setEnabled(false);
                       }

                    }
                    else {
                        hasText = true;
                        if (hasCommunity && hasText && hasTitle && hasPhoto) {
                            post.setEnabled(true);
                        }
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
                    if(hasCommunity&&hasText&&hasTitle&&hasPhoto) {
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
               if(hasCommunity&&hasText&&hasTitle&&hasPhoto){
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
    public void onBackPressed() {
        if(!hasCommunity&&!hasPhoto&&!hasText&&!hasTitle)
            super.onBackPressed();
        else{
            showDiscardDialog();

        }

    }
    private void showDiscardDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Discard Post ?");
        builder.setPositiveButton("DISCARD", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                WritePost.super.onBackPressed();


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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {



            case R.id.post:
                if(postFunc.equals("Write")){

               if(type.equals("Text")){
                   DependentClass.getInstance().writeTextandVideoPosts(getApplicationContext(),postTitle.getText().toString(),postText.getText().toString(),currentCommunity,0);
               }
               else if(type.equals("Video")){
                   DependentClass.getInstance().writeTextandVideoPosts(getApplicationContext(),postTitle.getText().toString(),postText.getText().toString(),currentCommunity,1);


               }
               super.onBackPressed();
                }
                else{
                    Constants.postComment.setPostText(postTitle.getText().toString());
                    DependentClass.getInstance().editPost(getApplicationContext(),Constants.postComment);

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
    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent,GALLERY );
    }
    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    //String path = saveImage(bitmap);
                    Toast.makeText(WritePost.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    uploaded.setVisibility(View.VISIBLE);
                    hasPhoto=true;
                    uploaded.setImageBitmap(bitmap);
                    camera.setVisibility(View.GONE);
                    gallery.setVisibility(View.GONE);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(WritePost.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

            uploaded.setVisibility(View.VISIBLE);
            hasPhoto=true;

            uploaded.setImageBitmap(thumbnail);
            camera.setVisibility(View.GONE);
            gallery.setVisibility(View.GONE);
           // saveImage(thumbnail);
            Toast.makeText(WritePost.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
        if(hasCommunity&&hasText&&hasTitle&&hasPhoto){
            post.setEnabled(true);
        }
    }
}
