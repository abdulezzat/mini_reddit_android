package com.example.android.minireddit.adapters;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.PopupMenu;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import java.lang.reflect.Method;
import com.example.android.minireddit.datastructure.Post;
import com.example.android.minireddit.R;

import java.util.List;


public class PosterAdapter extends ArrayAdapter<Post> {
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View ListItemView = convertView;
        if(ListItemView==null){//for making new List_item if there is no main one to change its data
            ListItemView= LayoutInflater.from(getContext()).inflate(R.layout.post_list_item,parent,false);
        }
        Post currentPost=getItem(position);// getting the current word in the arraylist



        ImageView postLogo=(ImageView)ListItemView.findViewById(R.id.postLogo);
        postLogo.setImageResource(Integer.valueOf(currentPost.getPostLogoUrl()));

        TextView postUser=(TextView)ListItemView.findViewById(R.id.postUser);
        postUser.setText(currentPost.getPostUser());

        TextView postInfo=(TextView) ListItemView.findViewById(R.id.postInfo);
        postInfo.setText(currentPost.getPostInfo());

        TextView postText=(TextView)ListItemView.findViewById(R.id.postText);
        postText.setText(currentPost.getPostText());

        ImageView postImage=(ImageView)ListItemView.findViewById(R.id.postImage);
        if(currentPost.getPostImageUrl()!=null)
        {
            postImage.setImageResource(Integer.valueOf(currentPost.getPostImageUrl()));

        }
        else
        {
            postImage.setVisibility(View.GONE);
        }

        TextView postlikeCount=(TextView)ListItemView.findViewById(R.id.postLikeCount);
        postlikeCount.setText(String.valueOf(currentPost.getPostLikeCount()));

        TextView postCommentCount=(TextView)ListItemView.findViewById(R.id.postCommentCount);
        postCommentCount.setText(String.valueOf(currentPost.getPostCommentCount()));

        ImageView menu=(ImageView) ListItemView.findViewById(R.id.postOptions);


        menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                switch (v.getId()) {
                    case R.id.postOptions:

                        PopupMenu popup = new PopupMenu(getContext(), v);
                        popup.getMenuInflater().inflate(R.menu.post_menu,
                                popup.getMenu());
                        setForceShowIcon(popup);

                        popup.show();
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {

                                switch (item.getItemId()) {
                                    case R.id.save:

                                        //Or Some other code you want to put here.. This is just an example.
                                        Toast.makeText(getContext(), " Install Clicked at position " + " : " , Toast.LENGTH_LONG).show();

                                        break;
                                    case R.id.blockUser:

                                        Toast.makeText(getContext(), "Add to Wish List Clicked at position " + " : " , Toast.LENGTH_LONG).show();

                                        break;

                                    default:
                                        break;
                                }

                                return true;
                            }
                        });

                        break;

                    default:
                        break;
                }


            }
        });



        return ListItemView;
    }

    public PosterAdapter(@NonNull Context context, @NonNull List<Post> objects) {
        super(context, 0,objects);

    }
    public static void setForceShowIcon(PopupMenu popupMenu) {
        try {
            Field[] fields = popupMenu.getClass().getDeclaredFields();
            for (Field field : fields) {
                if ("mPopup".equals(field.getName())) {
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popupMenu);
                    Class<?> classPopupHelper = Class.forName(menuPopupHelper
                            .getClass().getName());
                    Method setForceIcons = classPopupHelper.getMethod(
                            "setForceShowIcon", boolean.class);
                    setForceIcons.invoke(menuPopupHelper, true);
                    break;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


}
