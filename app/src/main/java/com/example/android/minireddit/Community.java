package com.example.android.minireddit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Community extends AppCompatActivity {
    Button Subscribe = (Button) findViewById(R.id.community_subscribe);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        Subscribe.setOnClickListener(subscribeListener);
    }

    private View.OnClickListener subscribeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (Subscribe.getText().toString()=="SUBSCRIBE")
            {
                Subscribe.setText("SUBSCRIBED");
            }
            if (Subscribe.getText().toString()=="SUBSCRIBED")
            {
                Subscribe.setText("SUBSCRIBE");
            }
        }
    };
}
