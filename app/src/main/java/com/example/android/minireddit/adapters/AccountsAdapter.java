package com.example.android.minireddit.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.minireddit.Constants;
import com.example.android.minireddit.R;
import com.example.android.minireddit.datastructure.Post;

import java.util.List;

public class AccountsAdapter extends ArrayAdapter<String> {
    private final Activity context;

    public AccountsAdapter(Activity context, @NonNull List<String> objects) {
        super(context, 0,objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.accounts_list_item_layout, null, true);

        String username = getItem(position);

        TextView usernameView = rowView.findViewById(R.id.username);
        usernameView.setText(username);

        ImageView check = (ImageView)rowView.findViewById(R.id.check);

        if(username.equals(Constants.user.getmUserName())){
            check.setVisibility(View.VISIBLE);
        }
        return rowView;
    }
}
