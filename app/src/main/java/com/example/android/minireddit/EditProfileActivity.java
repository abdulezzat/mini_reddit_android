package com.example.android.minireddit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.android.minireddit.datastructure.User;
import com.example.android.minireddit.networking.DependentClass;
import com.example.android.minireddit.networking.DownloadImageTask;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        DependentClass.getInstance().getUserPublicInfo(this, Constants.user.getmUserName());
        User currentUser=new User(Constants.vistedUser);

        ImageView headerPhoto = findViewById(R.id.header_photo);
        new DownloadImageTask(headerPhoto).execute(currentUser.getmHeaderImage());

        ImageView avatar = findViewById(R.id.profile_picture);
        new DownloadImageTask(avatar).execute(currentUser.getmProfileImage());

        EditText displayName = findViewById(R.id.display_name);
        displayName.setText(currentUser.getmDisplayName());

        EditText about = findViewById(R.id.about);
        about.setText(currentUser.getmAbout());

    }

    public void saveEdits(View view) {
        ImageView headerPhoto = findViewById(R.id.header_photo);
        ImageView avatar = findViewById(R.id.profile_picture);
        DependentClass.getInstance().updateUserProfileImage(view.getContext(),avatar.getDrawable().toString());

        EditText displayName = findViewById(R.id.display_name);
        DependentClass.getInstance().updateUserDisplayName(this, displayName.getText().toString());

        EditText about = findViewById(R.id.about);
        DependentClass.getInstance().updateUserAbout(this, about.getText().toString());

    }
}
