package com.example.android.minireddit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

        final User currentUser = Constants.visitedUser;

        ImageView headerPhoto = findViewById(R.id.header_photo);
        if (currentUser.getmHeaderImage() != null && !currentUser.getmHeaderImage().equals("null")) {
            new DownloadImageTask(headerPhoto).execute(currentUser.getmHeaderImage());
        } else {
            headerPhoto.setImageResource(R.drawable.half_transparent);
        }

        ImageView avatar = findViewById(R.id.profile_picture);
        if (currentUser.getmProfileImage() != null && !currentUser.getmProfileImage().equals("null")) {
            new DownloadImageTask(avatar).execute(currentUser.getmProfileImage());
        } else {
            avatar.setImageResource(R.drawable.default_avatar);
        }

        EditText displayName = findViewById(R.id.display_name);
        if (currentUser.getmDisplayName() != null && !currentUser.getmDisplayName().equals("null")) {
            displayName.setText(currentUser.getmDisplayName());
        } else {
            displayName.setText("");
        }

        EditText about = findViewById(R.id.about);
        if (currentUser.getmAbout() != null && !currentUser.getmAbout().equals("null")) {
            about.setText(currentUser.getmAbout());
        } else {
            about.setText("");
        }

        TextView save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView headerPhoto = findViewById(R.id.header_photo);
                //DependentClass.getInstance().updateUserHeaderImage(v.getContext(), headerPhoto.getDrawable().toString());

                ImageView avatar = findViewById(R.id.profile_picture);
                //DependentClass.getInstance().updateUserProfileImage(v.getContext(), avatar.getDrawable().toString());

                EditText displayName = findViewById(R.id.display_name);
                if (displayName.getText() != null && !displayName.getText().toString().equals("")) {
                    DependentClass.getInstance().updateUserDisplayName(v.getContext(), displayName.getText().toString());
                }

                EditText about = findViewById(R.id.about);
                if (about.getText() != null && !about.getText().toString().equals("")) {
                    DependentClass.getInstance().updateUserAbout(v.getContext(), about.getText().toString());
                }

                final AppCompatActivity act = (AppCompatActivity) EditProfileActivity.this;
                try {
                    act.onBackPressed();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AppCompatActivity act = (AppCompatActivity) EditProfileActivity.this;
                try {
                    act.onBackPressed();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
