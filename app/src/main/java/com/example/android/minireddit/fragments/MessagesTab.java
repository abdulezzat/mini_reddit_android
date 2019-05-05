package com.example.android.minireddit.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.android.minireddit.Constants;
import com.example.android.minireddit.R;
import com.example.android.minireddit.adapters.MessagesTabListAdapter;
import com.example.android.minireddit.adapters.SavedCommentsAdapter;
import com.example.android.minireddit.datastructure.Comment;
import com.example.android.minireddit.datastructure.Message;

import java.util.ArrayList;
import java.util.List;


public class MessagesTab extends Fragment {

    public int numberOfMessages = 5;
    public MessagesTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        List<Message> ms = new ArrayList<Message>();
        ms.add(new Message(1,"first message","subject1","u/receiver1","u/sender1","url1","url11","1h"));
        ms.add(new Message(2,"second message","subject2","u/receiver2","u/sender2","url2","url22","2h"));
        ms.add(new Message(3,"third message","subject3","u/receiver3","u/sender3","url3","url33","3h"));
        ms.add(new Message(4,"fourth message","subject4","u/receiver4","u/sender4","url4","url44","4h"));
        ms.add(new Message(5,"fifth message","subject5","u/receiver5","u/sender5","url5","url55","5h"));

        View rootView =  inflater.inflate(R.layout.fragment_messages_tab, container, false);

        ArrayList<Message> messages = new ArrayList<>();

        MessagesTabListAdapter messagesTabListAdapter = new MessagesTabListAdapter(this.getContext(),ms);
        ListView listView = (ListView) rootView.findViewById(R.id.message_tab_listview);
        listView.setAdapter(messagesTabListAdapter);


        return rootView;
    }


}
