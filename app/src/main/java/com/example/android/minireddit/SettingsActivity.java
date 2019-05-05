package com.example.android.minireddit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.minireddit.datastructure.User;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView username=(TextView) findViewById(R.id.username);
        View notifications= findViewById(R.id.notifications);
        View advanced= findViewById(R.id.advanced);
        View commentSort=findViewById(R.id.comment_sort);
        View webLinks=findViewById(R.id.web_links);
        View personalization=findViewById(R.id.personalization);

        String newUsername = "Anonymous ▼";
        if(Constants.user != null) {
            newUsername = Constants.user.getmUserName() + " ▼";
        } else {
            notifications.setVisibility(View.GONE);
            advanced.setVisibility(View.GONE);
            commentSort.setVisibility(View.GONE);
            webLinks.setVisibility(View.GONE);
            personalization.setVisibility(View.GONE);
        }
        username.setText(newUsername);
    }
}
