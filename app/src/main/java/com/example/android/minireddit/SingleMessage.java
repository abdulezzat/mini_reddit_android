package com.example.android.minireddit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SingleMessage extends AppCompatActivity {

    Button back= (Button) findViewById(R.id.single_message_back_button);;
    TextView user_name_top = (TextView) findViewById(R.id.single_message_user_name);
    TextView subject = (TextView) findViewById(R.id.single_message_title_text_view);
    TextView context_user_name = (TextView) findViewById(R.id.single_post_context_user_name_text_view);
    TextView hours_ago = (TextView) findViewById(R.id.single_post_context_hours_ago);
    TextView context = (TextView) findViewById(R.id.sinle_message_context_text_view);
    TextView reply = (TextView) findViewById(R.id.single_message_reply_edit_text);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_message);
        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReplyActivity();
            }
        });
    }
    public void openReplyActivity()
    {
        Intent intent = new Intent(this, MessageReply.class);
        startActivity(intent);
    }
}
