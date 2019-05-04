package com.example.android.minireddit.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.minireddit.R;
import com.example.android.minireddit.datastructure.Comment;
import com.example.android.minireddit.datastructure.Message;

import java.util.List;

public class MessagesTabListAdapter extends ArrayAdapter<Message> {

    public MessagesTabListAdapter(@NonNull Context context, @NonNull List<Message> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View ListItemView = convertView;
        if (ListItemView == null) { //for making new List_item if there is no main one to change its data
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.inbox_messages_list_item, parent, false);
        }
        final Message message = getItem(position); // getting the current Message in the ArrayList

        TextView subject = ListItemView.findViewById(R.id.inbox_message_list_item_message_subject_edit_text);
        if (message.getMessageSubject()==null)
            subject.setText("Message Subject");
        else
            subject.setText(message.getMessageSubject());

        TextView context = ListItemView.findViewById(R.id.inbox_message_list_item_message_content_edit_text);
        if (message.getMessageContent()==null)
            context.setText("This is the context of the message");
        else
            context.setText(message.getMessageContent());

        TextView userName = ListItemView.findViewById(R.id.inbox_message_list_item_message_user_name_text_view);
        if (message.getSenderName()==null)
            context.setText("u/UserName");
        else
            context.setText(message.getSenderName());

        TextView duration = ListItemView.findViewById(R.id.inbox_message_list_item_message_hours_ago);
        if (message.getDuration()==null)
            context.setText("2h");
        else
            context.setText(message.getDuration());

        return ListItemView;
    }
}
