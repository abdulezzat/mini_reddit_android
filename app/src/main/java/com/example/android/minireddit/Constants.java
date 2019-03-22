package com.example.android.minireddit;

import com.example.android.minireddit.adapters.PosterAdapter;
import com.example.android.minireddit.datastructure.User;

//
public class Constants {

    public static final boolean debug=true;
    //public static User user = null;
    public static User user = new User("admin","admin",null,"admin@gamil.com",null,null,200,null,false);
    public static final String TRENDIN_POST="";
    public static PosterAdapter poster=null;
    public static PosterAdapter homeposts=null;
}
