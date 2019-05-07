package com.example.android.minireddit.adapters;

import android.app.Notification;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.android.minireddit.fragments.MessagesTab;
import com.example.android.minireddit.fragments.ModMailTab;
import com.example.android.minireddit.fragments.NotificationTab;

public class InboxPagerAdapter extends FragmentPagerAdapter {

    private int numberOfPages = 3;
    NotificationTab notificationTab;
    MessagesTab messagesTab;
    ModMailTab modMailTab;
//    int mNoOfTabs;

//    public InboxPagerAdapter(FragmentManager fm, int NumberOfTabs)
//    {
//        super(fm);
//        mNoOfTabs = NumberOfTabs;
//    }

    public InboxPagerAdapter(FragmentManager fm)
    {
        super(fm);
        notificationTab = new NotificationTab();
        messagesTab = new MessagesTab();
        modMailTab = new ModMailTab();
    }

    @Override
    public Fragment getItem(int i) {
        if (i==0)
        {
            return notificationTab;
        }
        if (i == 1)
        {
            return messagesTab;
        }
        if (i == 2)
        {
            return modMailTab;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numberOfPages;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if(position==0) return "Notification";
        if(position==1) return "Message";
        if(position==2) return "Mod mail";
        else return "position222222";
    }

//    @Override
//    public Fragment getItem(int position) {
//        switch (position)
//        {
//            case 0:
//                NotificationTab notificationTab = new NotificationTab();
//                return notificationTab;
//            case 1:
//                MessagesTab messagesTab = new MessagesTab();
//                return messagesTab;
//            case 2:
//                ModMailTab modMailTab = new ModMailTab();
//                return modMailTab;
//            default:
//                return null;
//        }
//    }
//
//    @Override
//    public int getCount() {
//        return mNoOfTabs;
//    }
}
